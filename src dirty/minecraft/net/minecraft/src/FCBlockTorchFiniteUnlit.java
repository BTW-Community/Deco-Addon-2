package net.minecraft.src;

import java.util.Random;

public class FCBlockTorchFiniteUnlit extends FCBlockTorchBaseUnlit
{
    private Icon m_burnedIcon;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_burnedIcon = var1.registerIcon("fcBlockTorchFiniteIdle_burned");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return GetIsBurnedOut(var2) ? this.m_burnedIcon : super.getIcon(var1, var2);
    }
}
