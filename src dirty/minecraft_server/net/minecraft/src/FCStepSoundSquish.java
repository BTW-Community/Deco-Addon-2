package net.minecraft.src;

class FCStepSoundSquish extends StepSound
{
    FCStepSoundSquish(String var1, float var2, float var3)
    {
        super(var1, var2, var3);
    }

    /**
     * Used when a entity walks over, or otherwise interacts with the block.
     */
    public String getStepSound()
    {
        return "mob.slime.attack";
    }

    /**
     * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
     */
    public String getBreakSound()
    {
        return "mob.slime.small";
    }
}
