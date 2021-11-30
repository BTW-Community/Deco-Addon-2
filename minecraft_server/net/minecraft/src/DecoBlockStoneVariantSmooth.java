package net.minecraft.src;

public class DecoBlockStoneVariantSmooth extends Block{
	public DecoBlockStoneVariantSmooth(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStoneSmooth");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.register(this, new String[] {"granite", "andesite", "diorite", "slate"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
	{
		return 3;
	}

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2272, var3, var4, var5, this.blockID + (var6 << 12));
        
        ItemStack curItem = var2.getCurrentEquippedItem();
        
        if (curItem != null && (curItem.itemID == Item.pickaxeStone.itemID || curItem.itemID == Item.pickaxeIron.itemID)) {
        	this.DropComponentItemsOnBadBreak(var1, var3, var4, var5, var6, 1.0F);
        }
    }

	public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
	{
		if (var5 == 0) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.graniteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 1) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.andesiteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 2) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.dioriteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 2) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.slateCobbleLoose.blockID, 1, 0, var6);
		}
		return true;
	}
}
