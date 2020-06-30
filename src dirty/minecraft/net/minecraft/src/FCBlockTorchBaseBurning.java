package net.minecraft.src;

import java.util.Random;

public class FCBlockTorchBaseBurning extends FCBlockTorchBase
{
    protected FCBlockTorchBaseBurning(int var1)
    {
        super(var1);
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void OnFluidFlowIntoBlock(World var1, int var2, int var3, int var4, BlockFluid var5)
    {
        if (var5.blockMaterial == Material.water)
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcBlockTorchNetherUnlit.blockID, 1, 0));
        }
        else
        {
            super.OnFluidFlowIntoBlock(var1, var2, var3, var4, var5);
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        Vec3 var6 = this.GetParticalPos(var1, var2, var3, var4);
        var1.spawnParticle("smoke", var6.xCoord, var6.yCoord, var6.zCoord, 0.0D, 0.0D, 0.0D);
        var1.spawnParticle("flame", var6.xCoord, var6.yCoord, var6.zCoord, 0.0D, 0.0D, 0.0D);
    }

    protected Vec3 GetParticalPos(World var1, int var2, int var3, int var4)
    {
        Vec3 var5 = Vec3.createVectorHelper((double)var2 + 0.5D, (double)var3 + 0.92D, (double)var4 + 0.5D);
        int var6 = this.GetOrientation(var1, var2, var3, var4);
        double var7 = 0.27D;

        if (var6 == 1)
        {
            var5.xCoord -= var7;
        }
        else if (var6 == 2)
        {
            var5.xCoord += var7;
        }
        else if (var6 == 3)
        {
            var5.zCoord -= var7;
        }
        else if (var6 == 4)
        {
            var5.zCoord += var7;
        }
        else
        {
            var5.yCoord -= 0.22D;
        }

        return var5;
    }
}
