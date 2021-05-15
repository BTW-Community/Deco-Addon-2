package net.minecraft.src;

import java.util.Random;

public class DecoBlockLadder extends FCBlockLadder {
    protected static final AxisAlignedBB boxCollision = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    private final int ladderOnFireId;

	public DecoBlockLadder(int id, int ladderOnFireId) {
		super(id);
		this.ladderOnFireId = ladderOnFireId;
	}

    @Override
    public int idDropped(int var1, Random var2, int var3) {
        return this.blockID;
    }

	@Override
    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLadderOnFire.SetFacing(0, this.GetFacing(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.ladderOnFireId, var5);
        return true;
    }

	@Override
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = boxCollision.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        return var5;
    }
}
