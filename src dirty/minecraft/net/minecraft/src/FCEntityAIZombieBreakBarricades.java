package net.minecraft.src;

public class FCEntityAIZombieBreakBarricades extends EntityAIBase
{
    private EntityLiving m_AssociatedEntity;
    private int m_iDoorPosI;
    private int m_iDoorPosJ;
    private int m_iDoorPosK;
    private Block m_TargetBlock;
    private int breakingTime;
    private int field_75358_j = -1;

    public FCEntityAIZombieBreakBarricades(EntityLiving var1)
    {
        this.m_AssociatedEntity = var1;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.m_AssociatedEntity.isCollidedHorizontally)
        {
            PathNavigate var1 = this.m_AssociatedEntity.getNavigator();
            PathEntity var2 = var1.getPath();

            if (var2 != null && !var2.isFinished() && var1.getCanBreakDoors())
            {
                for (int var3 = 0; var3 < Math.min(var2.getCurrentPathIndex() + 2, var2.getCurrentPathLength()); ++var3)
                {
                    PathPoint var4 = var2.getPathPointFromIndex(var3);

                    if (this.m_AssociatedEntity.getDistanceSq((double)var4.xCoord, this.m_AssociatedEntity.posY, (double)var4.zCoord) > 2.25D)
                    {
                        break;
                    }

                    this.m_iDoorPosI = var4.xCoord;
                    this.m_iDoorPosJ = var4.yCoord + 1;
                    this.m_iDoorPosK = var4.zCoord;
                    this.m_TargetBlock = this.ShouldBreakBarricadeAtPos(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);

                    if (this.m_TargetBlock == null)
                    {
                        this.m_iDoorPosJ = var4.yCoord;
                        this.m_TargetBlock = this.ShouldBreakBarricadeAtPos(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);

                        if (this.m_TargetBlock == null)
                        {
                            this.m_iDoorPosJ = MathHelper.floor_double(this.m_AssociatedEntity.posY + 1.0D);
                            this.m_TargetBlock = this.ShouldBreakBarricadeAtPos(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);

                            if (this.m_TargetBlock == null)
                            {
                                this.m_iDoorPosJ = MathHelper.floor_double(this.m_AssociatedEntity.posY);
                                this.m_TargetBlock = this.ShouldBreakBarricadeAtPos(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);
                            }
                        }
                    }

                    if (this.m_TargetBlock != null)
                    {
                        return true;
                    }
                }

                this.m_iDoorPosI = MathHelper.floor_double(this.m_AssociatedEntity.posX);
                this.m_iDoorPosJ = MathHelper.floor_double(this.m_AssociatedEntity.posY + 1.0D);
                this.m_iDoorPosK = MathHelper.floor_double(this.m_AssociatedEntity.posZ);
                this.m_TargetBlock = this.ShouldBreakBarricadeAtPos(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);

                if (this.m_TargetBlock == null)
                {
                    this.m_iDoorPosJ = MathHelper.floor_double(this.m_AssociatedEntity.posY);
                    this.m_TargetBlock = this.ShouldBreakBarricadeAtPos(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);
                }

                if (this.m_TargetBlock != null)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.breakingTime = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        if (this.breakingTime <= 240 && this.m_AssociatedEntity.worldObj.getBlockId(this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK) == this.m_TargetBlock.blockID)
        {
            double var1 = this.m_AssociatedEntity.getDistanceSq((double)this.m_iDoorPosI, (double)this.m_iDoorPosJ, (double)this.m_iDoorPosK);
            return var1 < 4.0D;
        }
        else
        {
            return false;
        }
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        super.resetTask();
        this.m_AssociatedEntity.worldObj.destroyBlockInWorldPartially(this.m_AssociatedEntity.entityId, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK, -1);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.m_AssociatedEntity.getRNG().nextInt(20) == 0)
        {
            this.m_AssociatedEntity.worldObj.playAuxSFX(1010, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK, 0);
        }

        ++this.breakingTime;
        int var1 = (int)((float)this.breakingTime / 240.0F * 10.0F);

        if (var1 != this.field_75358_j)
        {
            this.m_AssociatedEntity.worldObj.destroyBlockInWorldPartially(this.m_AssociatedEntity.entityId, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK, var1);
            this.field_75358_j = var1;
        }

        if (this.breakingTime == 240)
        {
            int var2 = this.m_AssociatedEntity.worldObj.getBlockMetadata(this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);
            this.m_AssociatedEntity.worldObj.setBlockToAir(this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK);

            if (this.m_TargetBlock.blockID != Block.doorWood.blockID && this.m_TargetBlock.blockID != FCBetterThanWolves.fcBlockDoorWood.blockID)
            {
                this.m_TargetBlock.dropBlockAsItem(this.m_AssociatedEntity.worldObj, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK, var2, 0);
            }

            this.m_AssociatedEntity.worldObj.playAuxSFX(1012, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK, 0);
            this.m_AssociatedEntity.worldObj.playAuxSFX(2001, this.m_iDoorPosI, this.m_iDoorPosJ, this.m_iDoorPosK, this.m_TargetBlock.blockID);
        }
    }

    private Block ShouldBreakBarricadeAtPos(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 != 0)
        {
            Block var6 = Block.blocksList[var5];

            if (var6.IsBreakableBarricade(var1, var2, var3, var4))
            {
                return var6;
            }
        }

        return null;
    }
}
