package net.minecraft.src;

public class AddonBlockPergola extends Block {
    private final FCModelBlock blockModel = new AddonModelBlockPergola();
    
    private boolean swapSideIcons;
    
	protected AddonBlockPergola(int ID) {
		super(ID, Material.wood);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.InitBlockBounds(this.GetBlockBoundsFromPool());
	}

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public AxisAlignedBB GetBlockBoundsFromPool(IBlockAccess Access, int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, ((AddonModelBlockPergola) this.blockModel).blockHeight, 1);
    }
    
    public AxisAlignedBB GetBlockBoundsFromPool() {
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, ((AddonModelBlockPergola) this.blockModel).blockHeight, 1);
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = this.GetFacing(var1, var2, var3, var4);
        FCModelBlock var8 = this.blockModel.MakeTemporaryCopy();
        var8.RotateAroundJToFacing(var7);
        return var8.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Called when the block is placed in the world.
     */
    /*public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
    {
        int var7 = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var7 == 0 || var7 == 2)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }

        if (var7 == 1 || var7 == 3)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
    }*/

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var8 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetFacing(var1, var2, var3, var4, var7);
        //this.SetFacing(var1, var2, var3, var4, var8);
        
        //swapSideIcons = var8 > 1;
        
        if (var8 == 0 || var8 == 2)
        {
        	var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 2);
        }

        if (var8 == 1 || var8 == 3)
        {
        	var1.setBlockMetadataWithNotify(var2, var3, var4, 2, 2);
        }
    }

    public int GetFacing(int var1)
    {
        return var1;
    }

    public int SetFacing(int var1, int var2)
    {
        return var2;
    }
	
	//CLIENT ONLY
    private Icon icon_top;
    private Icon icon_bottom;
    private Icon icon_sides_0;
    private Icon icon_sides_1;
    
    public Icon getIcon(int side, int metadata) {
    	if (side == 0) {
    		return icon_bottom;
    	}
    	else if (side == 1) {
    		return icon_top;
    	}
    	else if (side == 2 || side == 3) {
    		return swapSideIcons ? icon_sides_1 : icon_sides_0;
    	}
    	else {
    		return swapSideIcons ? icon_sides_0 : icon_sides_1;
    	}
    }
    
    public void registerIcons(IconRegister register) {
    	icon_top = register.registerIcon("ginger_pergola_top");
    	icon_bottom = register.registerIcon("ginger_pergola_bottom");
    	icon_sides_0 = register.registerIcon("ginger_pergola_sides_0");
    	icon_sides_1 = register.registerIcon("ginger_pergola_sides_1");
    }
    
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1.blockAccess, var2, var3, var4);
        FCModelBlock var6 = this.blockModel.MakeTemporaryCopy();
        var6.RotateAroundJToFacing(var5);
        return var6.RenderAsBlock(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.blockModel.RenderAsItemBlock(var1, this, var2);
    }
}
