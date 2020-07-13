package net.minecraft.src;

public class AddonBlockPergola extends Block {
    private final FCModelBlock blockModel = new AddonModelBlockPergola();
    
	protected AddonBlockPergola(int ID) {
		super(ID, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.InitBlockBounds(this.GetBlockBoundsFromPool());
		this.setUnlocalizedName("pergola");
		this.InitBlockBounds(0, 0, 0, 1, ((AddonModelBlockPergola) blockModel).blockHeight, 1);
	}

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }
    
    public AxisAlignedBB GetBlockBoundsFromPool(IBlockAccess Access, int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, ((AddonModelBlockPergola) this.blockModel).blockHeight, 1);
    }
    
    public AxisAlignedBB GetBlockBoundsFromPool() {
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, ((AddonModelBlockPergola) this.blockModel).blockHeight, 1);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

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
    	else if (((side == 2 || side == 3) && metadata <=3) || ((side == 4 || side == 5) && metadata >= 4)) {
    		return icon_sides_0;
    	}
    	else {
    		return icon_sides_1;
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
