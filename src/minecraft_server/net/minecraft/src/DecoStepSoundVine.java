package net.minecraft.src;

public class DecoStepSoundVine extends DecoStepSound {
	public DecoStepSoundVine(float volume, float pitch) {
		super("grass", volume, pitch, "grass", Block.soundGrassFootstep.getVolume(), Block.soundGrassFootstep.getPitch());
	}

    /**
     * Used when a entity walks over, or otherwise interacts with the block.
     */
    public String getStepSound()
    {
        return "deco.step.vine";
    }

    /**
     * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
     */
    public String getBreakSound()
    {
        return "dig.grass";
    }
}