package deco.block.blocks;

import btw.world.feature.TreeUtils;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class CherrySaplingBlock extends DecoSaplingBlock {
    public static final String[] saplingTypes = new String[] {
            "cherry", "", "", "",
            "cherry", "", "", "",
            "cherry", "", "", "",
            "cherryMature", "", "",  ""
    };

    public CherrySaplingBlock(int id) {
        super(id, new String[] {"decoBlockSaplingCherry_0"});
        this.setUnlocalizedName("decoBlockSaplingCherry");
    }
    
    @Override
    public boolean generateTree(World world, Random rand, int x, int y, int z) {
        int var5 = 5;
        int var6 = 0;
        int var7 = 0;
    
        int var9 = rand.nextInt(3) + var5;
        boolean var10 = true;
    
        if (y >= 1 && y + var9 + 1 <= 256) {
            int var11;
            byte var12;
            int var14;
            int var15;
        
            for (var11 = y; var11 <= y + 1 + var9; ++var11) {
                var12 = 1;
            
                if (var11 == y) {
                    var12 = 0;
                }
            
                if (var11 >= y + 1 + var9 - 2) {
                    var12 = 2;
                }
            
                for (int var13 = x - var12; var13 <= x + var12 && var10; ++var13) {
                    for (var14 = z - var12; var14 <= z + var12 && var10; ++var14) {
                        if (var11 >= 0 && var11 < 256) {
                            var15 = world.getBlockId(var13, var11, var14);
                        
                            if (!world.isAirBlock(var13, var11, var14) && !Block.blocksList[var15].isLog(world, x, y, z) && !Block.blocksList[var15].isLeafBlock(world, x, y, z)) {
                                var10 = false;
                            }
                        }
                        else {
                            var10 = false;
                        }
                    }
                }
            }
        
            if (!var10) {
                return false;
            }
            else {
                var11 = world.getBlockId(x, y - 1, z);
            
                if (TreeUtils.canSaplingGrowOnBlock(world, x, y - 1, z) && y < 256 - var9 - 1) {
                    if (var11 == Block.grass.blockID) {
                        world.setBlockWithNotify(x, y - 1, z, Block.dirt.blockID);
                    }
                
                    var12 = 3;
                    byte var21 = 0;
                    int var16;
                    int var17;
                    int var18;
                
                    for (var14 = y - var12 + var9; var14 <= y + var9; ++var14) {
                        var15 = var14 - (y + var9);
                        var16 = var21 + 1 - var15 / 2;
                    
                        for (var17 = x - var16; var17 <= x + var16; ++var17) {
                            var18 = var17 - x;
                        
                            for (int var19 = z - var16; var19 <= z + var16; ++var19) {
                                int var20 = var19 - z;
                            
                                if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || rand.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[world.getBlockId(var17, var14, var19)]) {
                                    world.setBlockAndMetadataWithNotify(var17, var14, var19, DecoBlocks.cherryLeaves.blockID, var7);
                                }
                            }
                        }
                    }
                
                    for (var14 = 0; var14 < var9; ++var14) {
                        var15 = world.getBlockId(x, y + var14, z);
                    
                        if (world.isAirBlock(x, y + var14, z) || Block.blocksList[var15].isLeafBlock(world, x, y, z)) {
                            world.setBlockAndMetadataWithNotify(x, y + var14, z, DecoBlocks.cherryLog.blockID, var6);
                        }
                    }
                
                    if (var9 > 2) {
                        var14 = world.getBlockId(x, y, z);
                    
                        if (var14 == DecoBlocks.cherryLog.blockID) {
                            var15 = world.getBlockMetadata(x, y, z);
                        
                            if (var15 == var6) {
                                world.setBlock(x, y, z, DecoBlocks.cherryStump.blockID);
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
    
    @Override
    public int getLogID() {
        return DecoBlocks.cherryLog.blockID;
    }
    
    @Override
    public int getStumpID() {
        return DecoBlocks.cherryStump.blockID;
    }
}
