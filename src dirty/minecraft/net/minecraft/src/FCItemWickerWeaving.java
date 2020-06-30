package net.minecraft.src;

public class FCItemWickerWeaving extends FCItemCraftingProgressive
{
    public static final int m_iWickerWeavingMaxDamage = 300;

    public FCItemWickerWeaving(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(2);
        this.SetIncineratedInCrucible();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WICKER_PIECE);
        this.SetFilterableProperties(16);
        this.setUnlocalizedName("fcItemWickerWeaving");
    }

    protected void PlayCraftingFX(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.playSound("step.grass", 0.25F + 0.25F * (float)var2.rand.nextInt(2), (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.25F + 1.75F);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        var2.playSoundAtEntity(var3, "step.grass", 1.0F, var2.rand.nextFloat() * 0.1F + 0.9F);
        return new ItemStack(FCBetterThanWolves.fcItemWickerPiece, 1, 0);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("step.grass", 1.0F, var2.rand.nextFloat() * 0.1F + 0.9F);
        }

        super.onCreated(var1, var2, var3);
    }

    public boolean GetCanBeFedDirectlyIntoCampfire(int var1)
    {
        return false;
    }

    public boolean GetCanBeFedDirectlyIntoBrickOven(int var1)
    {
        return false;
    }

    protected int GetProgressiveCraftingMaxDamage()
    {
        return 300;
    }
}
