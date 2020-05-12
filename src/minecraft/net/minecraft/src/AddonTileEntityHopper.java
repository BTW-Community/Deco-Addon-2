package net.minecraft.src;

import java.util.List;

public class AddonTileEntityHopper extends FCTileEntityHopper {
    private final int m_iOverloadSoulCount = 8;
    private final int m_iXPInventorySpace = 100;
    private final int m_iXPEjectUnitSize = 20;
    private final int m_iXPDelayBetweenDrops = 10;
    private int m_iContainedSoulCount = 0;
    private int m_iContainedXPCount = 0;
    private int m_iHopperXPDropDelayCount = 10;
    private int m_iEjectCounter = 0;

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            boolean isHopperOn = ((FCBlockHopper)FCBetterThanWolves.fcHopper).IsBlockOn(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

            if (isHopperOn)
            {
                this.m_iMechanicalPowerIndicator = 1;
                this.AttemptToEjectXPFromInv();

                if (!this.m_bOutputBlocked)
                {
                    ++this.m_iEjectCounter;

                    if (this.m_iEjectCounter >= 3)
                    {
                        this.AttemptToEjectStackFromInv();
                        this.m_iEjectCounter = 0;
                    }
                }
                else
                {
                    this.m_iEjectCounter = 0;
                }
            }
            else
            {
                this.m_iMechanicalPowerIndicator = 0;
                this.m_iEjectCounter = 0;
                this.m_iHopperXPDropDelayCount = 0;
            }

            if (this.m_iContainedSoulCount > 0)
            {
                if (this.m_iFilterItemID == Block.slowSand.blockID)
                {
                    int idBelow = this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord);
                    int metadataBelow = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);

                    if (isHopperOn && (idBelow != FCBetterThanWolves.fcAestheticNonOpaque.blockID || metadataBelow != 0))
                    {
                        this.m_iContainedSoulCount = 0;
                    }

                    if (this.m_iContainedSoulCount >= 8)
                    {
                        if (isHopperOn && idBelow == FCBetterThanWolves.fcAestheticNonOpaque.blockID && metadataBelow == 0)
                        {
                            this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord - 1, this.zCoord, 0);
                            ItemStack var4 = new ItemStack(FCBetterThanWolves.fcItemSoulUrn.itemID, 1, 0);
                            FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord, var4);
                            this.m_iContainedSoulCount = 0;
                        }
                        else
                        {
                            this.HopperSoulOverload();
                        }
                    }
                }
                else
                {
                    this.m_iContainedSoulCount = 0;
                }
            }
        }
    }

    private void AttemptToEjectStackFromInv()
    {
        int randomSlot = FCUtilsInventory.GetRandomOccupiedStackInRange(this, this.worldObj.rand, 0, 17);

        if (randomSlot >= 0 && randomSlot <= 17)
        {
            ItemStack itemStackInSlot = this.getStackInSlot(randomSlot);
            int countToEject;

            if (8 > itemStackInSlot.stackSize)
            {
                countToEject = itemStackInSlot.stackSize;
            }
            else
            {
                countToEject = 8;
            }

            ItemStack itemStackToEject = new ItemStack(itemStackInSlot.itemID, countToEject, itemStackInSlot.getItemDamage());
            FCUtilsInventory.CopyEnchantments(itemStackToEject, itemStackInSlot);
            int x = this.xCoord;
            int yBelow = this.yCoord - 1;
            int z = this.zCoord;
            boolean canEject = false;
            boolean var12;
            int var20 = 0;

            if (this.worldObj.isAirBlock(x, yBelow, z))
            {
                canEject = true;
            }
            else if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, x, yBelow, z))
            {
                canEject = true;
            }
            else
            {
                int idBelow = this.worldObj.getBlockId(x, yBelow, z);
                Block blockBelow = Block.blocksList[idBelow];

                if (blockBelow != null && blockBelow.DoesBlockHopperEject(this.worldObj, x, yBelow, z))
                {
                    if (blockBelow.DoesBlockHopperInsert(this.worldObj, x, yBelow, z))
                    {
                        this.m_bOutputBlocked = true;
                    }
                    else
                    {
                        TileEntity tileEntityBelow = this.worldObj.getBlockTileEntity(x, yBelow, z);
                        var12 = false;

                        if (tileEntityBelow != null && tileEntityBelow instanceof IInventory)
                        {
                            byte var13 = 0;
                            int var14 = ((IInventory)tileEntityBelow).getSizeInventory() - 1;
                            boolean var15 = true;

                            if (idBelow != Block.furnaceIdle.blockID && idBelow != Block.furnaceBurning.blockID)
                            {
                                if (idBelow == FCBetterThanWolves.fcHopper.blockID)
                                {
                                    var14 = 17;
                                    int var16 = ((AddonTileEntityHopper)tileEntityBelow).m_iFilterItemID;

                                    if (var16 > 0)
                                    {
                                        var15 = false;
                                    }
                                }
                            }
                            else
                            {
                                var14 = 0;
                            }

                            if (var15)
                            {
                                boolean var21;
                                
                                if (idBelow != Block.chest.blockID && idBelow != FCBetterThanWolves.fcBlockChest.blockID)
                                {
                                	if (idBelow == AddonDefs.barrelFilling.blockID) {
                                		System.out.println("Barrel filling found");
                                    	//Makes sure barrel is facing up
                                    	if (AddonBlockBarrelFilling.isValidItemForFill(itemStackToEject)) {
                                    		var20 = ejectItemIntoBarrel(itemStackToEject, randomSlot, x, yBelow, z);
                                    	}
                                    	
                                		var21 = var20 > 0;
                                	}
                                	else
                                		var21 = FCUtilsInventory.AddItemStackToInventoryInSlotRange((IInventory)tileEntityBelow, itemStackToEject, var13, var14);
                                }
                                else
                                {
                                    var21 = FCUtilsInventory.AddItemStackToChest((TileEntityChest)tileEntityBelow, itemStackToEject);
                                }

                                if (!var21)
                                {
                                    var20 = countToEject - itemStackToEject.stackSize;
                                }
                                else
                                {
                                    var20 = countToEject;
                                }

                                if (var20 > 0)
                                {
                                    this.decrStackSize(randomSlot, var20);
                                    this.worldObj.playAuxSFX(2231, this.xCoord, this.yCoord, this.zCoord, 0);
                                }
                            }
                            else
                            {
                                this.m_bOutputBlocked = true;
                            }
                        }
                        else if (blockBelow instanceof AddonBlockBarrelEmpty)
                        {
                        	int metadataBelow = this.worldObj.getBlockMetadata(x, yBelow, z);
                        	int newMeta = AddonBlockBarrelFilling.getMetadataFromBarrel(idBelow, metadataBelow);
                        	
                        	//Makes sure barrel is facing up
                        	if (AddonDefs.barrelEmpty.GetFacing(metadataBelow) == 0 && AddonBlockBarrelFilling.isValidItemForFill(itemStackToEject)) {
                        		this.worldObj.setBlock(x, yBelow, z, AddonDefs.barrelFilling.blockID, newMeta, 2);
                        		ejectItemIntoBarrel(itemStackToEject, randomSlot, x, yBelow, z);
                        	}
                            else {
                                this.m_bOutputBlocked = true;
                            }
                        }
                        else
                        {
                            this.m_bOutputBlocked = true;
                        }
                    }
                }
                else
                {
                    canEject = true;
                }
            }

            if (canEject)
            {
                List var17 = this.worldObj.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord + 0.4F), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord + 0.4F), (double)((float)this.xCoord + 0.6F), (double)this.yCoord, (double)((float)this.zCoord + 0.6F)));

                if (var17 != null && var17.size() > 0)
                {
                    for (int var18 = 0; var18 < var17.size(); ++var18)
                    {
                        EntityMinecart var19 = (EntityMinecart)var17.get(var18);

                        if (var19.getMinecartType() == 1 && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord), (double)((float)this.xCoord + 0.25F), (double)this.yCoord, (double)((float)this.zCoord + 1.0F))) && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord + 0.75F), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord), (double)((float)this.xCoord + 1.0F), (double)this.yCoord, (double)((float)this.zCoord + 1.0F))) && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord), (double)((float)this.xCoord + 1.0F), (double)this.yCoord, (double)((float)this.zCoord + 0.25F))) && var19.boundingBox.intersectsWith(AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord), (double)((float)this.yCoord - 0.5F), (double)((float)this.zCoord + 0.75F), (double)((float)this.xCoord + 1.0F), (double)this.yCoord, (double)((float)this.zCoord + 1.0F))))
                        {
                            var12 = false;

                            if (FCUtilsInventory.AddItemStackToInventory((IInventory)var19, itemStackToEject))
                            {
                                var20 = countToEject;
                            }
                            else
                            {
                                var20 = countToEject - itemStackToEject.stackSize;
                            }

                            if (var20 > 0)
                            {
                                this.decrStackSize(randomSlot, var20);
                                this.worldObj.playAuxSFX(2231, this.xCoord, this.yCoord, this.zCoord, 0);
                            }

                            canEject = false;
                            break;
                        }
                    }
                }
            }

            if (canEject)
            {
                this.EjectStack(itemStackToEject);
                this.decrStackSize(randomSlot, countToEject);
            }
        }
    }

    private void EjectStack(ItemStack var1)
    {
        float var2 = this.worldObj.rand.nextFloat() * 0.1F + 0.45F;
        float var3 = -0.35F;
        float var4 = this.worldObj.rand.nextFloat() * 0.1F + 0.45F;
        EntityItem var5 = new EntityItem(this.worldObj, (double)((float)this.xCoord + var2), (double)((float)this.yCoord + var3), (double)((float)this.zCoord + var4), var1);
        var5.motionX = 0.0D;
        var5.motionY = -0.009999999776482582D;
        var5.motionZ = 0.0D;
        var5.delayBeforeCanPickup = 10;
        this.worldObj.spawnEntityInWorld(var5);
    }

    public void AttemptToEjectXPFromInv()
    {
        boolean var1 = true;

        if (this.m_iContainedXPCount >= 20)
        {
            int var2 = this.xCoord;
            int var3 = this.yCoord - 1;
            int var4 = this.zCoord;
            boolean var5 = false;

            if (this.worldObj.isAirBlock(var2, var3, var4))
            {
                var5 = true;
            }
            else
            {
                int var6 = this.worldObj.getBlockId(var2, var3, var4);

                if (var6 == FCBetterThanWolves.fcHopper.blockID)
                {
                    var1 = this.AttemptToEjectXPIntoHopper(var2, var3, var4);
                }
                else if (var6 == FCBetterThanWolves.fcBlockArcaneVessel.blockID)
                {
                    var1 = this.AttemptToEjectXPIntoArcaneVessel(var2, var3, var4);
                }
                else if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, var2, var3, var4))
                {
                    var5 = true;
                }
                else
                {
                    Block var7 = Block.blocksList[var6];

                    if (!var7.blockMaterial.isSolid())
                    {
                        var5 = true;
                    }
                }
            }

            if (var5)
            {
                if (this.m_iHopperXPDropDelayCount <= 0)
                {
                    this.EjectXPOrb(20);
                    this.m_iContainedXPCount -= 20;
                }
                else
                {
                    var1 = false;
                }
            }
        }

        if (var1)
        {
            this.ResetXPEjectCount();
        }
        else
        {
            --this.m_iHopperXPDropDelayCount;
        }
    }

    private boolean AttemptToEjectXPIntoHopper(int var1, int var2, int var3)
    {
        FCBlockHopper var4 = (FCBlockHopper)FCBetterThanWolves.fcHopper;
        AddonTileEntityHopper var5 = (AddonTileEntityHopper)this.worldObj.getBlockTileEntity(var1, var2, var3);

        if (var5 != null && this.m_iFilterItemID == Block.slowSand.blockID)
        {
            int var6 = 100 - var5.m_iContainedXPCount;

            if (var6 > 0)
            {
                if (this.m_iHopperXPDropDelayCount > 0)
                {
                    return false;
                }

                int var7 = 20;

                if (var6 < var7)
                {
                    var7 = var6;
                }

                var5.m_iContainedXPCount += var7;
                this.m_iContainedXPCount -= var7;
                this.worldObj.playAuxSFX(2232, this.xCoord, this.yCoord, this.zCoord, 0);
            }
        }

        return true;
    }

    private boolean AttemptToEjectXPIntoArcaneVessel(int var1, int var2, int var3)
    {
        FCBlockArcaneVessel var4 = (FCBlockArcaneVessel)FCBetterThanWolves.fcBlockArcaneVessel;
        FCTileEntityArcaneVessel var5 = (FCTileEntityArcaneVessel)this.worldObj.getBlockTileEntity(var1, var2, var3);

        if (var5 != null && !var4.GetMechanicallyPoweredFlag(this.worldObj, var1, var2, var3))
        {
            int var6 = 1000 - var5.GetContainedTotalExperience();

            if (var6 > 0)
            {
                if (this.m_iHopperXPDropDelayCount > 0)
                {
                    return false;
                }

                int var7 = 20;

                if (var6 < var7)
                {
                    var7 = var6;
                }

                var5.SetContainedRegularExperience(var5.GetContainedRegularExperience() + var7);
                this.m_iContainedXPCount -= var7;
                this.worldObj.playAuxSFX(2232, this.xCoord, this.yCoord, this.zCoord, 0);
            }
        }

        return true;
    }

    private void ResetXPEjectCount()
    {
        this.m_iHopperXPDropDelayCount = 10 + this.worldObj.rand.nextInt(3);
    }

    private void EjectXPOrb(int var1)
    {
        double var2 = this.worldObj.rand.nextDouble() * 0.1D + 0.45D;
        double var4 = -0.2D;
        double var6 = this.worldObj.rand.nextDouble() * 0.1D + 0.45D;
        EntityXPOrb var8 = new EntityXPOrb(this.worldObj, (double)this.xCoord + var2, (double)this.yCoord + var4, (double)this.zCoord + var6, var1);
        var8.motionX = 0.0D;
        var8.motionY = 0.0D;
        var8.motionZ = 0.0D;
        this.worldObj.spawnEntityInWorld(var8);
        this.worldObj.playAuxSFX(2230, this.xCoord, this.yCoord, this.zCoord, 0);
    }

    private void HopperSoulOverload()
    {
        this.worldObj.playAuxSFX(2225, this.xCoord, this.yCoord, this.zCoord, 0);
        ((FCBlockHopper)((FCBlockHopper)FCBetterThanWolves.fcHopper)).BreakHopper(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }
    
    private int ejectItemIntoBarrel(ItemStack stack, int slot, int x, int y, int z) {
    	AddonTileEntityBarrelFilling barrelTile = (AddonTileEntityBarrelFilling) this.worldObj.getBlockTileEntity(x, y, z);
    	
    	if (barrelTile != null) {
    		return barrelTile.attemptToAddItems(stack);
    	}
    	
    	else return 0;
    }
}