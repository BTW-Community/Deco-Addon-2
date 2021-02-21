package net.minecraft.src;

public class DecoBlockChair extends Block {
	public DecoBlockChair(int id, Material material, String tag) {
		super(id, material);
		this.setUnlocalizedName("decoBlockChair" + tag);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		DecoManager.Register(this);
		this.InitBlockBounds(.0625F, 0, .0625F, 0.9375F, .625F, 0.9375F);
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
		return SetFacing(var9, var5);
	}
	@Override public void onBlockPlacedBy(World world, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
	{
        int var8 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetFacing(world, var2, var3, var4, var7);
	}
}