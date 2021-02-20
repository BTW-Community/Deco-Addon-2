package net.minecraft.src;

import java.util.List;

public class DecoBlockBarrelFilled extends Block {
	public static final Item[] types = {FCBetterThanWolves.fcItemWheat, FCBetterThanWolves.fcItemHemp, Item.potato, Item.carrot, Item.fishRaw};
	public static final String[] typeTags = {"wheat", "hemp", "potato", "carrot", "fish"};
	public final String tag;
	
	public DecoBlockBarrelFilled(int id, String tag) {
		super(id, FCBetterThanWolves.fcMaterialPlanks);
        this.SetAxesEffectiveOn();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.tag = tag;
        this.setUnlocalizedName(tag);
	}

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int meta)
    {
        return meta;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
    	
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, types[var5].itemID, 8, 0, var6);
        return true;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int i = 0; i < types.length; i++)
    		par3List.add(new ItemStack(par1, 1, i));
    }
}