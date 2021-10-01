package net.minecraft.src;

import java.util.Random;

public class DecoBlockLadderOnFire extends FCBlockLadderOnFire {
    protected static final AxisAlignedBB boxCollision = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    private final int ladderId;

	public DecoBlockLadderOnFire(int id, int ladderId) {
		super(id);
		this.ladderId = ladderId;
	}

    @Override
    public int idDropped(int var1, Random var2, int var3) {
        return this.ladderId;
    }

	@Override
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = boxCollision.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        return var5;
    }

	@Override
    protected void Extinguish(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLadder.SetFacing(0, this.GetFacing(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, ladderId, var5);
    }
	
	//CLIENT ONLY
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	if (this.blockID == FCBetterThanWolves.fcBlockLadderOnFire.blockID) {
    		this.blockIcon = register.registerIcon("ladder");
    	}
    	else {
    		this.blockIcon = register.registerIcon(this.getUnlocalizedName2());
    	}
    }
    
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        if (this.RenderLadder(var1, var2, var3, var4)) {
        	this.RenderFirePortion(var1, var2, var3, var4);
        	return true;
        }
        return false;
    }

    public boolean RenderLadder(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetFacing(var5, var2, var3, var4);
        Tessellator var7 = Tessellator.instance;
        var7.setBrightness(this.getMixedBrightnessForBlock(var5, var2, var3, var4));
        float var8 = 1.0F;
        var7.setColorOpaque_F(var8, var8, var8);
        Icon var9 = this.blockIcon;

        if (var1.hasOverrideBlockTexture())
        {
            var9 = var1.GetOverrideTexture();
        }

        double var10 = (double)var9.getMinU();
        double var12 = (double)var9.getMinV();
        double var14 = (double)var9.getMaxU();
        double var16 = (double)var9.getMaxV();

        if (var6 == 5)
        {
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 1), var10, var12);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 1), var10, var16);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 0), var14, var16);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 0), var14, var12);
            
            //Back side
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 0), var14, var12);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 0), var14, var16);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 1), var10, var16);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 1), var10, var12);
        }
        else if (var6 == 4)
        {
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 1), var14, var16);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 1), var14, var12);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 0), var10, var12);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 0), var10, var16);
            
            //Back side
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 0), var10, var16);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 0), var10, var12);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 1), var14, var12);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 1), var14, var16);
        }
        else if (var6 == 3)
        {
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + 0.05000000074505806D, var14, var16);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + 0.05000000074505806D, var14, var12);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + 0.05000000074505806D, var10, var12);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + 0.05000000074505806D, var10, var16);
            
            //Back side
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + 0.05000000074505806D, var10, var16);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + 0.05000000074505806D, var10, var12);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + 0.05000000074505806D, var14, var12);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + 0.05000000074505806D, var14, var16);
        }
        else if (var6 == 2)
        {
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - 0.05000000074505806D, var10, var12);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - 0.05000000074505806D, var10, var16);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - 0.05000000074505806D, var14, var16);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - 0.05000000074505806D, var14, var12);
            
            //Back side
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - 0.05000000074505806D, var14, var12);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - 0.05000000074505806D, var14, var16);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - 0.05000000074505806D, var10, var16);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - 0.05000000074505806D, var10, var12);
        }

        return true;
    }

    private void RenderFirePortion(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetFacing(var5, var2, var3, var4);
        Tessellator var7 = Tessellator.instance;
        Icon var8;

        if ((var2 + var3 + var4 & 1) != 0)
        {
            var8 = Block.fire.func_94438_c(1);
        }
        else
        {
            var8 = Block.fire.func_94438_c(0);
        }

        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(Block.fire.getMixedBrightnessForBlock(var5, var2, var3, var4));
        double var9 = (double)var8.getMinU();
        double var11 = (double)var8.getMinV();
        double var13 = (double)var8.getMaxU();
        double var15 = (double)var8.getMaxV();
        float var17 = 1.4F;
        double var18 = 0.20000000298023224D;
        double var20 = 0.0D;
        float var22 = 0.0625F;

        if ((var2 / 2 + var3 / 2 + var4 / 2 & 1) != 0)
        {
            double var23 = var13;
            var13 = var9;
            var9 = var23;
        }

        if (var6 == 5)
        {
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)var2 + var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)var2 + var18 + var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
        }
        else if (var6 == 4)
        {
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)(var4 + 1), var13, var11);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)(var4 + 1), var13, var15);
            var7.addVertexWithUV((double)(var2 + 1) - var20, (double)((float)var3 + var22), (double)var4, var9, var15);
            var7.addVertexWithUV((double)var2 + 1.0D - var18 - var20, (double)((float)var3 + var17 + var22), (double)var4, var9, var11);
        }
        else if (var6 == 3)
        {
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var13, var11);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)var4 + var20, var13, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)var4 + var20, var9, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)var4 + var20, var9, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)var4 + var20, var13, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + var18 + var20, var13, var11);
        }
        else if (var6 == 2)
        {
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var9, var11);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)(var4 + 1) - var20, var9, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)(var4 + 1) - var20, var13, var15);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var13, var11);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var13, var11);
            var7.addVertexWithUV((double)var2, (double)((float)var3 + var22), (double)(var4 + 1) - var20, var13, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var22), (double)(var4 + 1) - var20, var9, var15);
            var7.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var22), (double)var4 + 1.0D - var18 - var20, var9, var11);
        }
    }
}
