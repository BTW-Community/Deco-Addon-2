package net.minecraft.src;

import java.util.Random;

public class FCItemFireStarterPrimitive extends FCItemFireStarter
{
    private final float m_fBaseChance;
    private final float m_fMaxChance;
    private final float m_fChanceIncreasePerUse;
    private final float m_fChanceDecayPerTick = 2.5E-4F;
    private final long m_lDelayBeforeDecay = 40L;

    public FCItemFireStarterPrimitive(int var1, int var2, float var3, float var4, float var5, float var6)
    {
        super(var1, var2, var3);
        this.m_fBaseChance = var4;
        this.m_fMaxChance = var5;
        this.m_fChanceIncreasePerUse = var6;
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT);
    }

    protected boolean CheckChanceOfStart(ItemStack var1, Random var2)
    {
        boolean var3 = false;
        float var4 = var1.GetAccumulatedChance(this.m_fBaseChance);
        long var5 = FCUtilsWorld.GetOverworldTimeServerOnly();
        long var7 = var1.GetTimeOfLastUse();

        if (var7 > 0L)
        {
            if (var5 > var7)
            {
                long var9 = var5 - var7 - 40L;

                if (var9 > 0L)
                {
                    var4 -= (float)var9 * 2.5E-4F;

                    if (var4 < this.m_fBaseChance)
                    {
                        var4 = this.m_fBaseChance;
                    }
                }
            }
            else if (var5 < var7)
            {
                var4 = this.m_fBaseChance;
            }
        }

        if (var2.nextFloat() <= var4)
        {
            var3 = true;
        }

        var4 += this.m_fChanceIncreasePerUse;

        if (var4 > this.m_fMaxChance)
        {
            var4 = this.m_fMaxChance;
        }

        var1.SetAccumulatedChance(var4);
        var1.SetTimeOfLastUse(var5);
        return var3;
    }

    protected void PerformUseEffects(EntityPlayer var1)
    {
        var1.playSound("random.eat", 0.5F + 0.5F * (float)var1.rand.nextInt(2), var1.rand.nextFloat() * 0.25F + 1.75F);

        if (var1.worldObj.isRemote)
        {
            for (int var2 = 0; var2 < 5; ++var2)
            {
                Vec3 var3 = var1.worldObj.getWorldVec3Pool().getVecFromPool(((double)var1.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                var3.rotateAroundX(-var1.rotationPitch * (float)Math.PI / 180.0F);
                var3.rotateAroundY(-var1.rotationYaw * (float)Math.PI / 180.0F);
                Vec3 var4 = var1.worldObj.getWorldVec3Pool().getVecFromPool(((double)var1.rand.nextFloat() - 0.5D) * 0.3D, (double)(-var1.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
                var4.rotateAroundX(-var1.rotationPitch * (float)Math.PI / 180.0F);
                var4.rotateAroundY(-var1.rotationYaw * (float)Math.PI / 180.0F);
                var4 = var4.addVector(var1.posX, var1.posY + (double)var1.getEyeHeight(), var1.posZ);
                var1.worldObj.spawnParticle("iconcrack_" + this.itemID, var4.xCoord, var4.yCoord, var4.zCoord, var3.xCoord, var3.yCoord + 0.05D, var3.zCoord);
            }
        }
    }

    protected boolean AttemptToLightBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        if (super.AttemptToLightBlock(var1, var2, var3, var4, var5, var6))
        {
            var1.SetAccumulatedChance(this.m_fBaseChance);
            return true;
        }
        else
        {
            return false;
        }
    }
}
