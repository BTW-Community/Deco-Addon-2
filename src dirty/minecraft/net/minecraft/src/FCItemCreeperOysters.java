package net.minecraft.src;

public class FCItemCreeperOysters extends FCItemFood
{
    public FCItemCreeperOysters(int var1)
    {
        super(var1, 2, 0.8F, false, "fcItemCreeperOysters");
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(2);
        this.setPotionEffect(Potion.poison.id, 5, 0, 1.0F);
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var1.m_iTimesCraftedThisTick == 0)
        {
            var1.playSound("mob.slime.attack", 0.5F, (var1.worldObj.rand.nextFloat() - var1.worldObj.rand.nextFloat()) * 0.1F + 0.7F);
        }
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 16;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return FCBetterThanWolves.fcBlockCreeperOysters.blockID;
    }
}
