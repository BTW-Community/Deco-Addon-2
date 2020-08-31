package net.minecraft.src;

public class DecoBlockLantern extends Block
{
	String tag;
	boolean animate;
	public DecoBlockLantern(int ID, Material material,float hardness, String tag, String name){this(ID,material,hardness,tag,name,false);}
	public DecoBlockLantern(int ID, Material material,float hardness, String tag, String name,boolean animate)
	{
		super(ID, material);
		this.tag=tag;
		this.animate=animate;
		setUnlocalizedName("ginger_lantern_"+tag);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(hardness);
		setLightValue(1F);
		DecoManager.Register(this, name);
		this.InitBlockBounds(.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D);
		
		if (material == Material.iron) {
			if (DecoManager.getNewSoundsInstalled())
				this.setStepSound(DecoDefs.stepSoundLantern);
			else
				this.setStepSound(Block.soundMetalFootstep);
		}
		else
			this.setStepSound(Block.soundWoodFootstep);
	}
	@Override public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
	{
		GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
		return SetFacing(var9, var5);
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	@Override public boolean renderAsNormalBlock()
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

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess Access, int x, int y, int z)
	{
		double[] bounds = new double[6];
		double[] bounds0 = {.3125D,	.5D,	.3125D,		.6875D,		1D,		.6875D};
		double[] bounds1 = {.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D};
		double[] bounds2 = {.3125D,	0.0D,	.625D,		.6875D,		.5D,	1D};
		double[] bounds3 = {.3125D,	0.0D,	0.0D,		.6875D,		.5D,	.375D};
		double[] bounds4 = {.625D,	0.0D,	.3125D,		1D,			.5D,	.6875D};
		double[] bounds5 = {0.0D,		0.0D,	.3125D,		.375D,		.5D,	.6875D};

		switch (GetFacing(Access, x, y, z))
		{
			default:
			case 0: bounds = bounds0;
				break;
			case 1: bounds = bounds1;
				break;
			case 2: bounds = bounds2;
				break;
			case 3: bounds = bounds3;
				break;
			case 4: bounds = bounds4;
				break;
			case 5: bounds = bounds5;
		}

		return AxisAlignedBB.getAABBPool().getAABB(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
	}

	public AxisAlignedBB GetBlockBoundsFromPoolForItemRender()
	{
		return AxisAlignedBB.getAABBPool().getAABB(.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D);
	}
	@Override public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
	{
		return var1.getBlockMetadata(var2, var3, var4);
	}
	@Override public void SetFacing(World var1, int var2, int var3, int var4, int var5)
	{
		var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
	}
	@Override public int GetFacing(int var1)
	{
		return var1;
	}
	@Override public int SetFacing(int var1, int var2)
	{
		return var2;
	}
	@Override public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
	{
		return false;
	}
	@Override public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
	{
		return false;
	}
	@Override public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
	{
		return false;
	}
	@Override public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
	{
		return false;
	}
	@Override public int RotateMetadataAroundJAxis(int var1, boolean var2)
	{
		return 0;
	}
	@Override public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
	{
		return false;
	}
//CLIENT ONLY
	Icon TopIcon;
	@Override public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
	{
		//return var5 == 0 && this.minY > 0.0D ? true : (var5 == 1 && this.maxY < 1.0D ? true : (var5 == 2 && this.minZ > 0.0D ? true : (var5 == 3 && this.maxZ < 1.0D ? true : (var5 == 4 && this.minX > 0.0D ? true : (var5 == 5 && this.maxX < 1.0D ? true : !var1.isBlockOpaqueCube(var2, var3, var4))))));
		return true;
	}
	@Override public void registerIcons(IconRegister Register)
	{
		blockIcon = Register.registerIcon("ginger_lantern_"+tag+(animate?"_anim":""));
		TopIcon = Register.registerIcon("ginger_lantern_"+tag+"_top");
	}
	@Override public Icon getIcon(int side, int meta)
	{
		return side < 2 ? TopIcon : blockIcon;
	}
}