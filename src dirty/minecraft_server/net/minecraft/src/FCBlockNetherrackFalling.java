package net.minecraft.src;

import java.util.Random;

public class FCBlockNetherrackFalling extends FCBlockFalling
{
    public FCBlockNetherrackFalling(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(0.6F);
        this.setResistance(0.6666667F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("hellrock");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Block.netherrack.blockID;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.0F;
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand var1)
    {
        super.onStartFalling(var1);
        int var2 = MathHelper.floor_double(var1.posX);
        int var3 = (int)var1.posY;
        int var4 = MathHelper.floor_double(var1.posZ);
        var1.worldObj.playAuxSFX(2228, var2, var3, var4, 0);

        if (var1.worldObj.getBlockId(var2, var3 + 1, var4) == Block.fire.blockID)
        {
            var1.metadata = 1;
            var1.worldObj.playAuxSFX(2281, MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ), 0);
        }
    }

    /**
     * Called when the falling block entity for this block hits the ground and turns back into a block
     */
    public void onFinishFalling(World var1, int var2, int var3, int var4, int var5)
    {
        super.onFinishFalling(var1, var2, var3, var4, var5);

        if (var5 != 0 && var1.isAirBlock(var2, var3 + 1, var4))
        {
            var1.setBlockWithNotify(var2, var3 + 1, var4, Block.fire.blockID);
            var1.SetBlockMetadataWithNotify(var2, var3, var4, 0, 2);
        }
    }

    protected void OnFallingUpdate(FCEntityFallingBlock var1)
    {
        if (var1.worldObj.isRemote && var1.metadata != 0)
        {
            this.EmitSmokeParticles(var1.worldObj, var1.posX, var1.posY, var1.posZ, var1.worldObj.rand);
        }
    }

    public void OnBlockDestroyedLandingFromFall(World var1, int var2, int var3, int var4, int var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var5, 0);
    }

    public boolean DoesInfiniteBurnToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 1;
    }

    private void EmitSmokeParticles(World var1, double var2, double var4, double var6, Random var8)
    {
        for (int var9 = 0; var9 < 5; ++var9)
        {
            double var10 = var2 - 0.6D + var8.nextDouble() * 1.2D;
            double var12 = var4 + 0.25D + var8.nextDouble() * 0.25D;
            double var14 = var6 - 0.6D + var8.nextDouble() * 1.2D;
            var1.spawnParticle("largesmoke", var10, var12, var14, 0.0D, 0.0D, 0.0D);
        }
    }
}
