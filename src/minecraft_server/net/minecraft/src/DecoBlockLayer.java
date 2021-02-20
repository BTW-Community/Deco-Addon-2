package net.minecraft.src;

import java.util.Random;

public class DecoBlockLayer extends Block {
	private int dropID;
	private Block referenceBlock;
	private int referenceBlockMetadata;

	public DecoBlockLayer(int id, Block referenceBlock) {
		this(id, referenceBlock, 0);
	}
	
	public DecoBlockLayer(int id, Block referenceBlock, int referenceBlockMetadata) {
		super(id, referenceBlock.blockMaterial);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
		this.dropID = this.blockID;
		this.referenceBlock = referenceBlock;
		this.referenceBlockMetadata = referenceBlockMetadata;
        this.setHardness(referenceBlock.blockHardness);
        this.setResistance(referenceBlock.blockResistance / 3.0F);
        this.setStepSound(referenceBlock.stepSound);
        this.SetBuoyant();
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public DecoBlockLayer(int id, Block referenceBlock, int referenceBlockMetadata, int dropID) {
		this(id, referenceBlock, referenceBlockMetadata);
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
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        Block var6 = Block.blocksList[var5];
        return var6 != null ? var6.CanGroundCoverRestOnBlock(var1, var2, var3 - 1, var4) : false;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.setBlockToAir(var2, var3, var4);
        }
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

    public static void ClearAnyGroundCoverRestingOnBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2 + 1, var3)];

        if (var4 != null)
        {
            if (var4.IsGroundCover())
            {
                var0.setBlockToAir(var1, var2 + 1, var3);
            }
            else if (var4.GroundCoverRestingOnVisualOffset(var0, var1, var2 + 1, var3) < -0.99F)
            {
                Block var5 = Block.blocksList[var0.getBlockId(var1, var2 + 2, var3)];

                if (var5 != null && var5.IsGroundCover())
                {
                    var0.setBlockToAir(var1, var2 + 2, var3);
                }
            }
        }
    }

    public static boolean IsGroundCoverRestingOnBlock(World var0, int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[var0.getBlockId(var1, var2 + 1, var3)];

        if (var4 != null)
        {
            if (var4.IsGroundCover())
            {
                return true;
            }

            if (var4.GroundCoverRestingOnVisualOffset(var0, var1, var2 + 1, var3) < -0.99F)
            {
                Block var5 = Block.blocksList[var0.getBlockId(var1, var2 + 2, var3)];

                if (var5 != null && var5.IsGroundCover())
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void ClientBreakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3 - 2, var4);
    }

	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, getBlockModelHeightFromMeta(meta), 1);
	}

    public float MobSpawnOnVerticalOffset(World world, int x, int y, int z)
    {
		int meta = world.getBlockMetadata(x, y, z);
        return 1 - getBlockModelHeightFromMeta(meta);
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
				this.dropBlockAsItem_do(world, x, y, z, new ItemStack(idDropped, meta + 1, this.damageDropped(meta)));
			}
		}
	}
}