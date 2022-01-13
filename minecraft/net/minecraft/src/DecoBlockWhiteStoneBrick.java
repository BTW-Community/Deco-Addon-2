package net.minecraft.src;

import java.util.Random;

public class DecoBlockWhiteStoneBrick extends Block {
	public static final int typeDefault = 0;
	public static final int typeMossy = 1;
	public static final int typeCracked = 2;
	public static final int typeChiseled = 3;
	public static final int typeSmooth = 4;
	
	public DecoBlockWhiteStoneBrick(int id) {
		super(id, Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		this.SetPicksEffectiveOn(true);
		setCreativeTab(CreativeTabs.tabBlock);
        this.setTickRandomly(true);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == 0 && !world.getBlockMaterial(x, y - 1, z).blocksMovement()) {
            int blockIDAbove = world.getBlockId(x, y + 1, z);
            Block blockAbove = Block.blocksList[blockIDAbove];
            
            if (rand.nextInt(15) == 0 && blockAbove != null) {
            	if (blockAbove.blockMaterial == Material.water) {
            		world.setBlockMetadataWithNotify(x, y, z, 1);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            	}
            	else if (blockAbove.blockMaterial == Material.lava) {
            		world.setBlockMetadataWithNotify(x, y, z, 2);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            	}
            }
        }
    }
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	//------ Client Only Methods ------//
	
	public static Icon[] icons = new Icon[5];
	
	@Override
	public void registerIcons(IconRegister register) {
		icons[0] = register.registerIcon("decoBlockWhiteBricks");
		icons[1] = register.registerIcon("decoBlockWhiteBricksMossy");
		icons[2] = register.registerIcon("decoBlockWhiteBricksCracked");
		icons[3] = register.registerIcon("decoBlockWhiteBricksChiseled");
		icons[4] = register.registerIcon("decoBlockWhiteStoneSmooth");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}
}
