package net.minecraft.src;

public class FCBlockSign extends BlockSign
{
    protected final boolean m_bFreeStanding;

    protected FCBlockSign(int var1, boolean var2)
    {
        super(var1, TileEntitySign.class, var2);
        this.m_bFreeStanding = var2;
        this.setHardness(1.0F);
        this.SetBuoyant();
        this.InitBlockBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("sign");
        this.disableStats();
    }

    public boolean DoesBlockHopperEject(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
        return true;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (!this.m_bFreeStanding)
        {
            int var5 = var1.getBlockMetadata(var2, var3, var4);
            float var6 = 0.28125F;
            float var7 = 0.78125F;
            float var8 = 0.0F;
            float var9 = 1.0F;
            float var10 = 0.125F;
            return var5 == 2 ? AxisAlignedBB.getAABBPool().getAABB((double)var8, (double)var6, (double)(1.0F - var10), (double)var9, (double)var7, 1.0D) : (var5 == 3 ? AxisAlignedBB.getAABBPool().getAABB((double)var8, (double)var6, 0.0D, (double)var9, (double)var7, (double)var10) : (var5 == 4 ? AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var10), (double)var6, (double)var8, 1.0D, (double)var7, (double)var9) : (var5 == 5 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, (double)var6, (double)var8, (double)var10, (double)var7, (double)var9) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D))));
        }
        else
        {
            return super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
        }
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return false;
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }
}
