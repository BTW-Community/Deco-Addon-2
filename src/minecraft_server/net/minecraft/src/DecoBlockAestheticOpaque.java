package net.minecraft.src;

import java.util.List;

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

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 3));
        var3.add(new ItemStack(var1, 1, 4));
        var3.add(new ItemStack(var1, 1, 5));
        var3.add(new ItemStack(var1, 1, 7));
        var3.add(new ItemStack(var1, 1, 9));
        var3.add(new ItemStack(var1, 1, 10));
        var3.add(new ItemStack(var1, 1, 13));
        var3.add(new ItemStack(var1, 1, 14));
        var3.add(new ItemStack(var1, 1, 15));
    }
}