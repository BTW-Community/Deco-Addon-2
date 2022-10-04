package deco.world.util;

import btw.world.feature.TreeUtils;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;

import java.util.Random;

public class TreeGenerator {
    public static boolean generateCherry(World world, Random rand, int x, int y, int z) {
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

                            if (!world.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID && var15 != DecoBlocks.cherryLog.blockID && var15 != DecoBlocks.cherryLeaves.blockID) {
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

                        if (world.isAirBlock(x, y + var14, z) || var15 == Block.leaves.blockID || var15 == DecoBlocks.cherryLeaves.blockID) {
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
    
    public static boolean generateAcacia(World world, Random rand, int x, int y, int z)
    {
        int baseHeight = 4 + rand.nextInt(3);
        
        //Base tree
        for(int i = 1; i < baseHeight + 4; i++) {
            int blockID = world.getBlockId(x, y + i, z);
            
            //Checks trunk space
            if (!(world.isAirBlock(x, y + i, z) || blockID == DecoBlocks.acaciaLeaves.blockID)) {
                return false;
            }
        }
        
        for(int i = 0; i < baseHeight + 3; i++) {
            world.setBlock(x, y + i, z, DecoBlocks.acaciaLog.blockID);
        }
        
        createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 3, z - 0, 3);
        createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 4, z - 0, 2);
        
        world.setBlock(x, y, z, DecoBlocks.acaciaStump.blockID);
        
        //Branches
        if(rand.nextInt(4) == 0 &&
                (world.isAirBlock(x + 0, y + baseHeight + 1, z + 1) || world.getBlockId(x + 0, y + baseHeight + 1, z + 1) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 1, y + baseHeight + 2, z + 2) || world.getBlockId(x + 1, y + baseHeight + 2, z + 2) == DecoBlocks.acaciaLeaves.blockID)) {
            world.setBlock(x + 0, y + baseHeight + 1, z + 1, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 1, y + baseHeight + 2, z + 2, DecoBlocks.acaciaLog.blockID);
            createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z + 2, 3);
            createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 3, z + 2, 2);
        }
        
        if(rand.nextInt(4) == 0 &&
                (world.isAirBlock(x + 1, y + baseHeight + 0, z + 0) || world.getBlockId(x + 1, y + baseHeight + 0, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 2, y + baseHeight + 1, z + 0) || world.getBlockId(x + 2, y + baseHeight + 1, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 3, y + baseHeight + 2, z + 1) || world.getBlockId(x + 3, y + baseHeight + 2, z + 1) == DecoBlocks.acaciaLeaves.blockID)) {
            world.setBlock(x + 1, y + baseHeight + 0, z + 0, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 2, y + baseHeight + 1, z + 0, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 3, y + baseHeight + 2, z - 1, DecoBlocks.acaciaLog.blockID);
            createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 1, 3);
            createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 1, 2);
        }
        
        if(rand.nextInt(4) == 0 &&
                (world.isAirBlock(x - 1, y + baseHeight + 0, z + 0) || world.getBlockId(x - 1, y + baseHeight + 0, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x - 2, y + baseHeight + 1, z + 0) || world.getBlockId(x - 2, y + baseHeight + 1, z + 0) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x - 3, y + baseHeight + 2, z - 1) || world.getBlockId(x - 3, y + baseHeight + 2, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x - 4, y + baseHeight + 3, z - 2) || world.getBlockId(x - 4, y + baseHeight + 3, z - 2) == DecoBlocks.acaciaLeaves.blockID)) {
            world.setBlock(x - 1, y + baseHeight + 0, z + 0, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x - 2, y + baseHeight + 1, z + 0, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x - 3, y + baseHeight + 2, z - 1, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x - 4, y + baseHeight + 3, z - 2, DecoBlocks.acaciaLog.blockID);
            createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 4, z - 2, 3);
            createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 5, z - 2, 2);
        }
        
        if(rand.nextInt(4) == 0 &&
                (world.isAirBlock(x + 0, y + baseHeight + 0, z - 1) || world.getBlockId(x + 0, y + baseHeight + 0, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 1, y + baseHeight + 1, z - 1) || world.getBlockId(x + 1, y + baseHeight + 1, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 2, y + baseHeight + 2, z - 2) || world.getBlockId(x + 2, y + baseHeight + 2, z - 2) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 3, y + baseHeight + 3, z - 2) || world.getBlockId(x + 3, y + baseHeight + 3, z - 2) == DecoBlocks.acaciaLeaves.blockID)) {
            world.setBlock(x + 0, y + baseHeight + 0, z - 1, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 1, y + baseHeight + 1, z - 2, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 2, y + baseHeight + 2, z - 2, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 3, y + baseHeight + 3, z - 2, DecoBlocks.acaciaLog.blockID);
            createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 2, 3);
            createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 2, 2);
        }
        
        if(rand.nextInt(4) == 0 &&
                (world.isAirBlock(x + 0, y + baseHeight + 0, z - 1) || world.getBlockId(x + 0, y + baseHeight + 0, z - 1) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 0, y + baseHeight + 0, z - 2) || world.getBlockId(x + 0, y + baseHeight + 0, z - 2) == DecoBlocks.acaciaLeaves.blockID) &&
                (world.isAirBlock(x + 1, y + baseHeight + 1, z - 3) || world.getBlockId(x + 1, y + baseHeight + 0, z - 3) == DecoBlocks.acaciaLeaves.blockID)) {
            world.setBlock(x + 0, y + baseHeight + 0, z - 1, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 0, y + baseHeight + 0, z - 2, DecoBlocks.acaciaLog.blockID);
            world.setBlock(x + 1, y + baseHeight + 1, z - 3, DecoBlocks.acaciaLog.blockID);
            createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 1, z - 3, 3);
            createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z - 3, 2);
        }
        
        return true;
    }
    
    private static void createAcaciaLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
    {
        for(int i = -size + x; i < size + 1 + x; i++) {
            for(int k = -size + z; k < size + 1 + z; k++) {
                int currentID = par1World.getBlockId(i, y, k);
                
                if (currentID == 0)
                {
                    if(i == -size + x && k == -size + z ){} else if(i == -size + x && k == size + z ){} else if(i == size + x && k == -size + z ){} else if(i == size + x && k == size + z ){}
                    else { par1World.setBlock(i, y, k, DecoBlocks.acaciaLeaves.blockID); }
                }
            }
        }
        
        if(size==3){par1World.setBlock(x, y, z, DecoBlocks.acaciaLog.blockID);}
    }
    
    public static boolean generateMahogany(World world, Random rand, int x, int y, int z) {
        return false;
    }
    
    public static boolean generateMangrove(World world, Random rand, int x, int y, int z) {
        return false;
    }
}
