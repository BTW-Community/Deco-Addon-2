package net.minecraft.src;

public class AddonBlockWorkbench extends Block {
	protected AddonBlockWorkbench(int ID) {
		super(ID, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.SetAxesEffectiveOn();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("workbench");
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}
    //
}
