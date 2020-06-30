package net.minecraft.src;

public abstract class FCTileEntityBasket extends TileEntity
{
    protected static final float m_fLidOpenRate = 0.1F;
    protected static final float m_fLidCloseRate = 0.2F;
    protected static final float m_fMaxKeepOpenRange = 8.0F;
    public float m_fLidOpenRatio = 0.0F;
    public float m_fPrevLidOpenRatio = 0.0F;
    public boolean m_bClosing = false;
    FCBlockBasket m_blockBasket;

    public FCTileEntityBasket(FCBlockBasket var1)
    {
        this.m_blockBasket = var1;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
        this.UpdateOpenState();
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int var1, int var2)
    {
        if (var1 == 1)
        {
            this.m_bClosing = var2 == 1;
            return true;
        }
        else
        {
            return super.receiveClientEvent(var1, var2);
        }
    }

    public abstract void EjectContents();

    public void StartClosingServerSide()
    {
        this.m_bClosing = true;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, 1);
    }

    private void UpdateOpenState()
    {
        this.m_fPrevLidOpenRatio = this.m_fLidOpenRatio;

        if (this.m_blockBasket.GetIsOpen(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
        {
            if (this.m_bClosing)
            {
                this.m_fLidOpenRatio -= 0.2F;

                if (this.m_fLidOpenRatio <= 0.0F)
                {
                    this.m_fLidOpenRatio = 0.0F;

                    if (!this.worldObj.isRemote)
                    {
                        this.m_blockBasket.SetIsOpen(this.worldObj, this.xCoord, this.yCoord, this.zCoord, false);
                        this.OnFinishedClosing();
                    }
                }
            }
            else if (this.ShouldStartClosingServerSide())
            {
                this.StartClosingServerSide();
            }
            else
            {
                this.m_fLidOpenRatio += 0.1F;

                if (this.m_fLidOpenRatio > 1.0F)
                {
                    this.m_fLidOpenRatio = 1.0F;
                }
            }
        }
        else
        {
            this.m_bClosing = false;
            this.m_fLidOpenRatio = 0.0F;
        }
    }

    public abstract boolean ShouldStartClosingServerSide();

    protected void OnFinishedClosing()
    {
        this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "step.gravel", 0.1F + this.worldObj.rand.nextFloat() * 0.1F, 1.0F + this.worldObj.rand.nextFloat() * 0.25F);
    }
}
