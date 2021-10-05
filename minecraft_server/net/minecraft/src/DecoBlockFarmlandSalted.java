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
}
