package net.minecraft.src;

public class DecoBlockInfusedStone extends Block {
	public static final int typeDefault = 0;
	public static final int typeSmooth = 1;
	public static final int typeBrick = 2;
	public static final int typeChiseled = 3;
	
	public DecoBlockInfusedStone(int id) {
		super(id, Material.rock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10F);
		this.setUnlocalizedName("decoBlockInfusedStone");
	}
	
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case typeDefault:
        	return DecoDefs.infusedStoneSidingAndCorner.blockID;
        case typeSmooth:
        	return DecoDefs.infusedStoneSmoothSidingAndCorner.blockID;
        case typeBrick:
        	return DecoDefs.infusedStoneBrickSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }
}
