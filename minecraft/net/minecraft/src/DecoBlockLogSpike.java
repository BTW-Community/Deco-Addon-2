package net.minecraft.src;

public class DecoBlockLogSpike extends FCBlockLogSpike {
    private Icon m_IconSide;
    private String topTexture;
    private String sideTexture;
    private FCModelBlock m_modelBlock = new FCModelBlockLogSpike();

    public DecoBlockLogSpike(int id, String side, String top) {
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
        
        if (this.blockID == DecoDefs.logSpikeBlood.blockID)
        	this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSoulDust.itemID, 1, 0, var6);
        else
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 1, 0, var6);
    }

    //CLIENT ONLY
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
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        int var5 = this.GetFacing(render.blockAccess, x, y, z);
        FCModelBlock var6 = this.m_modelBlock.MakeTemporaryCopy();
        var6.TiltToFacingAlongJ(var5);

        int var7 = render.blockAccess.getBlockMetadata(x, y, z);

        if (var7 == 4 || var7 == 5 || var7 == 6 || var7 == 7)
        {
			render.SetUvRotateTop(1);
			render.SetUvRotateBottom(1);
			render.SetUvRotateWest(1);
			render.SetUvRotateEast(1);
        }
        else if (var7 == 8 || var7 == 9 || var7 == 10 || var7 == 11)
        {
			render.SetUvRotateNorth(1);
			render.SetUvRotateSouth(1);
        }
        
        var6.RenderAsBlock(render, this, x, y, z);
		render.ClearUvRotation();
		return true;
    }
}
