package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class AddonRenderBlocks extends RenderBlocks {
	public AddonRenderBlocks() {
		super();
	}
	
	public AddonRenderBlocks(IBlockAccess blockAccess) {
		super(blockAccess);
	}

    /**
     * Renders the block at the given coordinates using the block's rendering type
     */
    public boolean renderBlockByRenderType(Block block, int x, int y, int z)
    {
        block.m_currentBlockRenderer = this;
        boolean wasRendered;
        
        if (AddonClientUtilsRender.shouldBlockRenderForMultipleLayers(this.blockAccess, x, y, z)) {
            wasRendered = renderStandardFullBlockWithBlending(block, x, y, z);
            block.RenderBlockSecondPass(this, x, y, z, wasRendered);
        }
        else {
            wasRendered = block.RenderBlock(this, x, y, z);
            block.RenderBlockSecondPass(this, x, y, z, wasRendered);
        }
        
        return wasRendered;
    }

    /**
     * Renders a block using the given texture instead of the block's own default texture
     */
    public void renderBlockUsingTexture(Block par1Block, int par2, int par3, int par4, Icon par5Icon)
    {
        this.setOverrideBlockTexture(par5Icon);
        this.renderBlockByRenderType(par1Block, par2, par3, par4);
        this.clearOverrideBlockTexture();
    }

    /**
     * Render all faces of a block
     */
    public void renderBlockAllFaces(Block par1Block, int par2, int par3, int par4)
    {
        this.SetRenderAllFaces(true);
        this.renderBlockByRenderType(par1Block, par2, par3, par4);
        this.SetRenderAllFaces(false);
    }
    
    public boolean renderStandardFullBlockWithBlending(Block block, int x, int y, int z) {
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    	return block.RenderBlock(this, x, y, z);
    }
}