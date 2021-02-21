package net.minecraft.src;

public class DecoBlockFenceGate extends FCBlockFenceGate {
	private String iconName;

	public DecoBlockFenceGate(int ID, String texture) {
		super(ID);
		this.setUnlocalizedName(texture);
		iconName = texture;
	}

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return true;
    }
	
	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = getDirection(var1.getBlockMetadata(var2, var3, var4));
        double offset = 0;
        
        if (isNextToWall(var1, var2, var3, var4))
        	offset = 0.1875D;
        
        return var5 != 2 && var5 != 0 ? AxisAlignedBB.getAABBPool().getAABB(0.4375D, 0.3125D - offset, 0.0D, 0.5625D, 1.0D - offset, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.3125D - offset, 0.4375D, 1.0D, 1.0D - offset, 0.5625D);
    }
	
	protected boolean isNextToWall(IBlockAccess blockAccess, int x, int y, int z) {
        int meta = blockAccess.getBlockMetadata(x, y, z);
        int dir = BlockDirectional.getDirection(meta);
        int otherID;
        int otherMeta;
        boolean isNextToWall = false;
		
        if (dir == 0 || dir == 2) {
        	otherID = blockAccess.getBlockId(x - 1, y, z);
        	otherMeta = blockAccess.getBlockMetadata(x - 1, y, z);
        	
        	if (DecoUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;

        	otherID = blockAccess.getBlockId(x + 1, y, z);
        	otherMeta = blockAccess.getBlockMetadata(x + 1, y, z);
        	
        	if (DecoUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;
        }
        else if (dir == 1 || dir == 3) {
        	otherID = blockAccess.getBlockId(x, y, z - 1);
        	otherMeta = blockAccess.getBlockMetadata(x, y, z - 1);
        	
        	if (DecoUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;

        	otherID = blockAccess.getBlockId(x, y, z + 1);
        	otherMeta = blockAccess.getBlockMetadata(x, y, z + 1);
        	
        	if (DecoUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;
        }
        
        return isNextToWall;
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        int var10 = world.getBlockMetadata(x, y, z);

        if (isFenceGateOpen(var10))
        {
            world.setBlockMetadataWithNotify(x, y, z, var10 & -5, 2);
            DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.gateClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        }
        else
        {
            int var11 = (MathHelper.floor_double((double)(par5EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int var12 = getDirection(var10);

            if (var12 == (var11 + 2) % 4)
            {
                var10 = var11;
            }

            world.setBlockMetadataWithNotify(x, y, z, var10 | 4, 2);
            DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.gateOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        }
        
        return true;
    }
	
	//CLIENT ONLY
	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
	}
	
	@Override
	public void registerIcons(IconRegister register)
	{
		blockIcon = register.registerIcon(iconName);
	}
	
	@Override
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
        boolean var5 = true;
        int var6 = render.blockAccess.getBlockMetadata(x, y, z);
        boolean var7 = this.isFenceGateOpen(var6);
        int var8 = getDirection(var6);
        float var9 = 0.375F;
        float var10 = 0.5625F;
        float var11 = 0.75F;
        float var12 = 0.9375F;
        float var13 = 0.3125F;
        float var14 = 1.0F;

        if (isNextToWall(render.blockAccess, x, y, z))
        {
            var9 -= 0.1875F;
            var10 -= 0.1875F;
            var11 -= 0.1875F;
            var12 -= 0.1875F;
            var13 -= 0.1875F;
            var14 -= 0.1875F;
        }

        render.SetRenderAllFaces(true);
        float var15;
        float var16;
        float var17;
        float var18;

        if (var8 != 3 && var8 != 1)
        {
            var15 = 0.0F;
            var17 = 0.125F;
            var16 = 0.4375F;
            var18 = 0.5625F;
            render.setRenderBounds((double)var15, (double)var13, (double)var16, (double)var17, (double)var14, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var15 = 0.875F;
            var17 = 1.0F;
            render.setRenderBounds((double)var15, (double)var13, (double)var16, (double)var17, (double)var14, (double)var18);
            render.renderStandardBlock(this, x, y, z);
        }
        else
        {
            render.SetUvRotateTop(1);
            var15 = 0.4375F;
            var17 = 0.5625F;
            var16 = 0.0F;
            var18 = 0.125F;
            render.setRenderBounds((double)var15, (double)var13, (double)var16, (double)var17, (double)var14, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var16 = 0.875F;
            var18 = 1.0F;
            render.setRenderBounds((double)var15, (double)var13, (double)var16, (double)var17, (double)var14, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            render.SetUvRotateTop(0);
        }

        if (var7)
        {
            if (var8 == 2 || var8 == 0)
            {
                render.SetUvRotateTop(1);
            }

            if (var8 == 3)
            {
                render.setRenderBounds(0.8125D, (double)var9, 0.0D, 0.9375D, (double)var12, 0.125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.8125D, (double)var9, 0.875D, 0.9375D, (double)var12, 1.0D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.5625D, (double)var9, 0.0D, 0.8125D, (double)var10, 0.125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.5625D, (double)var9, 0.875D, 0.8125D, (double)var10, 1.0D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.5625D, (double)var11, 0.0D, 0.8125D, (double)var12, 0.125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.5625D, (double)var11, 0.875D, 0.8125D, (double)var12, 1.0D);
                render.renderStandardBlock(this, x, y, z);
            }
            else if (var8 == 1)
            {
                render.setRenderBounds(0.0625D, (double)var9, 0.0D, 0.1875D, (double)var12, 0.125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.0625D, (double)var9, 0.875D, 0.1875D, (double)var12, 1.0D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.1875D, (double)var9, 0.0D, 0.4375D, (double)var10, 0.125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.1875D, (double)var9, 0.875D, 0.4375D, (double)var10, 1.0D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.1875D, (double)var11, 0.0D, 0.4375D, (double)var12, 0.125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.1875D, (double)var11, 0.875D, 0.4375D, (double)var12, 1.0D);
                render.renderStandardBlock(this, x, y, z);
            }
            else if (var8 == 0)
            {
                render.setRenderBounds(0.0D, (double)var9, 0.8125D, 0.125D, (double)var12, 0.9375D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.875D, (double)var9, 0.8125D, 1.0D, (double)var12, 0.9375D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.0D, (double)var9, 0.5625D, 0.125D, (double)var10, 0.8125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.875D, (double)var9, 0.5625D, 1.0D, (double)var10, 0.8125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.0D, (double)var11, 0.5625D, 0.125D, (double)var12, 0.8125D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.875D, (double)var11, 0.5625D, 1.0D, (double)var12, 0.8125D);
                render.renderStandardBlock(this, x, y, z);
            }
            else if (var8 == 2)
            {
                render.setRenderBounds(0.0D, (double)var9, 0.0625D, 0.125D, (double)var12, 0.1875D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.875D, (double)var9, 0.0625D, 1.0D, (double)var12, 0.1875D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.0D, (double)var9, 0.1875D, 0.125D, (double)var10, 0.4375D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.875D, (double)var9, 0.1875D, 1.0D, (double)var10, 0.4375D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.0D, (double)var11, 0.1875D, 0.125D, (double)var12, 0.4375D);
                render.renderStandardBlock(this, x, y, z);
                render.setRenderBounds(0.875D, (double)var11, 0.1875D, 1.0D, (double)var12, 0.4375D);
                render.renderStandardBlock(this, x, y, z);
            }
        }
        else if (var8 != 3 && var8 != 1)
        {
            var15 = 0.375F;
            var17 = 0.5F;
            var16 = 0.4375F;
            var18 = 0.5625F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var15 = 0.5F;
            var17 = 0.625F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var15 = 0.625F;
            var17 = 0.875F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var10, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            render.setRenderBounds((double)var15, (double)var11, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var15 = 0.125F;
            var17 = 0.375F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var10, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            render.setRenderBounds((double)var15, (double)var11, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
        }
        else
        {
            render.SetUvRotateTop(1);
            var15 = 0.4375F;
            var17 = 0.5625F;
            var16 = 0.375F;
            var18 = 0.5F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var16 = 0.5F;
            var18 = 0.625F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var16 = 0.625F;
            var18 = 0.875F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var10, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            render.setRenderBounds((double)var15, (double)var11, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            var16 = 0.125F;
            var18 = 0.375F;
            render.setRenderBounds((double)var15, (double)var9, (double)var16, (double)var17, (double)var10, (double)var18);
            render.renderStandardBlock(this, x, y, z);
            render.setRenderBounds((double)var15, (double)var11, (double)var16, (double)var17, (double)var12, (double)var18);
            render.renderStandardBlock(this, x, y, z);
        }

        render.SetRenderAllFaces(false);;
        render.SetUvRotateTop(0);
        render.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var5;

	}
}
