package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockNetherGrowth extends Block
{
    private static final float m_fBlockHardness = 0.2F;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconTopGrown;

    protected FCBlockNetherGrowth(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherGrowth);
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn(true);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("fcBlockGroth");
        this.setTickRandomly(true);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        int var5 = var1.getBlockId(var2, var3 - 1, var4);

        if (var5 == Block.netherrack.blockID)
        {
            var1.setBlockAndMetadata(var2, var3 - 1, var4, FCBetterThanWolves.fcAestheticOpaque.blockID, 8);
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = this.GetHeightLevel(var1, var2, var3, var4);

        if (var7 == 7)
        {
            this.ReleaseSpores(var1, var2, var3, var4);
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
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
        double var5 = (double)(this.GetHeightLevel(var1, var2, var3, var4) + 1) / 16.0D;
        return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, var5, 1.0D);
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 1;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.playAuxSFX(2001, var2, var3, var4, this.blockID + (var1.getBlockMetadata(var2, var3, var4) << 12));
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetHeightLevel(var1, var2, var3, var4);
        int var7 = var1.getBlockId(var2, var3 - 1, var4);
        boolean var8 = false;
        int var9;

        if (var7 == Block.netherrack.blockID)
        {
            var1.setBlockAndMetadata(var2, var3 - 1, var4, FCBetterThanWolves.fcAestheticOpaque.blockID, 8);
            var8 = true;
        }
        else if (var7 == FCBetterThanWolves.fcAestheticOpaque.blockID)
        {
            var9 = var1.getBlockMetadata(var2, var3 - 1, var4);

            if (var9 == 8)
            {
                var8 = true;
            }
        }

        if (var6 < 7)
        {
            boolean var11 = false;

            if (var8)
            {
                var11 = true;
            }
            else if (this.GetMaxHeightOfNeighbors(var1, var2, var3, var4) > var6 + 1)
            {
                var11 = true;
            }

            if (var11)
            {
                ++var6;
                this.SetHeightLevel(var1, var2, var3, var4, var6);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
        }

        if (var6 >= 1)
        {
            var9 = var5.nextInt(4) + 2;
            FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4);
            var10.AddFacingAsOffset(var9);

            if (this.IsBlockOpenToSpread(var1, var10.i, var10.j, var10.k))
            {
                if (var1.doesBlockHaveSolidTopSurface(var10.i, var10.j - 1, var10.k))
                {
                    this.SpreadToBlock(var1, var10.i, var10.j, var10.k);
                }
                else if (var8)
                {
                    --var10.j;

                    if (this.IsBlockOpenToSpread(var1, var10.i, var10.j, var10.k) && var1.doesBlockHaveSolidTopSurface(var10.i, var10.j - 1, var10.k))
                    {
                        this.SpreadToBlock(var1, var10.i, var10.j, var10.k);
                    }
                }
            }
            else if (var1.isAirBlock(var2, var3 + 1, var4) && var1.getBlockId(var10.i, var10.j, var10.k) == Block.netherrack.blockID)
            {
                ++var10.j;

                if (this.IsBlockOpenToSpread(var1, var10.i, var10.j, var10.k))
                {
                    this.SpreadToBlock(var1, var10.i, var10.j, var10.k);
                }
            }
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var5.isDead && !var1.isRemote)
        {
            int var6 = this.GetHeightLevel(var1, var2, var3, var4);

            if (var6 >= 7)
            {
                if (var5 instanceof EntityLiving)
                {
                    boolean var7 = true;

                    if (var5 instanceof EntityPlayer)
                    {
                        EntityPlayer var8 = (EntityPlayer)var5;

                        if (var8.IsWearingSoulforgedBoots())
                        {
                            var7 = false;
                        }
                    }

                    if (var7 && var5.attackEntityFrom(FCDamageSourceCustom.m_DamageSourceGroth, 2))
                    {
                        var5.isAirBorne = true;
                        var5.motionY += 0.84D;
                        var1.playAuxSFX(2225, var2, var3, var4, 0);
                    }
                }
                else if (var5 instanceof EntityItem)
                {
                    EntityItem var9 = (EntityItem)var5;

                    if (var9.delayBeforeCanPickup <= 0 && (var9.getEntityItem().getItem() instanceof ItemFood || var9.getEntityItem().itemID == Block.mushroomRed.blockID || var9.getEntityItem().itemID == Block.mushroomBrown.blockID))
                    {
                        var9.setDead();
                        var1.playAuxSFX(2226, var2, var3, var4, 0);
                    }
                }
            }
        }
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.8F;
    }

    public int GetHeightLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHeightLevelFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetHeightLevelFromMetadata(int var1)
    {
        return var1 & 7;
    }

    public void SetHeightLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 8;
        var6 |= var5;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    private int GetMaxHeightOfNeighbors(World var1, int var2, int var3, int var4)
    {
        int var5 = -1;

        for (int var6 = 2; var6 <= 5; ++var6)
        {
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
            var7.AddFacingAsOffset(var6);

            if (var1.getBlockId(var7.i, var7.j, var7.k) == this.blockID)
            {
                int var8 = this.GetHeightLevel(var1, var7.i, var7.j, var7.k);

                if (var8 > var5)
                {
                    var5 = var8;
                }
            }
        }

        return var5;
    }

    private void SpreadToBlock(World var1, int var2, int var3, int var4)
    {
        if (var1.getBlockId(var2, var3, var4) == Block.fire.blockID)
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
        }
        else if (var1.getBlockId(var2, var3, var4) == Block.mushroomBrown.blockID || var1.getBlockId(var2, var3, var4) == Block.mushroomRed.blockID)
        {
            var1.playAuxSFX(2226, var2, var3, var4, 0);
        }

        if (var1.setBlockWithNotify(var2, var3, var4, this.blockID))
        {
            var1.playAuxSFX(2228, var2, var3, var4, 0);
        }
    }

    private boolean IsBlockOpenToSpread(World var1, int var2, int var3, int var4)
    {
        if (var1.isAirBlock(var2, var3, var4))
        {
            return true;
        }
        else
        {
            int var5 = var1.getBlockId(var2, var3, var4);
            return var5 == Block.fire.blockID || var5 == Block.mushroomRed.blockID || var5 == Block.mushroomBrown.blockID;
        }
    }

    private void ReleaseSpores(World var1, int var2, int var3, int var4)
    {
        var1.playAuxSFX(2224, var2, var3, var4, 0);

        for (int var5 = var2 - 3; var5 <= var2 + 3; ++var5)
        {
            for (int var6 = var3 - 3; var6 <= var3 + 3; ++var6)
            {
                for (int var7 = var4 - 3; var7 <= var4 + 3; ++var7)
                {
                    if ((var5 != var2 || var6 != var3 || var7 != var4) && this.IsBlockOpenToSpread(var1, var5, var6, var7) && var1.doesBlockHaveSolidTopSurface(var5, var6 - 1, var7) && var1.rand.nextInt(2) == 0)
                    {
                        var1.setBlockWithNotify(var5, var6, var7, this.blockID);
                    }
                }
            }
        }

        double var16 = (double)var2 + 0.5D;
        double var17 = (double)var3 + 0.5D;
        double var9 = (double)var4 + 0.5D;
        List var11 = var1.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(var16 - 5.0D, var17 - 5.0D, var9 - 5.0D, var16 + 5.0D, var17 + 5.0D, var9 + 5.0D));

        if (var11 != null && var11.size() > 0)
        {
            for (int var12 = 0; var12 < var11.size(); ++var12)
            {
                EntityLiving var13 = (EntityLiving)var11.get(var12);
                boolean var14 = true;

                if (var13 instanceof EntityPlayer)
                {
                    EntityPlayer var15 = (EntityPlayer)var13;

                    if (var15.IsWearingFullSuitSoulforgedArmor())
                    {
                        var14 = false;
                    }
                }

                if (var14)
                {
                    var13.attackEntityFrom(FCDamageSourceCustom.m_DamageSourceGrothSpores, 4);
                    var13.addPotionEffect(new PotionEffect(Potion.poison.id, 300, 0));
                }
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockGroth_bottom");
        this.blockIcon = var2;
        this.m_IconBySideArray[0] = var2;
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockGroth_top");
        Icon var3 = var1.registerIcon("fcBlockGroth_side");
        this.m_IconBySideArray[2] = var3;
        this.m_IconBySideArray[3] = var3;
        this.m_IconBySideArray[4] = var3;
        this.m_IconBySideArray[5] = var3;
        this.m_IconTopGrown = var1.registerIcon("fcBlockGroth_top_grown");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        if (var1 == 1)
        {
            int var3 = this.GetHeightLevelFromMetadata(var2);

            if (var3 >= 7)
            {
                return this.m_IconTopGrown;
            }
        }

        return this.m_IconBySideArray[var1];
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.randomDisplayTick(var1, var2, var3, var4, var5);

        if (var5.nextInt(10) == 0)
        {
            float var6 = (float)(this.GetHeightLevel(var1, var2, var3, var4) + 1) / 16.0F;
            var1.spawnParticle("townaura", (double)((float)var2 + var5.nextFloat()), (double)((float)var3 + var6 + 0.1F), (double)((float)var4 + var5.nextFloat()), 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 0)
        {
            return false;
        }
        else
        {
            if (var5 != 1)
            {
                int var6 = var1.getBlockId(var2, var3, var4);

                if (var6 == this.blockID)
                {
                    int var7 = this.GetHeightLevel(var1, var2, var3, var4);

                    if (var7 >= 7)
                    {
                        return false;
                    }

                    FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));
                    int var9 = this.GetHeightLevel(var1, var8.i, var8.j, var8.k);

                    if (var7 >= var9)
                    {
                        return false;
                    }
                }
                else
                {
                    Block var10 = Block.blocksList[var6];

                    if (var10 != null)
                    {
                        return var10.ShouldRenderNeighborHalfSlabSide(var1, var2, var3, var4, var5, false);
                    }
                }
            }

            return true;
        }
    }

    public boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return !var6 || this.GetHeightLevel(var1, var2, var3, var4) < 7;
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 != 1;
    }
}
