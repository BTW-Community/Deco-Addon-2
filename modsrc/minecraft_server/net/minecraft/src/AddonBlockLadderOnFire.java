package net.minecraft.src;

public class AddonBlockLadderOnFire extends FCBlockLadderOnFire {
    protected static final AxisAlignedBB m_boxCollision = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);

	protected AddonBlockLadderOnFire(int ID) {
		super(ID);
	}

	@Override
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = m_boxCollision.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        return var5;
    }
}
