package deco.block.blocks;

import btw.block.blocks.legacy.LegacySaplingBlock;
import btw.util.ReflectionUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

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
            attemptToGrow(world, x, y, z, world.rand);
        }
        
        return true;
    }

    @Override
    public abstract void growTree(World world, int x, int y, int z, Random rand);

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