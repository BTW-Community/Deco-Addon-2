package net.minecraft.src;

import java.util.Random;

public class AddonBlockCobbleLooseDiorite extends FCBlockLavaReceiver
{

    public AddonBlockCobbleLooseDiorite(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("ginger_dioriteCobbleLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
        AddonManager.Register(this, "Loose Diorite Cobblestone");
    }

    public boolean OnMortarApplied(World world, int x, int y, int z)
    {
        world.setBlockAndMetadataWithNotify(x, y, z, AddonDefs.stoneTypesCobble.blockID, 2);
        return true;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForFall(var1, var2, var3, var4))
        {
            if (this.GetHasLavaInCracks(var1, var2, var3, var4))
            {
                if (this.HasWaterAbove(var1, var2, var3, var4))
                {
                    var1.playAuxSFX(2227, var2, var3, var4, 0);
                    var1.setBlockWithNotify(var2, var3, var4, AddonDefs.stoneTypes.blockID);
                    var1.setBlockMetadataWithClient(var2, var3, var4, 2);
                    return;
                }
            }
            else if (this.HasLavaAbove(var1, var2, var3, var4))
            {
                this.SetHasLavaInCracks(var1, var2, var3, var4, true);
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetHasLavaInCracks(var1, var2, var3, var4) && var1.IsRainingAtPos(var2, var3 + 1, var4))
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
            var1.setBlockWithNotify(var2, var3, var4, AddonDefs.stoneTypes.blockID);
            var1.setBlockMetadataWithClient(var2, var3, var4, 2);
        }
    }
}
