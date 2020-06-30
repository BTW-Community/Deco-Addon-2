package net.minecraft.src;

public abstract class FCEntityMechPowerHorizontal extends FCEntityMechPower
{
    public boolean m_bIAligned;

    public FCEntityMechPowerHorizontal(World var1)
    {
        super(var1);
        this.m_bIAligned = true;
    }

    public FCEntityMechPowerHorizontal(World var1, double var2, double var4, double var6, boolean var8)
    {
        super(var1, var2, var4, var6);
        this.m_bIAligned = var8;
        this.InitBoundingBox();
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        if (this.m_bProvidingPower)
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posY);
            int var3 = MathHelper.floor_double(this.posZ);
            int var4 = this.worldObj.getBlockId(var1, var2, var3);

            if (var4 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
            {
                int var5 = ((FCBlockAxle)FCBetterThanWolves.fcBlockAxlePowerSource).GetAxisAlignment(this.worldObj, var1, var2, var3);
                this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAxle.blockID);
                ((FCBlockAxle)FCBetterThanWolves.fcBlockAxle).SetAxisAlignment(this.worldObj, var1, var2, var3, var5);
            }
        }

        super.setDead();
    }

    protected boolean ValidateConnectedAxles()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (!FCUtilsMechPower.IsBlockIDAxle(var4))
        {
            return false;
        }
        else
        {
            FCBlockAxle var5 = (FCBlockAxle)Block.blocksList[var4];
            int var6 = var5.GetAxisAlignment(this.worldObj, var1, var2, var3);

            if (var6 != 0 && (var6 != 1 || !this.m_bIAligned) && (var6 != 2 || this.m_bIAligned))
            {
                if (!this.m_bProvidingPower)
                {
                    if (var4 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
                    {
                        this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAxle.blockID);
                        ((FCBlockAxle)FCBetterThanWolves.fcBlockAxle).SetAxisAlignment(this.worldObj, var1, var2, var3, var6);
                    }
                    else if (var5.GetPowerLevel(this.worldObj, var1, var2, var3) > 0)
                    {
                        return false;
                    }
                }
                else if (var4 == FCBetterThanWolves.fcBlockAxle.blockID)
                {
                    this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAxlePowerSource.blockID);
                    ((FCBlockAxle)FCBetterThanWolves.fcBlockAxlePowerSource).SetAxisAlignment(this.worldObj, var1, var2, var3, var6);
                }

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    protected void TransferPowerStateToConnectedAxles()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        int var4 = this.worldObj.getBlockId(var1, var2, var3);
        FCBlockAxle var5 = (FCBlockAxle)Block.blocksList[var4];
        int var6 = var5.GetAxisAlignment(this.worldObj, var1, var2, var3);

        if (this.m_bProvidingPower)
        {
            if (var4 == FCBetterThanWolves.fcBlockAxle.blockID)
            {
                this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAxlePowerSource.blockID);
                ((FCBlockAxle)FCBetterThanWolves.fcBlockAxlePowerSource).SetAxisAlignment(this.worldObj, var1, var2, var3, var6);
            }
        }
        else if (var4 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
        {
            this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockAxle.blockID);
            ((FCBlockAxle)FCBetterThanWolves.fcBlockAxle).SetAxisAlignment(this.worldObj, var1, var2, var3, var6);
        }
    }

    protected void InitBoundingBox()
    {
        if (this.m_bIAligned)
        {
            this.boundingBox.setBounds(this.posX - (double)(this.GetDepth() * 0.5F), this.posY - (double)(this.GetHeight() * 0.5F), this.posZ - (double)(this.GetWidth() * 0.5F), this.posX + (double)(this.GetDepth() * 0.5F), this.posY + (double)(this.GetHeight() * 0.5F), this.posZ + (double)(this.GetWidth() * 0.5F));
        }
        else
        {
            this.boundingBox.setBounds(this.posX - (double)(this.GetWidth() * 0.5F), this.posY - (double)(this.GetHeight() * 0.5F), this.posZ - (double)(this.GetDepth() * 0.5F), this.posX + (double)(this.GetWidth() * 0.5F), this.posY + (double)(this.GetHeight() * 0.5F), this.posZ + (double)(this.GetDepth() * 0.5F));
        }
    }

    protected AxisAlignedBB GetDeviceBounds()
    {
        return this.m_bIAligned ? AxisAlignedBB.getAABBPool().getAABB(this.posX - (double)(this.GetDepth() * 0.5F), this.posY - (double)(this.GetHeight() * 0.5F), this.posZ - (double)(this.GetWidth() * 0.5F), this.posX + (double)(this.GetDepth() * 0.5F), this.posY + (double)(this.GetHeight() * 0.5F), this.posZ + (double)(this.GetWidth() * 0.5F)) : AxisAlignedBB.getAABBPool().getAABB(this.posX - (double)(this.GetWidth() * 0.5F), this.posY - (double)(this.GetHeight() * 0.5F), this.posZ - (double)(this.GetDepth() * 0.5F), this.posX + (double)(this.GetWidth() * 0.5F), this.posY + (double)(this.GetHeight() * 0.5F), this.posZ + (double)(this.GetDepth() * 0.5F));
    }
}
