package net.minecraft.src;

public class DecoBlockTerracotta extends Block {

	public DecoBlockTerracotta(int id) {
		super(id, Material.rock);
	}

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.pileRedSand.itemID, 8, 0, var6);
        return true;
    }
}