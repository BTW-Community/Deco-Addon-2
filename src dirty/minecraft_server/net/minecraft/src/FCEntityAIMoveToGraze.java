package net.minecraft.src;

public class FCEntityAIMoveToGraze extends EntityAIBase
{
    private EntityAnimal m_myAnimal;
    private float m_fMoveSpeed;
    protected FCUtilsBlockPos m_destPos = new FCUtilsBlockPos();

    public FCEntityAIMoveToGraze(EntityAnimal var1, float var2)
    {
        this.m_myAnimal = var1;
        this.m_fMoveSpeed = var2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.m_myAnimal.IsSubjectToHunger() ? (!this.m_myAnimal.IsHungryEnoughToForceMoveToGraze() ? (this.m_myAnimal.getRNG().nextInt(120) == 0 ? FCUtilsRandomPositionGenerator.FindSimpleRandomTargetBlock(this.m_myAnimal, 10, 7, this.m_destPos) : false) : !this.m_myAnimal.ShouldStayInPlaceToGraze() && FCUtilsRandomPositionGenerator.FindSimpleRandomTargetBlock(this.m_myAnimal, 10, 7, this.m_destPos)) : this.m_myAnimal.getRNG().nextInt(120) == 0 && FCUtilsRandomPositionGenerator.FindSimpleRandomTargetBlock(this.m_myAnimal, 10, 7, this.m_destPos);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.m_myAnimal.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.m_myAnimal.getNavigator().tryMoveToXYZ(this.m_destPos.i, this.m_destPos.j, this.m_destPos.k, this.m_fMoveSpeed);
    }
}
