package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockCobble extends Block {
	public AddonBlockCobble(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"graniteCobble", "andesiteCobble", "dioriteCobble"}, new String[] {"Granite Cobblestone", "Andesite Cobblestone", "Diorite Cobblestone"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}
