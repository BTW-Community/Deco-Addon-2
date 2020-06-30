package net.minecraft.src;

public class FCBlockCompanionCube extends Block
{
    public static final int m_iNumSubtypes = 16;

    public FCBlockCompanionCube(int var1)
    {
        super(var1, Material.cloth);
        this.setHardness(0.4F);
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(FCEnumFlammability.CLOTH);
        this.setStepSound(Block.soundClothFootstep);
        this.setUnlocalizedName("fcBlockCompanionCube");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return !this.GetIsSlabFromMetadata(var9) ? this.SetFacing(var9, Block.GetOppositeFacing(var5)) : (var5 != 0 && (var5 == 1 || (double)var7 <= 0.5D) ? var9 : this.SetFacing(var9, 1));
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return (var1 & 8) > 0 ? 1 : 0;
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

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsSlab(var1, var2, var3, var4) ? (!this.GetIsUpsideDownSlab(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D)) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        if (!this.GetIsSlab(var1, var2, var3, var4))
        {
            SpawnHearts(var1, var2, var3, var4);
            int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacingReversed(var5);
            this.SetFacing(var1, var2, var3, var4, var7);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!this.GetIsSlab(var1, var2, var3, var4))
        {
            var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "mob.wolf.whine", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);
        }
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (!this.GetIsSlab(var1, var2, var3, var4))
        {
            var1.playAuxSFX(2242, var2, var3, var4, 0);
        }

        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        int var8 = ((FCBlockSaw)FCBetterThanWolves.fcSaw).GetFacing(var1, var5, var6, var7);

        if (!this.GetIsSlab(var1, var2, var3, var4))
        {
            if (var8 != 0 && var8 != 1)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcCompanionCube.blockID, 1);
                this.SetIsSlab(var1, var2, var3, var4, true);
                this.SetFacing(var1, var2, var3, var4, 0);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
            else
            {
                for (int var9 = 0; var9 < 2; ++var9)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcCompanionCube.blockID, 1);
                }

                var1.setBlockWithNotify(var2, var3, var4, 0);
            }

            FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4);
            var10.AddFacingAsOffset(Block.GetOppositeFacing(var8));
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.wolf.hurt", 5.0F, (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.2F + 1.0F);
        }
        else
        {
            if (var8 != 0 && var8 != 1)
            {
                return false;
            }

            FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcCompanionCube.blockID, 1);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }

        return true;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.GetIsSlab(var1, var2, var3, var4) ? (this.GetIsUpsideDownSlab(var1, var2, var3, var4) ? var5 == 1 : var5 == 0) : true;
    }

    public int GetFacing(int var1)
    {
        return var1 & -9;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= 8;
        var1 |= var2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsSlab(var1, var2, var3, var4);
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsSlab(var1, var2, var3, var4);
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsSlab(var1, var2, var3, var4);
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (!this.GetIsSlab(var1, var2, var3, var4) && super.RotateAroundJAxis(var1, var2, var3, var4, var5))
        {
            if (var1.rand.nextInt(12) == 0)
            {
                var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "mob.wolf.whine", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean IsNormalCube(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsSlab(var1, var2, var3, var4);
    }

    public boolean GetIsSlab(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsSlabFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public void SetIsSlab(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean GetIsSlabFromMetadata(int var1)
    {
        return (var1 & 8) > 0;
    }

    public boolean GetIsUpsideDownSlab(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsUpsideDownSlabFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsUpsideDownSlabFromMetadata(int var1)
    {
        return this.GetIsSlabFromMetadata(var1) ? this.GetFacing(var1) == 1 : false;
    }

    static void SpawnHearts(World var0, int var1, int var2, int var3)
    {
        String var4 = "heart";

        for (int var5 = 0; var5 < 7; ++var5)
        {
            double var6 = var0.rand.nextGaussian() * 0.02D;
            double var8 = var0.rand.nextGaussian() * 0.02D;
            double var10 = var0.rand.nextGaussian() * 0.02D;
            var0.spawnParticle(var4, (double)var1 + (double)var0.rand.nextFloat(), (double)(var2 + 1) + (double)var0.rand.nextFloat(), (double)var3 + (double)var0.rand.nextFloat(), var6, var8, var10);
        }
    }
}
