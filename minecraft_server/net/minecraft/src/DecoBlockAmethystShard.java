package net.minecraft.src;

import java.util.Random;

public class DecoBlockAmethystShard extends Block {
    public DecoBlockAmethystShard(int id) {
        super(id, Material.glass);
        this.setHardness(0.3F);
        this.setStepSound(Block.soundGlassFootstep);
        this.setUnlocalizedName("decoBlockAmethystShard");
        this.SetPicksEffectiveOn();
        
        this.InitBlockBounds(new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.875, 0.75));
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
        super.onNeighborBlockChange(world, x, y, z, neighborID);
        
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
    	return this.canAmethystStay(world, x, y, z, GetFacing(world, x, y, z));
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int facing) {
        return this.canAmethystStay(world, x, y, z, facing);
    }
    
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }
	
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return DecoDefs.amethystShard.itemID;
    }

    @Override
	public boolean GetPreventsFluidFlow(World world, int x, int y, int z, Block fluidBlock) {
    	return false;
	}
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    	this.SetFacing(world, x, y, z, side);
    	return side;
    }
    
    @Override
    public int GetFacing(int meta) {
    	return meta;
    }
    
    @Override
    public void SetFacing(World world, int x, int y, int z, int facing) {
    	world.setBlockMetadata(x, y, z, facing);
    }
    
    // ------ Class specific methods ------ //
    public boolean canAmethystStay(World world, int x, int y, int z, int facing) {
    	FCUtilsBlockPos pos = new FCUtilsBlockPos(x, y, z, DecoUtilsBlock.getOppositeFacing(facing));
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, pos.i, pos.j, pos.k, facing, true);
    }

    // ------ Client only methods ------ //
}