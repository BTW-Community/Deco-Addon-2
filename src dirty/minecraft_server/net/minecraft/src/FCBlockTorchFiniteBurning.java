package net.minecraft.src;

public class FCBlockTorchFiniteBurning extends FCBlockTorchBaseBurning implements ITileEntityProvider
{
    private boolean m_bIsBeingCrushed = false;

    protected FCBlockTorchFiniteBurning(int var1)
    {
        super(var1);
        this.isBlockContainer = true;
        this.setLightValue(0.875F);
        this.setUnlocalizedName("fcBlockTorchFiniteBurning");
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityTorchFinite();
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        super.breakBlock(var1, var2, var3, var4, var5, var6);

        if (!var1.isRemote)
        {
            FCTileEntityTorchFinite var7 = (FCTileEntityTorchFinite)var1.getBlockTileEntity(var2, var3, var4);
            int var8 = var7.m_iBurnTimeCountdown;

            if (var8 > 0 && !this.m_bIsBeingCrushed)
            {
                int var9 = (int)(0.0013333333F * (float)(24000 - var7.m_iBurnTimeCountdown));
                var9 = MathHelper.clamp_int(var9, 1, 31);
                ItemStack var10 = new ItemStack(this.blockID, 1, var9);
                long var11 = FCUtilsWorld.GetOverworldTimeServerOnly() + (long)var8;
                var10.setTagCompound(new NBTTagCompound());
                var10.getTagCompound().setLong("outTime", var11);
                this.dropBlockAsItem_do(var1, var2, var3, var4, var10);
            }
        }

        this.m_bIsBeingCrushed = false;
        var1.removeBlockTileEntity(var2, var3, var4);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        TileEntity var7 = var1.getBlockTileEntity(var2, var3, var4);

        if (var7 != null && var7 instanceof FCTileEntityTorchFinite && var6.hasTagCompound() && var6.getTagCompound().hasKey("outTime"))
        {
            long var8 = var6.getTagCompound().getLong("outTime");
            int var10 = (int)(var8 - FCUtilsWorld.GetOverworldTimeServerOnly());

            if (var10 < 0 || var10 > 24000)
            {
                var10 = 24000;
            }

            ((FCTileEntityTorchFinite)var7).m_iBurnTimeCountdown = var10;

            if (var10 < 600)
            {
                this.SetIsSputtering(var1, var2, var3, var4, true);
            }
        }
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        this.m_bIsBeingCrushed = true;
    }

    public void OnFluidFlowIntoBlock(World var1, int var2, int var3, int var4, BlockFluid var5)
    {
        var1.playAuxSFX(2227, var2, var3, var4, 0);
        this.m_bIsBeingCrushed = true;
    }

    public boolean OnRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        var1.setBlockToAir(var2, var3, var4);
        return false;
    }

    public void SetIsSputtering(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = SetIsSputtering(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public static int SetIsSputtering(int var0, boolean var1)
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

    public boolean GetIsSputtering(IBlockAccess var1, int var2, int var3, int var4)
    {
        return GetIsSputtering(var1.getBlockMetadata(var2, var3, var4));
    }

    public static boolean GetIsSputtering(int var0)
    {
        return (var0 & 8) != 0;
    }
}
