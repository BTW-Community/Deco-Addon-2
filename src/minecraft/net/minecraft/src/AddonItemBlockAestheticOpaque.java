package net.minecraft.src;

public class AddonItemBlockAestheticOpaque extends FCItemBlockAestheticOpaque {
	public AddonItemBlockAestheticOpaque(int var1) {
		super(var1);
	}

    protected void PlayPlaceSound(World var1, int var2, int var3, int var4, Block var5)
    {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
    	if (meta == 15)
    		var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, AddonDefs.stepSoundBone.getPlaceSound(), (AddonDefs.stepSoundBone.getVolume() + 1.0F) / 2.0F, AddonDefs.stepSoundBone.getPitch() * 0.8F);
    	else
    		var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, var5.stepSound.getPlaceSound(), (var5.stepSound.getVolume() + 1.0F) / 2.0F, var5.stepSound.getPitch() * 0.8F);
    }
}