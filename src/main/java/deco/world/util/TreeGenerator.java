package deco.world.util;

import btw.world.feature.TreeUtils;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.Direction;
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

                            if (!world.isAirBlock(var13, var11, var14) && var15 != DecoBlocks.mahoganyLeaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != DecoBlocks.mahoganyLog.blockID && var15 != DecoBlocks.cherryLog.blockID && var15 != DecoBlocks.cherryLeaves.blockID) {
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

                        if (world.isAirBlock(x, y + var14, z) || var15 == DecoBlocks.mahoganyLeaves.blockID || var15 == DecoBlocks.cherryLeaves.blockID) {
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
    
    public static boolean generateMahogany(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(8) + 8;
        boolean var7 = true;
        
        boolean vinesGrow = true;
        
        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            byte var9;
            int var11;
            int var12;
            
            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                var9 = 1;
                
                if (var8 == par4)
                {
                    var9 = 0;
                }
                
                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var9 = 2;
                }
                
                for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
                {
                    for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
                    {
                        if (var8 >= 0 && var8 < 256)
                        {
                            var12 = par1World.getBlockId(var10, var8, var11);
                            
                            if (var12 != 0 && var12 != DecoBlocks.mahoganyLeaves.blockID && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && var12 != DecoBlocks.mahoganyLog.blockID)
                            {
                                var7 = false;
                            }
                        }
                        else
                        {
                            var7 = false;
                        }
                    }
                }
            }
            
            if (!var7)
                return false;
            else
            {
                var8 = par1World.getBlockId(par3, par4 - 1, par5);
                
                if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 256 - var6 - 1)
                {
                    par1World.setBlock(par3, par4 - 1, par5, Block.dirt.blockID);
                    var9 = 3;
                    byte var18 = 0;
                    int var13;
                    int var14;
                    int var15;
                    
                    for (var11 = par4 - var9 + var6; var11 <= par4 + var6; ++var11)
                    {
                        var12 = var11 - (par4 + var6);
                        var13 = var18 + 1 - var12;
                        
                        for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                        {
                            var15 = var14 - par3;
                            
                            for (int var16 = par5 - var13; var16 <= par5 + var13; ++var16)
                            {
                                int var17 = var16 - par5;
                                
                                if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var14, var11, var16)])
                                {
                                    par1World.setBlockAndMetadata(var14, var11, var16, DecoBlocks.mahoganyLeaves.blockID, 0);
                                }
                            }
                        }
                    }
                    
                    for (var11 = 0; var11 < var6; ++var11)
                    {
                        var12 = par1World.getBlockId(par3, par4 + var11, par5);
                        
                        if (var12 == 0 || var12 == DecoBlocks.mahoganyLeaves.blockID)
                        {
                            par1World.setBlockAndMetadata(par3, par4 + var11, par5, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3 - 3, par4 + (var6 - 3), par5, DecoBlocks.mahoganyLog.blockID, 4);
                            par1World.setBlockAndMetadata(par3 + 3, par4 + (var6 - 3), par5, DecoBlocks.mahoganyLog.blockID, 4);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 3), par5 - 3, DecoBlocks.mahoganyLog.blockID, 8);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 3), par5 + 3, DecoBlocks.mahoganyLog.blockID, 8);
                            par1World.setBlockAndMetadata(par3 - 2, par4 + (var6 - 4), par5, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3 + 2, par4 + (var6 - 4), par5, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 4), par5 - 2, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 4), par5 + 2, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3 - 2, par4 + (var6 - 5), par5, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3 + 2, par4 + (var6 - 5), par5, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 5), par5 - 2, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 5), par5 + 2, DecoBlocks.mahoganyLog.blockID, 0);
                            par1World.setBlockAndMetadata(par3 - 1, par4 + (var6 - 6), par5, DecoBlocks.mahoganyLog.blockID, 4);
                            par1World.setBlockAndMetadata(par3 + 1, par4 + (var6 - 6), par5, DecoBlocks.mahoganyLog.blockID, 4);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 6), par5 - 1, DecoBlocks.mahoganyLog.blockID, 8);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 6), par5 + 1, DecoBlocks.mahoganyLog.blockID, 8);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 3), par5, DecoBlocks.mahoganyLeaves.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 2), par5, DecoBlocks.mahoganyLeaves.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 1), par5, DecoBlocks.mahoganyLeaves.blockID, 0);
                            par1World.setBlockAndMetadata(par3, par4 + (var6), par5, DecoBlocks.mahoganyLeaves.blockID, 0);
                            par1World.setBlock(par3, par4 + (var6 - 4), par5, 0);
                            par1World.setBlock(par3, par4 + (var6 - 5), par5, 0);
                            par1World.setBlockAndMetadata(par3 - 1, par4 + (var6 - 3), par5, DecoBlocks.mahoganyLog.blockID, 4);
                            par1World.setBlockAndMetadata(par3 + 1, par4 + (var6 - 3), par5, DecoBlocks.mahoganyLog.blockID, 4);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 3), par5 - 1, DecoBlocks.mahoganyLog.blockID, 8);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 3), par5 + 1, DecoBlocks.mahoganyLog.blockID, 8);
                            par1World.setBlockAndMetadata(par3, par4 + (var6 - 2), par5, DecoBlocks.mahoganyLog.blockID, 0);
                            
                            if (vinesGrow && var11 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var11, par5))
                                {
                                    par1World.setBlockAndMetadata(par3 - 1, par4 + var11, par5, Block.vine.blockID, 8);
                                }
                                
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var11, par5))
                                {
                                    par1World.setBlockAndMetadata(par3 + 1, par4 + var11, par5, Block.vine.blockID, 2);
                                }
                                
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 - 1))
                                {
                                    par1World.setBlockAndMetadata(par3, par4 + var11, par5 - 1, Block.vine.blockID, 1);
                                }
                                
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 + 1))
                                {
                                    par1World.setBlockAndMetadata(par3, par4 + var11, par5 + 1, Block.vine.blockID, 4);
                                }
                            }
                        }
                    }
                    
                    par1World.setBlockMetadataWithClient(par3, par4, par5, 0 | 12); // place stump
                    
                    if (vinesGrow)
                    {
                        for (var11 = par4 - 3 + var6; var11 <= par4 + var6; ++var11)
                        {
                            var12 = var11 - (par4 + var6);
                            var13 = 2 - var12 / 2;
                            
                            for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                            {
                                for (var15 = par5 - var13; var15 <= par5 + var13; ++var15)
                                {
                                    if (par1World.getBlockId(var14, var11, var15) == DecoBlocks.mahoganyLeaves.blockID)
                                    {
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0)
                                        {
                                            growVines(par1World, var14 - 1, var11, var15, 8);
                                        }
                                        
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0)
                                        {
                                            growVines(par1World, var14 + 1, var11, var15, 2);
                                        }
                                        
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0)
                                        {
                                            growVines(par1World, var14, var11, var15 - 1, 1);
                                        }
                                        
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0)
                                        {
                                            growVines(par1World, var14, var11, var15 + 1, 4);
                                        }
                                    }
                                }
                                
                                if (par2Random.nextInt(5) == 0 && var6 > 5)
                                {
                                    for (int i = 0; i < 2; ++i)
                                    {
                                        for (int j = 0; j < 4; ++j)
                                        {
                                            if (par2Random.nextInt(4 - i) == 0)
                                            {
                                                var13 = par2Random.nextInt(3);
                                                par1World.setBlockAndMetadata(par3 + Direction.offsetX[Direction.rotateOpposite[j]], par4 + var6 - 5 + i, par5 + Direction.offsetZ[Direction.rotateOpposite[j]], Block.cocoaPlant.blockID, var13 << 2 | j);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    return true;
                } else
                    return false;
            }
        } else
            return false;
    }
    
    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private static void growVines(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.setBlockAndMetadata(par2, par3, par4, Block.vine.blockID, par5);
        int var6 = 4;
        
        while (true)
        {
            --par3;
            
            if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0)
                return;
            
            par1World.setBlockAndMetadata(par2, par3, par4, Block.vine.blockID, par5);
            --var6;
        }
    }
    
    public static boolean generateMangrove(World world, Random rand, int x, int y, int z) {
        return false;
    }
}
