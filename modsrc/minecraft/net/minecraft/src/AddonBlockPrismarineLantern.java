package net.minecraft.src;

public class AddonBlockPrismarineLantern extends Block {
	protected AddonBlockPrismarineLantern(int id) {
        super(id, Material.glass);
        this.setHardness(0.6F);
        this.setResistance(0.5F);
        this.SetPicksEffectiveOn();
        this.setLightValue(1.0F);
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("ginger_prismarineLantern");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
}