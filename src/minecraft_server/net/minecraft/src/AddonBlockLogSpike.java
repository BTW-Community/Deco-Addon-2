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
}
