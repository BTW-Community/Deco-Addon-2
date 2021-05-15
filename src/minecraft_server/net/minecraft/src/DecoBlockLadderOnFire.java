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
}
