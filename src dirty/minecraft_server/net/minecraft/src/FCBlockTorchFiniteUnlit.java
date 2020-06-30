package net.minecraft.src;

import java.util.Random;

public class FCBlockTorchFiniteUnlit extends FCBlockTorchBaseUnlit
{
    protected FCBlockTorchFiniteUnlit(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockTorchFiniteIdle");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return GetIsBurnedOut(var1) ? 0 : super.idDropped(var1, var2, var3);
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsBurnedOut(var1, var2, var3, var4);
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        if (!this.GetIsBurnedOut(var1, var2, var3, var4))
        {
            if (this.IsRainingOnTorch(var1, var2, var3, var4))
            {
                var1.playAuxSFX(2227, var2, var3, var4, 0);
                return true;
            }
            else
            {
                return super.SetOnFireDirectly(var1, var2, var3, var4);
            }
        }
        else
        {
            return false;
        }
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsBurnedOut(var1, var2, var3, var4) ? 0 : super.GetChanceOfFireSpreadingDirectlyTo(var1, var2, var3, var4);
    }

    protected int GetLitBlockID()
    {
        return FCBetterThanWolves.fcBlockTorchFiniteBurning.blockID;
    }

    public void SetIsBurnedOut(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = SetIsBurnedOut(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public static int SetIsBurnedOut(int var0, boolean var1)
    {
        if (var1)
        {
            var0 |= 8;
        }
        else
        {
            var0 &= -9;
        }

        return var0;
    }

    public boolean GetIsBurnedOut(IBlockAccess var1, int var2, int var3, int var4)
    {
        return GetIsBurnedOut(var1.getBlockMetadata(var2, var3, var4));
    }

    public static boolean GetIsBurnedOut(int var0)
    {
        return (var0 & 8) != 0;
    }
}
