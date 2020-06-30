package net.minecraft.src;

import java.util.List;

public class FCUtilsMisc
{
    public static final int m_iTicksPerSecond = 20;
    public static final int m_iTicksPerMinute = 1200;
    public static final int m_iTicksPerGameDay = 24000;

    public static int ConvertPlacingEntityOrientationToBlockFacingReversed(EntityLiving var0)
    {
        float var1 = var0.rotationPitch;
        return var1 > 60.0F ? 1 : (var1 < -60.0F ? 0 : ConvertOrientationToFlatBlockFacingReversed(var0));
    }

    public static int ConvertOrientationToFlatBlockFacingReversed(EntityLiving var0)
    {
        int var2 = MathHelper.floor_double((double)(var0.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        byte var1;

        if (var2 == 0)
        {
            var1 = 2;
        }
        else if (var2 == 1)
        {
            var1 = 5;
        }
        else if (var2 == 2)
        {
            var1 = 3;
        }
        else
        {
            var1 = 4;
        }

        return var1;
    }

    public static int ConvertOrientationToFlatBlockFacing(EntityLiving var0)
    {
        int var2 = MathHelper.floor_double((double)(var0.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        byte var1;

        if (var2 == 0)
        {
            var1 = 3;
        }
        else if (var2 == 1)
        {
            var1 = 4;
        }
        else if (var2 == 2)
        {
            var1 = 2;
        }
        else
        {
            var1 = 5;
        }

        return var1;
    }

    static boolean IsIKInColdBiome(World var0, int var1, int var2)
    {
        BiomeGenBase var3 = var0.getBiomeGenForCoords(var1, var2);
        float var4 = var3.getFloatTemperature();
        return var4 <= 0.15F;
    }

    public static void PositionAllNonPlayerMoveableEntitiesOutsideOfLocation(World var0, int var1, int var2, int var3)
    {
        List var4 = var0.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB((double)var1, (double)var2, (double)var3, (double)var1 + 1.0D, (double)var2 + 1.0D, (double)var3 + 1.0D));

        if (var4 != null && var4.size() > 0)
        {
            for (int var5 = 0; var5 < var4.size(); ++var5)
            {
                Entity var6 = (Entity)var4.get(var5);

                if ((var6.canBePushed() || var6 instanceof EntityItem) && !(var6 instanceof EntityPlayer))
                {
                    PositionEntityOutsideOfLocation(var0, var6, var1, var2, var3);
                }
            }
        }
    }

    private static void PositionEntityOutsideOfLocation(World var0, Entity var1, int var2, int var3, int var4)
    {
        double var5 = (double)((float)var2);
        double var7 = (double)((float)var3);
        double var9 = (double)((float)var4);
        double var11 = (double)((float)(var2 + 1));
        double var13 = (double)((float)(var3 + 1));
        double var15 = (double)((float)(var4 + 1));
        boolean var17 = false;
        boolean var18 = false;
        boolean var19 = false;
        double var20 = 0.0D;
        double var22 = 0.0D;
        double var24 = 0.0D;

        if (var1.boundingBox.minX <= var11 && var1.boundingBox.maxX >= var5)
        {
            var17 = true;

            if (Math.abs(var11 - var1.boundingBox.minX) < Math.abs(var5 - var1.boundingBox.maxX))
            {
                var20 = var11 - var1.boundingBox.minX + 0.01D;
            }
            else
            {
                var20 = var5 - var1.boundingBox.maxX - 0.01D;
            }
        }

        if (var1.boundingBox.minY <= var13 && var1.boundingBox.maxY >= var7)
        {
            var18 = true;

            if (Math.abs(var13 - var1.boundingBox.minY) < Math.abs(var7 - var1.boundingBox.maxY))
            {
                var22 = var13 - var1.boundingBox.minY + 0.01D;
            }
            else
            {
                var22 = var7 - var1.boundingBox.maxY - 0.01D;
            }
        }

        if (var1.boundingBox.minZ <= var15 && var1.boundingBox.maxZ >= var9)
        {
            var19 = true;

            if (Math.abs(var15 - var1.boundingBox.minZ) < Math.abs(var9 - var1.boundingBox.maxZ))
            {
                var24 = var15 - var1.boundingBox.minZ + 0.01D;
            }
            else
            {
                var24 = var9 - var1.boundingBox.maxZ - 0.01D;
            }
        }

        double var26 = var1.posX;
        double var28 = var1.posY;
        double var30 = var1.posZ;

        if (var17 && Math.abs(var20) < 0.2D && (!var18 || Math.abs(var20) < Math.abs(var22)) && (!var19 || Math.abs(var20) < Math.abs(var24)))
        {
            var26 += var20;
        }
        else if (var18 && Math.abs(var22) < 0.2D && (!var19 || Math.abs(var22) < Math.abs(var24)))
        {
            var28 += var22;
        }
        else if (var19 && Math.abs(var24) < 0.2D)
        {
            var30 += var24;
        }

        var1.setPosition(var26, var28, var30);

        if (var1 instanceof EntityPlayerMP)
        {
            EntityPlayerMP var32 = (EntityPlayerMP)var1;
            FCUtilsWorld.SendPacketToPlayer(var32.playerNetServerHandler, new Packet13PlayerLookMove(var26, var28 + 1.6200000047683716D, var28, var30, var32.rotationYaw, var32.rotationPitch, false));
        }
    }

    public static void ServerPositionAllPlayerEntitiesOutsideOfLocation(World var0, int var1, int var2, int var3)
    {
        List var4 = var0.getEntitiesWithinAABB(EntityPlayerMP.class, AxisAlignedBB.getAABBPool().getAABB((double)var1, (double)var2, (double)var3, (double)var1 + 1.0D, (double)var2 + 1.0D, (double)var3 + 1.0D));

        if (var4 != null && var4.size() > 0)
        {
            for (int var5 = 0; var5 < var4.size(); ++var5)
            {
                EntityPlayerMP var6 = (EntityPlayerMP)var4.get(var5);
                ServerPositionPlayerEntityOutsideOfLocation(var0, var6, var1, var2, var3);
            }
        }
    }

    private static void ServerPositionPlayerEntityOutsideOfLocation(World var0, EntityPlayerMP var1, int var2, int var3, int var4)
    {
        double var5 = (double)((float)var2);
        double var7 = (double)((float)var3);
        double var9 = (double)((float)var4);
        double var11 = (double)((float)(var2 + 1));
        double var13 = (double)((float)(var3 + 1));
        double var15 = (double)((float)(var4 + 1));
        boolean var17 = false;
        boolean var18 = false;
        boolean var19 = false;
        double var20 = 0.0D;
        double var22 = 0.0D;
        double var24 = 0.0D;

        if (var1.boundingBox.minX <= var11 && var1.boundingBox.maxX >= var5)
        {
            var17 = true;

            if (Math.abs(var11 - var1.boundingBox.minX) < Math.abs(var5 - var1.boundingBox.maxX))
            {
                var20 = var11 - var1.boundingBox.minX + 0.01D;
            }
            else
            {
                var20 = var5 - var1.boundingBox.maxX - 0.01D;
            }
        }

        if (var1.boundingBox.minY <= var13 && var1.boundingBox.maxY >= var7)
        {
            var18 = true;

            if (Math.abs(var13 - var1.boundingBox.minY) < Math.abs(var7 - var1.boundingBox.maxY))
            {
                var22 = var13 - var1.boundingBox.minY + 0.01D;
            }
            else
            {
                var22 = var7 - var1.boundingBox.maxY - 0.01D;
            }
        }

        if (var1.boundingBox.minZ <= var15 && var1.boundingBox.maxZ >= var9)
        {
            var19 = true;

            if (Math.abs(var15 - var1.boundingBox.minZ) < Math.abs(var9 - var1.boundingBox.maxZ))
            {
                var24 = var15 - var1.boundingBox.minZ + 0.01D;
            }
            else
            {
                var24 = var9 - var1.boundingBox.maxZ - 0.01D;
            }
        }

        double var26 = var1.posX;
        double var28 = var1.posY;
        double var30 = var1.posZ;

        if (var17 && Math.abs(var20) < 0.2D && (!var18 || Math.abs(var20) < Math.abs(var22)) && (!var19 || Math.abs(var20) < Math.abs(var24)))
        {
            var26 += var20;
        }
        else if (var18 && Math.abs(var22) < 0.2D && (!var19 || Math.abs(var22) < Math.abs(var24)))
        {
            var28 += var22;
        }
        else if (var19 && Math.abs(var24) < 0.2D)
        {
            var30 += var24;
        }

        var1.setPosition(var26, var28, var30);
        FCUtilsWorld.SendPacketToPlayer(var1.playerNetServerHandler, new Packet13PlayerLookMove(var26, var28 + 1.6200000047683716D, var28, var30, var1.rotationYaw, var1.rotationPitch, false));
    }

    public static void PlayPlaceSoundForBlock(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];

        if (var5 != null)
        {
            var0.playSoundEffect((double)((float)var1 + 0.5F), (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), var5.stepSound.getStepSound(), (var5.stepSound.getVolume() + 1.0F) / 2.0F, var5.stepSound.getPitch() * 0.8F);
        }
    }

    public static boolean IsCreatureWearingBreedingHarness(EntityCreature var0)
    {
        if (var0 instanceof EntityAnimal)
        {
            EntityAnimal var1 = (EntityAnimal)var0;
            return var1.getWearingBreedingHarness();
        }
        else
        {
            return false;
        }
    }

    public static boolean StandardRotateAroundJ(Block var0, World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = StandardRotateMetadataAroundJ(var0, var6, var5);

        if (var7 != var6)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int StandardRotateMetadataAroundJ(Block var0, int var1, boolean var2)
    {
        int var3 = var0.GetFacing(var1);
        int var4 = Block.RotateFacingAroundJ(var3, var2);
        var1 = var0.SetFacing(var1, var4);
        return var1;
    }

    public static Vec3 ConvertBlockFacingToVector(int var0)
    {
        Vec3 var1 = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);

        switch (var0)
        {
            case 0:
                var1.yCoord += -1.0D;
                break;

            case 1:
                ++var1.yCoord;
                break;

            case 2:
                --var1.zCoord;
                break;

            case 3:
                ++var1.zCoord;
                break;

            case 4:
                --var1.xCoord;
                break;

            default:
                ++var1.xCoord;
        }

        return var1;
    }

    public static void PlaceNonPersistantWater(World var0, int var1, int var2, int var3)
    {
        var0.setBlockAndMetadataWithNotify(var1, var2, var3, Block.waterMoving.blockID, 1);
        FlowWaterIntoBlockIfPossible(var0, var1 + 1, var2, var3, 2);
        FlowWaterIntoBlockIfPossible(var0, var1 - 1, var2, var3, 2);
        FlowWaterIntoBlockIfPossible(var0, var1, var2, var3 + 1, 2);
        FlowWaterIntoBlockIfPossible(var0, var1, var2, var3 - 1, 2);
    }

    public static void PlaceNonPersistantWaterMinorSpread(World var0, int var1, int var2, int var3)
    {
        byte var4 = 5;
        var0.setBlockAndMetadataWithNotify(var1, var2, var3, Block.waterMoving.blockID, var4);
        FlowWaterIntoBlockSafe(var0, var1 + 1, var2, var3, var4 + 1);
        FlowWaterIntoBlockSafe(var0, var1 - 1, var2, var3, var4 + 1);
        FlowWaterIntoBlockSafe(var0, var1, var2, var3 + 1, var4 + 1);
        FlowWaterIntoBlockSafe(var0, var1, var2, var3 - 1, var4 + 1);
    }

    public static void FlowWaterIntoBlockSafe(World var0, int var1, int var2, int var3, int var4)
    {
        if (var0.isAirBlock(var1, var2, var3))
        {
            FlowWaterIntoBlockIfPossible(var0, var1, var2, var3, var4);
        }
    }

    public static void FlowWaterIntoBlockIfPossible(World var0, int var1, int var2, int var3, int var4)
    {
        if (CanWaterDisplaceBlock(var0, var1, var2, var3))
        {
            int var5 = var0.getBlockId(var1, var2, var3);

            if (var5 > 0)
            {
                Block.blocksList[var5].OnFluidFlowIntoBlock(var0, var1, var2, var3, Block.waterMoving);
            }

            var0.setBlockAndMetadataWithNotify(var1, var2, var3, Block.waterMoving.blockID, var4);
        }
    }

    public static boolean CanWaterDisplaceBlock(World var0, int var1, int var2, int var3)
    {
        Material var4 = var0.getBlockMaterial(var1, var2, var3);

        if (var4 == Block.waterMoving.blockMaterial)
        {
            return false;
        }
        else if (var4 == Material.lava)
        {
            return false;
        }
        else
        {
            Block var5 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
            return var5 == null || !var5.GetPreventsFluidFlow(var0, var1, var2, var3, Block.waterMoving);
        }
    }

    public static MovingObjectPosition GetMovingObjectPositionFromPlayerHitWaterAndLava(World var0, EntityPlayer var1, boolean var2)
    {
        float var3 = 1.0F;
        float var4 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var3;
        float var5 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var3;
        double var6 = var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var3;
        double var8 = var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var3 + 1.62D - (double)var1.yOffset;
        double var10 = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var3;
        Vec3 var12 = var0.getWorldVec3Pool().getVecFromPool(var6, var8, var10);
        float var13 = MathHelper.cos(-var5 * 0.01745329F - (float)Math.PI);
        float var14 = MathHelper.sin(-var5 * 0.01745329F - (float)Math.PI);
        float var15 = -MathHelper.cos(-var4 * 0.01745329F);
        float var16 = MathHelper.sin(-var4 * 0.01745329F);
        float var17 = var14 * var15;
        float var19 = var13 * var15;
        double var20 = 5.0D;
        Vec3 var22 = var12.addVector((double)var17 * var20, (double)var16 * var20, (double)var19 * var20);
        return FCUtilsWorld.RayTraceBlocksAlwaysHitWaterAndLava(var0, var12, var22, var2, !var2);
    }

    public static MovingObjectPosition GetMovingObjectPositionFromPlayerHitWaterAndLavaAndFire(World var0, EntityPlayer var1, boolean var2)
    {
        float var3 = 1.0F;
        float var4 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var3;
        float var5 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var3;
        double var6 = var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var3;
        double var8 = var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var3 + 1.62D - (double)var1.yOffset;
        double var10 = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var3;
        Vec3 var12 = var0.getWorldVec3Pool().getVecFromPool(var6, var8, var10);
        float var13 = MathHelper.cos(-var5 * 0.01745329F - (float)Math.PI);
        float var14 = MathHelper.sin(-var5 * 0.01745329F - (float)Math.PI);
        float var15 = -MathHelper.cos(-var4 * 0.01745329F);
        float var16 = MathHelper.sin(-var4 * 0.01745329F);
        float var17 = var14 * var15;
        float var19 = var13 * var15;
        double var20 = 5.0D;
        Vec3 var22 = var12.addVector((double)var17 * var20, (double)var16 * var20, (double)var19 * var20);
        return FCUtilsWorld.RayTraceBlocksAlwaysHitWaterAndLavaAndFire(var0, var12, var22, var2, !var2);
    }

    public static MovingObjectPosition RayTraceWithBox(World var0, int var1, int var2, int var3, Vec3 var4, Vec3 var5, Vec3 var6, Vec3 var7)
    {
        var6 = var6.addVector((double)(-var1), (double)(-var2), (double)(-var3));
        var7 = var7.addVector((double)(-var1), (double)(-var2), (double)(-var3));
        Vec3 var8 = var6.getIntermediateWithXValue(var7, var4.xCoord);
        Vec3 var9 = var6.getIntermediateWithXValue(var7, var5.xCoord);
        Vec3 var10 = var6.getIntermediateWithYValue(var7, var4.yCoord);
        Vec3 var11 = var6.getIntermediateWithYValue(var7, var5.yCoord);
        Vec3 var12 = var6.getIntermediateWithZValue(var7, var4.zCoord);
        Vec3 var13 = var6.getIntermediateWithZValue(var7, var5.zCoord);

        if (!isVecInsideYZBounds(var8, var4, var5))
        {
            var8 = null;
        }

        if (!isVecInsideYZBounds(var9, var4, var5))
        {
            var9 = null;
        }

        if (!isVecInsideXZBounds(var10, var4, var5))
        {
            var10 = null;
        }

        if (!isVecInsideXZBounds(var11, var4, var5))
        {
            var11 = null;
        }

        if (!isVecInsideXYBounds(var12, var4, var5))
        {
            var12 = null;
        }

        if (!isVecInsideXYBounds(var13, var4, var5))
        {
            var13 = null;
        }

        Vec3 var14 = null;

        if (var8 != null && (var14 == null || var6.squareDistanceTo(var8) < var6.squareDistanceTo(var14)))
        {
            var14 = var8;
        }

        if (var9 != null && (var14 == null || var6.squareDistanceTo(var9) < var6.squareDistanceTo(var14)))
        {
            var14 = var9;
        }

        if (var10 != null && (var14 == null || var6.squareDistanceTo(var10) < var6.squareDistanceTo(var14)))
        {
            var14 = var10;
        }

        if (var11 != null && (var14 == null || var6.squareDistanceTo(var11) < var6.squareDistanceTo(var14)))
        {
            var14 = var11;
        }

        if (var12 != null && (var14 == null || var6.squareDistanceTo(var12) < var6.squareDistanceTo(var14)))
        {
            var14 = var12;
        }

        if (var13 != null && (var14 == null || var6.squareDistanceTo(var13) < var6.squareDistanceTo(var14)))
        {
            var14 = var13;
        }

        if (var14 == null)
        {
            return null;
        }
        else
        {
            byte var15 = -1;

            if (var14 == var8)
            {
                var15 = 4;
            }

            if (var14 == var9)
            {
                var15 = 5;
            }

            if (var14 == var10)
            {
                var15 = 0;
            }

            if (var14 == var11)
            {
                var15 = 1;
            }

            if (var14 == var12)
            {
                var15 = 2;
            }

            if (var14 == var13)
            {
                var15 = 3;
            }

            return new MovingObjectPosition(var1, var2, var3, var15, var14.addVector((double)var1, (double)var2, (double)var3));
        }
    }

    public static boolean isVecInsideYZBounds(Vec3 var0, Vec3 var1, Vec3 var2)
    {
        return var0 == null ? false : var0.yCoord >= var1.yCoord && var0.yCoord <= var2.yCoord && var0.zCoord >= var1.zCoord && var0.zCoord <= var2.zCoord;
    }

    public static boolean isVecInsideXZBounds(Vec3 var0, Vec3 var1, Vec3 var2)
    {
        return var0 == null ? false : var0.xCoord >= var1.xCoord && var0.xCoord <= var2.xCoord && var0.zCoord >= var1.zCoord && var0.zCoord <= var2.zCoord;
    }

    public static boolean isVecInsideXYBounds(Vec3 var0, Vec3 var1, Vec3 var2)
    {
        return var0 == null ? false : var0.xCoord >= var1.xCoord && var0.xCoord <= var2.xCoord && var0.yCoord >= var1.yCoord && var0.yCoord <= var2.yCoord;
    }

    public static boolean DoesWaterHaveValidSource(World var0, int var1, int var2, int var3, int var4)
    {
        return DoesWaterHaveValidSourceRecursive(var0, var1, var2, var3, var1, var2, var3, var4);
    }

    private static boolean DoesWaterHaveValidSourceRecursive(World var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        if (var7 <= 0)
        {
            return true;
        }
        else if (!var0.checkChunksExist(var1 - 1, 64, var3 - 1, var1 + 1, 64, var3 + 1))
        {
            return true;
        }
        else
        {
            int var8 = var0.getBlockMetadata(var1, var2, var3);

            if (var8 == 0)
            {
                return true;
            }
            else
            {
                int var12;
                int var16;

                if (var8 >= 8)
                {
                    FCUtilsBlockPos var15 = new FCUtilsBlockPos(var1, var2, var3);
                    var15.AddFacingAsOffset(1);
                    var16 = var0.getBlockId(var15.i, var15.j, var15.k);

                    if (var16 == Block.waterMoving.blockID || var16 == Block.waterStill.blockID)
                    {
                        if (DoesWaterHaveValidSourceWithSourceCheck(var0, var15.i, var15.j, var15.k, var4, var5, var6, var7 - 1))
                        {
                            return true;
                        }

                        for (int var17 = 2; var17 < 6; ++var17)
                        {
                            var15 = new FCUtilsBlockPos(var1, var2, var3);
                            var15.AddFacingAsOffset(var17);
                            var16 = var0.getBlockId(var15.i, var15.j, var15.k);

                            if (var16 == Block.waterMoving.blockID || var16 == Block.waterStill.blockID)
                            {
                                var12 = var0.getBlockMetadata(var15.i, var15.j, var15.k);
                                boolean var19 = false;

                                if (var12 == 0)
                                {
                                    return true;
                                }
                            }
                        }
                    }

                    return false;
                }
                else
                {
                    int var9 = var0.getBlockId(var1, var2 - 1, var3);
                    FCUtilsBlockPos var11;

                    if (FCBetterThanWolves.m_bBlocksPotentialFluidSources[var9])
                    {
                        FCIBlockFluidSource var10 = (FCIBlockFluidSource)((FCIBlockFluidSource)Block.blocksList[var9]);

                        if (var10.IsSourceToFluidBlockAtFacing(var0, var1, var2 - 1, var3, 1) >= 0)
                        {
                            if (var9 != FCBetterThanWolves.fcBlockScrewPump.blockID)
                            {
                                return true;
                            }

                            if (var2 - 1 < var5)
                            {
                                return true;
                            }

                            var11 = new FCUtilsBlockPos(var1, var2 - 1, var3);
                            var12 = ((FCBlockScrewPump)FCBetterThanWolves.fcBlockScrewPump).GetFacing(var0, var11.i, var11.j, var11.k);
                            var11.AddFacingAsOffset(var12);

                            if (DoesWaterHaveValidSourceWithSourceCheck(var0, var11.i, var11.j, var11.k, var4, var5, var6, var7 - 1))
                            {
                                return true;
                            }
                        }
                    }

                    for (var16 = 2; var16 < 6; ++var16)
                    {
                        var11 = new FCUtilsBlockPos(var1, var2, var3);
                        var11.AddFacingAsOffset(var16);
                        var12 = var0.getBlockId(var11.i, var11.j, var11.k);

                        if (var12 != Block.waterMoving.blockID && var12 != Block.waterStill.blockID)
                        {
                            if (FCBetterThanWolves.m_bBlocksPotentialFluidSources[var12])
                            {
                                FCIBlockFluidSource var18 = (FCIBlockFluidSource)((FCIBlockFluidSource)Block.blocksList[var12]);

                                if (var18.IsSourceToFluidBlockAtFacing(var0, var11.i, var11.j, var11.k, Block.GetOppositeFacing(var16)) >= 0)
                                {
                                    return true;
                                }
                            }
                        }
                        else
                        {
                            int var13 = var0.getBlockMetadata(var11.i, var11.j, var11.k);
                            boolean var14 = false;

                            if (var13 >= 8)
                            {
                                var14 = true;
                            }
                            else if (var13 < var8)
                            {
                                var14 = true;
                            }

                            if (var14 && DoesWaterHaveValidSourceWithSourceCheck(var0, var11.i, var11.j, var11.k, var4, var5, var6, var7 - 1))
                            {
                                return true;
                            }
                        }
                    }

                    return false;
                }
            }
        }
    }

    private static boolean DoesWaterHaveValidSourceWithSourceCheck(World var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        return var1 == var4 && var2 == var5 && var3 == var6 ? false : DoesWaterHaveValidSourceRecursive(var0, var1, var2, var3, var4, var5, var6, var7);
    }
}
