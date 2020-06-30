package net.minecraft.src;

import java.util.Random;

public class ComponentScatteredFeatureJunglePyramid extends ComponentScatteredFeature
{
    private boolean field_74947_h;
    private boolean field_74948_i;

    /**
     * List of Dispenser contents to be generated in the Jungle Pyramid dispensers.
     */
    private static final WeightedRandomChestContent[] junglePyramidsDispenserContents = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.arrow.itemID, 0, 2, 7, 30)};

    /** List of random stones to be generated in the Jungle Pyramid. */
    private static StructureScatteredFeatureStones junglePyramidsRandomScatteredStones = new StructureScatteredFeatureStones((ComponentScatteredFeaturePieces2)null);
    private static final WeightedRandomChestContent[] m_LootListArray = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.pickaxeGold.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.axeGold.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.hoeGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(FCBetterThanWolves.fcBlockLightningRod.blockID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.emerald.itemID, 0, 1, 5, 15), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.book.itemID, 0, 2, 4, 25)};
    private static final WeightedRandomChestContent[] m_LootedLootListArray = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.book.itemID, 0, 2, 4, 25)};

    public ComponentScatteredFeatureJunglePyramid(Random par1Random, int par2, int par3)
    {
        super(par1Random, par2, 64, par3, 12, 10, 15);
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
            boolean var4 = FCUtilsHardcoreSpawn.IsInLootedTempleRadius(par1World, par3StructureBoundingBox.getCenterX(), par3StructureBoundingBox.getCenterZ());
            int var5 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3);
            int var6 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 2);
            int var7 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 0);
            int var8 = this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 1);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 1, 2, 9, 2, 2, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 1, 12, 9, 2, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 1, 3, 2, 2, 11, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 1, 3, 9, 2, 11, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 1, 10, 6, 1, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 13, 10, 6, 13, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, 3, 2, 1, 6, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 10, 3, 2, 10, 6, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 3, 2, 9, 3, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 6, 2, 9, 6, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 3, 7, 3, 8, 7, 11, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 8, 4, 7, 8, 10, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithAir(par1World, par3StructureBoundingBox, 3, 1, 3, 8, 2, 11);
            this.fillWithAir(par1World, par3StructureBoundingBox, 4, 3, 6, 7, 3, 9);
            this.fillWithAir(par1World, par3StructureBoundingBox, 2, 4, 2, 9, 5, 12);
            this.fillWithAir(par1World, par3StructureBoundingBox, 4, 6, 5, 7, 6, 9);
            this.fillWithAir(par1World, par3StructureBoundingBox, 5, 7, 6, 6, 7, 8);
            this.fillWithAir(par1World, par3StructureBoundingBox, 5, 1, 2, 6, 2, 2);
            this.fillWithAir(par1World, par3StructureBoundingBox, 5, 2, 12, 6, 2, 12);
            this.fillWithAir(par1World, par3StructureBoundingBox, 5, 5, 1, 6, 5, 1);
            this.fillWithAir(par1World, par3StructureBoundingBox, 5, 5, 13, 6, 5, 13);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 1, 5, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, 5, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 1, 5, 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, 0, 0, 10, 5, 9, par3StructureBoundingBox);
            int var9;

            for (var9 = 0; var9 <= 14; var9 += 14)
            {
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 4, var9, 2, 5, var9, false, par2Random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 4, var9, 4, 5, var9, false, par2Random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 4, var9, 7, 5, var9, false, par2Random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 4, var9, 9, 5, var9, false, par2Random, junglePyramidsRandomScatteredStones);
            }

            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 6, 0, 6, 6, 0, false, par2Random, junglePyramidsRandomScatteredStones);

            for (var9 = 0; var9 <= 11; var9 += 11)
            {
                for (int var10 = 2; var10 <= 12; var10 += 2)
                {
                    this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, var9, 4, var10, var9, 5, var10, false, par2Random, junglePyramidsRandomScatteredStones);
                }

                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, var9, 6, 5, var9, 6, 5, false, par2Random, junglePyramidsRandomScatteredStones);
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, var9, 6, 9, var9, 6, 9, false, par2Random, junglePyramidsRandomScatteredStones);
            }

            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 7, 2, 2, 9, 2, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 7, 2, 9, 9, 2, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, 7, 12, 2, 9, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, 7, 12, 9, 9, 12, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 9, 4, 4, 9, 4, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 9, 4, 7, 9, 4, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 9, 10, 4, 9, 10, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 9, 10, 7, 9, 10, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 9, 7, 6, 9, 7, false, par2Random, junglePyramidsRandomScatteredStones);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 5, 9, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 6, 9, 6, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var6, 5, 9, 8, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var6, 6, 9, 8, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 4, 0, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 5, 0, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 6, 0, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 7, 0, 0, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 4, 1, 8, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 4, 2, 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 4, 3, 10, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 7, 1, 8, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 7, 2, 9, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var5, 7, 3, 10, par3StructureBoundingBox);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 1, 9, 4, 1, 9, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, 1, 9, 7, 1, 9, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 4, 1, 10, 7, 2, 10, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 5, 4, 5, 6, 4, 5, false, par2Random, junglePyramidsRandomScatteredStones);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var7, 4, 4, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var8, 7, 4, 5, par3StructureBoundingBox);

            for (var9 = 0; var9 < 4; ++var9)
            {
                this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var6, 5, 0 - var9, 6 + var9, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, Block.stairsCobblestone.blockID, var6, 6, 0 - var9, 6 + var9, par3StructureBoundingBox);
                this.fillWithAir(par1World, par3StructureBoundingBox, 5, 0 - var9, 7 + var9, 6, 0 - var9, 9 + var9);
            }

            this.fillWithAir(par1World, par3StructureBoundingBox, 1, -3, 12, 10, -1, 13);
            this.fillWithAir(par1World, par3StructureBoundingBox, 1, -3, 1, 3, -1, 13);
            this.fillWithAir(par1World, par3StructureBoundingBox, 1, -3, 1, 9, -1, 5);

            for (var9 = 1; var9 <= 13; var9 += 2)
            {
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, -3, var9, 1, -2, var9, false, par2Random, junglePyramidsRandomScatteredStones);
            }

            for (var9 = 2; var9 <= 12; var9 += 2)
            {
                this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 1, -1, var9, 3, -1, var9, false, par2Random, junglePyramidsRandomScatteredStones);
            }

            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 2, -2, 1, 5, -2, 1, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 7, -2, 1, 9, -2, 1, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 6, -3, 1, 6, -3, 1, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 6, -1, 1, 6, -1, 1, false, par2Random, junglePyramidsRandomScatteredStones);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 3, -3, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.vine.blockID, 15, 3, -2, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 9, -3, 4, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.vine.blockID, 15, 8, -1, 3, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.vine.blockID, 15, 8, -2, 3, par3StructureBoundingBox);
            int var11;
            WeightedRandomChestContent[] var12;
            WeightedRandomChestContent[] var13;

            if (!this.field_74947_h)
            {
                var13 = m_LootListArray;
                var11 = 2 + par2Random.nextInt(5);

                if (var4)
                {
                    var13 = m_LootedLootListArray;
                    var11 /= 2;
                }

                var12 = WeightedRandomChestContent.func_92080_a(var13, new WeightedRandomChestContent[] {Item.enchantedBook.func_92114_b(par2Random)});
                this.field_74947_h = this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 8, -3, 3, var12, var11);
            }

            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 9, -3, 2, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 8, -3, 1, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 4, -3, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 5, -2, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 5, -1, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 6, -3, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 7, -2, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 7, -1, 5, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 8, -3, 5, par3StructureBoundingBox);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 9, -1, 1, 9, -1, 5, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithAir(par1World, par3StructureBoundingBox, 8, -3, 8, 10, -1, 10);
            this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 8, -2, 11, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 9, -2, 11, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 3, 10, -2, 11, par3StructureBoundingBox);

            if (var4)
            {
                this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, -2, 11, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, 9, -3, 11, par3StructureBoundingBox);
            }

            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 8, -3, 8, 8, -3, 10, false, par2Random, junglePyramidsRandomScatteredStones);
            this.fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 10, -3, 8, 10, -3, 10, false, par2Random, junglePyramidsRandomScatteredStones);
            this.placeBlockAtCurrentPosition(par1World, Block.cobblestoneMossy.blockID, 0, 10, -2, 9, par3StructureBoundingBox);

            if (!this.field_74948_i)
            {
                var13 = m_LootListArray;
                var11 = 2 + par2Random.nextInt(5);

                if (var4)
                {
                    var13 = m_LootedLootListArray;
                    var11 /= 2;
                }

                var12 = WeightedRandomChestContent.func_92080_a(var13, new WeightedRandomChestContent[] {Item.enchantedBook.func_92114_b(par2Random)});
                this.field_74948_i = this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 9, -3, 10, var12, var11);
            }

            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcAestheticOpaque.blockID, 12, 5, 4, 11, par3StructureBoundingBox);
            this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcAestheticOpaque.blockID, 12, 6, 4, 11, par3StructureBoundingBox);

            if (!var4)
            {
                if (this.coordBaseMode != 3 && this.coordBaseMode != 2)
                {
                    this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockArcaneVessel.blockID, 0, 5, 3, 10, par3StructureBoundingBox);
                    this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcHandCrank.blockID, 0, 6, 3, 10, par3StructureBoundingBox);
                    this.FillVesselWithExperience(par1World, par3StructureBoundingBox, 5, 3, 10);
                }
                else
                {
                    this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcBlockArcaneVessel.blockID, 0, 6, 3, 10, par3StructureBoundingBox);
                    this.placeBlockAtCurrentPosition(par1World, FCBetterThanWolves.fcHandCrank.blockID, 0, 5, 3, 10, par3StructureBoundingBox);
                    this.FillVesselWithExperience(par1World, par3StructureBoundingBox, 6, 3, 10);
                }
            }
            else
            {
                this.placeBlockAtCurrentPosition(par1World, 0, 0, 5, 3, 10, par3StructureBoundingBox);
                this.placeBlockAtCurrentPosition(par1World, 0, 0, 6, 3, 10, par3StructureBoundingBox);
            }

            return true;
        }
    }

    public void FillVesselWithExperience(World var1, StructureBoundingBox var2, int var3, int var4, int var5)
    {
        int var6 = this.getXWithOffset(var3, var5);
        int var7 = this.getYWithOffset(var4);
        int var8 = this.getZWithOffset(var3, var5);
        TileEntity var9 = var1.getBlockTileEntity(var6, var7, var8);

        if (var9 != null && var9 instanceof FCTileEntityArcaneVessel)
        {
            FCTileEntityArcaneVessel var10 = (FCTileEntityArcaneVessel)var9;
            var10.InitTempleExperience();
        }
    }
}
