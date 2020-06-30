package net.minecraft.src;

public class FCItemWolfChopCooked extends ItemFood
{
    public FCItemWolfChopCooked(int var1)
    {
        super(var1, 5, 0.25F, false, false);
        this.setUnlocalizedName("fcItemWolfChopCooked");
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("porkchopCooked");
    }
}
