package net.minecraft.src;

import java.util.List;

public class AddonItemDye extends FCItemDye
{
	//CLIENT ONLY METHODS
	public Icon[] ExtraIcons = new Icon[16];
	//
	public static final String[] ColorPlus_dyeColorNames = new String[] {"black","red","green","brown","blue","purple","cyan","silver","gray","pink","lime","yellow","lightBlue","magenta","orange", "white","black2","red2","green2","brown2","blue2","purple2","cyan2","silver2","gray2","pink2","lime2","yellow2","lightBlue2","magenta2","orange2","white2"};

	public AddonItemDye(int ID)
	{
		super(ID);
		setUnlocalizedName("dyePowder");
		SetBellowsBlowDistance(2);
		AddonManager.Name(new ItemStack(this, 1, 20), "Blue Dye");
		AddonManager.Name(new ItemStack(this, 1, 31), "White Dye");
	}
	@Override public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
	{
		return var2 != null && !var2.canPlayerEdit(var4, var5, var6, var7, var1) ?
				false :(var1.getItemDamage() == 15 ?
						this.ApplyBoneMeal(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10) : (var1.getItemDamage() == 3 ?
								this.ApplyCocoaBeans(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10) : false
								));
	}
	private boolean CanBonemealBeAppliedToBlock(World var1, int var2, int var3, int var4)
	{
		int var5 = var1.getBlockId(var2, var3, var4);
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		return var5 == Block.tilledField.blockID || var5 == FCBetterThanWolves.fcPlanter.blockID && var6 == 1 || var5 == Block.grass.blockID || var5 == FCBetterThanWolves.fcBlockPlanterSoil.blockID || var5 == FCBetterThanWolves.fcBlockFarmland.blockID;
	}
	private boolean ApplyCocoaBeans(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
	{
		int var11 = var3.getBlockId(var4, var5, var6);
		int var12 = var3.getBlockMetadata(var4, var5, var6);
		if (var11 == Block.wood.blockID && BlockLog.limitToValidMetadata(var12) == 3)
		{
			if (var7 != 0 && var7 != 1)
			{
				FCUtilsBlockPos var13 = new FCUtilsBlockPos(var4, var5, var6);
				var13.AddFacingAsOffset(var7);
				if (var3.isAirBlock(var13.i, var13.j, var13.k))
				{
					int var14 = Block.cocoaPlant.blockID;
					int var15 = Block.blocksList[var14].onBlockPlaced(var3, var13.i, var13.j, var13.k, var7, var8, var9, var10, 0);
					var3.setBlockAndMetadataWithNotify(var13.i, var13.j, var13.k, var14, var15);
					if (var2 == null || !var2.capabilities.isCreativeMode)
						--var1.stackSize;
				}
				return true;
			}
			else return false;
		}
		else return false;
	}
	private boolean ApplyBoneMeal(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
	{
		if (!this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
			--var5;
		if (this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
		{
			int var11 = var3.getBlockId(var4, var5, var6);
			if (var11 == Block.tilledField.blockID)
			{
				int var12 = var3.getBlockMetadata(var4, var5, var6);
				var3.setBlockAndMetadataWithNotify(var4, var5, var6, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, var12);
			}
			else if (var11 == FCBetterThanWolves.fcPlanter.blockID)
			{
				((FCBlockPlanter) ((FCBlockPlanter) FCBetterThanWolves.fcPlanter)).SetPlanterType(var3, var4, var5, var6, 2);
			}
			else if (var11 == Block.grass.blockID)
			{
				if (var3.provider.dimensionId == 1)
					return false;
				if (!var3.isRemote) this.GrowTallGrassAndFlowers(var3, var4, var5, var6);
			}
			else if (var11 == FCBetterThanWolves.fcBlockPlanterSoil.blockID) {
				FCBetterThanWolves.fcBlockPlanterSoil.AttemptToApplyFertilizerTo(var3, var4, var5, var6);
			}
			else if (var11 == FCBetterThanWolves.fcBlockFarmland.blockID) {
				FCBetterThanWolves.fcBlockFarmland.AttemptToApplyFertilizerTo(var3, var4, var5, var6);
			}

			--var1.stackSize;
			return true;
		}
		else return false;
	}
	private void GrowTallGrassAndFlowers(World CurrentWorld, int X, int Y, int Z)
	{
		if (Block.tallGrass.canBlockStay(CurrentWorld, X, Y + 1, Z) && CurrentWorld.isAirBlock(X, Y + 1, Z))
			CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 1);
	}
	@Override public String getUnlocalizedName(ItemStack I)
	{
		return super.getUnlocalizedName() + "." + ColorPlus_dyeColorNames[I.getItemDamage()&31];
	}
}