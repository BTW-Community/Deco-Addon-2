package net.minecraft.src;

public class FCEntityWither extends EntityWither
{
    public FCEntityWither(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
        this.tasks.addTask(5, new FCEntityAIWanderSimple(this, this.moveSpeed));
    }

    public void CheckForScrollDrop()
    {
        ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.knockback.effectId);
        this.entityDropItem(var1, 0.0F);
    }

    protected void ModSpecificOnLivingUpdate()
    {
        super.ModSpecificOnLivingUpdate();

        if (!this.worldObj.isRemote)
        {
            FCUtilsWorld.GameProgressSetWitherHasBeenSummonedServerOnly();
        }
    }
}
