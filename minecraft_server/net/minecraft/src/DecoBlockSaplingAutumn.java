package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSaplingAutumn extends DecoBlockSapling
{
    public static final String[] SAPLING_TYPES = new String[] {
			"red", "orange", "yellow", "",
			"red", "orange", "yellow", "",
			"red", "orange", "yellow", "",
			"redMature", "orangeMature", "yellowMature", ""
	};

    protected DecoBlockSaplingAutumn(int id) {
        super(id);
        this.setUnlocalizedName("decoBlockSaplingAutumn");
        this.baseTextureNames = new String[] {"decoBlockSaplingAutumnRed_0", "decoBlockSaplingAutumnOrange_0", "decoBlockSaplingAutumnYellow_0"};
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World world, int x, int y, int z, Random rand) {
        int treeType = world.getBlockMetadata(x, y, z) & 3;
        boolean planter = Block.blocksList[world.getBlockId(x, y - 1, z)] instanceof FCBlockPlanterBase;
        
    	world.setBlock(x, y, z, 0);
    	
    	boolean success = DecoUtilsTrees.generateAutumn(world, rand, x, y, z, treeType);
    	
    	if (!success) {
    		world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, treeType | 3 << 2);
    	}
    	else if (planter) {
    		world.setBlockMetadata(x, y, z, treeType);

			//Block break sfx
			world.playAuxSFX(2001, x, y - 1, z, FCBetterThanWolves.fcBlockPlanterSoil.blockID);

			world.setBlockAndMetadata(x, y - 1, z, Block.wood.blockID, 12);
    	}
    }
}