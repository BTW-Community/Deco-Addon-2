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
}