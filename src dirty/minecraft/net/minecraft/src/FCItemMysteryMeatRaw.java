package net.minecraft.src;

public class FCItemMysteryMeatRaw extends FCItemFood
{
    public FCItemMysteryMeatRaw(int var1)
    {
        super(var1, 4, 0.25F, true, "fcItemMysteryMeatRaw", true);
        this.SetStandardFoodPoisoningEffect();
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("beefRaw");
    }
}
