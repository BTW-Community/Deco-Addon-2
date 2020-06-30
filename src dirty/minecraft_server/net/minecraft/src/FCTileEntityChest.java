package net.minecraft.src;

public class FCTileEntityChest extends TileEntityChest
{
    public void openChest()
    {
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, FCBetterThanWolves.fcBlockChest.blockID);
        super.openChest();
    }

    public void closeChest()
    {
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, FCBetterThanWolves.fcBlockChest.blockID);
        super.closeChest();
    }

    /**
     * Performs the check for adjacent chests to determine if this chest is double or not.
     */
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPosition = null;
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);

            if (this.IsAdjacentChest(var1, this.xCoord - 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXNeg = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.IsAdjacentChest(var1, this.xCoord + 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXPos = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if (this.IsAdjacentChest(var1, this.xCoord, this.yCoord, this.zCoord - 1))
            {
                this.adjacentChestZNeg = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.IsAdjacentChest(var1, this.xCoord, this.yCoord, this.zCoord + 1))
            {
                this.adjacentChestZPosition = (TileEntityChest)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if (this.adjacentChestZNeg != null)
            {
                ((FCTileEntityChest)this.adjacentChestZNeg).ValidateNeighborConnection(this, 0);
            }

            if (this.adjacentChestZPosition != null)
            {
                ((FCTileEntityChest)this.adjacentChestZPosition).ValidateNeighborConnection(this, 2);
            }

            if (this.adjacentChestXPos != null)
            {
                ((FCTileEntityChest)this.adjacentChestXPos).ValidateNeighborConnection(this, 1);
            }

            if (this.adjacentChestXNeg != null)
            {
                ((FCTileEntityChest)this.adjacentChestXNeg).ValidateNeighborConnection(this, 3);
            }
        }
    }

    protected boolean IsAdjacentChest(int var1, int var2, int var3, int var4)
    {
        int var5 = this.worldObj.getBlockId(var2, var3, var4);

        if (var1 != var5 && Block.blocksList[var1] instanceof BlockChest)
        {
            return false;
        }
        else
        {
            Block var6 = Block.blocksList[var5];
            return var6 != null && var6 instanceof BlockChest ? ((BlockChest)var6).isTrapped == this.func_98041_l() : false;
        }
    }

    protected void ValidateNeighborConnection(TileEntityChest var1, int var2)
    {
        if (var1.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (var2)
            {
                case 0:
                    if (this.adjacentChestZPosition != var1)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;

                case 1:
                    if (this.adjacentChestXNeg != var1)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;

                case 2:
                    if (this.adjacentChestZNeg != var1)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;

                case 3:
                    if (this.adjacentChestXPos != var1)
                    {
                        this.adjacentChestChecked = false;
                    }
            }
        }
    }

    public void ClearContents()
    {
        for (int var1 = 0; var1 < this.getSizeInventory(); ++var1)
        {
            if (this.getStackInSlot(var1) != null)
            {
                this.setInventorySlotContents(var1, (ItemStack)null);
            }
        }
    }
}
