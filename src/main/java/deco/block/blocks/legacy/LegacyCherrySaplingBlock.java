package deco.block.blocks.legacy;

import deco.block.DecoBlocks;
import net.minecraft.src.World;

import java.util.Random;

public class LegacyCherrySaplingBlock extends LegacyDecoSaplingBlock {
    public static final String[] saplingTypes = new String[] {
            "cherry", "", "", "",
            "cherry", "", "", "",
            "cherry", "", "", "",
            "cherryMature", "", "",  ""
    };

    public LegacyCherrySaplingBlock(int blockID) {
        super(blockID, new String[] {"decoBlockSaplingCherry_0"});
        this.setUnlocalizedName("decoBlockSaplingCherryOld");
    }
    
    @Override
    public boolean generateTree(World world, Random rand, int x, int y, int z, int treeType) {
        return this.generateStandardTree(world, rand, x, y, z, DecoBlocks.cherryLog.blockID, DecoBlocks.cherryStump.blockID, DecoBlocks.cherryLeaves.blockID);
    }
    
    @Override
    public int getLogID(int type) {
        return DecoBlocks.cherryLog.blockID;
    }
    
    @Override
    public int getStumpID(int type) {
        return DecoBlocks.cherryStump.blockID;
    }
}
