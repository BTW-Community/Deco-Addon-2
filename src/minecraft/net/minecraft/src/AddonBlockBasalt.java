package net.minecraft.src;

public class AddonBlockBasalt extends Block {
	public AddonBlockBasalt(int id) {
		super(id, FCBetterThanWolves.fcMaterialNetherRock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
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
    
//CLIENT ONLY
	public static String[] topTextures = {"ginger_basalt_top", "ginger_basaltSmooth_top"};
	public static String[] sideTextures = {"ginger_basalt_side", "ginger_basaltSmooth_side"};
	public static Icon[] topIcons;
	public static Icon[] sideIcons;
	
	public Icon getIcon(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? topIcons[var4] : (var3 == 4 && (par1 == 5 || par1 == 4) ? topIcons[var4] : (var3 == 8 && (par1 == 2 || par1 == 3) ? topIcons[var4] : sideIcons[var4]));
    }
	@Override public void registerIcons(IconRegister register)
	{
		topIcons = new Icon[2];
		sideIcons = new Icon[2];
		
		for (int i = 0; i < 2; i++) {
			topIcons[i] = register.registerIcon(topTextures[i]);
			sideIcons[i] = register.registerIcon(sideTextures[i]);
		}
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
