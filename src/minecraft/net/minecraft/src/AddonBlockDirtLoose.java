package net.minecraft.src;

public class AddonBlockDirtLoose extends FCBlockDirtLoose {
	public AddonBlockDirtLoose(int id) {
		super(id);
	}
	
	public boolean AttempToSpreadGrassToBlock(World world, int x, int y, int z) {
		if (this.GetCanGrassSpreadToBlock(world, x, y, z) && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(world, x, y, z) && lightOpacity[world.getBlockId(x, y + 1, z)] <= 2) {
			if (world.GetBlockNaturalLightValueMaximum(x, y + 1, z) >= 11) {
				return this.SpreadGrassToBlock(world, x, y, z);
			}
			else {
				boolean spread = this.SpreadGrassToBlock(world, x, y, z);
				
				if (spread) {
					world.setBlockMetadata(x, y, z, 1);
					return true;
				}
				else {
					return false;
				}
			}
		}
		
		return false;
	}
}
