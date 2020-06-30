package net.minecraft.src;

public class FCUtilsBlockPos
{
    public int i;
    public int j;
    public int k;

    public FCUtilsBlockPos()
    {
        this.i = this.j = this.k = 0;
    }

    public FCUtilsBlockPos(int var1, int var2, int var3)
    {
        this.i = var1;
        this.j = var2;
        this.k = var3;
    }

    public FCUtilsBlockPos(int var1, int var2, int var3, int var4)
    {
        this(var1, var2, var3);
        this.AddFacingAsOffset(var4);
    }

    public void AddFacingAsOffset(int var1)
    {
        this.i += Facing.offsetsXForSide[var1];
        this.j += Facing.offsetsYForSide[var1];
        this.k += Facing.offsetsZForSide[var1];
    }

    public void Invert()
    {
        this.i = -this.i;
        this.j = -this.j;
        this.k = -this.k;
    }

    public void AddPos(FCUtilsBlockPos var1)
    {
        this.i += var1.i;
        this.j += var1.j;
        this.k += var1.k;
    }

    public void Set(int var1, int var2, int var3)
    {
        this.i = var1;
        this.j = var2;
        this.k = var3;
    }

    public void Set(FCUtilsBlockPos var1)
    {
        this.i = var1.i;
        this.j = var1.j;
        this.k = var1.k;
    }
}
