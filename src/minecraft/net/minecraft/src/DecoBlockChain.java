package net.minecraft.src;

import java.util.Random;

public class DecoBlockChain extends Block {
    private final FCModelBlock blockModel = new DecoModelBlockChain();

	public DecoBlockChain(int ID)
	{
		super(ID, Material.iron);
		setUnlocalizedName("decoBlockChain");
		if (DecoManager.getNewSoundsInstalled())
			this.setStepSound(DecoDefs.stepSoundChain);
		else
			this.setStepSound(Block.soundMetalFootstep);
		//setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		setHardness(0.5F);
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

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || var5 == 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return DecoDefs.chainItem.itemID;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return DecoDefs.chainItem.itemID;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

	//CLIENT ONLY
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        FCModelBlock var6 = this.blockModel.MakeTemporaryCopy();
        return var6.RenderAsBlock(var1, this, var2, var3, var4);
    }
}
