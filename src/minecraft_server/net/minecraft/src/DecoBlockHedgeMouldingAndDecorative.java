package net.minecraft.src;

import java.util.Random;

public class DecoBlockHedgeMouldingAndDecorative extends DecoBlockMouldingAndDecorativeWall {
	private boolean shouldColor;
	private Block owner;
	private int ownerMeta;

	protected DecoBlockHedgeMouldingAndDecorative(int var1, Material var2, String var3, String var4, int var5, float var6, float var7, StepSound var8, String var9, boolean shouldColor, Block owner, int ownerMeta) {
		super(var1, var2, var3, var4, var5, var6, var7, var8, var9);
		this.blockMaterial = DecoDefs.materialHedge;
		this.SetBuoyant();
		this.SetFireProperties(FCEnumFlammability.LEAVES);
		this.shouldColor = shouldColor;
	}

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.5F;
    }
	
	public boolean GetCanGrassGrowUnderBlock(World world, int x, int y, int z, boolean var5) {
		return true;
	}

	public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
	{
		super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
		this.GenerateAshOnBurn(var1, var2, var3, var4);
	}

	protected void GenerateAshOnBurn(World var1, int var2, int var3, int var4)
	{
		if (this.owner != FCBetterThanWolves.fcBlockBloodLeaves) {
			for (int var5 = var3; var5 > 0; --var5)
			{
				if (FCBlockAshGroundCover.CanAshReplaceBlock(var1, var2, var5, var4))
				{
					int var6 = var1.getBlockId(var2, var5 - 1, var4);
					Block var7 = Block.blocksList[var6];

					if (var7 != null && var7.CanGroundCoverRestOnBlock(var1, var2, var5 - 1, var4))
					{
						var1.setBlockWithNotify(var2, var5, var4, FCBetterThanWolves.fcBlockAshGroundCover.blockID);
						break;
					}
				}
				else if (var1.getBlockId(var2, var5, var4) != Block.fire.blockID)
				{
					break;
				}
			}
		}
	}

	@Override public boolean isOpaqueCube()
	{
		return false;
	}
}