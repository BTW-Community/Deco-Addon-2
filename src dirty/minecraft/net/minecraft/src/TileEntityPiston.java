package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileEntityPiston extends TileEntity
{
    private int storedBlockID;
    private int storedMetadata;

    /** the side the front of the piston is on */
    private int storedOrientation;

    /** if this piston is extending or not */
    private boolean extending;
    private boolean shouldHeadBeRendered;
    private float progress;

    /** the progress in (de)extending */
    private float lastProgress;
    private List pushedObjects;
    private boolean m_bShoveledBlock;

    public TileEntityPiston(int var1, int var2, int var3, boolean var4, boolean var5, boolean var6)
    {
        this(var1, var2, var3, var4, var5);
        this.m_bShoveledBlock = true;
    }

    public TileEntityPiston()
    {
        this.pushedObjects = new ArrayList();
        this.m_bShoveledBlock = false;
    }

    public TileEntityPiston(int par1, int par2, int par3, boolean par4, boolean par5)
    {
        this.pushedObjects = new ArrayList();
        this.m_bShoveledBlock = false;
        this.storedBlockID = par1;
        this.storedMetadata = par2;
        this.storedOrientation = par3;
        this.extending = par4;
        this.shouldHeadBeRendered = par5;
    }

    public int getStoredBlockID()
    {
        return this.storedBlockID;
    }

    /**
     * Returns block data at the location of this entity (client-only).
     */
    public int getBlockMetadata()
    {
        return this.storedMetadata;
    }

    /**
     * Returns true if a piston is extending
     */
    public boolean isExtending()
    {
        return this.extending;
    }

    /**
     * Returns the orientation of the piston as an int
     */
    public int getPistonOrientation()
    {
        return this.storedOrientation;
    }

    public boolean shouldRenderHead()
    {
        return this.shouldHeadBeRendered;
    }

    /**
     * Get interpolated progress value (between lastProgress and progress) given the fractional time between ticks as an
     * argument.
     */
    public float getProgress(float par1)
    {
        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        return this.lastProgress + (this.progress - this.lastProgress) * par1;
    }

    public float getOffsetX(float par1)
    {
        return this.extending ? (this.getProgress(par1) - 1.0F) * (float)Facing.offsetsXForSide[this.storedOrientation] : (1.0F - this.getProgress(par1)) * (float)Facing.offsetsXForSide[this.storedOrientation];
    }

    public float getOffsetY(float par1)
    {
        return this.extending ? (this.getProgress(par1) - 1.0F) * (float)Facing.offsetsYForSide[this.storedOrientation] : (1.0F - this.getProgress(par1)) * (float)Facing.offsetsYForSide[this.storedOrientation];
    }

    public float getOffsetZ(float par1)
    {
        return this.extending ? (this.getProgress(par1) - 1.0F) * (float)Facing.offsetsZForSide[this.storedOrientation] : (1.0F - this.getProgress(par1)) * (float)Facing.offsetsZForSide[this.storedOrientation];
    }

    private void updatePushedObjects(float par1, float par2)
    {
        if (this.extending)
        {
            par1 = 1.0F - par1;
        }
        else
        {
            --par1;
        }

        AxisAlignedBB var3 = Block.pistonMoving.getAxisAlignedBB(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, par1, this.storedOrientation);

        if (var3 != null)
        {
            List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);

            if (!var4.isEmpty())
            {
                this.pushedObjects.addAll(var4);
                Iterator var5 = this.pushedObjects.iterator();

                while (var5.hasNext())
                {
                    Entity var6 = (Entity)var5.next();
                    var6.moveEntity((double)(par2 * (float)Facing.offsetsXForSide[this.storedOrientation]), (double)(par2 * (float)Facing.offsetsYForSide[this.storedOrientation]), (double)(par2 * (float)Facing.offsetsZForSide[this.storedOrientation]));
                }

                this.pushedObjects.clear();
            }
        }
    }

    /**
     * removes a pistons tile entity (and if the piston is moving, stops it)
     */
    public void clearPistonTileEntity()
    {
        if (this.lastProgress < 1.0F && this.worldObj != null)
        {
            this.lastProgress = this.progress = 1.0F;
            this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
            this.invalidate();

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.pistonMoving.blockID)
            {
                if (this.DestroyAndDropIfShoveled())
                {
                    return;
                }

                this.PreBlockPlaced();
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata, 3);
                this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
            }
        }
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        this.lastProgress = this.progress;

        if (this.lastProgress >= 1.0F)
        {
            this.updatePushedObjects(1.0F, 0.25F);
            this.AttemptToPackItems();
            this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
            this.invalidate();

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.pistonMoving.blockID)
            {
                if (this.DestroyAndDropIfShoveled())
                {
                    return;
                }

                this.PreBlockPlaced();
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata, 3);
                this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
            }
        }
        else
        {
            this.progress += 0.5F;

            if (this.progress >= 1.0F)
            {
                this.progress = 1.0F;
            }

            if (this.extending)
            {
                this.updatePushedObjects(this.progress, this.progress - this.lastProgress + 0.0625F);
            }
        }
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.storedBlockID = par1NBTTagCompound.getInteger("blockId");
        this.storedMetadata = par1NBTTagCompound.getInteger("blockData");
        this.storedOrientation = par1NBTTagCompound.getInteger("facing");
        this.lastProgress = this.progress = par1NBTTagCompound.getFloat("progress");
        this.extending = par1NBTTagCompound.getBoolean("extending");

        if (par1NBTTagCompound.hasKey("fcShovel"))
        {
            this.m_bShoveledBlock = par1NBTTagCompound.getBoolean("fcShovel");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("blockId", this.storedBlockID);
        par1NBTTagCompound.setInteger("blockData", this.storedMetadata);
        par1NBTTagCompound.setInteger("facing", this.storedOrientation);
        par1NBTTagCompound.setFloat("progress", this.lastProgress);
        par1NBTTagCompound.setBoolean("extending", this.extending);
        par1NBTTagCompound.setBoolean("fcShovel", this.m_bShoveledBlock);
    }

    private void AttemptToPackItems()
    {
        if (!this.worldObj.isRemote && this.isExtending() && (this.storedBlockID == Block.pistonExtension.blockID || Block.isNormalCube(this.storedBlockID) || this.storedBlockID == Block.glass.blockID))
        {
            FCUtilsBlockPos var1 = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord, this.storedOrientation);

            if (this.IsLocationSuitableForPacking(var1.i, var1.j, var1.k, Block.GetOppositeFacing(this.storedOrientation)))
            {
                AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB((double)var1.i, (double)var1.j, (double)var1.k, (double)var1.i + 1.0D, (double)var1.j + 1.0D, (double)var1.k + 1.0D);
                List var3 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, var2);

                if (!var3.isEmpty())
                {
                    Iterator var4 = var3.iterator();

                    while (var4.hasNext())
                    {
                        EntityItem var5 = (EntityItem)var4.next();

                        if (!var5.isDead)
                        {
                            ItemStack var6 = var5.getEntityItem();

                            if (this.IsPackableItem(var6))
                            {
                                int var7 = this.GetItemCountToPack(var6);
                                int var8 = this.CountItemsOfTypeInList(var6, var3);

                                if (var8 >= var7)
                                {
                                    this.RemoveItemsOfTypeFromList(var6, var7, var3);
                                    this.CreatePackedBlockOfTypeAtLocation(var6, var1.i, var1.j, var1.k);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean IsLocationSuitableForPacking(int var1, int var2, int var3, int var4)
    {
        if (this.worldObj.isAirBlock(var1, var2, var3))
        {
            for (int var5 = 0; var5 < 6; ++var5)
            {
                if (var5 != var4)
                {
                    FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1, var2, var3, var5);

                    if (!this.IsBlockSuitableForPackingToFacing(var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)))
                    {
                        return false;
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean IsBlockSuitableForPackingToFacing(int var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[this.worldObj.getBlockId(var1, var2, var3)];
        return var5 != null ? var5.CanContainPistonPackingToFacing(this.worldObj, var1, var2, var3, var4) : false;
    }

    private boolean IsPackableItem(ItemStack var1)
    {
        return var1.getItem().IsPistonPackable(var1);
    }

    private int GetItemCountToPack(ItemStack var1)
    {
        return var1.getItem().GetRequiredItemCountToPistonPack(var1);
    }

    private void CreatePackedBlockOfTypeAtLocation(ItemStack var1, int var2, int var3, int var4)
    {
        int var5 = var1.getItem().GetResultingBlockIDOnPistonPack(var1);
        int var6 = var1.getItem().GetResultingBlockMetadataOnPistonPack(var1);
        this.worldObj.setBlockAndMetadataWithNotify(var2, var3, var4, var5, var6);
        this.worldObj.playAuxSFX(2236, var2, var3, var4, var5);
    }

    private int CountItemsOfTypeInList(ItemStack var1, List var2)
    {
        Iterator var3 = var2.iterator();
        int var4 = 0;

        while (var3.hasNext())
        {
            EntityItem var5 = (EntityItem)var3.next();

            if (!var5.isDead)
            {
                ItemStack var6 = var5.getEntityItem();

                if (var6.itemID == var1.itemID)
                {
                    var4 += var6.stackSize;
                }
            }
        }

        return var4;
    }

    private void RemoveItemsOfTypeFromList(ItemStack var1, int var2, List var3)
    {
        Iterator var4 = var3.iterator();

        while (var4.hasNext())
        {
            EntityItem var5 = (EntityItem)var4.next();

            if (!var5.isDead)
            {
                ItemStack var6 = var5.getEntityItem();

                if (var6.itemID == var1.itemID)
                {
                    if (var6.stackSize > var2)
                    {
                        var6.stackSize -= var2;
                        break;
                    }

                    var2 -= var6.stackSize;
                    var6.stackSize = 0;
                    var5.setDead();

                    if (var2 <= 0)
                    {
                        break;
                    }
                }
            }
        }
    }

    private boolean DestroyAndDropIfShoveled()
    {
        if (this.m_bShoveledBlock)
        {
            Block var1 = Block.blocksList[this.storedBlockID];

            if (var1 != null && !this.worldObj.isRemote)
            {
                ItemStack var2 = null;

                if (var1.canSilkHarvest(this.storedMetadata))
                {
                    var2 = var1.createStackedBlock(this.storedMetadata);
                }
                else
                {
                    var2 = new ItemStack(var1.idDropped(this.storedMetadata, this.worldObj.rand, 0), var1.quantityDropped(this.worldObj.rand), var1.damageDropped(this.storedMetadata));
                }

                if (var2 != null)
                {
                    this.EjectStackOnShoveled(var2);
                }
            }

            this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
            return true;
        }
        else
        {
            return false;
        }
    }

    private void EjectStackOnShoveled(ItemStack var1)
    {
        FCUtilsBlockPos var2 = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord, Block.GetOppositeFacing(this.storedOrientation));
        FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, var2.i, var2.j, var2.k, var1, this.storedOrientation);
    }

    private void PreBlockPlaced()
    {
        Block var1 = Block.blocksList[this.storedBlockID];

        if (var1 != null && !this.worldObj.isRemote)
        {
            this.storedMetadata = var1.OnPreBlockPlacedByPiston(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.storedMetadata, this.GetDirectionMoving());
        }
    }

    private int GetDirectionMoving()
    {
        return !this.extending ? Block.GetOppositeFacing(this.storedOrientation) : this.storedOrientation;
    }
}
