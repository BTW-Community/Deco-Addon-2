package net.minecraft.src;

public class FCBlockPlanterBase extends Block
{
    protected static final double m_dPlanterWidth = 0.75D;
    protected static final double m_dPlanterHalfWidth = 0.375D;
    protected static final double m_dPlanterBandHeight = 0.3125D;
    protected static final double m_dPlanterBandHalfHeight = 0.15625D;

    protected FCBlockPlanterBase(int var1)
    {
        super(var1, Material.glass);
        this.setHardness(0.6F);
        this.SetPicksEffectiveOn(true);
        this.setTickRandomly(true);
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("fcBlockPlanterSoil");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && var5.isEntityAlive() && var5 instanceof EntityItem)
        {
            EntityItem var6 = (EntityItem)var5;
            ItemStack var7 = var6.getEntityItem();

            if (var7.getItem().itemID == Item.dyePowder.itemID && var7.getItemDamage() == 15 && this.AttemptToApplyFertilizerTo(var1, var2, var3, var4))
            {
                --var7.stackSize;

                if (var7.stackSize <= 0)
                {
                    var6.setDead();
                }

                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.pop", 0.25F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }
        }
    }

    public float GetPlantGrowthOnMultiplier(World var1, int var2, int var3, int var4, Block var5)
    {
        return this.GetIsFertilizedForPlantGrowth(var1, var2, var3, var4) ? 2.0F : 1.0F;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.125D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.875D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.0D, 0.6875D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var7.GetFirstIntersection();
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.0F;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || super.HasCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 1;
    }
}
