package net.minecraft.src;

import java.util.Random;

public class FCBlockFireStoked extends FCBlockFire
{
    private final int m_iTickRate = 42;
    private Icon[] fireTextureArray;

    protected FCBlockFireStoked(int var1)
    {
        super(var1);
        this.setHardness(0.0F);
        this.setLightValue(1.0F);
        this.SetFireProperties(60, 0);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockStokedFire");
        this.disableStats();
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 42;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.ValidateState(var1, var2, var3, var4))
        {
            if (var1.getBlockId(var2, var3 + 1, var4) == Block.brick.blockID)
            {
                var1.setBlockWithNotify(var2, var3 + 1, var4, FCBetterThanWolves.fcKiln.blockID);
            }

            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 < 15)
            {
                ++var6;
                var1.setBlockMetadata(var2, var3, var4, var6);
            }

            this.TryToDestroyBlockWithFire(var1, var2 + 1, var3, var4, 300, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2 - 1, var3, var4, 300, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3 - 1, var4, 250, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3 + 1, var4, 250, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3, var4 - 1, 300, var5, 0);
            this.TryToDestroyBlockWithFire(var1, var2, var3, var4 + 1, 300, var5, 0);
            CheckForFireSpreadFromLocation(var1, var2, var3, var4, var5, 0);

            if (var6 >= 3)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.fire.blockID, 0);
            }
            else
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var1.rand.nextInt(10));
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.setBlockMetadata(var2, var3, var4, 0);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) * 4);
        }
    }

    public boolean DoesInfiniteBurnToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 1;
    }

    public boolean ValidateState(World var1, int var2, int var3, int var4)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
            return false;
        }
        else if (var1.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcBBQ.blockID)
        {
            if (!FCBetterThanWolves.fcBBQ.IsLit(var1, var2, var3 - 1, var4))
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.fire.blockID, 0);
            return false;
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.fireTextureArray = new Icon[] {var1.registerIcon("fcBlockFireStokedStub_0", new FCClientTextureFireStoked("fcBlockFireStokedStub_0", 0)), var1.registerIcon("fcBlockFireStokedStub_1", new FCClientTextureFireStoked("fcBlockFireStokedStub_1", 1))};
    }

    public Icon func_94438_c(int var1)
    {
        return this.fireTextureArray[var1];
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.fireTextureArray[0];
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        Tessellator var6 = Tessellator.instance;
        Icon var7 = this.func_94438_c(0);
        Icon var8 = this.func_94438_c(1);

        if ((var2 + var4 & 1) != 0)
        {
            var7 = this.func_94438_c(1);
            var8 = this.func_94438_c(0);
        }

        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(this.getMixedBrightnessForBlock(var5, var2, var3, var4));
        double var10 = (double)var7.getMinU();
        double var12 = (double)var7.getMinV();
        double var14 = (double)var7.getMaxU();
        double var16 = (double)var7.getMaxV();
        float var18 = 1.0F;
        double var19 = (double)var2 + 0.5D + 0.2D;
        double var21 = (double)var2 + 0.5D - 0.2D;
        double var23 = (double)var4 + 0.5D + 0.2D;
        double var25 = (double)var4 + 0.5D - 0.2D;
        double var27 = (double)var2 + 0.5D - 0.3D;
        double var29 = (double)var2 + 0.5D + 0.3D;
        double var31 = (double)var4 + 0.5D - 0.3D;
        double var33 = (double)var4 + 0.5D + 0.3D;
        var6.addVertexWithUV(var27, (double)((float)var3 + var18), (double)(var4 + 1), var14, var12);
        var6.addVertexWithUV(var19, (double)(var3 + 0), (double)(var4 + 1), var14, var16);
        var6.addVertexWithUV(var19, (double)(var3 + 0), (double)(var4 + 0), var10, var16);
        var6.addVertexWithUV(var27, (double)((float)var3 + var18), (double)(var4 + 0), var10, var12);
        var6.addVertexWithUV(var29, (double)((float)var3 + var18), (double)(var4 + 0), var14, var12);
        var6.addVertexWithUV(var21, (double)(var3 + 0), (double)(var4 + 0), var14, var16);
        var6.addVertexWithUV(var21, (double)(var3 + 0), (double)(var4 + 1), var10, var16);
        var6.addVertexWithUV(var29, (double)((float)var3 + var18), (double)(var4 + 1), var10, var12);
        var10 = (double)var8.getMinU();
        var12 = (double)var8.getMinV();
        var14 = (double)var8.getMaxU();
        var16 = (double)var8.getMaxV();
        var6.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var18), var33, var14, var12);
        var6.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var25, var14, var16);
        var6.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var25, var10, var16);
        var6.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var18), var33, var10, var12);
        var6.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var18), var31, var14, var12);
        var6.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var23, var14, var16);
        var6.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var23, var10, var16);
        var6.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var18), var31, var10, var12);
        var19 = (double)var2 + 0.5D - 0.5D;
        var21 = (double)var2 + 0.5D + 0.5D;
        var23 = (double)var4 + 0.5D - 0.5D;
        var25 = (double)var4 + 0.5D + 0.5D;
        var27 = (double)var2 + 0.5D - 0.5D;
        var29 = (double)var2 + 0.5D + 0.5D;
        var31 = (double)var4 + 0.5D - 0.5D;
        var33 = (double)var4 + 0.5D + 0.5D;
        var6.addVertexWithUV(var27, (double)((float)var3 + var18), (double)(var4 + 0), var10, var12);
        var6.addVertexWithUV(var19, (double)(var3 + 0), (double)(var4 + 0), var10, var16);
        var6.addVertexWithUV(var19, (double)(var3 + 0), (double)(var4 + 1), var14, var16);
        var6.addVertexWithUV(var27, (double)((float)var3 + var18), (double)(var4 + 1), var14, var12);
        var6.addVertexWithUV(var29, (double)((float)var3 + var18), (double)(var4 + 1), var10, var12);
        var6.addVertexWithUV(var21, (double)(var3 + 0), (double)(var4 + 1), var10, var16);
        var6.addVertexWithUV(var21, (double)(var3 + 0), (double)(var4 + 0), var14, var16);
        var6.addVertexWithUV(var29, (double)((float)var3 + var18), (double)(var4 + 0), var14, var12);
        var10 = (double)var7.getMinU();
        var12 = (double)var7.getMinV();
        var14 = (double)var7.getMaxU();
        var16 = (double)var7.getMaxV();
        var6.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var18), var33, var10, var12);
        var6.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var25, var10, var16);
        var6.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var25, var14, var16);
        var6.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var18), var33, var14, var12);
        var6.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var18), var31, var10, var12);
        var6.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var23, var10, var16);
        var6.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var23, var14, var16);
        var6.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var18), var31, var14, var12);
        return true;
    }
}
