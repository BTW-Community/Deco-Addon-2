package net.minecraft.src;

public class DecoItemBlockAestheticOpaque extends FCItemBlockAestheticOpaque {
	public DecoItemBlockAestheticOpaque(int var1) {
		super(var1);
	}

    protected void PlayPlaceSound(World world, int x, int y, int z, Block block)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	if (meta == 15 && DecoManager.getNewSoundsInstalled())
    		DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, DecoDefs.stepSoundBone.getPlaceSound(), (DecoDefs.stepSoundBone.getVolume() + 1.0F) / 2.0F, DecoDefs.stepSoundBone.getPitch() * 0.8F, 
    				block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
    	else
    		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
    }
}