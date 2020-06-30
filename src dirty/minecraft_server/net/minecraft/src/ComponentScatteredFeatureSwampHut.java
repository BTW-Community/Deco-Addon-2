package net.minecraft.src;

import java.util.Random;

public class ComponentScatteredFeatureSwampHut extends ComponentScatteredFeature
{
    /** Whether this swamp hut has a witch. */
    private boolean hasWitch;
    private boolean m_bHasLootBasket = false;
    private static FCUtilsRandomItemStack[] m_LootBasketContents = null;

    public ComponentScatteredFeatureSwampHut(Random par1Random, int par2, int par3)
    {
        super(par1Random, par2, 64, par3, 7, 5, 9);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        if (!this.func_74935_a(par1World, par3StructureBoundingBox, 0))
        {
            return false;
        }
        else
        {
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 5, 1, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 2, 5, 4, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 0, 4, 1, 0, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 2, 3, 3, 2, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 2, 3, 1, 3, 6, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 2, 3, 5, 3, 6, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 7, 4, 3, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 2, 1, 3, 2, Block.wood.blockID, Block.wood.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 2, 5, 3, 2, Block.wood.blockID, Block.wood.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 7, 1, 3, 7, Block.wood.blockID, Block.wood.blockID, false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 7, 5, 3, 7, Block.wood.blockID, Block.wood.blockID, false);
            this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 2, 3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 3, 3, 7, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 1, 3, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 3, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 3, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.flowerPot.blockID, 7, 1, 3, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 1, 2, 2, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.brewingStand.blockID, 0, 2, 3, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 2, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 5, 2, 1, par3StructureBoundingBox);
            int var4 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
            int var5 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 1);
            int var6 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 0);
            int var7 = this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 1, 6, 4, 1, Block.stairsWoodSpruce.blockID, var4, Block.stairsWoodSpruce.blockID, var4, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 2, 0, 4, 7, Block.stairsWoodSpruce.blockID, var6, Block.stairsWoodSpruce.blockID, var6, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 4, 2, 6, 4, 7, Block.stairsWoodSpruce.blockID, var5, Block.stairsWoodSpruce.blockID, var5, false);
            this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 8, 6, 4, 8, Block.stairsWoodSpruce.blockID, var7, Block.stairsWoodSpruce.blockID, var7, false);
            int var8;
            int var9;

            for (var8 = 2; var8 <= 7; var8 += 5)
            {
                for (var9 = 1; var9 <= 5; var9 += 4)
                {
                    this.fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, var9, -1, var8, par3StructureBoundingBox);
                }
            }

            if (!this.m_bHasLootBasket)
            {
                this.AddLootBasket(par1World, par3StructureBoundingBox, 3, 2, 6);
            }

            if (!this.hasWitch)
            {
                var8 = this.getXWithOffset(2, 5);
                var9 = this.getYWithOffset(2);
                int var10 = this.getZWithOffset(2, 5);

                if (par3StructureBoundingBox.isVecInside(var8, var9, var10))
                {
                    this.hasWitch = true;
                    FCEntityWitch var11 = new FCEntityWitch(par1World);
                    var11.setLocationAndAngles((double)var8 + 0.5D, (double)var9, (double)var10 + 0.5D, 0.0F, 0.0F);
                    var11.initCreature();
                    var11.SetPersistent(true);
                    par1World.spawnEntityInWorld(var11);
                }

                this.SpawnAdditionalWitches(par1World);
            }

            return true;
        }
    }

    private void InitContentsArray()
    {
        m_LootBasketContents = new FCUtilsRandomItemStack[] {new FCUtilsRandomItemStack(FCBetterThanWolves.fcItemHempSeeds.itemID, 0, 1, 4, 5), new FCUtilsRandomItemStack(Item.glassBottle.itemID, 0, 2, 8, 10), new FCUtilsRandomItemStack(FCBetterThanWolves.fcItemMushroomRed.itemID, 0, 5, 16, 5)};
    }

    private void SpawnAdditionalWitches(World var1)
    {
        int var2 = 2;

        if (!this.hasWitch)
        {
            ++var2;
        }

        int var3 = this.boundingBox.minX >> 4 << 4;
        int var4 = this.boundingBox.minZ >> 4 << 4;
        byte var5 = 16;
        int var6 = 0;

        while (var6 < var2)
        {
            int var7 = 0;

            while (true)
            {
                if (var7 < 20)
                {
                    int var8 = var3 + var1.rand.nextInt(var5);
                    int var9 = var4 + var1.rand.nextInt(var5);
                    int var10 = var1.getTopSolidOrLiquidBlock(var8, var9);

                    if (!SpawnerAnimals.CanWitchSpawnAtLocationDuringWorldGen(var1, var8, var10, var9))
                    {
                        ++var7;
                        continue;
                    }

                    this.hasWitch = true;
                    FCEntityWitch var11 = new FCEntityWitch(var1);
                    var11.setLocationAndAngles((double)var8 + 0.5D, (double)var10, (double)var9 + 0.5D, 0.0F, 0.0F);
                    var11.initCreature();
                    var11.SetPersistent(true);
                    var1.spawnEntityInWorld(var11);
                }

                ++var6;
                break;
            }
        }
    }

    private void AddLootBasket(World var1, StructureBoundingBox var2, int var3, int var4, int var5)
    {
        if (m_LootBasketContents == null)
        {
            this.InitContentsArray();
        }

        int var6 = this.getXWithOffset(var3, var5);
        int var7 = this.getYWithOffset(var4);
        int var8 = this.getZWithOffset(var3, var5);

        if (var2.isVecInside(var6, var7, var8) && var1.getBlockId(var6, var7, var8) != FCBetterThanWolves.fcBlockBasketWicker.blockID)
        {
            this.m_bHasLootBasket = true;
            var1.setBlock(var6, var7, var8, FCBetterThanWolves.fcBlockBasketWicker.blockID, var1.rand.nextInt(4) | 4, 2);
            FCTileEntityBasketWicker var9 = (FCTileEntityBasketWicker)var1.getBlockTileEntity(var6, var7, var8);

            if (var9 != null)
            {
                var9.SetStorageStack(FCUtilsRandomItemStack.GetRandomStack(var1.rand, m_LootBasketContents));
            }
        }
    }
}
