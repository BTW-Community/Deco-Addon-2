package net.minecraft.src;

public class DecoItemBlockColored extends ItemBlockWithMetadata {
	public DecoItemBlockColored(int id, Block block) {
		super(id, block);
	}

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return Block.blocksList[this.m_iBlockID].getUnlocalizedName() + "." + DecoUtilsMisc.colorOrder[stack.getItemDamage()];
    }
}