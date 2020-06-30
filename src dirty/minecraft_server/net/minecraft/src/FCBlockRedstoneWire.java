package net.minecraft.src;

public class FCBlockRedstoneWire extends BlockRedstoneWire
{
    public FCBlockRedstoneWire(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public boolean TriggersBuddy()
    {
        return false;
    }
}
