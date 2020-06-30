package net.minecraft.src;

public class FCEntitySnowman extends EntitySnowman
{
    public FCEntitySnowman(World var1)
    {
        super(var1);
        this.tasks.RemoveAllTasksOfClass(EntityAIWander.class);
        this.tasks.addTask(2, new FCEntityAIWanderSimple(this, 0.2F));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.EntityLivingOnLivingUpdate();

        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1);
        }

        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getBiomeGenForCoords(var1, var2).getFloatTemperature() > 1.0F)
        {
            this.attackEntityFrom(DamageSource.onFire, 1);
        }
        else
        {
            for (int var3 = 0; var3 < 4; ++var3)
            {
                int var4 = MathHelper.floor_double(this.posX + (double)((float)(var3 % 2 * 2 - 1) * 0.25F));
                int var5 = (int)this.posY;
                int var6 = MathHelper.floor_double(this.posZ + (double)((float)(var3 / 2 % 2 * 2 - 1) * 0.25F));

                if (this.worldObj.getBiomeGenForCoords(var4, var6).getFloatTemperature() < 0.8F)
                {
                    if (this.worldObj.isAirBlock(var4, var5, var6))
                    {
                        if (Block.snow.canPlaceBlockAt(this.worldObj, var4, var5, var6))
                        {
                            this.worldObj.setBlock(var4, var5, var6, Block.snow.blockID);
                        }
                    }
                    else if (this.worldObj.isAirBlock(var4, var5 + 1, var6) && Block.snow.canPlaceBlockAt(this.worldObj, var4, var5 + 1, var6))
                    {
                        this.worldObj.setBlock(var4, var5 + 1, var6, Block.snow.blockID);
                    }
                }
            }
        }
    }
}
