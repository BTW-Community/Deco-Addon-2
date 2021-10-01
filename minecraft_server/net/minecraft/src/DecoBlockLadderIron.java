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
}