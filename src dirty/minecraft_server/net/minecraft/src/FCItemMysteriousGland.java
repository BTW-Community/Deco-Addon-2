package net.minecraft.src;

public class FCItemMysteriousGland extends Item
{
    public FCItemMysteriousGland(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(2);
        this.SetFilterableProperties(2);
        this.setPotionEffect(PotionHelper.speckledMelonEffect);
        this.setUnlocalizedName("fcItemMysteriousGland");
    }
}
