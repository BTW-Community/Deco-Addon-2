package net.minecraft.src;

public class FCItemEgg extends FCItemThrowable
{
    public FCItemEgg(int var1)
    {
        super(var1);
        this.maxStackSize = 16;
        this.SetIncineratedInCrucible();
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("egg");
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    protected void SpawnThrownEntity(ItemStack var1, World var2, EntityPlayer var3)
    {
        var2.spawnEntityInWorld(new EntityEgg(var2, var3));
    }

    protected EntityThrowable GetEntityFiredByByBlockDispenser(World var1, double var2, double var4, double var6)
    {
        return new EntityEgg(var1, var2, var4, var6);
    }
}
