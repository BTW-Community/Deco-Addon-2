package net.minecraft.src;

import java.util.List;

public class DecoBlockLogCherry extends Block {
	public DecoBlockLogCherry(int id) {
		super(id, FCBetterThanWolves.fcMaterialLog);
		this.setHardness(1.25F);
		this.setResistance(3.33F);
		this.SetAxesEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.SetBuoyant();
		this.SetCanBeCookedByKiln(true);
		this.SetItemIndexDroppedWhenCookedByKiln(263);
		this.SetItemDamageDroppedWhenCookedByKiln(1);
		this.SetFireProperties(FCEnumFlammability.LOGS);
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("cherryLog");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
	{
		return true;
	}

	public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
	{
		int var7 = var2.getBlockMetadata(var3, var4, var5);
		byte var8 = 0;
		int var10;

		int var9 = var7 >> 2 & 3;
		var10 = FCBetterThanWolves.fcBlockLogDamaged.SetOrientation(var8, var9);

		var2.setBlockAndMetadataWithNotify(var3, var4, var5, DecoDefs.logDamagedCherry.blockID, var10);

		if (!var2.isRemote && ((var7 & 3) == 0 || (var7 & 3) == 2))
		{
			FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5), var6);
		}

		return true;
	}

	public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
	{
		return true;
	}

	public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
	{
		this.ConvertToSmouldering(var1, var2, var3, var4);
	}

    public void ConvertToSmouldering(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLogSmouldering.SetIsStump(0, false);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLogSmouldering.blockID, var5);
    }

	public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
	{
		int meta = var1.getBlockMetadata(var2, var3, var4);

		super.OnBlockSawed(var1, var2, var3, var4);

		for (int var5 = 0; var5 < 2; ++var5)
		{
			FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 0);
		}

		if ((meta & 3) == 0 || (meta & 3) == 2)
			FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 5);

		return true;
	}

	public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
	{
		return Block.planks.blockID;
	}

	public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
	{
		return 4;
	}

	public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
	{
		return 5;
	}

	public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
	{
		this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 5, 0, var6);
		this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, 5, var6);
		return true;
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

	@Override public boolean ToggleFacing(World world, int X, int Y, int Z, boolean var5)
	{
		this.RotateAroundJAxis(world, X, Y, Z, var5);
		return true;
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

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}

	//CLIENT ONLY
	public static String[] topTextures = {"ginger_logCherryTop", "ginger_strippedCherryTop", "ginger_logCherrySide", "ginger_strippedCherrySide"};
	public static String[] sideTextures = {"ginger_logCherrySide", "ginger_strippedCherrySide", "ginger_logCherrySide", "ginger_strippedCherrySide"};
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
		topIcons = new Icon[4];
		sideIcons = new Icon[4];

		for (int i = 0; i < 4; i++) {
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

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }
}
