package net.minecraft.src;

public class DecoBlockConcrete extends Block{
	private Icon[] icons = new Icon[16];
	
	public DecoBlockConcrete(int id) {
		super(id, Material.rock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("decoBlockConcrete");
		DecoManager.Register(this, DecoUtilsMisc.colorOrder);
	}
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
	}

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        return DecoDefs.concreteSidingAndCorner[world.getBlockMetadata(x, y, z)].blockID;
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }
}
