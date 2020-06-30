package net.minecraft.src;

public class EntityAIFollowOwner extends EntityAIBase
{
    private EntityTameable thePet;
    private EntityLiving theOwner;
    World theWorld;
    private float field_75336_f;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;

    public EntityAIFollowOwner(EntityTameable par1EntityTameable, float par2, float par3, float par4)
    {
        this.thePet = par1EntityTameable;
        this.theWorld = par1EntityTameable.worldObj;
        this.field_75336_f = par2;
        this.petPathfinder = par1EntityTameable.getNavigator();
        this.minDist = par3;
        this.maxDist = par4;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLiving var1 = this.thePet.getOwner();

        if (var1 == null)
        {
            return false;
        }
        else if (this.thePet.isSitting())
        {
            return false;
        }
        else if (this.thePet.getDistanceSqToEntity(var1) < (double)(this.minDist * this.minDist))
        {
            return false;
        }
        else
        {
            this.theOwner = var1;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theOwner) > (double)(this.maxDist * this.maxDist) && !this.thePet.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.field_75343_h = 0;
        this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
        this.thePet.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float)this.thePet.getVerticalFaceSpeed());

        if (this.thePet.ridingEntity == null)
        {
            if (!this.thePet.isSitting() && --this.field_75343_h <= 0)
            {
                this.field_75343_h = 10;

                if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.field_75336_f) && this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0D)
                {
                    this.HandleTeleportation();
                }
            }
        }
    }

    private void HandleTeleportation()
    {
        if (!this.thePet.IsAITryingToSit())
        {
            Float var1 = Float.valueOf(MathHelper.sin(this.theOwner.rotationYaw / 180.0F * (float)Math.PI) * 4.0F);
            Float var2 = Float.valueOf(-(MathHelper.cos(this.theOwner.rotationYaw / 180.0F * (float)Math.PI) * 4.0F));
            int var3 = MathHelper.floor_double(this.theOwner.posX + (double)var1.floatValue());
            int var4 = MathHelper.floor_double(this.theOwner.posZ + (double)var2.floatValue());
            int var5 = MathHelper.floor_double(this.theOwner.boundingBox.minY);

            for (int var6 = 0; var6 <= 4; ++var6)
            {
                for (int var7 = 0; var7 <= 4; ++var7)
                {
                    int var8 = var6 + 1 >> 1;
                    int var9 = var7 + 1 >> 1;

                    if ((var6 & 1) == 0)
                    {
                        var8 = -var8;
                    }

                    if ((var9 & 1) == 0)
                    {
                        var9 = -var9;
                    }

                    if (this.theWorld.doesBlockHaveSolidTopSurface(var3 + var8, var5 - 1, var4 + var9) && !this.theWorld.isBlockNormalCube(var3 + var8, var5, var4 + var9) && !this.theWorld.isBlockNormalCube(var3 + var8, var5 + 1, var4 + var9))
                    {
                        this.thePet.setLocationAndAngles((double)((float)(var3 + var8) + 0.5F), (double)var5, (double)((float)(var4 + var9) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
                        this.petPathfinder.clearPathEntity();
                        return;
                    }
                }
            }
        }
    }
}
