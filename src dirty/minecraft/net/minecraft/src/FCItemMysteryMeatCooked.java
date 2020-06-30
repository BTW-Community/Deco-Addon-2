package net.minecraft.src;

public class FCItemMysteryMeatCooked extends ItemFood
{
    public FCItemMysteryMeatCooked(int var1)
    {
        super(var1, 5, 0.25F, true, false);
        this.setUnlocalizedName("fcItemMysteryMeatCooked");
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("beefCooked");
    }
}
