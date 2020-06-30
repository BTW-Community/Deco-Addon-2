package net.minecraft.src;

public class AddonBlockFenceGate extends FCBlockFenceGate {
	private String iconName;

	public AddonBlockFenceGate(int ID, String texture) {
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
        	
        	if (AddonUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;

        	otherID = blockAccess.getBlockId(x + 1, y, z);
        	otherMeta = blockAccess.getBlockMetadata(x + 1, y, z);
        	
        	if (AddonUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;
        }
        else if (dir == 1 || dir == 3) {
        	otherID = blockAccess.getBlockId(x, y, z - 1);
        	otherMeta = blockAccess.getBlockMetadata(x, y, z - 1);
        	
        	if (AddonUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;

        	otherID = blockAccess.getBlockId(x, y, z + 1);
        	otherMeta = blockAccess.getBlockMetadata(x, y, z + 1);
        	
        	if (AddonUtilsBlock.isWall(otherID, otherMeta))
        		isNextToWall = true;
        }
        
        return isNextToWall;
	}
}
