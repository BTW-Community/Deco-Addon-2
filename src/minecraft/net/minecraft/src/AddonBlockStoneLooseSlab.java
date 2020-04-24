package net.minecraft.src;

public class AddonBlockStoneLooseSlab extends FCBlockMortarReceiverSlab
{
	private Block[] owners = {AddonDefs.graniteCobbleLoose, AddonDefs.andesiteCobbleLoose, AddonDefs.dioriteCobbleLoose, AddonDefs.graniteStoneBrickLoose, AddonDefs.andesiteStoneBrickLoose, AddonDefs.dioriteStoneBrickLoose};
	private int[] mortaredIDs;
	private int[] mortaredMetas = {6, 7, 0, 1, 2, 3};
	
    public AddonBlockStoneLooseSlab(int var1, int[] mortarID)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("stoneLooseSlab");
        mortaredIDs = mortarID;
    }

    public int GetCombinedBlockID(int var1)
    {
        return owners[var1 & 7].blockID;
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
    	int looseMeta = var1.getBlockMetadata(var2, var3, var4);
        int mortaredMeta = mortaredMetas[looseMeta & 7];

        if (this.GetIsUpsideDown(var1, var2, var3, var4))
        {
        	mortaredMeta |= 8;
        	looseMeta |= 8;
        }

        var1.setBlockAndMetadataWithNotify(var2, var3, var4, mortaredIDs[looseMeta & 7], mortaredMeta);
        return true;
    }

	public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
	{
		return this.GetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4));
	}

	public boolean GetIsUpsideDown(int var1)
	{
		return (var1 & 8) > 0;
	}

	public void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5)
	{
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		var1.setBlockMetadataWithNotify(var2, var3, var4, this.SetIsUpsideDown(var6, var5));
	}

	public int SetIsUpsideDown(int var1, boolean var2)
	{
		if (var2)
		{
			var1 |= 8;
		}
		else
		{
			var1 &= 7;
		}

		return var1;
	}

    //CLIENT ONLY
    public Icon getIcon(int side, int meta) {
    	return owners[meta & 7].getIcon(side, meta);
    }
    
    public void registerIcons(IconRegister register) {}
}
