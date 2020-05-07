package net.minecraft.src;

public class AddonBlockBonePillar extends Block {
	public AddonBlockBonePillar(int id) {
        super(id, FCBetterThanWolves.fcMaterialMiscellaneous);
        this.setHardness(2.0F);
        this.SetPicksEffectiveOn();
        this.SetBuoyancy(1.0F);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("bonePillar");
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
	@Override 
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int var10 = par9 & 3;
        byte var11 = 0;

        switch (par5)
        {
            case 0:
            case 1:
                var11 = 0;
                break;

            case 2:
            case 3:
                var11 = 8;
                break;

            case 4:
            case 5:
                var11 = 4;
        }

        return var10 | var11;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = var1 & 12;

        if (var3 != 0)
        {
            if (var3 == 4)
            {
                var3 = 8;
            }
            else if (var3 == 8)
            {
                var3 = 4;
            }

            var1 = var1 & -13 | var3;
        }

        return var1;
    }
    
//CLIENT ONLY
	public static Icon topIcon;
	public static Icon sideIcon;
	
	public Icon getIcon(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? topIcon : (var3 == 4 && (par1 == 5 || par1 == 4) ? topIcon : (var3 == 8 && (par1 == 2 || par1 == 3) ? topIcon : sideIcon));
    }
	@Override public void registerIcons(IconRegister register)
	{
		topIcon = register.registerIcon("ginger_bonePillar_top");
		sideIcon = register.registerIcon("ginger_bonePillar_side");
	}
	@Override public boolean RenderBlock(RenderBlocks r, int X, int Y, int Z)
	{

        int var5 = r.blockAccess.getBlockMetadata(X, Y, Z);
        int var6 = var5 & 12;

        if (var6 == 4)
        {
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			r.SetUvRotateWest(1);
			r.SetUvRotateEast(1);
        }
        else if (var6 == 8)
        {
			r.SetUvRotateNorth(1);
			r.SetUvRotateSouth(1);
        }
        
		r.setRenderBounds(0D,0D,0D,1D,1D,1D);
		r.renderStandardBlock(this, X, Y, Z);
		r.ClearUvRotation();
		return true;
	}
}
