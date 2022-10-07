package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.PlanterBlockBase;
import btw.block.blocks.legacy.LegacySaplingBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public abstract class DecoSaplingBlock extends LegacySaplingBlock {
    private String[] baseTextureNames;

    public DecoSaplingBlock(int blockID, String[] baseTextureNames) {
        super(blockID);

        this.baseTextureNames = baseTextureNames;
    }
    
    @Override
    //Debug method
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            growTree(world, x, y, z, world.rand);
        }
        
        return true;
    }
    
    @Override
    public void growTree(World world, int x, int y, int z, Random rand) {
        int treeType = world.getBlockMetadata(x, y, z) & 3;
        boolean planter = Block.blocksList[world.getBlockId(x, y - 1, z)] instanceof PlanterBlockBase;
        
        world.setBlock(x, y, z, 0);
        
        boolean success = this.generateTree(world, rand, x, y, z);
        
        if (!success) {
            world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, treeType | 3 << 2);
        }
        else if (planter) {
            world.setBlockAndMetadata(x, y, z, this.getLogID(), this.getLogMetadata());
            
            //Block break sfx
            world.playAuxSFX(2001, x, y - 1, z, BTWBlocks.planterWithSoil.blockID);
    
            world.setBlockAndMetadata(x, y - 1, z, this.getStumpID(), this.getStumpMetadata());
        }
    }
    
    //------------- Class Specific Methods ------------//
    
    public abstract boolean generateTree(World world, Random rand, int x, int y, int z);
    
    public abstract int getLogID();
    
    public int getLogMetadata() {
        return 0;
    }
    
    public abstract int getStumpID();
    
    public int getStumpMetadata() {
        return 0;
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    private Icon[][] iconArray;

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        iconArray = new Icon[baseTextureNames.length][4];

        for (int i = 0; i < baseTextureNames.length; i++) {
            for (int j = 0; j < 4; j++) {
                iconArray[i][j] = register.registerIcon(baseTextureNames[i] + j);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
        int subtype = meta & 3;
        int growthStage = (meta & -4) >> 2;
        return this.iconArray[subtype][growthStage];
    }
}