package net.minecraft.src;

public class FCBlockPlatform extends Block
{
    private boolean[][][] bPlatformAlreadyConsideredForEntityConversion;
    private boolean[][][] bPlatformAlreadyConsideredForConnectedTest;

    protected FCBlockPlatform(int var1)
    {
        super(var1, Material.wood);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn();
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(FCEnumFlammability.WICKER);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockPlatform");
        this.bPlatformAlreadyConsideredForEntityConversion = new boolean[5][5][5];
        this.bPlatformAlreadyConsideredForConnectedTest = new boolean[5][5][5];
        this.ResetPlatformConsideredForEntityConversionArray();
        this.ResetPlatformConsideredForConnectedTestArray();
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return true;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 <= 1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsNormalCube(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    private void ConvertToEntity(World var1, int var2, int var3, int var4, FCEntityMovingAnchor var5)
    {
        FCEntityMovingPlatform var6 = new FCEntityMovingPlatform(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), var5);
        var1.spawnEntityInWorld(var6);
        this.AttemptToLiftBlockWithPlatform(var1, var2, var3 + 1, var4);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    private void AttemptToLiftBlockWithPlatform(World var1, int var2, int var3, int var4)
    {
        if (FCEntityBlockLiftedByPlatform.CanBlockBeConvertedToEntity(var1, var2, var3, var4))
        {
            new FCEntityBlockLiftedByPlatform(var1, var2, var3, var4);
        }
    }

    private int GetDistToClosestConnectedAnchorPoint(World var1, int var2, int var3, int var4)
    {
        int var5 = -1;

        for (int var6 = var2 - 2; var6 <= var2 + 2; ++var6)
        {
            for (int var7 = var3 - 2; var7 <= var3 + 2; ++var7)
            {
                for (int var8 = var4 - 2; var8 <= var4 + 2; ++var8)
                {
                    int var9 = var1.getBlockId(var6, var7, var8);

                    if (var9 == this.blockID)
                    {
                        int var10 = var1.getBlockId(var6, var7 + 1, var8);

                        if (var10 == FCBetterThanWolves.fcAnchor.blockID && ((FCBlockAnchor)((FCBlockAnchor)FCBetterThanWolves.fcAnchor)).GetFacing(var1, var6, var7 + 1, var8) == 1 && this.IsPlatformConnectedToAnchorPoint(var1, var2, var3, var4, var6, var7, var8))
                        {
                            int var11 = Math.abs(var6 - var2) + Math.abs(var7 - var3) + Math.abs(var8 - var4);

                            if (var5 == -1 || var11 < var5)
                            {
                                var5 = var11;
                            }
                        }
                    }
                }
            }
        }

        return var5;
    }

    private boolean IsPlatformConnectedToAnchorPoint(World var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        this.ResetPlatformConsideredForConnectedTestArray();
        return var2 == var5 && var3 == var6 && var4 == var7 ? true : this.PropogateTestForConnected(var1, var5, var6, var7, var5, var6, var7, var2, var3, var4);
    }

    private boolean PropogateTestForConnected(World var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10)
    {
        int var11 = var2 - var5;
        int var12 = var3 - var6;
        int var13 = var4 - var7;

        if (this.bPlatformAlreadyConsideredForConnectedTest[var11 + 2][var12 + 2][var13 + 2])
        {
            return false;
        }
        else
        {
            this.bPlatformAlreadyConsideredForConnectedTest[var11 + 2][var12 + 2][var13 + 2] = true;

            for (int var14 = 0; var14 < 6; ++var14)
            {
                FCUtilsBlockPos var15 = new FCUtilsBlockPos(var2, var3, var4);
                var15.AddFacingAsOffset(var14);

                if (var15.i == var8 && var15.j == var9 && var15.k == var10)
                {
                    return true;
                }

                int var16 = var1.getBlockId(var15.i, var15.j, var15.k);

                if (var16 == this.blockID)
                {
                    int var17 = Math.abs(var5 - var15.i);
                    int var18 = Math.abs(var6 - var15.j);
                    int var19 = Math.abs(var7 - var15.k);

                    if (var17 <= 2 && var18 <= 2 && var19 <= 2 && this.PropogateTestForConnected(var1, var15.i, var15.j, var15.k, var5, var6, var7, var8, var9, var10))
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    void ResetPlatformConsideredForEntityConversionArray()
    {
        for (int var1 = 0; var1 < 5; ++var1)
        {
            for (int var2 = 0; var2 < 5; ++var2)
            {
                for (int var3 = 0; var3 < 5; ++var3)
                {
                    this.bPlatformAlreadyConsideredForEntityConversion[var1][var2][var3] = false;
                }
            }
        }
    }

    void ResetPlatformConsideredForConnectedTestArray()
    {
        for (int var1 = 0; var1 < 5; ++var1)
        {
            for (int var2 = 0; var2 < 5; ++var2)
            {
                for (int var3 = 0; var3 < 5; ++var3)
                {
                    this.bPlatformAlreadyConsideredForConnectedTest[var1][var2][var3] = false;
                }
            }
        }
    }

    public void CovertToEntitiesFromThisPlatform(World var1, int var2, int var3, int var4, FCEntityMovingAnchor var5)
    {
        this.ResetPlatformConsideredForEntityConversionArray();
        this.PropogateCovertToEntity(var1, var2, var3, var4, var5, var2, var3, var4);
    }

    private void PropogateCovertToEntity(World var1, int var2, int var3, int var4, FCEntityMovingAnchor var5, int var6, int var7, int var8)
    {
        int var9 = var2 - var6;
        int var10 = var3 - var7;
        int var11 = var4 - var8;

        if (!this.bPlatformAlreadyConsideredForEntityConversion[var9 + 2][var10 + 2][var11 + 2])
        {
            this.bPlatformAlreadyConsideredForEntityConversion[var9 + 2][var10 + 2][var11 + 2] = true;
            int var12 = Math.abs(var9) + Math.abs(var10) + Math.abs(var11);
            int var13 = this.GetDistToClosestConnectedAnchorPoint(var1, var2, var3, var4);

            if (var13 == -1 || var12 <= var13)
            {
                this.ConvertToEntity(var1, var2, var3, var4, var5);

                for (int var14 = 0; var14 < 6; ++var14)
                {
                    FCUtilsBlockPos var15 = new FCUtilsBlockPos(var2, var3, var4);
                    var15.AddFacingAsOffset(var14);
                    int var16 = var1.getBlockId(var15.i, var15.j, var15.k);

                    if (var16 == this.blockID)
                    {
                        int var17 = Math.abs(var6 - var15.i);
                        int var18 = Math.abs(var7 - var15.j);
                        int var19 = Math.abs(var8 - var15.k);

                        if (var17 <= 2 && var18 <= 2 && var19 <= 2)
                        {
                            this.PropogateCovertToEntity(var1, var15.i, var15.j, var15.k, var5, var6, var7, var8);
                        }
                    }
                }
            }
        }
    }
}
