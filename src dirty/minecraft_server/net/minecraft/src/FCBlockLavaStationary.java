package net.minecraft.src;

import java.util.Random;

public class FCBlockLavaStationary extends BlockStationary
{
    protected FCBlockLavaStationary(int var1, Material var2)
    {
        super(var1, var2);
    }

    public boolean CanPathThroughBlock(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return var5.handleLavaMovement();
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -2;
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.CheckForDirectSetOnFireToNeighborsInContact(var1, var2, var3, var4);
        this.CheckForStartingFiresAroundLava(var1, var2, var3, var4, var5);
    }

    private boolean CanBlockMaterialBurn(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMaterial(var2, var3, var4).getCanBurn();
    }

    private void CheckForDirectSetOnFireToNeighborsInContact(World var1, int var2, int var3, int var4)
    {
        this.CheckForDirectSetOnFire(var1, var2, var3 - 1, var4);
        this.CheckForDirectSetOnFire(var1, var2 - 1, var3, var4);
        this.CheckForDirectSetOnFire(var1, var2 + 1, var3, var4);
        this.CheckForDirectSetOnFire(var1, var2, var3, var4 - 1);
        this.CheckForDirectSetOnFire(var1, var2, var3, var4 + 1);
    }

    private void CheckForDirectSetOnFire(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];

        if (var5 != null && var5.GetCanBeSetOnFireDirectly(var1, var2, var3, var4))
        {
            var5.SetOnFireDirectly(var1, var2, var3, var4);
        }
    }

    private void CheckForStartingFiresAroundLava(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var5.nextInt(3);
        int var7;
        int var8;

        if (var6 != 0)
        {
            for (var7 = 0; var7 < var6; ++var7)
            {
                var2 += var5.nextInt(3) - 1;
                ++var3;
                var4 += var5.nextInt(3) - 1;
                var8 = var1.getBlockId(var2, var3, var4);
                Block var9 = Block.blocksList[var8];

                if (var9 != null && !var9.IsAirBlock())
                {
                    if (var9.GetCanBeSetOnFireDirectly(var1, var2, var3, var4))
                    {
                        var9.SetOnFireDirectly(var1, var2, var3, var4);
                        break;
                    }

                    if (var9.blockMaterial.blocksMovement())
                    {
                        return;
                    }
                }
                else if (this.CanBlockMaterialBurn(var1, var2 - 1, var3, var4) || this.CanBlockMaterialBurn(var1, var2 + 1, var3, var4) || this.CanBlockMaterialBurn(var1, var2, var3, var4 - 1) || this.CanBlockMaterialBurn(var1, var2, var3, var4 + 1) || this.CanBlockMaterialBurn(var1, var2, var3 - 1, var4) || this.CanBlockMaterialBurn(var1, var2, var3 + 1, var4))
                {
                    var1.setBlock(var2, var3, var4, Block.fire.blockID);
                    return;
                }
            }
        }
        else
        {
            var7 = var2;
            var8 = var4;

            for (int var11 = 0; var11 < 3; ++var11)
            {
                var2 = var7 + var5.nextInt(3) - 1;
                var4 = var8 + var5.nextInt(3) - 1;

                if (var1.isAirBlock(var2, var3 + 1, var4) && this.CanBlockMaterialBurn(var1, var2, var3, var4))
                {
                    var1.setBlock(var2, var3 + 1, var4, Block.fire.blockID);
                }
                else
                {
                    Block var10 = Block.blocksList[var1.getBlockId(var2, var3, var4)];

                    if (var10 != null && var10.GetCanBeSetOnFireDirectly(var1, var2, var3, var4))
                    {
                        var10.SetOnFireDirectly(var1, var2, var3, var4);
                    }
                }
            }
        }
    }
}
