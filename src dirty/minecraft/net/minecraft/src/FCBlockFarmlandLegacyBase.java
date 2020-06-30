package net.minecraft.src;

public abstract class FCBlockFarmlandLegacyBase extends FCBlockFarmlandBase
{
    protected FCBlockFarmlandLegacyBase(int var1)
    {
        super(var1);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        Block var6 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
        var1.getBlockMaterial(var2, var3 + 1, var4);

        if (var6 != null)
        {
            if (var6.blockMaterial.isSolid())
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            }
            else if (var6.GetConvertsLegacySoil(var1, var2, var3 + 1, var4))
            {
                this.ConvertToNewSoil(var1, var2, var3, var4);
            }
        }
    }

    protected boolean IsHydrated(int var1)
    {
        return var1 > 0;
    }

    protected int SetFullyHydrated(int var1)
    {
        return var1 | 7;
    }

    protected void DryIncrementally(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5 - 1);
    }

    protected abstract void ConvertToNewSoil(World var1, int var2, int var3, int var4);
}
