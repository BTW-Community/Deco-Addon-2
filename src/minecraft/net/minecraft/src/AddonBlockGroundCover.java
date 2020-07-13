package net.minecraft.src;

import java.util.Random;

public class AddonBlockGroundCover extends FCBlockGroundCover {
	private int dropID;
	private Block referenceBlock;
	private int referenceBlockMetadata;

	public AddonBlockGroundCover(int id, Material material, Block referenceBlock) {
		this(id, material, referenceBlock, 0);
	}
	
	public AddonBlockGroundCover(int id, Material material, Block referenceBlock, int referenceBlockMetadata) {
		super(id, material);
		this.dropID = this.blockID;
		this.referenceBlock = referenceBlock;
		this.referenceBlockMetadata = referenceBlockMetadata;
        this.setHardness(referenceBlock.blockHardness);
        this.setResistance(referenceBlock.blockResistance / 3.0F);
        this.setStepSound(referenceBlock.stepSound);
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public AddonBlockGroundCover(int id, Material material, Block referenceBlock, int referenceBlockMetadata, int dropID) {
		this(id, material, referenceBlock, referenceBlockMetadata);
		this.dropID = dropID;
	}
	
	public boolean addLayer(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int newMeta = meta + 1;
		
		if (newMeta >= 8)
			return false;
		
		return world.setBlockMetadataWithNotify(x, y, z, newMeta);
	}

	public float getBlockModelHeightFromMeta(int meta) {
		return (meta % 8 + 1) * 0.125F;
	}

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 var5, Vec3 var6)
    {
        float var7 = 0.0F;
        int idBelow = world.getBlockId(x, y - 1, z);
        Block var9 = Block.blocksList[idBelow];

        if (var9 != null)
        {
            var7 = var9.GroundCoverRestingOnVisualOffset(world, x, y - 1, z);
        }
        
        int meta = world.getBlockMetadata(x, y, z);
        FCUtilsRayTraceVsComplexBlock var10 = new FCUtilsRayTraceVsComplexBlock(world, x, y, z, var5, var6);
        var10.AddBoxWithLocalCoordsToIntersectionList(0.0D, (double)var7, 0.0D, 1.0D, (double)(getBlockModelHeightFromMeta(meta) + var7), 1.0D);
        return var10.GetFirstIntersection();
    }
    

	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, getBlockModelHeightFromMeta(meta), 1);
	}

    public float MobSpawnOnVerticalOffset(World world, int x, int y, int z)
    {
		int meta = world.getBlockMetadata(x, y, z);
        return 1 - (getBlockModelHeightFromMeta(meta) + .125F);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
		int meta = world.getBlockMetadata(x, y, z);
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, getBlockModelHeightFromMeta(meta), 1);
    }

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return dropID;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float par6, int par7)
	{
		if (!world.isRemote)
		{
			int idDropped = this.idDropped(meta, world.rand, par7);

			if (idDropped > 0)
			{
				this.dropBlockAsItem_do(world, x, y, z, new ItemStack(idDropped, meta, this.damageDropped(meta)));
			}
		}
	}

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2)
        {
            if (var1.isBlockOpaqueCube(var2, var3, var4))
            {
                return false;
            }
            else
            {
                if (var1.getBlockId(var2, var3, var4) == this.blockID)
                {
                    FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));

                    if (var1.getBlockId(var6.i, var6.j, var6.k) == this.blockID)
                    {
                        int var7 = var1.getBlockId(var2, var3 - 1, var4);
                        int meta = var1.getBlockMetadata(var2, var3, var4);

                        if (var7 != 0 && blocksList[var7].GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4) > getBlockModelHeightFromMeta(meta) - 0.01F)
                        {
                            return false;
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return var5 == 1 ? true : super.shouldSideBeRendered(var1, var2, var3, var4, var5);
        }
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 1)
        {
            int var6 = var1.getBlockId(var2, var3 - 1, var4);
            int meta = var1.getBlockMetadata(var2, var3, var4);

            if (var6 != 0 && blocksList[var6].GroundCoverRestingOnVisualOffset(var1, var2, var3 - 1, var4) > -1 * getBlockModelHeightFromMeta(meta))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.referenceBlock.getMixedBrightnessForBlock(var1, var2, var3, var4);
    }

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.referenceBlock.getBlockBrightness(var1, var2, var3, var4);
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return this.referenceBlock.getRenderBlockPass();
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.referenceBlock.getIcon(var1, this.referenceBlockMetadata);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1) {}
}