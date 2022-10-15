package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LogBlock;
import btw.block.blocks.PlanksBlock;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import deco.block.util.BlockInterface;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class DecoLogBlock extends LogBlock implements BlockInterface {
    public static final int TYPE_LOG = 0;
    public static final int TYPE_STRIPPED = 1;
    public static final int TYPE_WOOD = 2;
    public static final int TYPE_STRIPPED_WOOD = 3;
    
    private String[] topTextures;
    private String[] sideTextures;
    
    private boolean[] isStripped;

    private int[] woodTypes;
    public int[] chewedLogIDs;
    
    public DecoLogBlock(int blockID, int woodType, int chewedLogID, String[] topTextures, String[] sideTextures) {
        this(blockID,
                new int[] {woodType, woodType, woodType, woodType},
                new int[] {chewedLogID, chewedLogID, chewedLogID, chewedLogID},
                new boolean[] {false, true, false, true},
                topTextures, sideTextures);
    }
    
    public DecoLogBlock(int blockID, int[] woodTypes, int[] chewedLogIDs, boolean[] isStripped, String[] topTextures, String[] sideTextures) {
        super(blockID);

        this.woodTypes = woodTypes;
        this.chewedLogIDs = chewedLogIDs;
        
        this.isStripped = isStripped;

        this.topTextures = topTextures;
        this.sideTextures = sideTextures;
    }

    @Override
    public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int oldMetadata = world.getBlockMetadata(x, y, z);

        int orientation = (oldMetadata >> 2) & 3;
        int newMetadata = BTWBlocks.oakChewedLog.setOrientation(oldMetadata, orientation);

        world.setBlockAndMetadataWithNotify(x, y, z, this.chewedLogIDs[oldMetadata & 3], newMetadata);

        if (!world.isRemote && !isStripped(oldMetadata)) {
            ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.bark, 1, this.woodTypes[oldMetadata & 3]), side);
        }

        return true;
    }

    @Override
    public int getFurnaceBurnTime(int itemDamage) {
        return PlanksBlock.getFurnaceBurnTimeByWoodType(this.woodTypes[itemDamage & 3]);
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
        dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 6, 0, chanceOfDrop);
        
        if (this.isStripped(metadata)) {
            dropItemsIndividually(world, x, y, z, BTWItems.bark.itemID, 1, this.woodTypes[metadata & 3], chanceOfDrop);
        }

        return true;
    }
    
    @Override
    public boolean canCocoaBeansGrowOnBlock(IBlockAccess blockAccess, int x, int y, int z) {
        int logType = blockAccess.getBlockMetadata(x, y, z) & 3;
        int woodType = this.woodTypes[logType];
        
        return WoodTypeHelper.canWoodTypeGrowCocoa(woodType);
    }

    //------------- Log Override Methods ------------//

    @Override
    public boolean getIsStump(int iMetadata) {
        return false;
    }

    //------------- Class Specific Methods ------------//

    public boolean isStripped(int metadata) {
        return this.isStripped[(metadata & 3)];
    }
    
    public int getWoodType(int metadata) {
        return this.woodTypes[metadata & 3];
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    public Icon[] topIconArray;
    @Environment(EnvType.CLIENT)
    public Icon[] sideIconArray;

    @Environment(EnvType.CLIENT)
    @Override
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

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {
        topIconArray = new Icon[topTextures.length];
        sideIconArray = new Icon[sideTextures.length];

        for (int i = 0; i < topTextures.length; i++) {
            topIconArray[i] = iconRegister.registerIcon(topTextures[i]);
            sideIconArray[i] = iconRegister.registerIcon(sideTextures[i]);
        }

        super.registerIcons(iconRegister);
    }
    
    @Environment(EnvType.CLIENT)
    @Override
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < topTextures.length; i++) {
            list.add(new ItemStack(blockID, 1, i));
        }
    }
}
