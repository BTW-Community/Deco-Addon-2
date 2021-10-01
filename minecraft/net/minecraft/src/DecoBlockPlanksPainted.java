package net.minecraft.src;

import java.util.List;

public class DecoBlockPlanksPainted extends FCBlockPlanks {
	private String nameTag;
	private Icon[] icons = new Icon[16];

	public DecoBlockPlanksPainted(int ID, String tag) {
		super(ID);
		this.nameTag = tag;
		this.setUnlocalizedName(tag);
	}
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
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
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
	
	//CLIENT ONLY
	@Override
	public Icon getIcon(int side, int metadata){
		return this.icons[metadata];
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < 16; i++)
			this.icons[i] = iconRegister.registerIcon(nameTag+"_" + DecoUtilsMisc.colorOrder[i]);
	}
}
