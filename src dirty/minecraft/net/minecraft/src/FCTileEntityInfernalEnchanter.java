package net.minecraft.src;

public class FCTileEntityInfernalEnchanter extends TileEntity
{
    private int[] m_iTimeSinceLastCandleFlame = new int[4];
    public boolean m_bPlayerNear;
    private static final int m_iMaxTimeBetweenFlameUpdates = 10;

    public FCTileEntityInfernalEnchanter()
    {
        for (int var1 = 0; var1 < 4; ++var1)
        {
            this.m_iTimeSinceLastCandleFlame[var1] = 0;
        }

        this.m_bPlayerNear = false;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
        EntityPlayer var1 = this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), 4.5D);

        if (var1 != null)
        {
            if (!this.m_bPlayerNear)
            {
                this.LightCandles();
                this.m_bPlayerNear = true;
            }
            else
            {
                this.UpdateCandleFlames();
            }
        }
        else
        {
            this.m_bPlayerNear = false;
        }
    }

    private void LightCandles()
    {
        for (int var1 = 0; var1 < 4; ++var1)
        {
            this.DisplayCandleFlameAtIndex(var1);
        }

        this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.ghast.fireball", 1.0F, this.worldObj.rand.nextFloat() * 0.4F + 0.8F);
    }

    private void UpdateCandleFlames()
    {
        for (int var1 = 0; var1 < 4; ++var1)
        {
            ++this.m_iTimeSinceLastCandleFlame[var1];

            if (this.m_iTimeSinceLastCandleFlame[var1] > 10 || this.worldObj.rand.nextInt(5) == 0)
            {
                this.DisplayCandleFlameAtIndex(var1);
            }
        }
    }

    private void DisplayCandleFlameAtIndex(int var1)
    {
        double var2 = (double)this.xCoord + 0.125D;
        double var4 = (double)((float)this.yCoord + 0.5F + 0.25F + 0.175F);
        double var6 = (double)this.zCoord + 0.125D;

        if (var1 == 1 || var1 == 3)
        {
            var2 = (double)this.xCoord + 0.875D;
        }

        if (var1 == 2 || var1 == 3)
        {
            var6 = (double)this.zCoord + 0.875D;
        }

        this.DisplayCandleFlameAtLoc(var2, var4, var6);
        this.m_iTimeSinceLastCandleFlame[var1] = 0;
    }

    private void DisplayCandleFlameAtLoc(double var1, double var3, double var5)
    {
        this.worldObj.spawnParticle("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);
    }
}
