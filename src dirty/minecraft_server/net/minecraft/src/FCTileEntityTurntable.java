package net.minecraft.src;

public class FCTileEntityTurntable extends TileEntity
{
    private final int m_iMaxHeightOfBlocksRotated = 2;
    private int m_iRotationTickCount = 0;
    public int m_iCraftingRotationCount = 0;
    private static int[] m_iTicksToRotate = new int[] {10, 20, 40, 80, 200, 600, 1200, 2400, 6000, 12000, 24000};
    private int m_iSwitchOverride = -1;

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.m_iRotationTickCount = var1.getInteger("m_iRotationCount");

        if (var1.hasKey("m_iSwitchSetting"))
        {
            this.m_iSwitchOverride = var1.getInteger("m_iSwitchSetting");

            if (this.m_iSwitchOverride > 3)
            {
                this.m_iSwitchOverride = 3;
            }
        }

        if (var1.hasKey("m_iPotteryRotationCount"))
        {
            this.m_iCraftingRotationCount = var1.getInteger("m_iPotteryRotationCount");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("m_iRotationCount", this.m_iRotationTickCount);
        var1.setInteger("m_iPotteryRotationCount", this.m_iCraftingRotationCount);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (this.worldObj.isRemote)
        {
            if (((FCBlockTurntable)FCBetterThanWolves.fcTurntable).IsBlockMechanicalOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
            {
                ++this.m_iRotationTickCount;

                if (this.m_iRotationTickCount >= this.GetTicksToRotate())
                {
                    this.worldObj.playSound((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "random.click", 0.05F, 1.0F);
                    this.m_iRotationTickCount = 0;
                }
            }
            else
            {
                this.m_iRotationTickCount = 0;
            }
        }
        else
        {
            if (this.m_iSwitchOverride >= 0)
            {
                ((FCBlockTurntable)FCBetterThanWolves.fcTurntable).SetSwitchSetting(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.m_iSwitchOverride);
                this.m_iSwitchOverride = -1;
            }

            byte var1 = 9;

            if (this.worldObj.checkChunksExist(this.xCoord - var1, this.yCoord - var1, this.zCoord - var1, this.xCoord + var1, this.yCoord + var1, this.zCoord + var1))
            {
                if (((FCBlockTurntable)FCBetterThanWolves.fcTurntable).IsBlockMechanicalOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
                {
                    ++this.m_iRotationTickCount;

                    if (this.m_iRotationTickCount >= this.GetTicksToRotate())
                    {
                        this.RotateTurntable();
                        this.m_iRotationTickCount = 0;
                    }
                }
                else
                {
                    this.m_iRotationTickCount = 0;
                }
            }
        }
    }

    private int GetTicksToRotate()
    {
        return m_iTicksToRotate[((FCBlockTurntable)FCBetterThanWolves.fcTurntable).GetSwitchSetting(this.worldObj, this.xCoord, this.yCoord, this.zCoord)];
    }

    private void RotateTurntable()
    {
        boolean var1 = ((FCBlockTurntable)FCBetterThanWolves.fcTurntable).IsBlockRedstoneOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        int var2 = this.m_iCraftingRotationCount;

        for (int var3 = this.yCoord + 1; var3 <= this.yCoord + 2; ++var3)
        {
            Block var4 = Block.blocksList[this.worldObj.getBlockId(this.xCoord, var3, this.zCoord)];

            if (var4 == null || !var4.CanRotateOnTurntable(this.worldObj, this.xCoord, var3, this.zCoord))
            {
                break;
            }

            boolean var5 = var4.CanTransmitRotationHorizontallyOnTurntable(this.worldObj, this.xCoord, var3, this.zCoord);
            boolean var6 = var4.CanTransmitRotationVerticallyOnTurntable(this.worldObj, this.xCoord, var3, this.zCoord);
            var2 = var4.RotateOnTurntable(this.worldObj, this.xCoord, var3, this.zCoord, var1, var2);

            if (var5)
            {
                this.RotateBlocksAttachedToBlock(this.xCoord, var3, this.zCoord, var1);
            }

            if (!var6)
            {
                break;
            }
        }

        if (var2 > this.m_iCraftingRotationCount)
        {
            this.m_iCraftingRotationCount = var2;
        }
        else
        {
            this.m_iCraftingRotationCount = 0;
        }

        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, FCBetterThanWolves.fcTurntable.blockID);
    }

    private void RotateBlocksAttachedToBlock(int var1, int var2, int var3, boolean var4)
    {
        int[] var5 = new int[4];
        int[] var6 = new int[4];
        int var7;

        for (var7 = 0; var7 < 4; ++var7)
        {
            var5[var7] = 0;
            var6[var7] = 0;
        }

        int var9;

        for (var7 = 2; var7 <= 5; ++var7)
        {
            FCUtilsBlockPos var8 = new FCUtilsBlockPos(var1, var2, var3);
            var8.AddFacingAsOffset(var7);
            var9 = this.worldObj.getBlockId(var8.i, var8.j, var8.k);
            Block var10 = Block.blocksList[var9];

            if (var10 != null)
            {
                int var11 = Block.GetOppositeFacing(var7);

                if (var10.CanRotateAroundBlockOnTurntableToFacing(this.worldObj, var8.i, var8.j, var8.k, var11) && var10.OnRotatedAroundBlockOnTurntableToFacing(this.worldObj, var8.i, var8.j, var8.k, var11))
                {
                    int var12 = Block.RotateFacingAroundJ(var7, var4);
                    var5[var12 - 2] = var9;
                    var6[var12 - 2] = var10.GetNewMetadataRotatedAroundBlockOnTurntableToFacing(this.worldObj, var8.i, var8.j, var8.k, var11, Block.GetOppositeFacing(var12));
                    this.worldObj.setBlockWithNotify(var8.i, var8.j, var8.k, 0);
                }
            }
        }

        for (var7 = 0; var7 < 4; ++var7)
        {
            int var15 = var5[var7];

            if (var15 != 0)
            {
                var9 = var7 + 2;
                int var16 = var6[var7];
                FCUtilsBlockPos var17 = new FCUtilsBlockPos(var1, var2, var3);
                var17.AddFacingAsOffset(var9);

                if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, var17.i, var17.j, var17.k))
                {
                    this.worldObj.setBlockAndMetadataWithNotify(var17.i, var17.j, var17.k, var15, var16);
                }
                else
                {
                    Block var18 = Block.blocksList[var15];
                    int var13 = Block.RotateFacingAroundJ(var9, !var4);
                    FCUtilsBlockPos var14 = new FCUtilsBlockPos(var1, var2, var3);
                    var14.AddFacingAsOffset(var13);
                    var18.dropBlockAsItem(this.worldObj, var14.i, var14.j, var14.k, var15, 0);
                }
            }
        }
    }
}
