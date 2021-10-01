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
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        AxisAlignedBB boxPool = this.GetBlockBoundsFromPoolBasedOnState(world, x, y, z).offset((double)x, (double)y, (double)z);
        AxisAlignedBB box = boxPool.MakeTemporaryCopy();
        
        box.minX -= x;
        box.maxX -= x;
        box.minY -= y;
        box.maxY -= y;
        box.minZ -= z;
        box.maxZ -= z;
        
        double tempX;
        double tempZ;
        
        double offset = 1 - box.maxY;
        
        switch (this.GetFacing(world.getBlockMetadata(x, y, z))) {
        default:
        case 0:
        	box.minY += offset;
        	box.maxY += offset;
        case 1:
        	break;
        case 2:
        	box.minY += offset;
        	box.maxY += offset;
        case 3:
        	tempZ = box.minZ;
        	box.minZ = box.minY;
        	box.minY = tempZ;

        	tempZ = box.maxZ;
        	box.maxZ = box.maxY;
        	box.maxY = tempZ;
        	break;
        case 4:
        	box.minY += offset;
        	box.maxY += offset;
        case 5:
        	tempX = box.minX;
        	box.minX = box.minY;
        	box.minY = tempX;

        	tempX = box.maxX;
        	box.maxX = box.maxY;
        	box.maxY = tempX;
        	break;
        }
        
        box.minX += x;
        box.maxX += x;
        box.minY += y;
        box.maxY += y;
        box.minZ += z;
        box.maxZ += z;
        
        return box;
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
    public int idDropped(int par1, Random par2Random, int par3) {
        return DecoDefs.amethystShard.itemID;
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
    @Override
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
        DecoClientUtilsRender.renderCrossedSquares(render, this, x, y, z, this.GetFacing(render.blockAccess.getBlockMetadata(x, y, z)));
        return true;
    }
    
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return DecoDefs.amethystShard.itemID;
    }
}