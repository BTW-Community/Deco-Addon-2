package net.minecraft.src;

public class AddonBlockLogSpike extends FCBlockLogSpike {
    private Icon m_IconSide;
    private String topTexture;
    private String sideTexture;
    private FCModelBlock m_modelBlock = new FCModelBlockLogSpike();

    public AddonBlockLogSpike(int id, String side, String top) {
    	super(id);
    	topTexture = top;
    	sideTexture = side;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        float var6 = 1.0F;

        if (var5 != null)
        {
            var6 = 1.0F / var5.explosionSize;
        }
        
        if (this.blockID == AddonDefs.logSpikeBlood.blockID)
        	this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSoulDust.itemID, 1, 0, var6);
        else
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 1, 0, var6);
    }
}
