package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class SpawnerAnimals
{
    /**
     * Given a chunk, find a random position in it.
     */
    protected static ChunkPosition getRandomSpawningPointInChunk(World par0World, int par1, int par2)
    {
        Chunk var3 = par0World.getChunkFromChunkCoords(par1, par2);
        int var4 = par1 * 16 + par0World.rand.nextInt(16);
        int var5 = par2 * 16 + par0World.rand.nextInt(16);
        int var6 = par0World.rand.nextInt(var3 == null ? par0World.getActualHeight() : var3.getTopFilledSegment() + 16 - 1);
        return new ChunkPosition(var4, var6, var5);
    }

    /**
     * adds all chunks within the spawn radius of the players to eligibleChunksForSpawning. pars: the world,
     * hostileCreatures, passiveCreatures. returns number of eligible chunks.
     */
    public static final int findChunksForSpawning(WorldServer par0WorldServer, boolean par1, boolean par2, boolean par3)
    {
        if (!par1 && !par2)
        {
            return 0;
        }
        else
        {
            LinkedList var4 = par0WorldServer.GetActiveChunksCoordsList();
            int var5 = 0;
            EnumCreatureType[] var7 = EnumCreatureType.values();
            int var6 = var7.length;

            for (int var8 = 0; var8 < var6; ++var8)
            {
                EnumCreatureType var9 = var7[var8];

                if ((!var9.getPeacefulCreature() || par2) && (var9.getPeacefulCreature() || par1) && (!var9.getAnimal() || par3) && par0WorldServer.CountEntitiesThatApplyToSpawnCap(var9.getCreatureClass()) <= var9.getMaxNumberOfCreature() * var4.size() / 256)
                {
                    Iterator var10 = var4.iterator();
                    label77:

                    while (var10.hasNext())
                    {
                        ChunkCoordIntPair var11 = (ChunkCoordIntPair)var10.next();
                        ChunkPosition var12 = getRandomSpawningPointInChunk(par0WorldServer, var11.chunkXPos, var11.chunkZPos);
                        int var13 = var12.x;
                        int var14 = var12.y;
                        int var15 = var12.z;

                        if (!par0WorldServer.isBlockNormalCube(var13, var14, var15) && CanCreatureTypeSpawnInMaterial(var9, par0WorldServer.getBlockMaterial(var13, var14, var15)))
                        {
                            int var16 = 0;
                            int var17 = 0;

                            while (var17 < 3)
                            {
                                int var18 = var13;
                                int var19 = var14;
                                int var20 = var15;
                                byte var21 = 6;
                                SpawnListEntry var22 = null;
                                int var23 = 0;

                                while (true)
                                {
                                    if (var23 < 4)
                                    {
                                        label70:
                                        {
                                            var18 += par0WorldServer.rand.nextInt(var21) - par0WorldServer.rand.nextInt(var21);
                                            var19 += par0WorldServer.rand.nextInt(1) - par0WorldServer.rand.nextInt(1);
                                            var20 += par0WorldServer.rand.nextInt(var21) - par0WorldServer.rand.nextInt(var21);

                                            if (canCreatureTypeSpawnAtLocation(var9, par0WorldServer, var18, var19, var20))
                                            {
                                                float var24 = (float)var18 + 0.5F;
                                                float var25 = (float)var19;
                                                var25 += GetVerticalOffsetForPos(var9, par0WorldServer, var18, var19, var20);
                                                var25 += 0.01F;
                                                float var26 = (float)var20 + 0.5F;

                                                if (par0WorldServer.getClosestPlayer((double)var24, (double)var25, (double)var26, 24.0D) == null)
                                                {
                                                    if (var22 == null)
                                                    {
                                                        var22 = par0WorldServer.spawnRandomCreature(var9, var18, var19, var20);

                                                        if (var22 == null)
                                                        {
                                                            break label70;
                                                        }
                                                    }

                                                    EntityLiving var27;

                                                    try
                                                    {
                                                        var27 = (EntityLiving)var22.entityClass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par0WorldServer});
                                                    }
                                                    catch (Exception var29)
                                                    {
                                                        var29.printStackTrace();
                                                        return var5;
                                                    }

                                                    var27.setLocationAndAngles((double)var24, (double)var25, (double)var26, par0WorldServer.rand.nextFloat() * 360.0F, 0.0F);

                                                    if (var27.getCanSpawnHere())
                                                    {
                                                        ++var16;
                                                        par0WorldServer.spawnEntityInWorld(var27);
                                                        creatureSpecificInit(var27, par0WorldServer, var24, var25, var26);

                                                        if (var16 >= var27.getMaxSpawnedInChunk())
                                                        {
                                                            continue label77;
                                                        }
                                                    }

                                                    var5 += var16;
                                                }
                                            }

                                            ++var23;
                                            continue;
                                        }
                                    }

                                    ++var17;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            return var5;
        }
    }

    /**
     * determines if a skeleton spawns on a spider, and if a sheep is a different color
     */
    private static void creatureSpecificInit(EntityLiving par0EntityLiving, World par1World, float par2, float par3, float par4)
    {
        par0EntityLiving.initCreature();
    }

    /**
     * Called during chunk generation to spawn initial creatures.
     */
    public static void performWorldGenSpawning(World par0World, BiomeGenBase par1BiomeGenBase, int par2, int par3, int par4, int par5, Random par6Random)
    {
        List var7 = par1BiomeGenBase.getSpawnableList(EnumCreatureType.creature);

        if (!var7.isEmpty())
        {
            while (par6Random.nextFloat() < par1BiomeGenBase.getSpawningChance())
            {
                SpawnListEntry var8 = (SpawnListEntry)WeightedRandom.getRandomItem(par0World.rand, var7);
                int var9 = var8.minGroupCount + par6Random.nextInt(1 + var8.maxGroupCount - var8.minGroupCount);
                int var10 = par2 + par6Random.nextInt(par4);
                int var11 = par3 + par6Random.nextInt(par5);
                int var12 = var10;
                int var13 = var11;

                for (int var14 = 0; var14 < var9; ++var14)
                {
                    boolean var15 = false;

                    for (int var16 = 0; !var15 && var16 < 4; ++var16)
                    {
                        int var17 = par0World.getTopSolidOrLiquidBlock(var10, var11);

                        if (CanAnimalSpawnAtLocationDuringWorldGen(EnumCreatureType.creature, par0World, var10, var17, var11))
                        {
                            float var18 = (float)var10 + 0.5F;
                            float var19 = (float)var17;
                            float var20 = (float)var11 + 0.5F;
                            EntityLiving var21;

                            try
                            {
                                var21 = (EntityLiving)var8.entityClass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par0World});
                            }
                            catch (Exception var23)
                            {
                                var23.printStackTrace();
                                continue;
                            }

                            var21.setLocationAndAngles((double)var18, (double)var19, (double)var20, par6Random.nextFloat() * 360.0F, 0.0F);
                            par0World.spawnEntityInWorld(var21);
                            creatureSpecificInit(var21, par0World, var18, var19, var20);
                            var15 = true;
                        }

                        var10 += par6Random.nextInt(5) - par6Random.nextInt(5);

                        for (var11 += par6Random.nextInt(5) - par6Random.nextInt(5); var10 < par2 || var10 >= par2 + par4 || var11 < par3 || var11 >= par3 + par4; var11 = var13 + par6Random.nextInt(5) - par6Random.nextInt(5))
                        {
                            var10 = var12 + par6Random.nextInt(5) - par6Random.nextInt(5);
                        }
                    }
                }
            }
        }
    }

    public static boolean CanAnimalSpawnAtLocationDuringWorldGen(EnumCreatureType var0, World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (!Block.isNormalCube(var5) && !var1.getBlockMaterial(var2, var3, var4).isLiquid() && var5 != Block.leaves.blockID)
        {
            int var6 = var1.getBlockId(var2, var3 + 1, var4);

            if (!Block.isNormalCube(var6) && var6 != Block.leaves.blockID)
            {
                int var7 = var1.getBlockId(var2, var3 - 1, var4);
                return Block.isNormalCube(var7) && var7 != Block.bedrock.blockID;
            }
        }

        return false;
    }

    public static boolean CanWitchSpawnAtLocationDuringWorldGen(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);

        if (!Block.isNormalCube(var4) && !var0.getBlockMaterial(var1, var2, var3).isLiquid() && var4 != Block.leaves.blockID)
        {
            int var5 = var0.getBlockId(var1, var2 + 1, var3);

            if (!Block.isNormalCube(var5) && var5 != Block.leaves.blockID)
            {
                int var6 = var0.getBlockId(var1, var2 - 1, var3);
                return var6 != Block.bedrock.blockID && (Block.isNormalCube(var6) || var0.getBlockMaterial(var1, var2 - 1, var3) == Material.water || var6 == Block.leaves.blockID);
            }
        }

        return false;
    }

    /**
     * Returns whether or not the specified creature type can spawn at the specified location.
     */
    public static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType par0EnumCreatureType, World par1World, int par2, int par3, int par4)
    {
        if (par0EnumCreatureType.getCreatureMaterial() == Material.water)
        {
            return par1World.getBlockMaterial(par2, par3, par4).isLiquid() && par1World.getBlockMaterial(par2, par3 - 1, par4).isLiquid() && !par1World.isBlockNormalCube(par2, par3 + 1, par4);
        }
        else if (!par1World.isBlockNormalCube(par2, par3, par4) && !par1World.getBlockMaterial(par2, par3, par4).isLiquid())
        {
            Block var5 = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
            return var5 != null && var5.CanMobsSpawnOn(par1World, par2, par3 - 1, par4);
        }
        else
        {
            return false;
        }
    }

    public static float GetVerticalOffsetForPos(EnumCreatureType var0, World var1, int var2, int var3, int var4)
    {
        if (var0.getCreatureMaterial() != Material.water)
        {
            Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var5 != null)
            {
                return var5.MobSpawnOnVerticalOffset(var1, var2, var3 - 1, var4);
            }
        }

        return 0.0F;
    }

    public static boolean CanCreatureTypeSpawnInMaterial(EnumCreatureType var0, Material var1)
    {
        return var1 == Material.water ? var0.getCreatureMaterial() == Material.water : var0.getCreatureMaterial() != Material.water;
    }
}
