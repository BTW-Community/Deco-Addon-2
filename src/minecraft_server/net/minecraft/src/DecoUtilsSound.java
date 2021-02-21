package net.minecraft.src;

public class DecoUtilsSound {
	public static void playSoundWithVanillaFallback(World world, double x, double y, double z, String sound, float volume, float pitch, String fallbackSound, float fallbackVolume, float fallbackPitch) {
		if (DecoManager.getNewSoundsInstalled())
			world.playSound(x, y, z, sound, volume, pitch);
		else
			world.playSound(x, y, z, fallbackSound, fallbackVolume, fallbackPitch);
	}
	
	public static void playSoundWithNullFallback(World world, double x, double y, double z, String sound, float volume, float pitch) {
		if (DecoManager.getNewSoundsInstalled())
			world.playSound(x, y, z, sound, volume, pitch);
	}
	
	public static void playSoundAtEntityWithVanillaFallback(World world, Entity entity, String sound, float volume, float pitch, String fallbackSound, float fallbackVolume, float fallbackPitch) {
		if (DecoManager.getNewSoundsInstalled())
			world.playSoundAtEntity(entity, sound, volume, pitch);
		else
			world.playSoundAtEntity(entity, fallbackSound, fallbackVolume, fallbackPitch);
	}
	
	public static void playSoundAtEntityWithNullFallback(World world, Entity entity, String sound, float volume, float pitch) {
		if (DecoManager.getNewSoundsInstalled())
			world.playSoundAtEntity(entity, sound, volume, pitch);
	}
}