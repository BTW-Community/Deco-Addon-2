package net.minecraft.src;

import java.util.Random;

public class FCBlockWeeds extends FCBlockPlants
{
    public static final double m_dWeedsBoundsWidth = 0.75D;
    public static final double m_dWeedsBoundsHalfWidth = 0.375D;
    private Icon[] m_weedIconArray = null;

    protected FCBlockWeeds(int var1)
    {
        super(var1, Material.plants);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.CROPS);
        this.InitBlockBounds(-0.25D, 0.0D, -0.25D, 1.25D, 0.5D, 1.25D);
        this.setStepSound(soundGrassFootstep);
        this.setTickRandomly(true);
        this.disableStats();
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return -1;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);

        if (!this.canBlockStay(var1, var2, var3, var4))
        {
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5 = 0.0F;
        Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var6 != null)
        {
            var5 = var6.GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4);
        }

        int var7 = this.GetWeedsGrowthLevel(var1, var2, var3, var4);
        double var8 = GetWeedsBoundsHeight(var7);
        return AxisAlignedBB.getAABBPool().getAABB(0.125D, (double)(0.0F + var5), 0.125D, 0.875D, var8 + (double)var5, 0.875D);
    }

    public void RemoveWeeds(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var5 != null)
        {
            var5.RemoveWeeds(var1, var2, var3 - 1, var4);
        }

        var1.setBlockToAir(var2, var3, var4);
    }

    public boolean CanWeedsGrowInBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        var1.getBlockId(var2, var3, var4);
        return var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcBlockFarmland.blockID || var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcBlockFarmlandFertilized.blockID;
    }

    public static double GetWeedsBoundsHeight(int var0)
    {
        return (double)((var0 >> 1) + 1) / 8.0D;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.m_weedIconArray = new Icon[8];
        this.m_weedIconArray[0] = this.m_weedIconArray[1] = var1.registerIcon("fcBlockWeeds_0");
        this.m_weedIconArray[2] = this.m_weedIconArray[3] = var1.registerIcon("fcBlockWeeds_1");
        this.m_weedIconArray[4] = this.m_weedIconArray[5] = var1.registerIcon("fcBlockWeeds_2");
        this.m_weedIconArray[6] = this.m_weedIconArray[7] = var1.registerIcon("fcBlockWeeds_3");
        this.blockIcon = this.m_weedIconArray[7];
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        this.RenderWeeds(this, var1, var2, var3, var4);
        return true;
    }

    public void RenderWeeds(Block var1, RenderBlocks var2, int var3, int var4, int var5)
    {
        int var6 = var1.GetWeedsGrowthLevel(var2.blockAccess, var3, var4, var5);

        if (var6 > 0)
        {
            double var7 = 0.0D;
            Block var9 = Block.blocksList[var2.blockAccess.getBlockId(var3, var4 - 1, var5)];

            if (var9 != null)
            {
                var7 = (double)var9.GroundCoverRestingOnVisualOffset(var2.blockAccess, var3, var4 - 1, var5);
            }

            Tessellator var10 = Tessellator.instance;
            var10.setBrightness(var1.getMixedBrightnessForBlock(var2.blockAccess, var3, var4, var5));
            var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            var6 = MathHelper.clamp_int(var6, 0, 7);
            var1.RenderCrossHatch(var2, var3, var4, var5, this.m_weedIconArray[var6], 0.125D, var7);
        }
    }
}
