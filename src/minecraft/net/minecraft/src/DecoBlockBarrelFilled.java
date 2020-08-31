package net.minecraft.src;

import java.util.List;

public class DecoBlockBarrelFilled extends Block {
	public static final Item[] types = {FCBetterThanWolves.fcItemWheat, FCBetterThanWolves.fcItemHemp, Item.potato, Item.carrot, Item.fishRaw};
	public static final String[] typeTags = {"_wheat", "_hemp", "_potato", "_carrot", "_fish"};
	public static final String[] typeNames = {"Wheat", "Hemp", "Potatoes", "Carrots", "Fish"};
	public final String tag;
	public final String name;
	
	public DecoBlockBarrelFilled(int id, String tag, String name) {
		super(id, FCBetterThanWolves.fcMaterialPlanks);
        this.SetAxesEffectiveOn();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.tag = tag;
        this.name = name;
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
        this.DropItemsIndividualy(var1, var2, var3, var4, types[meta].itemID, 8, 0, var6);
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
	
	//CLIENT ONLY
	public Icon[] topIcons;
	public Icon sideIcon;
	public Icon bottomIcon;
	@Override
	public Icon getIcon(int side, int meta) {
		switch (side) {
		case 0:
			return bottomIcon;
		case 1:
			return topIcons[meta];
		default:
			return sideIcon;
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		sideIcon = register.registerIcon("ginger_" + tag + "_side");
		bottomIcon = register.registerIcon("ginger_" + tag + "_top");
		topIcons = new Icon[typeTags.length];
		
		for (int i = 0; i < typeTags.length; i++) {
			topIcons[i] = register.registerIcon("ginger_" + tag + typeTags[i]);
		}
	}
}