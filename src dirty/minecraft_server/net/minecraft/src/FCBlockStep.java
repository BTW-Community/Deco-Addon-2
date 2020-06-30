package net.minecraft.src;

public class FCBlockStep extends BlockStep
{
    private static final int m_iStepTypeStone = 0;
    private static final int m_iStepTypeSandstone = 1;
    private static final int m_iStepTypeWood = 2;
    private static final int m_iStepTypeCobble = 3;
    private static final int m_iStepTypeBrick = 4;
    private static final int m_iStepTypeStoneBrick = 5;
    private static final int m_iStepTypeNetherBrick = 6;
    private static final int m_iStepTypeNetherQuartz = 7;

    public FCBlockStep(int var1, boolean var2)
    {
        super(var1, var2);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetBlockType(var1, var2, var3, var4);
        return var5 == 1 ? 3 : (var5 != 3 && var5 != 4 && var5 != 5 && var5 != 6 ? super.GetHarvestToolLevel(var1, var2, var3, var4) : 1000);
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        int var7 = this.GetBlockType(var5);

        if (var7 == 1)
        {
            if (this.isDoubleSlab)
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 16, 0, var6);
            }
            else
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 8, 0, var6);
            }

            return true;
        }
        else if (var7 == 3)
        {
            if (this.isDoubleSlab)
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockCobblestoneLoose.blockID, 1, 0, var6);
            }
            else
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockCobblestoneLooseSlab.blockID, 1, 0, var6);
            }

            return true;
        }
        else if (var7 == 4)
        {
            if (this.isDoubleSlab)
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockBrickLoose.blockID, 1, 0, var6);
            }
            else
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockBrickLooseSlab.blockID, 1, 0, var6);
            }

            return true;
        }
        else if (var7 == 5)
        {
            if (this.isDoubleSlab)
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockStoneBrickLoose.blockID, 1, 0, var6);
            }
            else
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockStoneBrickLooseSlab.blockID, 1, 0, var6);
            }

            return true;
        }
        else if (var7 == 6)
        {
            if (this.isDoubleSlab)
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockNetherBrickLoose.blockID, 1, 0, var6);
            }
            else
            {
                this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcBlockNetherBrickLooseSlab.blockID, 1, 0, var6);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean HasContactPointToFullFace(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (!this.isDoubleSlab && var5 < 2)
        {
            boolean var6 = this.GetIsUpsideDown(var1, var2, var3, var4);
            return var6 == (var5 == 1);
        }
        else
        {
            return true;
        }
    }

    public boolean HasContactPointToSlabSideFace(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.isDoubleSlab || var6 == this.GetIsUpsideDown(var1, var2, var3, var4);
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetBlockType(var1, var2, var3, var4);
        return var5 == 3 || var5 == 4 || var5 == 5 || var5 == 6;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetBlockType(var1, var2, var3, var4);
        return var5 == 2 ? false : (var5 == 6 ? true : super.CanMobsSpawnOn(var1, var2, var3, var4));
    }

    public int GetBlockType(int var1)
    {
        return var1 & 7;
    }

    public int GetBlockType(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBlockType(var1.getBlockMetadata(var2, var3, var4));
    }
}
