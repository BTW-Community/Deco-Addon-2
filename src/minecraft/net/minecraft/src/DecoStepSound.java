package net.minecraft.src;

public class DecoStepSound extends StepSound {
	private String fallbackSoundName;
	private float fallbackVolume;
	private float fallbackPitch;
	
	public DecoStepSound(String sound, float volume, float pitch, String fallbackSoundName, float fallbackVolume, float fallbackPitch) {
		super(sound, volume, pitch);
		this.fallbackSoundName = fallbackSoundName;
		this.fallbackVolume = fallbackVolume;
		this.fallbackPitch = fallbackPitch;
	}

    /**
     * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
     */
    public String getBreakSound()
    {
        return DecoManager.getNewSoundsInstalled() ? "deco.dig." + this.stepSoundName : "dig." + this.fallbackSoundName;
    }

    /**
     * Used when a entity walks over, or otherwise interacts with the block.
     */
    public String getStepSound()
    {
        return DecoManager.getNewSoundsInstalled() ? "deco.step." + this.stepSoundName : "step." + this.fallbackSoundName;
    }

    public float getVolume()
    {
        return DecoManager.getNewSoundsInstalled() ? this.stepSoundVolume : this.fallbackVolume;
    }

    public float getPitch()
    {
        return DecoManager.getNewSoundsInstalled() ? this.stepSoundPitch : this.fallbackPitch;
    }
}