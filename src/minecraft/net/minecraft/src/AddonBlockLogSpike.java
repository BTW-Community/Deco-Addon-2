package net.minecraft.src;

public class AddonBlockLogSpike extends FCBlockLogSpike {
    private Icon m_IconSide;
    private String topTexture;
    private String sideTexture;

    public AddonBlockLogSpike(int id, String side, String top) {
    	super(id);
    	topTexture = top;
    	sideTexture = side;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon(topTexture);
        this.m_IconSide = var1.registerIcon(sideTexture);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = this.GetFacing(var2);
        return var1 != var3 && var1 != Block.GetOppositeFacing(var3) ? this.m_IconSide : this.blockIcon;
    }
}
