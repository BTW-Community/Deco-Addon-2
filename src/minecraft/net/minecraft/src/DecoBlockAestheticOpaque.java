package net.minecraft.src;

public class DecoBlockAestheticOpaque extends FCBlockAestheticOpaque {
	public DecoBlockAestheticOpaque(int id) {
		super(id);
	}

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        int meta = var1.getBlockMetadata(var2, var3, var4);
        switch (meta) {
        case 1:
        	return FCBetterThanWolves.fcStepSoundSquish;
        case 15:
        	return DecoManager.getNewSoundsInstalled() ? DecoDefs.stepSoundBone : soundGravelFootstep;
        default:
        	return this.stepSound;
        }
    }
}