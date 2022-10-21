package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.PlanterBlockBase;
import btw.block.blocks.legacy.LegacySaplingBlock;
import btw.world.feature.TreeUtils;
import btw.world.util.BlockPos;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public abstract class DecoSaplingBlock extends LegacySaplingBlock {
    private String[] baseTextureNames;

    public DecoSaplingBlock(int blockID, String[] baseTextureNames) {
        super(blockID);
        this.setStepSound(soundGrassFootstep);
        
        this.baseTextureNames = baseTextureNames;
    }
    
    @Override
    //Debug method
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return false;
        
        /*
        if (!world.isRemote) {
            growTree(world, x, y, z, world.rand);
        }
        
        return true;
         */
    }
    
    @Override
    public void growTree(World world, int x, int y, int z, Random rand) {
        int treeType = world.getBlockMetadata(x, y, z) & 3;
        
        GrowthResult result = this.checkFor2x2(world, x, y, z, treeType);
        
        if (this.canGrow2x2(treeType) && result.result != GrowthResult.NOT_2X2) {
            if (result.result == GrowthResult.FULLY_GROWN_2X2) {
                attemptGrowth2x2(world, result.loc.x, result.loc.y, result.loc.z, treeType, rand);
            }
        }
        else {
            boolean planter = Block.blocksList[world.getBlockId(x, y - 1, z)] instanceof PlanterBlockBase;
    
            world.setBlock(x, y, z, 0);
    
            boolean success = this.generateTree(world, rand, x, y, z, treeType);
    
            if (!success) {
                world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, treeType | 3 << 2);
            }
            else if (planter) {
                world.setBlockAndMetadata(x, y, z, this.getLogID(treeType), this.getLogMetadata(treeType));
                //Block break sfx
                world.playAuxSFX(2001, x, y - 1, z, BTWBlocks.planterWithSoil.blockID);
                world.setBlockAndMetadata(x, y - 1, z, this.getStumpID(treeType), this.getStumpMetadata(treeType));
            }
        }
    }
    
    //------------- Class Specific Methods ------------//
    
    public void attemptGrowth2x2(World world, int x, int y, int z, int treeType, Random rand) {
        world.setBlock(x, y, z, 0);
        world.setBlock(x + 1, y, z, 0);
        world.setBlock(x, y, z + 1, 0);
        world.setBlock(x + 1, y, z + 1, 0);
    
        boolean success = this.generateTree2x2(world, rand, x, y, z, treeType);
    
        if (!success) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, treeType | 3 << 2);
            world.setBlockAndMetadataWithNotify(x + 1, y, z, this.blockID, treeType | 3 << 2);
            world.setBlockAndMetadataWithNotify(x, y, z + 1, this.blockID, treeType | 3 << 2);
            world.setBlockAndMetadataWithNotify(x + 1, y, z + 1, this.blockID, treeType | 3 << 2);
        }
    }
    
    public boolean canGrow2x2(int treeType) {
        return false;
    }
    
    public GrowthResult checkFor2x2(World world, int x, int y, int z, int treeType) {
        int xOffset = 1;
    
        while (xOffset >= -1) {
            int zOffset = 1;
        
            while (zOffset >= -1) {
                if (isSameSapling(world, x + xOffset, y, z + zOffset, treeType) &&
                        isSameSapling(world, x + xOffset + 1, y, z + zOffset, treeType) &&
                        isSameSapling(world, x + xOffset, y, z + zOffset + 1, treeType) &&
                        isSameSapling(world, x + xOffset + 1, y, z + zOffset + 1, treeType))
                {
                    if (getSaplingGrowthStage(world, x + xOffset, y, z + zOffset) == 3 && getSaplingGrowthStage(world, x + xOffset + 1, y, z + zOffset) == 3 &&
                            getSaplingGrowthStage(world, x + xOffset, y, z + zOffset + 1) == 3 &&
                            getSaplingGrowthStage(world, x + xOffset + 1, y, z + zOffset + 1) == 3)
                    {
                        return new GrowthResult(new BlockPos(x + xOffset + 1, y, z + zOffset + 1));
                    }
                    else {
                        // we have 4 saplings, but they aren't all fully grown.  Bomb out entirely
                        return new GrowthResult(GrowthResult.NOT_ALL_GROWN_FOR_2X2);
                    }
                }
            
                zOffset--;
            }
        
            xOffset--;
        }
        
        return new GrowthResult(GrowthResult.NOT_2X2);
    }
    
    public abstract boolean generateTree(World world, Random rand, int x, int y, int z, int treeType);
    
    public boolean generateTree2x2(World world, Random rand, int x, int y, int z, int treeType) {
        return false;
    }
    
    public abstract int getLogID(int type);
    
    public int getLogMetadata(int type) {
        return 0;
    }
    
    public abstract int getStumpID(int type);
    
    public int getStumpMetadata(int type) {
        return 0;
    }
    
    private static class GrowthResult {
        static final int NOT_2X2 = 0;
        static final int NOT_ALL_GROWN_FOR_2X2 = 1;
        static final int FULLY_GROWN_2X2 = 2;
        
        int result;
        
        BlockPos loc;
        
        GrowthResult(int result) {
            this.result = result;
        }
        
        GrowthResult(BlockPos loc) {
            this.result = FULLY_GROWN_2X2;
            this.loc = loc;
        }
    }
    
    //------------- Built In Generators ------------//
    
    public boolean generateStandardTree(World world, Random rand, int x, int y, int z, int logID, int stumpID, int leafID) {
        return this.generateStandardTree(world, rand, x, y, z, logID, 0, stumpID, 0, leafID, 0, 5);
    }
    
    public boolean generateStandardTree(World world, Random rand, int x, int y, int z, int logID, int stumpID, int leafID, int baseHeight) {
        return this.generateStandardTree(world, rand, x, y, z, logID, 0, stumpID, 0, leafID, 0, baseHeight);
    }
    
    public boolean generateStandardTree(World world, Random rand, int x, int y, int z, int logID, int logMetadata, int stumpID, int stumpMetadata, int leafID,
            int leafMetadata)
    {
        return this.generateStandardTree(world, rand, x, y, z, logID, logMetadata, stumpID, stumpMetadata, leafID, leafMetadata, 5);
    }
    
    public boolean generateStandardTree(World world, Random rand, int x, int y, int z, int logID, int logMetadata, int stumpID, int stumpMetadata, int leafID,
            int leafMetadata, int baseHeight)
    {
        int treeHeight = rand.nextInt(3) + baseHeight;
        boolean shouldGrow = true;
        
        if (y >= 1 && y + treeHeight + 1 <= 256) {
            for (int j = y; j <= y + 1 + treeHeight; ++j) {
                byte widthForCheck = 1;
                
                if (j == y) {
                    widthForCheck = 0;
                }
                
                if (j >= y + 1 + treeHeight - 2) {
                    widthForCheck = 2;
                }
                
                for (int i = x - widthForCheck; i <= x + widthForCheck && shouldGrow; ++i) {
                    for (int k = z - widthForCheck; k <= z + widthForCheck && shouldGrow; ++k) {
                        if (j >= 0 && j < 256) {
                            int blockID = world.getBlockId(i, j, k);
                            
                            if (!world.isAirBlock(i, j, k) && !Block.blocksList[blockID].isLog(world, x, y, z) && !Block.blocksList[blockID].isLeafBlock(world, x, y, z)) {
                                shouldGrow = false;
                            }
                        }
                        else {
                            shouldGrow = false;
                        }
                    }
                }
            }
            
            if (!shouldGrow) {
                return false;
            }
            else {
                int blockIDBelow = world.getBlockId(x, y - 1, z);
                
                if (TreeUtils.canSaplingGrowOnBlock(world, x, y - 1, z) && y < 256 - treeHeight - 1) {
                    if (blockIDBelow == Block.grass.blockID) {
                        world.setBlockWithNotify(x, y - 1, z, Block.dirt.blockID);
                    }
                    
                    byte canopyHeight = 3;
                    
                    for (int j = y - canopyHeight + treeHeight; j <= y + treeHeight; ++j) {
                        int offsetY = j - (y + treeHeight);
                        int leafSizeXZ = 1 - offsetY / 2;
                        
                        for (int i = x - leafSizeXZ; i <= x + leafSizeXZ; ++i) {
                            int offsetX = i - x;
                            
                            for (int k = z - leafSizeXZ; k <= z + leafSizeXZ; ++k) {
                                int offsetZ = k - z;
                                
                                if ((Math.abs(offsetX) != leafSizeXZ || Math.abs(offsetZ) != leafSizeXZ || rand.nextInt(2) != 0 && offsetY != 0) && !Block.opaqueCubeLookup[world.getBlockId(i, j, k)]) {
                                    world.setBlockAndMetadataWithNotify(i, j, k, leafID, leafMetadata);
                                }
                            }
                        }
                    }
                    
                    for (int j = 0; j < treeHeight; ++j) {
                        int blockID = world.getBlockId(x, y + j, z);
                        
                        if (world.isAirBlock(x, y + j, z) || Block.blocksList[blockID].isLeafBlock(world, x, y, z)) {
                            world.setBlockAndMetadataWithNotify(x, y + j, z, logID, logMetadata);
                        }
                    }
                    
                    if (treeHeight > 2) {
                        int blockID = world.getBlockId(x, y, z);
                        
                        if (blockID == logID) {
                            int metadata = world.getBlockMetadata(x, y, z);
                            
                            if (metadata == logMetadata) {
                                world.setBlockAndMetadata(x, y, z, stumpID, stumpMetadata);
                            }
                        }
                    }
                    
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    private Icon[][] iconArray;

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        iconArray = new Icon[baseTextureNames.length][4];

        for (int i = 0; i < baseTextureNames.length; i++) {
            for (int j = 0; j < 4; j++) {
                iconArray[i][j] = register.registerIcon(baseTextureNames[i] + j);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
        int subtype = meta & 3;
        int growthStage = (meta & -4) >> 2;
        return this.iconArray[subtype][growthStage];
    }
    
    @Environment(EnvType.CLIENT)
    @Override
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < baseTextureNames.length; i++) {
            list.add(new ItemStack(blockID, 1, i));
            list.add(new ItemStack(blockID, 1, i | 12));
        }
    }
}