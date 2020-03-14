package net.minecraft.src;

public class AddonBlockTerracottaUnfired extends FCBlockFallingFullBlock
{
	public AddonBlockTerracottaUnfired(int id) {
		super(id, Material.sand);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetFilterableProperties(8);
        this.setStepSound(soundSandFootstep);
        this.setUnlocalizedName("ginger_unfiredTerracotta");
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        AddonManager.Register(this, "Unfired Terracotta");
	}

    public void OnCookedByKiln(World var1, int var2, int var3, int var4) {
        var1.setBlockWithNotify(var2, var3, var4, 0);
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, AddonDefs.terracotta.blockID, 0);
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4) {
        return 0.8F;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4) {
        return 4;
    }
}