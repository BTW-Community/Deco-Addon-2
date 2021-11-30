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
    
    //CLIENT ONLY
    private Icon[] icons;
    
    public Icon getIcon(int side, int meta) {
    	return icons[meta];
    }
    
    public void registerIcons(IconRegister register) {
    	icons = new Icon[4];
    	
    	icons[0] = register.registerIcon("decoBlockInfusedStone");
    	icons[1] = register.registerIcon("decoBlockInfusedStoneSmooth");
    	icons[2] = register.registerIcon("decoBlockInfusedStoneBrick");
    	icons[3] = register.registerIcon("decoBlockInfusedStoneChiseled");
    }
}
