package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class DecoBlockDirtSlab extends FCBlockDirtSlab {
	public DecoBlockDirtSlab(int id) {
		super(id);
	}

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        if (this.GetCanGrassSpreadToBlock(var1, var2, var3, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 11 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4))
        	return this.SpreadGrassToBlock(var1, var2, var3, var4);
        return false;
    }
    
    //CLIENT ONLY
    private Icon iconGrassTopRough;
    private Icon m_IconGrassSide;
    private Icon m_IconGrassSideOverlay;
    private Icon m_IconGrassTop;
    private Icon m_IconGrassTopItem;
    private Icon m_IconGrassSideHalf;
    private Icon m_IconGrassSideOverlayHalf;
    private Icon m_IconPackedEarth;
    private Icon m_IconGrassWithSnowSide;
    private Icon m_IconGrassWithSnowSideHalf;

    private Icon GetIconFromMetadata(int var1, int var2)
    {
        int var3 = this.GetSubtype(var2);

        if (var3 == 1 && var1 != 0)
        {
            if (var1 != 1)
            {
                boolean var4 = (var2 & 1) > 0;
                return var4 ? this.m_IconGrassSide : this.m_IconGrassSideHalf;
            }
            else
            {
                return this.m_IconGrassTop;
            }
        }
        else
        {
            return var3 == 3 ? this.m_IconPackedEarth : this.blockIcon;
        }
    }
    
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	World world = null;
    	
    	if (blockAccess instanceof ChunkCache) {
    		ChunkCache chunkCache = (ChunkCache) blockAccess;
    		world = DecoManager.getWorldFromChunkCache(chunkCache);
    	}
    	else if (blockAccess instanceof World) {
    		world = (World) blockAccess;
    	}
    	
    	int skylight;
    	
    	if (world != null)
    		skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	else
    		skylight = 9;
    	
        return side == 1 ? (skylight >= 9 ? this.m_IconGrassTop : this.iconGrassTopRough) : this.GetIconFromMetadata(side, world.getBlockMetadata(x,  y,  z));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("dirt");
        this.m_IconGrassSide = var1.registerIcon("grass_side");
        this.m_IconGrassSideOverlay = var1.registerIcon("grass_side_overlay");
        this.m_IconGrassTop = var1.registerIcon("grass_top");
        this.iconGrassTopRough = var1.registerIcon("decoBlockGrassRough");
        this.m_IconGrassTopItem = var1.registerIcon("fcBlockSlabDirt_grass_top_item");
        this.m_IconGrassSideHalf = var1.registerIcon("FCBlockSlabDirt_grass_side");
        this.m_IconGrassSideOverlayHalf = var1.registerIcon("FCBlockSlabDirt_grass_side_overlay");
        this.m_IconPackedEarth = var1.registerIcon("FCBlockPackedEarth");
        this.m_IconGrassWithSnowSide = var1.registerIcon("snow_side");
        this.m_IconGrassWithSnowSideHalf = var1.registerIcon("FCBlockSlabDirt_grass_snow_side");
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        int var6 = FCBetterThanWolves.fcBlockDirtSlab.GetSubtype(var5, var2, var3, var4);

        if (var6 == 1 && var5.getBlockId(var2, var3 + 1, var4) != Block.snow.blockID)
        {
            int var7 = this.colorMultiplier(var5, var2, var3, var4);
            float var8 = (float)(var7 >> 16 & 255) / 255.0F;
            float var9 = (float)(var7 >> 8 & 255) / 255.0F;
            float var10 = (float)(var7 & 255) / 255.0F;
            return Minecraft.isAmbientOcclusionEnabled() ? var1.renderGrassBlockWithAmbientOcclusion(this, var2, var3, var4, var8, var9, var10, this.GetSideOverlayTexture(var5, var2, var3, var4)) : var1.renderGrassBlockWithColorMultiplier(this, var2, var3, var4, var8, var9, var10, this.GetSideOverlayTexture(var5, var2, var3, var4));
        }
        else
        {
            return var1.renderStandardBlock(this, var2, var3, var4);
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, var2 << 1);
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);

        if (var5 == 1 && var1.getBlockId(var2, var3 + 1, var4) != Block.snow.blockID)
        {
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;

            for (int var9 = -1; var9 <= 1; ++var9)
            {
                for (int var10 = -1; var10 <= 1; ++var10)
                {
                    int var11 = var1.getBiomeGenForCoords(var2 + var10, var4 + var9).getBiomeGrassColor();
                    var6 += (var11 & 16711680) >> 16;
                    var7 += (var11 & 65280) >> 8;
                    var8 += var11 & 255;
                }
            }

            return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
        }
        else
        {
            return super.colorMultiplier(var1, var2, var3, var4);
        }
    }

    public Icon GetSideOverlayTexture(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsUpsideDown(var1, var2, var3, var4) ? this.m_IconGrassSideOverlayHalf : this.m_IconGrassSideOverlay;
    }
}