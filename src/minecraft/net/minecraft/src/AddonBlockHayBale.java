package net.minecraft.src;

public class AddonBlockHayBale extends Block
{
	public AddonBlockHayBale(int id)
	{
		super(id, Material.cloth);
		setUnlocalizedName("blockHay");
		setStepSound(soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		this.SetAxesEffectiveOn(true);
		this.setHardness(0.5F);
		this.setResistance(2.0F);
		AddonManager.Register(this);
		AddonManager.Name(this, "Hay Bale");
	}

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
	@Override public boolean isOpaqueCube()
	{
		return true;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return true;
	}
	@Override public boolean canDropFromExplosion(Explosion var1)
	{
		return false;
	}
	@Override public void onBlockDestroyedByExplosion(World world, int X, int Y, int Z, Explosion exp)
	{
		float v = 1.0F;

		if (exp != null)
		{
			v = 1.0F / exp.explosionSize;
		}

		for (int i=0;i<8;++i)
			if (world.rand.nextFloat()<=v)
				dropBlockAsItem_do(world, X, Y, Z, new ItemStack(FCBetterThanWolves.fcItemWheat));
	}

	@Override public int GetFacing(IBlockAccess access, int X, int Y, int Z)
	{
		return access.getBlockMetadata(X,Y,Z);
	}
	@Override public void SetFacing(World world, int X, int Y, int Z, int facing)
	{
		world.setBlockMetadataWithNotify(X,Y,Z,facing);
	}
	@Override public int GetFacing(int meta)
	{
		return meta;
	}
	@Override public int SetFacing(int var1, int var2)
	{
		return var2;
	}
	@Override public boolean CanRotateOnTurntable(IBlockAccess access, int X, int Y, int Z)
	{
		return access.getBlockMetadata(X,Y,Z)!=0;
	}
	@Override public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess access, int X, int Y, int Z)
	{
		return true;
	}
	@Override public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess access, int X, int Y, int Z)
	{
		return true;
	}
	@Override public boolean RotateAroundJAxis(World world, int X, int Y, int Z, boolean var5)
	{
		return FCUtilsMisc.StandardRotateAroundJ(this, world, X, Y, Z, var5);
	}
	@Override public int RotateMetadataAroundJAxis(int var1, boolean var2)
	{
		return FCUtilsMisc.StandardRotateMetadataAroundJ(this, var1, var2);
	}
	@Override public boolean ToggleFacing(World world, int X, int Y, int Z, boolean var5)
	{
		this.RotateAroundJAxis(world, X, Y, Z, var5);
		return true;
	}
	@Override public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
	{
		if(var5<2)return 0;
		else if(var5<4)return 1;
		else return 2;
	}
	@Override public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
	{/*
		int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacing(var5);
		if(var7<2)var7=0;
		else if(var7<4)var7=1;
		else var7=2;
		this.SetFacing(var1, var2, var3, var4, var7);//*/
	}
//CLIENT ONLY
	static Icon topIcon;
	@Override public Icon getIcon(int side, int meta)
	{
		switch(meta)
		{
			case 0:
				return (side<2)?topIcon:blockIcon;
			case 1:
				return (side<4&&side>1)?topIcon:blockIcon;
			default:
				return (side>3)?topIcon:blockIcon;
		}
	}
	@Override public void registerIcons(IconRegister r)
	{
		blockIcon = r.registerIcon("ginger_hay_side");
		topIcon = r.registerIcon("ginger_hay_top");
	}
	@Override public boolean RenderBlock(RenderBlocks r, int X, int Y, int Z)
	{
		int f = GetFacing(r.blockAccess, X, Y, Z);
		switch(f)
		{
		case 1:
			r.SetUvRotateNorth(1);
			r.SetUvRotateSouth(1);
			break;
		case 2:
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			r.SetUvRotateWest(1);
			r.SetUvRotateEast(1);
		default:

		}
		r.setRenderBounds(0D,0D,0D,1D,1D,1D);
		r.renderStandardBlock(this, X, Y, Z);
		r.ClearUvRotation();
		return true;
	}
//
}