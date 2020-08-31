package net.minecraft.src;

public class DecoBlockInfusedStone extends Block {
	public DecoBlockInfusedStone(int id) {
		super(id, Material.rock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10F);
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
    	
    	icons[0] = register.registerIcon("ginger_infusedStone");
    	icons[1] = register.registerIcon("ginger_infusedStoneSmooth");
    	icons[2] = register.registerIcon("ginger_infusedStoneBrick");
    	icons[3] = register.registerIcon("ginger_infusedStoneChiseled");
    }
}
