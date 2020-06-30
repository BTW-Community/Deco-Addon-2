package net.minecraft.src;

public class FCBlockNetherQuartzOre extends BlockOre
{
    public FCBlockNetherQuartzOre(int var1)
    {
        super(var1);
        this.SetBlockMaterial(FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("netherquartz");
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockNetherQuartz");
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderStandardFullBlock(this, var2, var3, var4);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return true;
    }

    public void RenderBlockMovedByPiston(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.RenderStandardFullBlockMovedByPiston(this, var2, var3, var4);
    }
}
