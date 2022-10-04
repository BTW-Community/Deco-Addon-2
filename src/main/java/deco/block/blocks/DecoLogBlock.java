package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LogBlock;
import btw.block.blocks.PlanksBlock;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class DecoLogBlock extends LogBlock {
    public static final int TYPE_LOG = 0;
    public static final int TYPE_STRIPPED = 1;
    public static final int TYPE_WOOD = 2;
    public static final int TYPE_STRIPPED_WOOD = 3;
    
    private String[] topTextures;
    private String[] sideTextures;

    public final int WOOD_TYPE;
    public final int CHEWED_LOG_ID;

    public DecoLogBlock(int blockID, int woodType, int chewedLogID, String[] topTextures, String[] sideTextures) {
        super(blockID);

        this.WOOD_TYPE = woodType;
        this.CHEWED_LOG_ID = chewedLogID;

        this.topTextures = topTextures;
        this.sideTextures = sideTextures;
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int oldMetadata = world.getBlockMetadata(x, y, z);
        int newMetadata;

        int orientation = (oldMetadata >> 2) & 3;
        newMetadata = BTWBlocks.oakChewedLog.setOrientation(oldMetadata, orientation);

        world.setBlockAndMetadataWithNotify(x, y, z, this.CHEWED_LOG_ID, newMetadata);

        if (!world.isRemote && !isStripped(oldMetadata)) {
            ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.bark, 1, WOOD_TYPE), side);
        }

        return true;
    }

    @Override
    public int getFurnaceBurnTime(int itemDamage) {
        return PlanksBlock.getFurnaceBurnTimeByWoodType(WOOD_TYPE) * 4;
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
        dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 6, 0, chanceOfDrop);
        dropItemsIndividually(world, x, y, z, BTWItems.bark.itemID, 1, this.WOOD_TYPE, chanceOfDrop);

        return true;
    }

    //------------- Log Override Methods ------------//

    @Override
    public boolean getIsStump(int iMetadata) {
        return false;
    }

    //------------- Class Specific Methods ------------//

    public boolean isStripped(int metadata) {
        return (metadata & 1) == 1;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    public Icon[] topIconArray;
    @Environment(EnvType.CLIENT)
    public Icon[] sideIconArray;

    @Override
    @Environment(EnvType.CLIENT)
    public Icon getIcon(int side, int metadata) {
        int facing = metadata >> 2 & 3;

        if (facing == 0 && (side == 0 || side == 1)) {
            return topIconArray[metadata & 3];
        }
        else if (facing == 1 && (side == 4 || side == 5)) {
            return topIconArray[metadata & 3];
        }
        else if (facing == 2 && (side == 2 || side == 3)) {
            return topIconArray[metadata & 3];
        }

        return sideIconArray[metadata & 3];
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        topIconArray = new Icon[topTextures.length];
        sideIconArray = new Icon[sideTextures.length];

        for (int i = 0; i < topTextures.length; i++) {
            topIconArray[i] = iconRegister.registerIcon(topTextures[i]);
            sideIconArray[i] = iconRegister.registerIcon(sideTextures[i]);
        }

        super.registerIcons(iconRegister);
    }
}
