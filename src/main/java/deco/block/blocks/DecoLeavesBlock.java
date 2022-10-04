package deco.block.blocks;

import btw.block.blocks.LeavesBlock;
import btw.item.items.ShearsItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class DecoLeavesBlock extends LeavesBlock {
    private final int SAPLING_ID;
    private int[] saplingMetadatas;

    private String[] textures;

    public DecoLeavesBlock(int blockID, int saplingID, String texture) {
        this(blockID, saplingID, new int[]{0}, new String[]{texture});
    }

    public DecoLeavesBlock(int blockID, int saplingID, int[] saplingMetas, String[] textures) {
        super(blockID);

        this.SAPLING_ID = saplingID;
        this.saplingMetadatas = saplingMetas;

        this.textures = textures;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int idDropped(int metadata, Random rand, int par3) {
        return SAPLING_ID;
    }

    @Override
    public int damageDropped(int metadata) {
        return saplingMetadatas[metadata & 3];
    }

    @Override
    public int quantityDropped(Random rand) {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ShearsItem) {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.blockID, 1, metadata & 3));
        }
        else {
            super.harvestBlock(world, player, x, y, z, metadata);
        }
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    private Icon[] iconArray;

    @Environment(EnvType.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return 0xffffff;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getRenderColor(int metadata) {
        return 0xffffff;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        iconArray = new Icon[textures.length];

        for (int i = 0; i < iconArray.length; i++) {
            iconArray[i] = register.registerIcon(textures[i]);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata) {
        return iconArray[metadata & 3];
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < textures.length; i++) {
            list.add(new ItemStack(blockID, 1, i));
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return true;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean shouldRenderNeighborFullFaceSide(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return true;
    }
}