package net.minecraft.src;

import java.util.List;

public class DecoBlockBarrelEmpty extends DecoBlockDirectional {
	
	public DecoBlockBarrelEmpty(int id, String[] topTextures, String[] sideTextures) {
		super(id, FCBetterThanWolves.fcMaterialPlanks, topTextures, sideTextures);
        this.SetAxesEffectiveOn();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override public boolean isOpaqueCube()
	{
		return true;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return true;
	}

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int i = 0; i < topTextures.length; i++) {
    		par3List.add(new ItemStack(par1, 1, i));
    	}
    }

    public boolean DoesBlockHopperInsert(World var1, int var2, int var3, int var4)
    {
        return false;
    }
}