package net.minecraft.src;

public class DecoBlockBookshelf extends FCBlockBookshelf {
	private final Type type;
	private String textureBase;
	
	public DecoBlockBookshelf(int id, String textureBase, Type type) {
		super(id);
		this.textureBase = textureBase;
		this.type = type;
		
		this.setUnlocalizedName(textureBase);
	}
	
	@Override
    public int damageDropped(int meta) {
        return meta;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        
        switch (type) {
        case ENCHANTED:
        	this.DropItemsIndividualy(var1, var2, var3, var4, Item.book.itemID, 3, 0, var6);
        	break;
        case FULL:
        	this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.bookPlain.itemID, 3, 0, var6);
        	break;
        case RACK:
        	this.DropItemsIndividualy(var1, var2, var3, var4, Item.glassBottle.itemID, 1, 0, var6);
        	break;
        default:
        }
        
        return true;
    }
    
    public static enum Type {
    	EMPTY,
    	FULL,
    	ENCHANTED,
    	RACK_EMPTY,
    	RACK,
    }
	
	//CLIENT ONLY
	private Icon[] sideTextures = new Icon[7];
	
	@Override
	public Icon getIcon(int side, int meta) {
		if (side == 0 || side == 1) {
			return Block.planks.getIcon(side, meta);
		}
		else {
			return sideTextures[meta];
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		sideTextures[0] = register.registerIcon(textureBase + "Oak");
		sideTextures[1] = register.registerIcon(textureBase + "Spruce");
		sideTextures[2] = register.registerIcon(textureBase + "Birch");
		sideTextures[3] = register.registerIcon(textureBase + "Jungle");
		sideTextures[4] = register.registerIcon(textureBase + "Blood");
		sideTextures[5] = register.registerIcon(textureBase + "Cherry");
		sideTextures[6] = register.registerIcon(textureBase + "Acacia");
	}
}