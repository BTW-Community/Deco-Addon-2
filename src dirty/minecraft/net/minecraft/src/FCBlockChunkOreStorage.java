package net.minecraft.src;

public abstract class FCBlockChunkOreStorage extends FCBlockFallingFullBlock
{
    protected FCBlockChunkOreStorage(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.SetCanBeCookedByKiln(true);
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }
}
