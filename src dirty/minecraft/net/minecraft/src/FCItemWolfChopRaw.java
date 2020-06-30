package net.minecraft.src;

public class FCItemWolfChopRaw extends FCItemFood
{
    public FCItemWolfChopRaw(int var1)
    {
        super(var1, 4, 0.25F, false, "fcItemWolfChopRaw", true);
        this.SetStandardFoodPoisoningEffect();
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("porkchopRaw");
    }
}
