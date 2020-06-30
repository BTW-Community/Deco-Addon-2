package net.minecraft.src;

public class FCBlockDeadBush extends BlockDeadBush
{
    protected static final double m_dWidth = 0.8D;
    protected static final double m_dHalfWidth = 0.4D;

    protected FCBlockDeadBush(int var1)
    {
        super(var1);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.InitBlockBounds(0.09999999999999998D, 0.0D, 0.09999999999999998D, 0.9D, 0.8D, 0.9D);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("deadbush");
    }

    public boolean CanSpitWebReplaceBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsReplaceableVegetation(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return var5.CanGrazeOnRoughVegetation();
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == Block.sand.blockID;
    }
}
