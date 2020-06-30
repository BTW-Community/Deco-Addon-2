package net.minecraft.src;

import java.util.Random;

public class FCBlockVase extends BlockContainer
{
    public static final float m_fVaseBaseWidth = 0.5F;
    public static final float m_fVaseBaseHalfWidth = 0.25F;
    public static final float m_fVaseBaseHeight = 0.0625F;
    public static final float m_fVaseBodyWidth = 0.625F;
    public static final float m_fVaseBodyHalfWidth = 0.3125F;
    public static final float m_fVaseBodyHeight = 0.375F;
    public static final float m_fVaseNeckBaseWidth = 0.5F;
    public static final float m_fVaseNeckBaseHalfWidth = 0.25F;
    public static final float m_fVaseNeckBaseHeight = 0.0625F;
    public static final float m_fVaseNeckWidth = 0.25F;
    public static final float m_fVaseNeckHalfWidth = 0.125F;
    public static final float m_fVaseNeckHeight = 0.4375F;
    public static final float m_fVaseTopWidth = 0.375F;
    public static final float m_fVaseTopHalfWidth = 0.1875F;
    public static final float m_fVaseTopHeight = 0.0625F;

    public FCBlockVase(int var1)
    {
        super(var1, Material.glass);
        this.setHardness(0.0F);
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("fcBlockVase");
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
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityVase();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.getCurrentEquippedItem();

        if (var1.isRemote)
        {
            return true;
        }
        else
        {
            if (var10 != null && var10.stackSize > 0)
            {
                FCTileEntityVase var11 = (FCTileEntityVase)var1.getBlockTileEntity(var2, var3, var4);
                int var12 = var10.stackSize;

                if (FCUtilsInventory.AddItemStackToInventory(var11, var10))
                {
                    var5.destroyCurrentEquippedItem();
                    var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.pop", 0.25F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    return true;
                }

                if (var10.stackSize < var12)
                {
                    var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.pop", 0.25F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCTileEntityVase var7 = (FCTileEntityVase)((FCTileEntityVase)var1.getBlockTileEntity(var2, var3, var4));

        if (var7 != null)
        {
            FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, var7);
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6)
    {
        if (!var1.isRemote && !EnchantmentHelper.getSilkTouchModifier(var6))
        {
            this.CheckForExplosion(var1, var2, var3, var4);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.25D, 0.0D, 0.25D, 0.75D, 0.0625D, 0.75D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.1875D, 0.0625D, 0.1875D, 0.8125D, 0.4375D, 0.8125D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.25D, 0.4375D, 0.25D, 0.75D, 0.5D, 0.75D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.375D, 0.5D, 0.375D, 0.625D, 0.9375D, 0.625D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.3125D, 0.9375D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
        return var7.GetFirstIntersection();
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 1;
    }

    public void OnArrowImpact(World var1, int var2, int var3, int var4, EntityArrow var5)
    {
        if (!var1.isRemote)
        {
            this.BreakVase(var1, var2, var3, var4);
        }
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public void BreakVase(World var1, int var2, int var3, int var4)
    {
        var1.playAuxSFX(2001, var2, var3, var4, this.blockID);
        this.CheckForExplosion(var1, var2, var3, var4);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    private boolean CheckForExplosion(World var1, int var2, int var3, int var4)
    {
        FCTileEntityVase var5 = (FCTileEntityVase)((FCTileEntityVase)var1.getBlockTileEntity(var2, var3, var4));

        if (var5 != null && FCUtilsInventory.GetFirstOccupiedStackOfItem(var5, FCBetterThanWolves.fcItemBlastingOil.itemID) >= 0)
        {
            FCUtilsInventory.ClearInventoryContents(var5);
            var1.createExplosion((Entity)null, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, 1.5F, true);
            return true;
        }
        else
        {
            return false;
        }
    }
}
