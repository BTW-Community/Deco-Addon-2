package net.minecraft.src;

public class DecoBlockRedSand extends FCBlockFallingFullBlock {
    public DecoBlockRedSand(int var1) {
        super(var1, Material.sand);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetFilterableProperties(8);
        this.setStepSound(soundSandFootstep);
        this.setUnlocalizedName("decoBlockRedSand");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public float GetMovementModifier(World var1, int var2, int var3, int var4) {
        return 0.8F;
    }

    @Override
    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6) {
        this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.pileRedSand.itemID, 6, 0, var6);
        return true;
    }

    @Override
    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4) {
        return true;
    }

    @Override
    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4) {
        return true;
    }

    @Override
    public boolean CanCactusGrowOnBlock(World var1, int var2, int var3, int var4) {
        return true;
    }
}