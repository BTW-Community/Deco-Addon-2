package net.minecraft.src;

public class DecoBlockMouldingAndDecorativeWall extends FCBlockMouldingAndDecorative {
	public DecoBlockMouldingAndDecorativeWall(int var1, Material var2, String var3, String var4, int var5, float var6,
			float var7, StepSound var8, String var9) {
		super(var1, var2, var3, var4, var5, var6, var7, var8, var9);
	}

	public DecoBlockMouldingAndDecorativeWall(int var1, Material var2, String var3, String var4, String var5,
			String var6, String var7, int var8, float var9, float var10, StepSound var11, String var12) {
		super(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
	}

	//CLIENT ONLY
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = var5.getBlockMetadata(var2, var3, var4);

        if (!this.IsDecorative(var6))
        {
            return super.RenderBlock(var1, var2, var3, var4);
        }
        else
        {
            switch (var6)
            {
                case 13:
                    return RenderPedestalUp(var1, var5, var2, var3, var4, this);

                case 14:
                    return RenderPedestalDown(var1, var5, var2, var3, var4, this);

                case 15:
                    return RenderTable(var1, var5, var2, var3, var4, this);

                default:
                    var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
                    return var1.renderStandardBlock(this, var2, var3, var4);
            }
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        if (this.IsDecorative(var2))
        {
            this.RenderDecorativeInvBlock(var1, this, var2, var3);
        }
        else
        {
            super.RenderBlockAsItem(var1, var2, var3);
        }
    }

    protected void RenderDecorativeInvBlock(RenderBlocks var1, Block var2, int var3, float var4)
    {
        switch (var3)
        {
            case 12:
                var1.setRenderBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
                FCClientUtilsRender.RenderInvBlockWithMetadata(var1, var2, -0.5F, -0.5F, -0.5F, var3);
                break;

            case 13:
                RenderPedestalUpInvBlock(var1, var2);
                break;

            case 14:
                RenderPedestalDownInvBlock(var1, var2);
                break;

            case 15:
                RenderTableInvBlock(var1, var2);
                break;

            default:
                var1.renderBlockAsItemVanilla(var2, var3, var4);
        }
    }
    
    public static boolean RenderTable(RenderBlocks renderBlocks, IBlockAccess blockAccess, int x, int y, int z, Block block)
    {
        FCBlockMouldingAndDecorative blockMoulding = (FCBlockMouldingAndDecorative)block;
        renderBlocks.setRenderBounds(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderBlocks.renderStandardBlock(block, x, y, z);

        if (blockMoulding.DoesTableHaveLeg(blockAccess, x, y, z))
        {
            renderBlocks.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.875D, 0.75D);
            renderBlocks.renderStandardBlock(block, x, y, z);
            
            if (blockMoulding.DoesTableHaveLeg(blockAccess, x - 1, y, z) && Block.blocksList[blockAccess.getBlockId(x - 1, y, z)] instanceof DecoBlockMouldingAndDecorativeWall && blockAccess.getBlockMetadata(x - 1, y, z) == 15) {
                renderBlocks.setRenderBounds(0.25D, 0.0D, 0.6875D, 0D, 0.875D, 0.3125D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
            if (blockMoulding.DoesTableHaveLeg(blockAccess, x, y, z - 1) && Block.blocksList[blockAccess.getBlockId(x, y, z - 1)] instanceof DecoBlockMouldingAndDecorativeWall && blockAccess.getBlockMetadata(x, y, z - 1) == 15) {
                renderBlocks.setRenderBounds(0.6875D, 0.0D, 0.25D, 0.3125D, 0.875D, 0D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
            if (blockMoulding.DoesTableHaveLeg(blockAccess, x + 1, y, z) && Block.blocksList[blockAccess.getBlockId(x + 1, y, z)] instanceof DecoBlockMouldingAndDecorativeWall && blockAccess.getBlockMetadata(x + 1, y, z) == 15) {
                renderBlocks.setRenderBounds(1D, 0.0D, 0.6875D, 0.75D, 0.875D, 0.3125D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
            if (blockMoulding.DoesTableHaveLeg(blockAccess, x, y, z + 1) && Block.blocksList[blockAccess.getBlockId(x, y, z + 1)] instanceof DecoBlockMouldingAndDecorativeWall && blockAccess.getBlockMetadata(x, y, z + 1) == 15) {
                renderBlocks.setRenderBounds(0.6875D, 0.0D, 1D, 0.3125D, 0.875D, 0.75D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
        }

        return true;
    }

    public static void RenderTableInvBlock(RenderBlocks var0, Block var1)
    {
        var0.setRenderBounds(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 15);
        var0.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.875D, 0.75D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 15);
    }
}