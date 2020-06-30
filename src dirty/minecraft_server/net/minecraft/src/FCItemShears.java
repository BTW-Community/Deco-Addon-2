package net.minecraft.src;

public class FCItemShears extends ItemShears
{
    public FCItemShears(int var1)
    {
        super(var1);
        this.SetInfernalMaxEnchantmentCost(25);
        this.SetInfernalMaxNumEnchants(2);
    }

    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7)
    {
        if (var3 != Block.leaves.blockID && var3 != FCBetterThanWolves.fcBlockBloodLeaves.blockID && var3 != Block.web.blockID && var3 != FCBetterThanWolves.fcBlockWeb.blockID && var3 != Block.tallGrass.blockID && var3 != Block.vine.blockID && var3 != Block.tripWire.blockID && var3 != FCBetterThanWolves.fcBlockHempCrop.blockID)
        {
            return super.onBlockDestroyed(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            var1.damageItem(1, var7);
            return true;
        }
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.blockID == Block.web.blockID || var3.blockID == FCBetterThanWolves.fcBlockWeb.blockID || var3.blockID == Block.redstoneWire.blockID || var3.blockID == Block.tripWire.blockID;
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return this.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6) ? (var3.blockID != FCBetterThanWolves.fcBlockBloodLeaves.blockID && var3.blockID != Block.leaves.blockID && var3.blockID != Block.web.blockID && var3.blockID != FCBetterThanWolves.fcBlockWeb.blockID ? 5.0F : 15.0F) : super.getStrVsBlock(var1, var2, var3, var4, var5, var6);
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return !var3.blockMaterial.isToolNotRequired() && this.canHarvestBlock(var1, var2, var3, var4, var5, var6) ? true : var3 == Block.cloth || var3 == Block.leaves || var3 == FCBetterThanWolves.fcBlockBloodLeaves || var3 == FCBetterThanWolves.fcWoolSlab || var3 == FCBetterThanWolves.fcBlockWoolSlabTop || var3 == Block.vine || var3 == FCBetterThanWolves.fcBlockHempCrop;
    }

    public boolean IsDamagedInCrafting()
    {
        return true;
    }

    public void OnDamagedInCrafting(EntityPlayer var1)
    {
        if (var1.m_iTimesCraftedThisTick == 0)
        {
            var1.playSound("mob.sheep.shear", 0.8F, 1.0F);
        }
    }

    public void OnBrokenInCrafting(EntityPlayer var1)
    {
        var1.playSound("random.break", 0.8F, 0.8F + var1.worldObj.rand.nextFloat() * 0.4F);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("random.anvil_use", 0.5F, var2.rand.nextFloat() * 0.25F + 1.25F);
        }

        super.onCreated(var1, var2, var3);
    }
}
