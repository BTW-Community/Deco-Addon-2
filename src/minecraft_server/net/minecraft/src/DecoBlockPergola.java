package net.minecraft.src;

public class DecoBlockPergola extends Block {
    private final FCModelBlock blockModel = new DecoModelBlockPergola();
    
	protected DecoBlockPergola(int ID) {
		super(ID, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.InitBlockBounds(this.GetBlockBoundsFromPool());
		this.setUnlocalizedName("decoBlockPergola");
		this.InitBlockBounds(0, 0, 0, 1, 0.5, 1);
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
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, ((DecoModelBlockPergola) this.blockModel).blockHeight, 1);
    }
    
    public AxisAlignedBB GetBlockBoundsFromPool() {
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, ((DecoModelBlockPergola) this.blockModel).blockHeight, 1);
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
}
