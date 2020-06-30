package net.minecraft.src;

public class FCTileEntityTorchFinite extends TileEntity
{
    private static final float m_fChanceOfFireSpread = 0.01F;
    private static final float m_fChanceOfGoingOutFromRain = 0.01F;
    public static final int m_iMaxBurnTime = 24000;
    public static final int m_iSputterTime = 600;
    public int m_iBurnTimeCountdown = 0;

    public FCTileEntityTorchFinite()
    {
        this.m_iBurnTimeCountdown = 24000;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);

        if (var1.hasKey("fcBurnCounter"))
        {
            this.m_iBurnTimeCountdown = var1.getInteger("fcBurnCounter");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("fcBurnCounter", this.m_iBurnTimeCountdown);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
        int var1;

        if (!this.worldObj.isRemote)
        {
            --this.m_iBurnTimeCountdown;

            if (this.m_iBurnTimeCountdown > 0 && (this.worldObj.rand.nextFloat() > 0.01F || !this.IsRainingOnTorch()))
            {
                if (this.m_iBurnTimeCountdown < 600)
                {
                    var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);

                    if (!FCBlockTorchFiniteBurning.GetIsSputtering(var1))
                    {
                        FCBlockTorchFiniteBurning var2 = (FCBlockTorchFiniteBurning)((FCBlockTorchFiniteBurning)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
                        var2.SetIsSputtering(this.worldObj, this.xCoord, this.yCoord, this.zCoord, true);
                    }
                }

                if (this.worldObj.rand.nextFloat() <= 0.01F)
                {
                    FCBlockFire.CheckForFireSpreadAndDestructionToOneBlockLocation(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord);
                }
            }
            else if (this.worldObj.doChunksNearChunkExist(this.xCoord, this.yCoord, this.zCoord, 32))
            {
                this.ExtinguishTorch();
            }
            else
            {
                this.m_iBurnTimeCountdown = 0;
            }
        }
        else
        {
            var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);

            if (FCBlockTorchFiniteBurning.GetIsSputtering(var1))
            {
                this.Sputter();
            }
        }
    }

    private boolean IsRainingOnTorch()
    {
        FCBlockTorchFiniteBurning var1 = (FCBlockTorchFiniteBurning)((FCBlockTorchFiniteBurning)Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)]);
        return var1.IsRainingOnTorch(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    private void ExtinguishTorch()
    {
        this.m_iBurnTimeCountdown = 0;
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        int var2 = FCBlockTorchBase.GetOrientation(var1);
        int var3 = FCBlockTorchBase.SetOrientation(0, var2);
        var3 = FCBlockTorchFiniteUnlit.SetIsBurnedOut(var3, true);
        this.worldObj.playAuxSFX(1004, this.xCoord, this.yCoord, this.zCoord, 0);
        this.worldObj.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, FCBetterThanWolves.fcBlockTorchFiniteUnlit.blockID, var3);
    }

    private void Sputter()
    {
        int var1 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        int var2 = FCBlockTorchBase.GetOrientation(var1);
        double var3 = 0.27D;
        double var5 = (double)this.xCoord + 0.5D + (this.worldObj.rand.nextDouble() * 0.1D - 0.05D);
        double var7 = (double)this.yCoord + 0.92D + (this.worldObj.rand.nextDouble() * 0.1D - 0.05D);
        double var9 = (double)this.zCoord + 0.5D + (this.worldObj.rand.nextDouble() * 0.1D - 0.05D);

        if (var2 == 1)
        {
            var5 -= var3;
        }
        else if (var2 == 2)
        {
            var5 += var3;
        }
        else if (var2 == 3)
        {
            var9 -= var3;
        }
        else if (var2 == 4)
        {
            var9 += var3;
        }
        else
        {
            var7 -= 0.22D;
        }

        this.worldObj.spawnParticle("smoke", var5, var7, var9, 0.0D, 0.0D, 0.0D);
    }
}
