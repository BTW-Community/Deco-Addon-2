package net.minecraft.src;

public class FCItemKnitting extends FCItemCraftingProgressive
{
    public FCItemKnitting(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT.m_iBurnTime + 2 * FCEnumFurnaceBurnTime.WOOL.m_iBurnTime);
        this.setUnlocalizedName("fcItemKnitting");
    }

    protected void PlayCraftingFX(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.playSound("step.wood", 0.25F + 0.25F * (float)var2.rand.nextInt(2), (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.25F + 1.75F);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        int var4 = FCItemWool.GetClosestColorIndex(GetColor(var1));
        ItemStack var5 = new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, var4);
        var2.playSoundAtEntity(var3, "step.cloth", 1.0F, var2.rand.nextFloat() * 0.1F + 0.9F);
        FCUtilsItem.GivePlayerStackOrEject(var3, var5);
        return new ItemStack(FCBetterThanWolves.fcItemKnittingNeedles);
    }

    public boolean GetCanBeFedDirectlyIntoCampfire(int var1)
    {
        return false;
    }

    public boolean GetCanBeFedDirectlyIntoBrickOven(int var1)
    {
        return false;
    }

    public static void SetColor(ItemStack var0, int var1)
    {
        NBTTagCompound var2 = var0.getTagCompound();

        if (var2 == null)
        {
            var2 = new NBTTagCompound();
            var0.setTagCompound(var2);
        }

        var2.setInteger("fcColor", var1);
    }

    public static int GetColor(ItemStack var0)
    {
        NBTTagCompound var1 = var0.getTagCompound();
        return var1 != null && var1.hasKey("fcColor") ? var1.getInteger("fcColor") : 0;
    }
}
