package net.minecraft.src;

public class AddonStepSound extends StepSound {
	public AddonStepSound(String sound, float volume, float pitch) {
		super(sound, volume, pitch);
	}

    /**
     * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
     */
    public String getBreakSound()
    {
        return "deco.dig." + this.stepSoundName;
    }

    /**
     * Used when a entity walks over, or otherwise interacts with the block.
     */
    public String getStepSound()
    {
        return "deco.step." + this.stepSoundName;
    }
}