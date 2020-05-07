package net.minecraft.src;

public class AddonBlockSoulboundChest extends Block {
	public AddonBlockSoulboundChest(int id) {
		super(id, FCBetterThanWolves.fcMaterialSoulforgedSteel);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(100.0F);
        this.setResistance(2000.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("ginger_soulboundChest");
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new AddonTileEntitySoulboundChest();
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4 - 1) == this.blockID ? AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D) : (var1.getBlockId(var2, var3, var4 + 1) == this.blockID ? AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D) : (var1.getBlockId(var2 - 1, var3, var4) == this.blockID ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D) : (var1.getBlockId(var2 + 1, var3, var4) == this.blockID ? AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D) : AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D))));
    }

    protected boolean canSilkHarvest(int var1)
    {
        return true;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 6, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 2, 0, var6);
        return true;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2 - 1, var3, var4) != this.blockID && var1.getBlockId(var2 + 1, var3, var4) != this.blockID && var1.getBlockId(var2, var3, var4 - 1) != this.blockID && var1.getBlockId(var2, var3, var4 + 1) != this.blockID;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return Block.RotateFacingAroundJ(var1, var2);
    }

    public boolean CanSupportFallingBlocks(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}
