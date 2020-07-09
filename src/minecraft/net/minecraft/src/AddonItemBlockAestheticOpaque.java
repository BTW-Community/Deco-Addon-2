package net.minecraft.src;

public class AddonItemBlockAestheticOpaque extends FCItemBlockAestheticOpaque {
	public AddonItemBlockAestheticOpaque(int var1) {
		super(var1);
	}

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	if (meta == 15 && AddonManager.getNewSoundsInstalled())
    		AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, AddonDefs.stepSoundBone.getPlaceSound(), (AddonDefs.stepSoundBone.getVolume() + 1.0F) / 2.0F, AddonDefs.stepSoundBone.getPitch() * 0.8F, 
    				block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
    	else
    		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
    }
}