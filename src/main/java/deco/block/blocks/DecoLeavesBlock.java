package deco.block.blocks;

import btw.block.blocks.LeavesBlock;
import btw.item.items.ShearsItem;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class DecoLeavesBlock extends LeavesBlock {
    private int[] saplingIDs;

    private String[] textures;

    public DecoLeavesBlock(int blockID, int saplingID, String texture) {
        this(blockID, new int[]{saplingID}, new String[]{texture});
    }

    public DecoLeavesBlock(int blockID, int[] saplingIDs, String[] textures) {
        super(blockID);

        this.saplingIDs = saplingIDs;
        this.textures = textures;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int idDropped(int metadata, Random rand, int par3) {
        return saplingIDs[metadata & 3];
    }

    @Override
    public int damageDropped(int metadata) {
        return 0;
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
    
    @Override
    public boolean canMobsSpawnOn(World world, int x, int y, int k) {
        if (this.blockMaterial == DecoBlocks.hedgeMaterial) {
            return false;
        }
        else {
            return super.canMobsSpawnOn(world, x, y, k);
        }
    }
    
    //------------- Class Specific Methods ------------//
    
    public boolean spawnsJungleSpiders = false;
    
    public Block setSpawnsJungleSpiders() {
        this.blockMaterial = Material.leaves;
        this.spawnsJungleSpiders = true;
        return this;
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