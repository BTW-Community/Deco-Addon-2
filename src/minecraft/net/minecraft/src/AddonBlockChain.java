package net.minecraft.src;

import java.util.Random;

public class AddonBlockChain extends Block {
    private final FCModelBlock blockModel = new AddonModelBlockChain();

	public AddonBlockChain(int ID)
	{
		super(ID, Material.iron);
		setUnlocalizedName("ginger_chain");
		setStepSound(Block.soundMetalFootstep);
		//setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(0.5F);
		AddonManager.Register(this, "Chain");
		this.SetPicksEffectiveOn(true);
		this.InitBlockBounds(.4375D,	0.0D,	.4375D,		.5625D,		1.0D,	.5625D);
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
	@Override public boolean renderAsNormalBlock()
	{
		return false;
	}

	//CLIENT ONLY
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        FCModelBlock var6 = this.blockModel.MakeTemporaryCopy();
        return var6.RenderAsBlock(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.blockModel.RenderAsItemBlock(var1, this, var2);
    }
}
