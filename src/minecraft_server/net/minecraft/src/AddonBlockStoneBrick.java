package net.minecraft.src;

public class AddonBlockStoneBrick extends Block {

	protected AddonBlockStoneBrick(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"graniteBrick", "andesiteBrick", "dioriteBrick"}, new String[] {"Granite Brick", "Andesite Brick", "Diorite Brick"});
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
