package net.minecraft.src;

import java.util.List;

public class BlockPistonBase extends Block
{
    /** This pistons is the sticky one? */
    protected final boolean isSticky;

    public BlockPistonBase(int par1, boolean par2)
    {
        super(par1, Material.piston);
        this.isSticky = par2;
        this.setStepSound(soundStoneFootstep);
        this.setHardness(0.5F);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 16;
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
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        return false;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int var7 = determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadata(par2, par3, par4, var7, 2);

        if (!par1World.isRemote)
        {
            this.updatePistonState(par1World, par2, par3, par4);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            this.updatePistonState(par1World, par2, par3, par4);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote && par1World.getBlockTileEntity(par2, par3, par4) == null)
        {
            this.updatePistonState(par1World, par2, par3, par4);
        }
    }

    /**
     * handles attempts to extend or retract the piston.
     */
    protected void updatePistonState(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        int var6 = getOrientation(var5);

        if (var6 != 7)
        {
            boolean var7 = this.isIndirectlyPowered(par1World, par2, par3, par4, var6);

            if (var7 && !isExtended(var5))
            {
                if (this.canExtend(par1World, par2, par3, par4, var6))
                {
                    par1World.addBlockEvent(par2, par3, par4, this.blockID, 0, var6);
                }
            }
            else if (!var7 && isExtended(var5))
            {
                par1World.setBlockMetadata(par2, par3, par4, var6, 2);
                par1World.addBlockEvent(par2, par3, par4, this.blockID, 1, var6);
            }
        }
    }

    /**
     * checks the block to that side to see if it is indirectly powered.
     */
    private boolean isIndirectlyPowered(World par1World, int par2, int par3, int par4, int par5)
    {
        return par5 != 0 && par1World.getIndirectPowerOutput(par2, par3 - 1, par4, 0) ? true : (par5 != 1 && par1World.getIndirectPowerOutput(par2, par3 + 1, par4, 1) ? true : (par5 != 2 && par1World.getIndirectPowerOutput(par2, par3, par4 - 1, 2) ? true : (par5 != 3 && par1World.getIndirectPowerOutput(par2, par3, par4 + 1, 3) ? true : (par5 != 5 && par1World.getIndirectPowerOutput(par2 + 1, par3, par4, 5) ? true : (par5 != 4 && par1World.getIndirectPowerOutput(par2 - 1, par3, par4, 4) ? true : (par1World.getIndirectPowerOutput(par2, par3, par4, 0) ? true : (par1World.getIndirectPowerOutput(par2, par3 + 2, par4, 1) ? true : (par1World.getIndirectPowerOutput(par2, par3 + 1, par4 - 1, 2) ? true : (par1World.getIndirectPowerOutput(par2, par3 + 1, par4 + 1, 3) ? true : (par1World.getIndirectPowerOutput(par2 - 1, par3 + 1, par4, 4) ? true : par1World.getIndirectPowerOutput(par2 + 1, par3 + 1, par4, 5)))))))))));
    }

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (!par1World.isRemote)
        {
            boolean var7 = this.isIndirectlyPowered(par1World, par2, par3, par4, par6);

            if (var7 && par5 == 1)
            {
                par1World.setBlockMetadata(par2, par3, par4, par6 | 8, 2);
                return false;
            }

            if (!var7 && par5 == 0)
            {
                return false;
            }
        }

        if (par5 == 0)
        {
            if (!this.tryExtend(par1World, par2, par3, par4, par6))
            {
                return false;
            }

            par1World.setBlockMetadata(par2, par3, par4, par6 | 8, 2);
            par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "tile.piston.out", 0.5F, par1World.rand.nextFloat() * 0.25F + 0.6F);
        }
        else if (par5 == 1)
        {
            TileEntity var16 = par1World.getBlockTileEntity(par2 + Facing.offsetsXForSide[par6], par3 + Facing.offsetsYForSide[par6], par4 + Facing.offsetsZForSide[par6]);

            if (var16 instanceof TileEntityPiston)
            {
                ((TileEntityPiston)var16).clearPistonTileEntity();
            }

            par1World.setBlock(par2, par3, par4, Block.pistonMoving.blockID, par6, 3);
            par1World.setBlockTileEntity(par2, par3, par4, BlockPistonMoving.getTileEntity(this.blockID, par6, par6, false, true));

            if (this.isSticky)
            {
                int var8 = par2 + Facing.offsetsXForSide[par6] * 2;
                int var9 = par3 + Facing.offsetsYForSide[par6] * 2;
                int var10 = par4 + Facing.offsetsZForSide[par6] * 2;
                int var11 = par1World.getBlockId(var8, var9, var10);
                int var12 = par1World.getBlockMetadata(var8, var9, var10);
                boolean var13 = false;

                if (var11 == Block.pistonMoving.blockID)
                {
                    TileEntity var14 = par1World.getBlockTileEntity(var8, var9, var10);

                    if (var14 instanceof TileEntityPiston)
                    {
                        TileEntityPiston var15 = (TileEntityPiston)var14;

                        if (var15.getPistonOrientation() == par6 && var15.isExtending())
                        {
                            var15.clearPistonTileEntity();
                            var11 = var15.getStoredBlockID();
                            var12 = var15.getBlockMetadata();
                            var13 = true;
                        }
                    }
                }

                Block var17 = Block.blocksList[var11];

                if (!var13 && var17 != null && var17.CanBlockBePulledByPiston(par1World, var8, var9, var10, Block.GetOppositeFacing(par6)))
                {
                    var12 = var17.AdjustMetadataForPistonMove(var12);
                    par2 += Facing.offsetsXForSide[par6];
                    par3 += Facing.offsetsYForSide[par6];
                    par4 += Facing.offsetsZForSide[par6];
                    par1World.setBlock(par2, par3, par4, Block.pistonMoving.blockID, var12, 3);
                    par1World.setBlockTileEntity(par2, par3, par4, BlockPistonMoving.getTileEntity(var11, var12, par6, false, false));
                    par1World.setBlockToAir(var8, var9, var10);
                }
                else if (!var13)
                {
                    par1World.setBlockToAir(par2 + Facing.offsetsXForSide[par6], par3 + Facing.offsetsYForSide[par6], par4 + Facing.offsetsZForSide[par6]);
                }
            }
            else
            {
                par1World.setBlockToAir(par2 + Facing.offsetsXForSide[par6], par3 + Facing.offsetsYForSide[par6], par4 + Facing.offsetsZForSide[par6]);
            }

            par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "tile.piston.in", 0.5F, par1World.rand.nextFloat() * 0.15F + 0.6F);
        }

        return true;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        if (isExtended(var5))
        {
            switch (getOrientation(var5))
            {
                case 0:
                    this.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 1:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                    break;

                case 2:
                    this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                    break;

                case 3:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                    break;

                case 4:
                    this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 5:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * returns an int which describes the direction the piston faces
     */
    public static int getOrientation(int par0)
    {
        return par0 & 7;
    }

    /**
     * Determine if the metadata is related to something powered.
     */
    public static boolean isExtended(int par0)
    {
        return (par0 & 8) != 0;
    }

    /**
     * gets the way this piston should face for that entity that placed it.
     */
    public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLiving par4EntityLiving)
    {
        if (MathHelper.abs((float)par4EntityLiving.posX - (float)par1) < 2.0F && MathHelper.abs((float)par4EntityLiving.posZ - (float)par3) < 2.0F)
        {
            double var5 = par4EntityLiving.posY + 1.82D - (double)par4EntityLiving.yOffset;

            if (var5 - (double)par2 > 2.0D)
            {
                return 1;
            }

            if ((double)par2 - var5 > 0.0D)
            {
                return 0;
            }
        }

        int var7 = MathHelper.floor_double((double)(par4EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)));
    }

    /**
     * checks to see if this piston could push the blocks in front of it.
     */
    protected boolean canExtend(World par0World, int par1, int par2, int par3, int par4)
    {
        return false;
    }

    /**
     * attempts to extend the piston. returns false if impossible.
     */
    protected boolean tryExtend(World par1World, int par2, int par3, int par4, int par5)
    {
        return false;
    }
}
