package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class AddonUtilsSound {
	public static void playSoundWithVanillaFallback(World world, double x, double y, double z, String sound, float volume, float pitch, String fallbackSound, float fallbackVolume, float fallbackPitch) {
		if (AddonManager.getNewSoundsInstalled())
			world.playSound(x, y, z, sound, volume, pitch);
		else
			world.playSound(x, y, z, fallbackSound, fallbackVolume, fallbackPitch);
	}
	
	public static void playSoundWithNullFallback(World world, double x, double y, double z, String sound, float volume, float pitch) {
		if (AddonManager.getNewSoundsInstalled())
			world.playSound(x, y, z, sound, volume, pitch);
	}
	
	public static void playSoundAtEntityWithVanillaFallback(World world, Entity entity, String sound, float volume, float pitch, String fallbackSound, float fallbackVolume, float fallbackPitch) {
		if (AddonManager.getNewSoundsInstalled())
			world.playSoundAtEntity(entity, sound, volume, pitch);
		else
			world.playSoundAtEntity(entity, fallbackSound, fallbackVolume, fallbackPitch);
	}
	
	public static void playSoundAtEntityWithNullFallback(World world, Entity entity, String sound, float volume, float pitch) {
		if (AddonManager.getNewSoundsInstalled())
			world.playSoundAtEntity(entity, sound, volume, pitch);
	}
	
	public static boolean playCustomSoundForBlockBreak(Minecraft mc, World world, int x, int y, int z, Block block, int meta) {
		boolean customSound = false;
		
		if (block.blockID == FCBetterThanWolves.fcAestheticOpaque.blockID && meta == 15) {
			if (AddonManager.getNewSoundsInstalled()) {
				customSound = true;
				playBlockSound(mc, x, y, z, AddonDefs.stepSoundBone, true);
			}
			else
				customSound = false;
		}
		
		return false;
	}
	
	public static boolean playCustomSoundForBlockConvert(Minecraft mc, World world, int x, int y, int z, Block block, int meta) {
		boolean customSound = false;
		
		if (block instanceof BlockLog || block instanceof FCBlockLogDamaged || block instanceof FCBlockLogSpike || block.blockID == AddonDefs.cherryLog.blockID || block.blockID == FCBetterThanWolves.fcBloodWood.blockID || block.blockID == AddonDefs.bloodLog.blockID || block instanceof AddonBlockStem) {
			if (AddonManager.getNewSoundsInstalled()) {
				customSound = true;
				playBlockSound(mc, x, y, z, "deco.random.strip", 1, 1, false);
			}
			else
				customSound = false;
		}
		else if (block.blockID == Block.dirt.blockID || block.blockID == Block.grass.blockID || block.blockID == Block.mycelium.blockID || block.blockID == FCBetterThanWolves.fcBlockDirtLoose.blockID) {
			if (AddonManager.getNewSoundsInstalled()) {
				customSound = true;
				playBlockSound(mc, x, y, z, "deco.random.till", 1, 1, false);
			}
			else
				customSound = false;
		}
		
		return customSound;
	}
	
	private static void playBlockSound(Minecraft mc, int x, int y, int z, String breakSound, float breakVolume, float breakPitch, boolean modulate) {
		if (modulate)
			mc.sndManager.playSound(breakSound, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, (breakVolume + 1.0F) / 2.0F, breakPitch * 0.8F);
		else
			mc.sndManager.playSound(breakSound, (float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F, breakVolume, breakPitch);
	}

	private static void playBlockSound(Minecraft mc, int x, int y, int z, StepSound stepSound, boolean modulate) {
		playBlockSound(mc, x, y, z, stepSound.getBreakSound(), stepSound.getVolume(), stepSound.getPitch(), modulate);
	}
}