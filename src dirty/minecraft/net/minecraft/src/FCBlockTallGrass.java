package net.minecraft.src;

import java.util.Random;

public class FCBlockTallGrass extends BlockTallGrass
{
    private static final double m_dHalfWidth = 0.4000000059604645D;

    protected FCBlockTallGrass(int var1)
    {
        super(var1);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.GRASS);
        this.InitBlockBounds(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.9000000059604645D, 0.8D, 0.9000000059604645D);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("tallgrass");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return -1;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.GetBlockNaturalLightValueMaximum(var2, var3 + 1, var4);
        int var7 = var6 - var1.skylightSubtracted;

        if (var7 >= 11 && var1.provider.dimensionId != 1)
        {
            int var8 = var1.getBlockMetadata(var2, var3, var4);

            if (var8 == 1 && var5.nextInt(3) == 0)
            {
                int var9 = var2 + var5.nextInt(3) - 1;
                int var10 = var3 - 1 + var5.nextInt(5) - 3;
                int var11 = var4 + var5.nextInt(3) - 1;
                var1.getBlockId(var9, var10 + 1, var11);

                if (var1.getBlockId(var9, var10, var11) == Block.tilledField.blockID && var1.isAirBlock(var9, var10 + 1, var11))
                {
                    int var13 = var1.GetBlockNaturalLightValueMaximum(var9, var10 + 1, var11);

                    if (var13 >= 11)
                    {
                        var1.setBlockAndMetadataWithNotify(var9, var10 + 1, var11, Block.tallGrass.blockID, 1);
                    }
                }
            }
        }

        super.updateTick(var1, var2, var3, var4, var5);
    }

    public boolean CanSpitWebReplaceBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsReplaceableVegetation(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return this.createStackedBlock(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return var1.getBlockMetadata(var2, var3, var4) == 1 || var5.CanGrazeOnRoughVegetation();
    }

    public int GetHerbivoreItemFoodValue(int var1)
    {
        return var1 == 1 ? 200 : super.GetHerbivoreItemFoodValue(var1);
    }
}
