package net.minecraft.src;

public class FCEntityWitherSkull extends EntityWitherSkull
{
    private static final int m_iBlightSpreadRange = 3;
    private static final double m_dBlightSpreadRangeSq = 9.0D;

    public FCEntityWitherSkull(World var1)
    {
        super(var1);
    }

    public FCEntityWitherSkull(World var1, EntityLiving var2, double var3, double var5, double var7)
    {
        super(var1, var2, var3, var5, var7);
    }

    public float func_82146_a(Explosion var1, World var2, int var3, int var4, int var5, Block var6)
    {
        float var7 = var6.getExplosionResistance(this, var2, var3, var4, var5);

        if (this.IsHighExplosive() && var6 != Block.bedrock && var6 != Block.endPortal && var6 != Block.endPortalFrame && var6 != FCBetterThanWolves.fcSoulforgedSteelBlock)
        {
            var7 = Math.min(0.8F, var7);
        }

        return var7;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition var1)
    {
        if (!this.worldObj.isRemote)
        {
            if (var1.entityHit != null)
            {
                if (this.shootingEntity != null)
                {
                    if (var1.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8) && !var1.entityHit.isEntityAlive())
                    {
                        this.shootingEntity.heal(5);
                    }
                }
                else
                {
                    var1.entityHit.attackEntityFrom(DamageSource.magic, 5);
                }

                if (var1.entityHit instanceof EntityLiving)
                {
                    byte var2 = 5;

                    if (this.worldObj.difficultySetting == 2)
                    {
                        var2 = 10;
                    }
                    else if (this.worldObj.difficultySetting == 3)
                    {
                        var2 = 40;
                    }

                    ((EntityLiving)var1.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * var2, 1));
                }
            }

            this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));

            if (!this.IsHighExplosive())
            {
                this.SpreadBlightInArea();
            }

            this.setDead();
        }
    }

    protected boolean IsHighExplosive()
    {
        return this.isInvulnerable();
    }

    protected void SpreadBlightInArea()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);

        for (int var4 = var1 - 3; var4 <= var1 + 3; ++var4)
        {
            for (int var5 = var2 - 3; var5 <= var2 + 3; ++var5)
            {
                for (int var6 = var3 - 3; var6 <= var3 + 3; ++var6)
                {
                    double var7 = (double)(var4 - var1);
                    double var9 = (double)(var5 - var2);
                    double var11 = (double)(var6 - var3);
                    double var13 = var7 * var7 + var9 * var9 + var11 * var11;

                    if (var13 <= 9.0D)
                    {
                        this.AttemptSpreadBlightToBlock(var4, var5, var6);
                    }
                }
            }
        }
    }

    protected void AttemptSpreadBlightToBlock(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (var4 == Block.grass.blockID)
        {
            int var5 = this.worldObj.getBlockId(var1, var2 + 1, var3);

            if (Block.lightOpacity[var5] <= 2)
            {
                this.worldObj.setBlockAndMetadataWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID, 0);
            }
        }
    }

    public FCEntityWitherSkull(World var1, double var2, double var4, double var6, double var8, double var10, double var12)
    {
        super(var1, var2, var4, var6, var8, var10, var12);
    }
}
