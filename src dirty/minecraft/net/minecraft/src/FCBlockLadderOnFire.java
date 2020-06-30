package net.minecraft.src;

import java.util.Random;

public class FCBlockLadderOnFire extends FCBlockLadderBase
{
    private static final int m_iChanceOfLightingLadderAbove = 4;
    private static final int m_iTickRate = 60;
    private static final int m_iTickRateVariance = 30;

    protected FCBlockLadderOnFire(int var1)
    {
        super(var1);
        this.setLightValue(1.0F);
        this.setTickRandomly(true);
        this.setUnlocalizedName("fcBlockLadderOnFire");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 60;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var1.rand.nextInt(30));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsRainingOnLadder(var1, var2, var3, var4))
        {
            this.Extinguish(var1, var2, var3, var4);
        }
        else
        {
            FCBlockFire.CheckForFireSpreadFromLocation(var1, var2, var3, var4, var5, 0);
            int var6 = this.GetBurnCounter(var1, var2, var3, var4);

            if (var6 < 3)
            {
                if (var5.nextInt(4) == 0)
                {
                    this.LightLadderAtLocationIfPresent(var1, var2, var3 + 1, var4);
                }

                this.SetBurnCounter(var1, var2, var3, var4, var6 + 1);
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var5.nextInt(30));
            }
            else
            {
                this.LightLadderAtLocationIfPresent(var1, var2, var3 + 1, var4);
                var1.setBlockToAir(var2, var3, var4);
            }
        }
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    protected void LightLadderAtLocationIfPresent(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == FCBetterThanWolves.fcBlockLadder.blockID)
        {
            FCBetterThanWolves.fcBlockLadder.SetOnFireDirectly(var1, var2, var3, var4);
        }
    }

    protected void Extinguish(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLadder.SetFacing(0, this.GetFacing(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLadder.blockID, var5);
    }

    protected boolean IsRainingOnLadder(World var1, int var2, int var3, int var4)
    {
        return var1.IsRainingAtPos(var2, var3, var4);
    }

    protected int GetBurnCounter(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBurnCounter(var1.getBlockMetadata(var2, var3, var4));
    }

    protected int GetBurnCounter(int var1)
    {
        return var1 >> 2 & 3;
    }

    protected void SetBurnCounter(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetBurnCounter(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    protected int SetBurnCounter(int var1, int var2)
    {
        var1 &= -13;
        return var1 | var2 << 2;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        boolean var5 = super.RenderBlock(var1, var2, var3, var4);

        if (var5)
        {
            this.RenderFirePortion(var1, var2, var3, var4);
        }

        return var5;
    }

    private void RenderFirePortion(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetFacing(var5, var2, var3, var4);
        Tessellator var7 = Tessellator.instance;
        Icon var8;

        if ((var2 + var3 + var4 & 1) != 0)
        {
            var8 = Block.fire.func_94438_c(1);
        }
        else
        {
            var8 = Block.fire.func_94438_c(0);
        }

        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(Block.fire.getMixedBrightnessForBlock(var5, var2, var3, var4));
        double var9 = (double)var8.getMinU();
        double var11 = (double)var8.getMinV();
        double var13 = (double)var8.getMaxU();
        double var15 = (double)var8.getMaxV();
        float var17 = 1.4F;
        double var18 = 0.20000000298023224D;
        double var20 = 0.0D;
        float var22 = 0.0625F;

        if ((var2 / 2 + var3 / 2 + var4 / 2 & 1) != 0)
        {
            double var23 = var13;
            var13 = var9;
            var9 = var23;
        }

        if (var6 == 5)
        {
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
        }
        else if (var6 == 4)
        {
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
        }
        else if (var6 == 3)
        {
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var13, var11);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)var4 + var20, var13, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)var4 + var20, var9, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)var4 + var20, var9, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)var4 + var20, var13, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var13, var11);
        }
        else if (var6 == 2)
        {
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)(var4 + 1) - var20, var9, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)(var4 + 1) - var20, var13, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var13, var11);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var13, var11);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)(var4 + 1) - var20, var13, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)(var4 + 1) - var20, var9, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var9, var11);
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var5.nextInt(24) == 0)
        {
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "fire.fire", 1.0F + var5.nextFloat(), var5.nextFloat() * 0.7F + 0.3F, false);
        }

        for (int var6 = 0; var6 < 3; ++var6)
        {
            double var7 = (double)var2 + var5.nextDouble();
            double var9 = (double)var3 + var5.nextDouble() * 0.5D + 0.5D;
            double var11 = (double)var4 + var5.nextDouble();
            var1.spawnParticle("largesmoke", var7, var9, var11, 0.0D, 0.0D, 0.0D);
        }
    }
}
