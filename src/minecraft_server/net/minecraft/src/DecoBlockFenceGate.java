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
            world.setBlockMetadataWithNotify(x, y, z, var10 & -5);
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

            world.setBlockMetadataWithNotify(x, y, z, var10 | 4);
            DecoUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.gateOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        }
        
        return true;
    }
}
