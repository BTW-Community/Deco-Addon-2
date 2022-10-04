package deco.item.itemblocks;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlockWithMetadata;
import net.minecraft.src.ItemStack;

public class DecoLogItemBlock extends ItemBlockWithMetadata {
    public DecoLogItemBlock(int blockID, Block referenceBlock) {
        super(blockID, referenceBlock);
    }

    @Override
    public int getCampfireBurnTime(int itemDamage) {
        // logs can't be burned directly in a campfire without being split first
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack reference) {
        String unlocalizedName = super.getUnlocalizedName();
        switch (reference.getItemDamage()) {
            case 0:
                unlocalizedName += ".log";
                break;
            case 1:
                unlocalizedName += ".stripped";
                break;
            case 2:
                unlocalizedName += ".wood";
                break;
            case 3:
                unlocalizedName += ".strippedWood";
                break;
        }

        return unlocalizedName;
    }
}
