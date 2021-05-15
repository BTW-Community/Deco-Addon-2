package net.minecraft.src;

public class DecoStepSoundLadderIron extends StepSound {
	public DecoStepSoundLadderIron(float par2, float par3) {
		super("ladder", par2, par3);
	}

    public String getBreakSound() {
    	return "dig.stone";
    }
}