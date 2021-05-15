package net.minecraft.src;

import java.util.Random;

public class DecoBlockLadderIron extends FCBlockLadderBase {
    protected static final AxisAlignedBB boxCollision = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    
	public DecoBlockLadderIron(int id) {
		super(id);

		this.SetAxesEffectiveOn(false);
		this.setHardness(1.5F);
		this.SetNonBuoyant();
		this.SetBlockMaterial(Material.iron);
		this.setStepSound(DecoDefs.stepSoundLadderIron);
		this.setUnlocalizedName("decoBlockLadderIron");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

    @Override
    public int idDropped(int var1, Random var2, int var3) {
        return this.blockID;
    }

	@Override
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = boxCollision.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        return var5;
    }

	@Override
    protected boolean CanAttachToFacing(World world, int x, int y, int z, int facing)
    {
		return super.CanAttachToFacing(world, x, y, z, facing);
		
		/*
        if (facing >= 2)
        {
            FCUtilsBlockPos pos = new FCUtilsBlockPos(x, y, z, facing);
            
            if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, pos.i, pos.j, pos.k, Block.GetOppositeFacing(facing))) {
            	return true;
            }
            else {
            	return world.getBlockId(x, y + 1, z) == this.blockID && world.getBlockMetadata(x, y + 1, z) == world.getBlockMetadata(x, y, z);
            }
        }
        else
        {
            return false;
        }
        */
    }
	
	//CLIENT ONLY
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	if (this.blockID == FCBetterThanWolves.fcBlockLadder.blockID) {
    		this.blockIcon = register.registerIcon("ladder");
    	}
    	else {
    		this.blockIcon = register.registerIcon(this.getUnlocalizedName2());
    	}
    }

    @Override
    public Icon GetHopperFilterIcon()
    {
        return this.blockIcon;
    }
    
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return this.RenderLadder(var1, var2, var3, var4);
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
}