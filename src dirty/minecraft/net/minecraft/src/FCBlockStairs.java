package net.minecraft.src;

import java.util.Random;

public class FCBlockStairs extends FCBlockStairsBase
{
    protected final Block m_referenceBlock;
    protected final int m_iReferenceBlockMetadata;

    protected FCBlockStairs(int var1, Block var2, int var3)
    {
        super(var1, var2.blockMaterial);
        this.m_referenceBlock = var2;
        this.m_iReferenceBlockMetadata = var3;
        this.setHardness(var2.blockHardness);
        this.setResistance(var2.blockResistance / 3.0F);
        this.setStepSound(var2.stepSound);
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        this.m_referenceBlock.onBlockClicked(var1, var2, var3, var4, var5);
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5)
    {
        this.m_referenceBlock.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
    }

    /**
     * Returns how much this block can resist explosions from the passed in entity.
     */
    public float getExplosionResistance(Entity var1)
    {
        return this.m_referenceBlock.getExplosionResistance(var1);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return this.m_referenceBlock.tickRate(var1);
    }

    /**
     * Can add to the passed in vector for a movement vector to be applied to the entity. Args: x, y, z, entity, vec3d
     */
    public void velocityToAddToEntity(World var1, int var2, int var3, int var4, Entity var5, Vec3 var6)
    {
        this.m_referenceBlock.velocityToAddToEntity(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Returns if this block is collidable (only used by Fire). Args: x, y, z
     */
    public boolean isCollidable()
    {
        return this.m_referenceBlock.isCollidable();
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int var1, boolean var2)
    {
        return this.m_referenceBlock.canCollideCheck(var1, var2);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return this.m_referenceBlock.canPlaceBlockAt(var1, var2, var3, var4);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        this.m_referenceBlock.onBlockAdded(var1, var2, var3, var4);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        super.breakBlock(var1, var2, var3, var4, var5, var6);
        this.m_referenceBlock.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5)
    {
        this.m_referenceBlock.onEntityWalking(var1, var2, var3, var4, var5);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.m_referenceBlock.updateTick(var1, var2, var3, var4, var5);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        return this.m_referenceBlock.onBlockActivated(var1, var2, var3, var4, var5, 0, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        this.m_referenceBlock.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.m_referenceBlock.randomDisplayTick(var1, var2, var3, var4, var5);
    }

    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_referenceBlock.getMixedBrightnessForBlock(var1, var2, var3, var4);
    }

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_referenceBlock.getBlockBrightness(var1, var2, var3, var4);
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return this.m_referenceBlock.getRenderBlockPass();
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_referenceBlock.getIcon(var1, this.m_iReferenceBlockMetadata);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.m_referenceBlock.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1) {}
}
