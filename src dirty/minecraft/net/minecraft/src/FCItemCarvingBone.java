package net.minecraft.src;

public class FCItemCarvingBone extends FCItemCraftingProgressive
{
    public FCItemCarvingBone(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemCarvingBone");
    }

    protected void PlayCraftingFX(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.playSound("random.eat", 0.5F + 0.5F * (float)var2.rand.nextInt(2), var2.rand.nextFloat() * 0.25F + 1.25F);
        this.SpawnUseParticles(var1, var2, var3);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.playSound("mob.zombie.woodbreak", 0.1F, 1.25F + var2.rand.nextFloat() * 0.25F);
        return new ItemStack(FCBetterThanWolves.fcItemFishHookBone, 1, 0);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("mob.zombie.woodbreak", 0.1F, 1.25F + var2.rand.nextFloat() * 0.25F);
        }

        super.onCreated(var1, var2, var3);
    }

    protected void SpawnUseParticles(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var2.isRemote)
        {
            Vec3 var4 = var2.getWorldVec3Pool().getVecFromPool(((double)var2.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            var4.rotateAroundX(-var3.rotationPitch * (float)Math.PI / 180.0F);
            var4.rotateAroundY(-var3.rotationYaw * (float)Math.PI / 180.0F);
            Vec3 var5 = var2.getWorldVec3Pool().getVecFromPool(((double)var2.rand.nextFloat() - 0.5D) * 0.3D, (double)(-var2.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
            var5.rotateAroundX(-var3.rotationPitch * (float)Math.PI / 180.0F);
            var5.rotateAroundY(-var3.rotationYaw * (float)Math.PI / 180.0F);
            var5 = var5.addVector(var3.posX, var3.posY + (double)var3.getEyeHeight(), var3.posZ);
            var2.spawnParticle("iconcrack_" + var1.getItem().itemID, var5.xCoord, var5.yCoord, var5.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord);
        }
    }
}
