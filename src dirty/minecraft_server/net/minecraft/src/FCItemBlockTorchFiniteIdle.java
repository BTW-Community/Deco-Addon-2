package net.minecraft.src;

public class FCItemBlockTorchFiniteIdle extends FCItemBlockTorchIdle
{
    public FCItemBlockTorchFiniteIdle(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockTorchFiniteIdle");
    }

    protected ItemStack OnRightClickOnIgniter(ItemStack var1, World var2, EntityPlayer var3)
    {
        int var4 = MathHelper.floor_double(var3.posX);
        int var5 = MathHelper.floor_double(var3.boundingBox.minY);
        int var6 = MathHelper.floor_double(var3.posZ);
        var3.playSound("mob.ghast.fireball", 1.0F, var2.rand.nextFloat() * 0.4F + 0.8F);
        ItemStack var7 = new ItemStack(FCBetterThanWolves.fcBlockTorchFiniteBurning, 1, 0);
        long var8 = FCUtilsWorld.GetOverworldTimeServerOnly() + 24000L;
        var7.setTagCompound(new NBTTagCompound());
        var7.getTagCompound().setLong("outTime", var8);
        --var1.stackSize;

        if (var1.stackSize <= 0)
        {
            return var7;
        }
        else
        {
            FCUtilsItem.GivePlayerStackOrEject(var3, var7, var4, var5, var6);
            return var1;
        }
    }
}
