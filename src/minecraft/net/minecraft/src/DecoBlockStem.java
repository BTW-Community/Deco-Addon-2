package net.minecraft.src;

import java.util.List;

public class DecoBlockStem extends DecoBlockDirectional {
	private int barkMeta;
	private int damagedLogID;
	
	protected DecoBlockStem(int id, String[] topTextures, String[] sideTextures, int barkMeta, int damagedLogID) {
		super(id, FCBetterThanWolves.fcMaterialLog, topTextures, sideTextures);
		this.barkMeta = barkMeta;
		this.damagedLogID = damagedLogID;
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

		var2.setBlockAndMetadataWithNotify(var3, var4, var5, damagedLogID, var10);

		if (!var2.isRemote && ((var7 & 3) == 0 || (var7 & 3) == 2))
		{
			FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, barkMeta), var6);
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
			FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, barkMeta);

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
		this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, barkMeta, var6);
		return true;
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
}