package net.minecraft.src;

import java.util.Random;

public class AddonBlockPodzol extends FCBlockDirt {
	protected AddonBlockPodzol(int ID) {
		super(ID);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 3, 0, var6);
        return true;
    }
	
	@Override
    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }
	
	@Override
    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5) {}
}
