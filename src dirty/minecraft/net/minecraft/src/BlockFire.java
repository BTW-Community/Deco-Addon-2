package net.minecraft.src;

import java.util.Random;

public class BlockFire extends Block
{
    /** The chance this block will encourage nearby blocks to catch on fire */
    public static int[] chanceToEncourageFire = new int[4096];

    /**
     * This is an array indexed by block ID the larger the number in the array the more likely a block type will catch
     * fires
     */
    public static int[] abilityToCatchFire = new int[4096];
    private Icon[] iconArray;

    protected BlockFire(int par1)
    {
        super(par1, Material.fire);
        this.setTickRandomly(true);
    }

    /**
     * Sets the burn rate for a block. The larger abilityToCatchFire the more easily it will catch. The larger
     * chanceToEncourageFire the faster it will burn and spread to other blocks. Args: blockID, chanceToEncourageFire,
     * abilityToCatchFire
     */
    private void setBurnRate(int par1, int par2, int par3)
    {
        chanceToEncourageFire[par1] = par2;
        abilityToCatchFire[par1] = par3;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
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
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 3;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World par1World)
    {
        return 30;
    }

    public boolean func_82506_l()
    {
        return false;
    }

    private void tryToCatchBlockOnFire(World par1World, int par2, int par3, int par4, int par5, Random par6Random, int par7)
    {
        int var8 = abilityToCatchFire[par1World.getBlockId(par2, par3, par4)];

        if (par6Random.nextInt(par5) < var8)
        {
            boolean var9 = par1World.getBlockId(par2, par3, par4) == Block.tnt.blockID;

            if (par6Random.nextInt(par7 + 10) < 5 && !par1World.IsRainingAtPos(par2, par3, par4))
            {
                int var10 = par7 + par6Random.nextInt(5) / 4;

                if (var10 > 15)
                {
                    var10 = 15;
                }

                par1World.setBlock(par2, par3, par4, this.blockID, var10, 3);
            }
            else
            {
                par1World.setBlockToAir(par2, par3, par4);
            }

            if (var9)
            {
                Block.tnt.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            }
        }
    }

    /**
     * Returns true if at least one block next to this one can burn.
     */
    protected boolean canNeighborBurn(World par1World, int par2, int par3, int par4)
    {
        return this.canBlockCatchFire(par1World, par2 + 1, par3, par4) ? true : (this.canBlockCatchFire(par1World, par2 - 1, par3, par4) ? true : (this.canBlockCatchFire(par1World, par2, par3 - 1, par4) ? true : (this.canBlockCatchFire(par1World, par2, par3 + 1, par4) ? true : (this.canBlockCatchFire(par1World, par2, par3, par4 - 1) ? true : this.canBlockCatchFire(par1World, par2, par3, par4 + 1)))));
    }

    /**
     * Returns if this block is collidable (only used by Fire). Args: x, y, z
     */
    public boolean isCollidable()
    {
        return false;
    }

    /**
     * Checks the specified block coordinate to see if it can catch fire.  Args: blockAccess, x, y, z
     */
    public boolean canBlockCatchFire(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return chanceToEncourageFire[par1IBlockAccess.getBlockId(par2, par3, par4)] > 0;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) || this.canNeighborBurn(par1World, par2, par3, par4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !this.canNeighborBurn(par1World, par2, par3, par4))
        {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (par1World.provider.dimensionId > 0 || par1World.getBlockId(par2, par3 - 1, par4) != Block.obsidian.blockID || !Block.portal.tryToCreatePortal(par1World, par2, par3, par4))
        {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !this.canNeighborBurn(par1World, par2, par3, par4))
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
            else
            {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World) + par1World.rand.nextInt(10));
            }
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(24) == 0)
        {
            par1World.playSound((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), "fire.fire", 1.0F + par5Random.nextFloat(), par5Random.nextFloat() * 0.7F + 0.3F, false);
        }

        int var6;
        float var7;
        float var8;
        float var9;

        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !Block.fire.canBlockCatchFire(par1World, par2, par3 - 1, par4))
        {
            if (Block.fire.canBlockCatchFire(par1World, par2 - 1, par3, par4))
            {
                for (var6 = 0; var6 < 2; ++var6)
                {
                    var7 = (float)par2 + par5Random.nextFloat() * 0.1F;
                    var8 = (float)par3 + par5Random.nextFloat();
                    var9 = (float)par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Block.fire.canBlockCatchFire(par1World, par2 + 1, par3, par4))
            {
                for (var6 = 0; var6 < 2; ++var6)
                {
                    var7 = (float)(par2 + 1) - par5Random.nextFloat() * 0.1F;
                    var8 = (float)par3 + par5Random.nextFloat();
                    var9 = (float)par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Block.fire.canBlockCatchFire(par1World, par2, par3, par4 - 1))
            {
                for (var6 = 0; var6 < 2; ++var6)
                {
                    var7 = (float)par2 + par5Random.nextFloat();
                    var8 = (float)par3 + par5Random.nextFloat();
                    var9 = (float)par4 + par5Random.nextFloat() * 0.1F;
                    par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Block.fire.canBlockCatchFire(par1World, par2, par3, par4 + 1))
            {
                for (var6 = 0; var6 < 2; ++var6)
                {
                    var7 = (float)par2 + par5Random.nextFloat();
                    var8 = (float)par3 + par5Random.nextFloat();
                    var9 = (float)(par4 + 1) - par5Random.nextFloat() * 0.1F;
                    par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
                }
            }

            if (Block.fire.canBlockCatchFire(par1World, par2, par3 + 1, par4))
            {
                for (var6 = 0; var6 < 2; ++var6)
                {
                    var7 = (float)par2 + par5Random.nextFloat();
                    var8 = (float)(par3 + 1) - par5Random.nextFloat() * 0.1F;
                    var9 = (float)par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        else
        {
            for (var6 = 0; var6 < 3; ++var6)
            {
                var7 = (float)par2 + par5Random.nextFloat();
                var8 = (float)par3 + par5Random.nextFloat() * 0.5F + 0.5F;
                var9 = (float)par4 + par5Random.nextFloat();
                par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[] {par1IconRegister.registerIcon("fire_0"), par1IconRegister.registerIcon("fire_1")};
    }

    public Icon func_94438_c(int par1)
    {
        return this.iconArray[par1];
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.iconArray[0];
    }

    public boolean ShouldFirePreferToDisplayUpwards(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) && Block.fire.canBlockCatchFire(var1, var2, var3 - 1, var4) || this.IsBlockInfiniteBurnToTopForRender(var1, var2, var3 - 1, var4);
    }

    public boolean IsBlockInfiniteBurnToTopForRender(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        return var5 == Block.netherrack.blockID || var5 == FCBetterThanWolves.fcBBQ.blockID || this.DoesInfiniteBurnToFacing(var1, var2, var3, var4, 1);
    }
}
