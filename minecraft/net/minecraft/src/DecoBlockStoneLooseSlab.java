package net.minecraft.src;

public class DecoBlockStoneLooseSlab extends FCBlockMortarReceiverSlab
{
	public final Block[] blockTypes;
	private int[] mortaredIDs;
	private int[] mortaredMetas;
	
    public DecoBlockStoneLooseSlab(int var1, Block[] parents, int[] mortaredIDs, int[] mortaredMetas)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("stoneLooseSlab");
        this.blockTypes = parents;
        this.mortaredIDs = mortaredIDs;
        this.mortaredMetas = mortaredMetas;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if ((var5.blockID == this.blockID) && (var5.metadata == var1.getBlockMetadata(var2, var3, var4)) && !this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            this.ConvertToFullBlock(var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int damageDropped(int meta) {
    	return meta % 8;
    }

    public int GetCombinedBlockID(int var1)
    {
        return blockTypes[var1 & 7].blockID;
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
    	return blockTypes[meta & 7].getIcon(side, meta);
    }
    
    public void registerIcons(IconRegister register) {}
}
