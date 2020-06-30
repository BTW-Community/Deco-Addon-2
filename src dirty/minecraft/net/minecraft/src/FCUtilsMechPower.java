package net.minecraft.src;

public class FCUtilsMechPower
{
    static boolean IsBlockPoweredByAxleToSide(World var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);
        var5.AddFacingAsOffset(var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);

        if (IsBlockIDAxle(var6))
        {
            FCBlockAxle var7 = (FCBlockAxle)Block.blocksList[var6];

            if (var7.IsAxleOrientedTowardsFacing(var0, var5.i, var5.j, var5.k, var4) && var7.GetPowerLevel(var0, var5.i, var5.j, var5.k) > 0)
            {
                return true;
            }
        }

        return false;
    }

    public static boolean IsBlockIDAxle(int var0)
    {
        return var0 == FCBetterThanWolves.fcBlockAxle.blockID || var0 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID;
    }

    public static boolean DoesBlockHaveFacingAxleToSide(IBlockAccess var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);
        var5.AddFacingAsOffset(var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);

        if (IsBlockIDAxle(var6))
        {
            FCBlockAxle var7 = (FCBlockAxle)Block.blocksList[var6];

            if (var7.IsAxleOrientedTowardsFacing(var0, var5.i, var5.j, var5.k, var4))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean DoesBlockHaveAnyFacingAxles(IBlockAccess var0, int var1, int var2, int var3)
    {
        for (int var4 = 0; var4 <= 5; ++var4)
        {
            if (DoesBlockHaveFacingAxleToSide(var0, var1, var2, var3, var4))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean IsBlockPoweredByHandCrank(World var0, int var1, int var2, int var3)
    {
        for (int var4 = 1; var4 <= 5; ++var4)
        {
            if (IsBlockPoweredByHandCrankToSide(var0, var1, var2, var3, var4))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean IsBlockPoweredByHandCrankToSide(World var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);
        var5.AddFacingAsOffset(var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);

        if (var6 == FCBetterThanWolves.fcHandCrank.blockID)
        {
            Block var7 = Block.blocksList[var6];
            FCIBlockMechanical var8 = (FCIBlockMechanical)var7;

            if (var8.IsOutputtingMechanicalPower(var0, var5.i, var5.j, var5.k))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean IsBlockPoweredByAxle(World var0, int var1, int var2, int var3, FCIBlockMechanical var4)
    {
        for (int var5 = 0; var5 <= 5; ++var5)
        {
            if (var4.CanInputAxlePowerToFacing(var0, var1, var2, var3, var5) && IsBlockPoweredByAxleToSide(var0, var1, var2, var3, var5))
            {
                return true;
            }
        }

        return false;
    }

    public static void DestroyHorizontallyAttachedAxles(World var0, int var1, int var2, int var3)
    {
        for (int var4 = 2; var4 <= 5; ++var4)
        {
            FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);
            var5.AddFacingAsOffset(var4);
            int var6 = var0.getBlockId(var5.i, var5.j, var5.k);

            if (IsBlockIDAxle(var6))
            {
                FCBlockAxle var7 = (FCBlockAxle)Block.blocksList[var6];

                if (var7.IsAxleOrientedTowardsFacing(var0, var5.i, var5.j, var5.k, var4))
                {
                    var7.BreakAxle(var0, var5.i, var5.j, var5.k);
                }
            }
        }
    }

    public static boolean IsPoweredGearBox(IBlockAccess var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);

        if (var4 != FCBetterThanWolves.fcBlockRedstoneClutch.blockID && var4 != FCBetterThanWolves.fcBlockGearBox.blockID)
        {
            return false;
        }
        else
        {
            FCBlockGearBox var5 = (FCBlockGearBox)Block.blocksList[var4];
            return var5.IsGearBoxOn(var0, var1, var2, var3);
        }
    }
}
