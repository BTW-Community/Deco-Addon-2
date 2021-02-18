package net.minecraft.src;

public class DecoBlockSignWall extends DecoBlockSign {
    public DecoBlockSignWall(int var1, int metaDrop, String baseTexture)
    {
        super(var1, false, metaDrop, baseTexture);
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == Block.GetOppositeFacing(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean OnRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        var1.setBlockToAir(var2, var3, var4);
        return false;
    }

}
