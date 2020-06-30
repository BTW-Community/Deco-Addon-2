package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class FCUtilsWorld
{
    public static boolean IsReplaceableBlock(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];
        return var5 == null || var5.blockMaterial.isReplaceable();
    }

    public static MovingObjectPosition RayTraceBlocksAlwaysHitWaterAndLava(World var0, Vec3 var1, Vec3 var2, boolean var3, boolean var4)
    {
        if (!Double.isNaN(var1.xCoord) && !Double.isNaN(var1.yCoord) && !Double.isNaN(var1.zCoord))
        {
            if (!Double.isNaN(var2.xCoord) && !Double.isNaN(var2.yCoord) && !Double.isNaN(var2.zCoord))
            {
                int var5 = MathHelper.floor_double(var2.xCoord);
                int var6 = MathHelper.floor_double(var2.yCoord);
                int var7 = MathHelper.floor_double(var2.zCoord);
                int var8 = MathHelper.floor_double(var1.xCoord);
                int var9 = MathHelper.floor_double(var1.yCoord);
                int var10 = MathHelper.floor_double(var1.zCoord);
                int var11 = var0.getBlockId(var8, var9, var10);
                int var12 = var0.getBlockMetadata(var8, var9, var10);
                Block var13 = Block.blocksList[var11];

                if ((!var4 || var13 == null || var13.getCollisionBoundingBoxFromPool(var0, var8, var9, var10) != null) && var11 > 0 && (var13.canCollideCheck(var12, var3) || var11 == Block.waterMoving.blockID || var11 == Block.waterStill.blockID || var11 == Block.lavaMoving.blockID || var11 == Block.lavaStill.blockID))
                {
                    MovingObjectPosition var14 = var13.collisionRayTrace(var0, var8, var9, var10, var1, var2);

                    if (var14 != null)
                    {
                        return var14;
                    }
                }

                int var42 = 200;

                while (var42-- >= 0)
                {
                    if (Double.isNaN(var1.xCoord) || Double.isNaN(var1.yCoord) || Double.isNaN(var1.zCoord))
                    {
                        return null;
                    }

                    if (var8 == var5 && var9 == var6 && var10 == var7)
                    {
                        return null;
                    }

                    boolean var15 = true;
                    boolean var16 = true;
                    boolean var17 = true;
                    double var18 = 999.0D;
                    double var20 = 999.0D;
                    double var22 = 999.0D;

                    if (var5 > var8)
                    {
                        var18 = (double)var8 + 1.0D;
                    }
                    else if (var5 < var8)
                    {
                        var18 = (double)var8 + 0.0D;
                    }
                    else
                    {
                        var15 = false;
                    }

                    if (var6 > var9)
                    {
                        var20 = (double)var9 + 1.0D;
                    }
                    else if (var6 < var9)
                    {
                        var20 = (double)var9 + 0.0D;
                    }
                    else
                    {
                        var16 = false;
                    }

                    if (var7 > var10)
                    {
                        var22 = (double)var10 + 1.0D;
                    }
                    else if (var7 < var10)
                    {
                        var22 = (double)var10 + 0.0D;
                    }
                    else
                    {
                        var17 = false;
                    }

                    double var24 = 999.0D;
                    double var26 = 999.0D;
                    double var28 = 999.0D;
                    double var30 = var2.xCoord - var1.xCoord;
                    double var32 = var2.yCoord - var1.yCoord;
                    double var34 = var2.zCoord - var1.zCoord;

                    if (var15)
                    {
                        var24 = (var18 - var1.xCoord) / var30;
                    }

                    if (var16)
                    {
                        var26 = (var20 - var1.yCoord) / var32;
                    }

                    if (var17)
                    {
                        var28 = (var22 - var1.zCoord) / var34;
                    }

                    boolean var36 = false;
                    byte var43;

                    if (var24 < var26 && var24 < var28)
                    {
                        if (var5 > var8)
                        {
                            var43 = 4;
                        }
                        else
                        {
                            var43 = 5;
                        }

                        var1.xCoord = var18;
                        var1.yCoord += var32 * var24;
                        var1.zCoord += var34 * var24;
                    }
                    else if (var26 < var28)
                    {
                        if (var6 > var9)
                        {
                            var43 = 0;
                        }
                        else
                        {
                            var43 = 1;
                        }

                        var1.xCoord += var30 * var26;
                        var1.yCoord = var20;
                        var1.zCoord += var34 * var26;
                    }
                    else
                    {
                        if (var7 > var10)
                        {
                            var43 = 2;
                        }
                        else
                        {
                            var43 = 3;
                        }

                        var1.xCoord += var30 * var28;
                        var1.yCoord += var32 * var28;
                        var1.zCoord = var22;
                    }

                    Vec3 var37 = Vec3.createVectorHelper(var1.xCoord, var1.yCoord, var1.zCoord);
                    var8 = (int)(var37.xCoord = (double)MathHelper.floor_double(var1.xCoord));

                    if (var43 == 5)
                    {
                        --var8;
                        ++var37.xCoord;
                    }

                    var9 = (int)(var37.yCoord = (double)MathHelper.floor_double(var1.yCoord));

                    if (var43 == 1)
                    {
                        --var9;
                        ++var37.yCoord;
                    }

                    var10 = (int)(var37.zCoord = (double)MathHelper.floor_double(var1.zCoord));

                    if (var43 == 3)
                    {
                        --var10;
                        ++var37.zCoord;
                    }

                    int var38 = var0.getBlockId(var8, var9, var10);
                    int var39 = var0.getBlockMetadata(var8, var9, var10);
                    Block var40 = Block.blocksList[var38];

                    if ((!var4 || var40 == null || var40.getCollisionBoundingBoxFromPool(var0, var8, var9, var10) != null) && var38 > 0 && (var40.canCollideCheck(var39, var3) || var38 == Block.waterMoving.blockID || var38 == Block.waterStill.blockID || var38 == Block.lavaMoving.blockID || var38 == Block.lavaStill.blockID))
                    {
                        MovingObjectPosition var41 = var40.collisionRayTrace(var0, var8, var9, var10, var1, var2);

                        if (var41 != null)
                        {
                            return var41;
                        }
                    }
                }

                return null;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public static MovingObjectPosition RayTraceBlocksAlwaysHitWaterAndLavaAndFire(World var0, Vec3 var1, Vec3 var2, boolean var3, boolean var4)
    {
        if (!Double.isNaN(var1.xCoord) && !Double.isNaN(var1.yCoord) && !Double.isNaN(var1.zCoord))
        {
            if (!Double.isNaN(var2.xCoord) && !Double.isNaN(var2.yCoord) && !Double.isNaN(var2.zCoord))
            {
                int var5 = MathHelper.floor_double(var2.xCoord);
                int var6 = MathHelper.floor_double(var2.yCoord);
                int var7 = MathHelper.floor_double(var2.zCoord);
                int var8 = MathHelper.floor_double(var1.xCoord);
                int var9 = MathHelper.floor_double(var1.yCoord);
                int var10 = MathHelper.floor_double(var1.zCoord);
                int var11 = var0.getBlockId(var8, var9, var10);
                int var12 = var0.getBlockMetadata(var8, var9, var10);
                Block var13 = Block.blocksList[var11];

                if ((!var4 || var13 == null || var13.getCollisionBoundingBoxFromPool(var0, var8, var9, var10) != null) && var11 > 0 && (var13.canCollideCheck(var12, var3) || var11 == Block.waterMoving.blockID || var11 == Block.waterStill.blockID || var11 == Block.lavaMoving.blockID || var11 == Block.lavaStill.blockID || var11 == Block.fire.blockID || var11 == FCBetterThanWolves.fcBlockFireStoked.blockID))
                {
                    MovingObjectPosition var14 = var13.collisionRayTrace(var0, var8, var9, var10, var1, var2);

                    if (var14 != null)
                    {
                        return var14;
                    }
                }

                int var42 = 200;

                while (var42-- >= 0)
                {
                    if (Double.isNaN(var1.xCoord) || Double.isNaN(var1.yCoord) || Double.isNaN(var1.zCoord))
                    {
                        return null;
                    }

                    if (var8 == var5 && var9 == var6 && var10 == var7)
                    {
                        return null;
                    }

                    boolean var15 = true;
                    boolean var16 = true;
                    boolean var17 = true;
                    double var18 = 999.0D;
                    double var20 = 999.0D;
                    double var22 = 999.0D;

                    if (var5 > var8)
                    {
                        var18 = (double)var8 + 1.0D;
                    }
                    else if (var5 < var8)
                    {
                        var18 = (double)var8 + 0.0D;
                    }
                    else
                    {
                        var15 = false;
                    }

                    if (var6 > var9)
                    {
                        var20 = (double)var9 + 1.0D;
                    }
                    else if (var6 < var9)
                    {
                        var20 = (double)var9 + 0.0D;
                    }
                    else
                    {
                        var16 = false;
                    }

                    if (var7 > var10)
                    {
                        var22 = (double)var10 + 1.0D;
                    }
                    else if (var7 < var10)
                    {
                        var22 = (double)var10 + 0.0D;
                    }
                    else
                    {
                        var17 = false;
                    }

                    double var24 = 999.0D;
                    double var26 = 999.0D;
                    double var28 = 999.0D;
                    double var30 = var2.xCoord - var1.xCoord;
                    double var32 = var2.yCoord - var1.yCoord;
                    double var34 = var2.zCoord - var1.zCoord;

                    if (var15)
                    {
                        var24 = (var18 - var1.xCoord) / var30;
                    }

                    if (var16)
                    {
                        var26 = (var20 - var1.yCoord) / var32;
                    }

                    if (var17)
                    {
                        var28 = (var22 - var1.zCoord) / var34;
                    }

                    boolean var36 = false;
                    byte var43;

                    if (var24 < var26 && var24 < var28)
                    {
                        if (var5 > var8)
                        {
                            var43 = 4;
                        }
                        else
                        {
                            var43 = 5;
                        }

                        var1.xCoord = var18;
                        var1.yCoord += var32 * var24;
                        var1.zCoord += var34 * var24;
                    }
                    else if (var26 < var28)
                    {
                        if (var6 > var9)
                        {
                            var43 = 0;
                        }
                        else
                        {
                            var43 = 1;
                        }

                        var1.xCoord += var30 * var26;
                        var1.yCoord = var20;
                        var1.zCoord += var34 * var26;
                    }
                    else
                    {
                        if (var7 > var10)
                        {
                            var43 = 2;
                        }
                        else
                        {
                            var43 = 3;
                        }

                        var1.xCoord += var30 * var28;
                        var1.yCoord += var32 * var28;
                        var1.zCoord = var22;
                    }

                    Vec3 var37 = Vec3.createVectorHelper(var1.xCoord, var1.yCoord, var1.zCoord);
                    var8 = (int)(var37.xCoord = (double)MathHelper.floor_double(var1.xCoord));

                    if (var43 == 5)
                    {
                        --var8;
                        ++var37.xCoord;
                    }

                    var9 = (int)(var37.yCoord = (double)MathHelper.floor_double(var1.yCoord));

                    if (var43 == 1)
                    {
                        --var9;
                        ++var37.yCoord;
                    }

                    var10 = (int)(var37.zCoord = (double)MathHelper.floor_double(var1.zCoord));

                    if (var43 == 3)
                    {
                        --var10;
                        ++var37.zCoord;
                    }

                    int var38 = var0.getBlockId(var8, var9, var10);
                    int var39 = var0.getBlockMetadata(var8, var9, var10);
                    Block var40 = Block.blocksList[var38];

                    if ((!var4 || var40 == null || var40.getCollisionBoundingBoxFromPool(var0, var8, var9, var10) != null) && var38 > 0 && (var40.canCollideCheck(var39, var3) || var38 == Block.waterMoving.blockID || var38 == Block.waterStill.blockID || var38 == Block.lavaMoving.blockID || var38 == Block.lavaStill.blockID || var38 == Block.fire.blockID || var38 == FCBetterThanWolves.fcBlockFireStoked.blockID))
                    {
                        MovingObjectPosition var41 = var40.collisionRayTrace(var0, var8, var9, var10, var1, var2);

                        if (var41 != null)
                        {
                            return var41;
                        }
                    }
                }

                return null;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public static int IsValidSourceForFluidBlockToFacing(World var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);
        var5.AddFacingAsOffset(var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);

        if (FCBetterThanWolves.m_bBlocksPotentialFluidSources[var6])
        {
            FCIBlockFluidSource var7 = (FCIBlockFluidSource)((FCIBlockFluidSource)Block.blocksList[var6]);
            return var7.IsSourceToFluidBlockAtFacing(var0, var5.i, var5.j, var5.k, Block.GetOppositeFacing(var4));
        }
        else
        {
            return -1;
        }
    }

    public static boolean IsValidLightLevelForMobSpawning(World var0, int var1, int var2, int var3)
    {
        return var0.provider.dimensionId == -1 || var0.getBlockLightValue(var1, var2, var3) <= 7 && var0.getLightBrightness(var1, var2, var3) <= 0.5F;
    }

    public static boolean CanMobsSpawnHere(World var0, int var1, int var2, int var3)
    {
        if (!var0.isBlockNormalCube(var1, var2, var3) && !var0.getBlockMaterial(var1, var2, var3).isLiquid())
        {
            Block var4 = Block.blocksList[var0.getBlockId(var1, var2 - 1, var3)];

            if (var4 != null && var4.CanMobsSpawnOn(var0, var1, var2 - 1, var3) && var4 != Block.leaves && IsValidLightLevelForMobSpawning(var0, var1, var2, var3))
            {
                Block var5 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
                return var5 == null || var5.getCollisionBoundingBoxFromPool(var0, var1, var2, var3) == null;
            }
        }

        return false;
    }

    public static boolean DoesBlockHaveSmallCenterHardpointToFacing(IBlockAccess var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var0.getBlockId(var1, var2, var3);
        Block var7 = Block.blocksList[var6];
        return var7 != null ? var7.HasSmallCenterHardPointToFacing(var0, var1, var2, var3, var4, var5) : false;
    }

    public static boolean DoesBlockHaveSmallCenterHardpointToFacing(IBlockAccess var0, int var1, int var2, int var3, int var4)
    {
        return DoesBlockHaveSmallCenterHardpointToFacing(var0, var1, var2, var3, var4, false);
    }

    public static boolean DoesBlockHaveCenterHardpointToFacing(IBlockAccess var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var0.getBlockId(var1, var2, var3);
        Block var7 = Block.blocksList[var6];
        return var7 != null ? var7.HasCenterHardPointToFacing(var0, var1, var2, var3, var4, var5) : false;
    }

    public static boolean DoesBlockHaveCenterHardpointToFacing(IBlockAccess var0, int var1, int var2, int var3, int var4)
    {
        return DoesBlockHaveCenterHardpointToFacing(var0, var1, var2, var3, var4, false);
    }

    public static boolean DoesBlockHaveLargeCenterHardpointToFacing(IBlockAccess var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var0.getBlockId(var1, var2, var3);
        Block var7 = Block.blocksList[var6];
        return var7 != null ? var7.HasLargeCenterHardPointToFacing(var0, var1, var2, var3, var4, var5) : false;
    }

    public static boolean DoesBlockHaveLargeCenterHardpointToFacing(IBlockAccess var0, int var1, int var2, int var3, int var4)
    {
        return DoesBlockHaveLargeCenterHardpointToFacing(var0, var1, var2, var3, var4, false);
    }

    public static boolean IsBlockRestingOnThatBelow(IBlockAccess var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];
        return var5 != null ? var5.IsBlockRestingOnThatBelow(var0, var1, var2, var3) : false;
    }

    public static boolean DoesBlockHaveSolidTopSurface(IBlockAccess var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
        return var4 != null && var4.HasLargeCenterHardPointToFacing(var0, var1, var2, var3, 1);
    }

    public static boolean IsStairBlock(IBlockAccess var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];
        return var5 != null ? var5.IsStairBlock() : false;
    }

    public static long GetOverworldTimeServerOnly()
    {
        return MinecraftServer.getServer() != null ? MinecraftServer.getServer().worldServers[0].getWorldTime() : 0L;
    }

    public static boolean GameProgressHasNetherBeenAccessedServerOnly()
    {
        return MinecraftServer.getServer() != null ? MinecraftServer.getServer().worldServers[0].worldInfo.HasNetherBeenAccessed() : false;
    }

    public static void GameProgressSetNetherBeenAccessedServerOnly()
    {
        if (MinecraftServer.getServer() != null)
        {
            MinecraftServer.getServer().worldServers[0].worldInfo.SetNetherBeenAccessed();
        }
    }

    public static boolean GameProgressHasWitherBeenSummonedServerOnly()
    {
        return MinecraftServer.getServer() != null ? MinecraftServer.getServer().worldServers[0].worldInfo.HasWitherBeenSummoned() : false;
    }

    public static void GameProgressSetWitherHasBeenSummonedServerOnly()
    {
        if (MinecraftServer.getServer() != null)
        {
            MinecraftServer.getServer().worldServers[0].worldInfo.SetWitherHasBeenSummoned();
        }
    }

    public static boolean GameProgressHasEndDimensionBeenAccessedServerOnly()
    {
        return MinecraftServer.getServer() != null ? MinecraftServer.getServer().worldServers[0].worldInfo.HasEndDimensionBeenAccessed() : false;
    }

    public static void GameProgressSetEndDimensionHasBeenAccessedServerOnly()
    {
        if (MinecraftServer.getServer() != null)
        {
            MinecraftServer.getServer().worldServers[0].worldInfo.SetEndDimensionHasBeenAccessed();
        }
    }

    public static int RotateFacingForCoordBaseMode(int var0, int var1)
    {
        if (var1 == 0)
        {
            if (var0 == 2)
            {
                return 3;
            }

            if (var0 == 3)
            {
                return 2;
            }
        }
        else if (var1 == 1)
        {
            if (var0 == 2)
            {
                return 4;
            }

            if (var0 == 3)
            {
                return 5;
            }

            if (var0 == 4)
            {
                return 2;
            }

            if (var0 == 5)
            {
                return 3;
            }
        }
        else if (var1 == 3)
        {
            if (var0 == 2)
            {
                return 5;
            }

            if (var0 == 3)
            {
                return 4;
            }

            if (var0 == 4)
            {
                return 2;
            }

            if (var0 == 5)
            {
                return 3;
            }
        }

        return var0;
    }

    public static void SendPacketToAllPlayersTrackingEntity(WorldServer var0, Entity var1, Packet var2)
    {
        var0.getEntityTracker().sendPacketToAllPlayersTrackingEntity(var1, var2);
    }

    public static void SendPacketToPlayer(NetServerHandler var0, Packet var1)
    {
        var0.sendPacketToPlayer(var1);
    }

    public static boolean HasNeighborWithMortarInFullFaceContactToFacing(World var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3, var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);
        Block var7 = Block.blocksList[var6];
        return var7 != null && var7.HasMortar(var0, var5.i, var5.j, var5.k) && var7.HasContactPointToFullFace(var0, var5.i, var5.j, var5.k, Block.GetOppositeFacing(var4));
    }

    public static boolean HasNeighborWithMortarInSlabSideContactToFacing(World var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1, var2, var3, var4);
        int var7 = var0.getBlockId(var6.i, var6.j, var6.k);
        Block var8 = Block.blocksList[var7];
        return var8 != null && var8.HasMortar(var0, var6.i, var6.j, var6.k) && var8.HasContactPointToSlabSideFace(var0, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var4), var5);
    }

    public static boolean HasNeighborWithMortarInStairShapedContactToFacing(World var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3, var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);
        Block var7 = Block.blocksList[var6];
        return var7 != null && var7.HasMortar(var0, var5.i, var5.j, var5.k) && var7.HasContactPointToStairShapedFace(var0, var5.i, var5.j, var5.k, Block.GetOppositeFacing(var4));
    }

    public static boolean HasNeighborWithMortarInStairNarrowVerticalContactToFacing(World var0, int var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1, var2, var3, var4);
        int var7 = var0.getBlockId(var6.i, var6.j, var6.k);
        Block var8 = Block.blocksList[var7];
        return var8 != null && var8.HasMortar(var0, var6.i, var6.j, var6.k) && var8.HasContactPointToStairNarrowVerticalFace(var0, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var4), var5);
    }

    public static boolean HasStickySnowNeighborInFullFaceContactToFacing(World var0, int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3, var4);
        int var6 = var0.getBlockId(var5.i, var5.j, var5.k);
        Block var7 = Block.blocksList[var6];
        return var7 != null && var7.IsStickyToSnow(var0, var5.i, var5.j, var5.k) && var7.HasContactPointToFullFace(var0, var5.i, var5.j, var5.k, Block.GetOppositeFacing(var4));
    }

    public static boolean HasStickySnowNeighborInSlabSideContactToFacing(World var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1, var2, var3, var4);
        int var7 = var0.getBlockId(var6.i, var6.j, var6.k);
        Block var8 = Block.blocksList[var7];
        return var8 != null && var8.IsStickyToSnow(var0, var6.i, var6.j, var6.k) && var8.HasContactPointToSlabSideFace(var0, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var4), var5);
    }

    public static void ClearAnyGroundCoverOnBlock(World var0, int var1, int var2, int var3)
    {
        if (IsGroundCoverOnBlock(var0, var1, var2, var3))
        {
            var0.setBlockToAir(var1, var2 + 1, var3);
        }
    }

    public static boolean IsGroundCoverOnBlock(IBlockAccess var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2 + 1, var3);

        if (var4 != 0)
        {
            Block var5 = Block.blocksList[var4];

            if (var5.IsGroundCover())
            {
                return true;
            }
        }

        return false;
    }

    public static boolean IsWaterSourceBlock(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        return (var4 == Block.waterMoving.blockID || var4 == Block.waterStill.blockID) && var0.getBlockMetadata(var1, var2, var3) == 0;
    }
}
