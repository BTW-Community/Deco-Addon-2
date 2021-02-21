package net.minecraft.src;

public class DecoBlockChairWood extends DecoBlockChair {
	public DecoBlockChairWood(int id, String woodType) {
		super(id, FCBetterThanWolves.fcMaterialPlanks, woodType);
		this.SetAxesEffectiveOn( true );
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
		this.setStepSound(soundWoodFootstep);
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
}
