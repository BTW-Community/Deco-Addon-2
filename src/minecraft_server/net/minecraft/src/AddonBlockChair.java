package net.minecraft.src;

public class AddonBlockChair extends Block {
	public AddonBlockChair(int id, Material material, String tag, String name) {
		super(id, material);
		this.setUnlocalizedName("ginger_chair_" + tag);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		AddonManager.Register(this, name + " Chair");
	}

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }
	
	@Override
	public int GetFacing(IBlockAccess var1, int var2, int var3, int var4) {
		return var1.getBlockMetadata(var2, var3, var4);
	}
	
	@Override
	public void SetFacing(World var1, int var2, int var3, int var4, int var5) {
		var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
	}
	
	@Override
	public int GetFacing(int metadata) {
		return metadata;
	}
	
	@Override public int SetFacing(int var1, int var2)
	{
		return var2;
	}
	@Override public boolean CanRotateOnTurntable(IBlockAccess blockAccess, int x, int y, int z)
	{
		return true;
	}
	@Override public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess blockAccess, int x, int y, int z)
	{
		return false;
	}
	@Override public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess blockAccess, int x, int y, int z)
	{
		return false;
	}
	@Override public boolean RotateAroundJAxis(World world, int x, int y, int z, boolean var5)
	{
		return FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, var5);
	}
	@Override public int RotateMetadataAroundJAxis(int var1, boolean var2)
	{
		return FCUtilsMisc.StandardRotateMetadataAroundJ(this, var1, var2);
	}
	@Override public boolean ToggleFacing(World world, int x, int y, int z, boolean var5)
	{
		this.RotateAroundJAxis(world, x, y, z, var5);
		return true;
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override public int onBlockPlaced(World world, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
	{
		/*if (var5 < 2)
		{
			var5 = 2;
		}
		else
		{
			var5 = FCUtilsMisc.GetOppositeFacing(var5);
		}*/
		return SetFacing(var9, var5);
	}
	@Override public void onBlockPlacedBy(World world, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
	{
        int var8 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetFacing(world, var2, var3, var4, var7);
	}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int var2, int var3, int var4)
	{
		int var5 = this.GetFacing(world, var2, var3, var4);
		return var5 != 2 && var5 != 3 ? AxisAlignedBB.getAABBPool().getAABB((double) ((float) var2), (double) ((float) var3), (double) ((float) var4 + 0.5F - 0.25F), (double) ((float) var2 + 1.0F), (double) ((float) var3 + 1.0F), (double) ((float) var4 + 0.5F + 0.25F)) : AxisAlignedBB.getAABBPool().getAABB((double) ((float) var2 + 0.5F - 0.25F), (double) ((float) var3), (double) ((float) var4), (double) ((float) var2 + 0.5F + 0.25F), (double) ((float) var3 + 1.0F), (double) ((float) var4 + 1.0F));
	}
	@Override public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int var2, int var3, int var4)
	{
		setBlockBounds(.0625F, 0.0F, .0625F, .9375F, 1.25F, .9375F);
	}
}