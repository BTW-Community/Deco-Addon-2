package net.minecraft.src;

import java.util.Random;

public class DecoBlockFarmlandSalted extends FCBlockFarmland {
    protected DecoBlockFarmlandSalted(int var1) {
        super(var1);
    }

    @Override
    public float GetPlantGrowthOnMultiplier(World var1, int var2, int var3, int var4, Block var5) {
        return 0.0F;
    }
    
    @Override
    public void UpdateWeedGrowth(World var1, int var2, int var3, int var4, Random var5) {}

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1) {
        this.blockIcon = var1.registerIcon("dirt");
        this.m_iconTopWet = var1.registerIcon("decoBlockFarmlandSalted_wet");
        this.m_iconTopDry = var1.registerIcon("decoBlockFarmlandSalted_dry");
    }
}
