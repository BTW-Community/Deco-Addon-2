package net.minecraft.src;

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
        
        if (AddonClientUtilsRender.shouldBlockRenderForMultipleLayers(this.blockAccess, x, y, z)) {
        	System.out.println("Multilayer render check");
        }
        
        boolean wasRendered = block.RenderBlock(this, x, y, z);
        block.RenderBlockSecondPass(this, x, y, z, wasRendered);
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
}