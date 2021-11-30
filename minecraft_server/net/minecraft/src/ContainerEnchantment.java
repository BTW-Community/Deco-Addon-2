package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ContainerEnchantment extends Container
{
    /** SlotEnchantmentTable object with ItemStack to be enchanted */
    public IInventory tableInventory = new SlotEnchantmentTable(this, "Enchant", true, 1);

    /** current world (for bookshelf counting) */
    private World worldPointer;
    private int posX;
    private int posY;
    private int posZ;
    private Random rand = new Random();

    /** used as seed for EnchantmentNameParts (see GuiEnchantment) */
    public long nameSeed;

    /** 3-member array storing the enchantment levels of each slot */
    public int[] enchantLevels = new int[3];

    public ContainerEnchantment(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
        this.worldPointer = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        this.addSlotToContainer(new SlotEnchantment(this, this.tableInventory, 0, 25, 47));
        int var6;

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (int var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 142));
        }
    }

    public void onCraftGuiOpened(ICrafting par1ICrafting)
    {
        super.onCraftGuiOpened(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            var2.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
            var2.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
            var2.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventory) {
		if (inventory == this.tableInventory) {
			ItemStack stackToEnchant = inventory.getStackInSlot(0);

			if (stackToEnchant != null && stackToEnchant.isItemEnchantable())
			{
				this.nameSeed = this.rand.nextLong();

				if (!this.worldPointer.isRemote)
				{
					int numBookshelves = 0;

					for (int i = -1; i <= 1; ++i) {
						for (int k = -1; k <= 1; ++k) {
							if (k != 0 || i != 0) {
								for (int j = -1; j <= 2; j++) {
									if (this.worldPointer.isAirBlock(this.posX + i, this.posY + j, this.posZ + k)) {
										if (this.worldPointer.getBlockId(this.posX + i * 2, this.posY + j, this.posZ + k * 2) == Block.bookShelf.blockID) {
											numBookshelves++;
										}

										if (i != 0 && k != 0) {
											if (this.worldPointer.getBlockId(this.posX + i * 2, this.posY + j, this.posZ + k) == Block.bookShelf.blockID) {
												numBookshelves++;
											}

											if (this.worldPointer.getBlockId(this.posX + i, this.posY + j, this.posZ + k * 2) == Block.bookShelf.blockID) {
												numBookshelves++;
											}
										}
									}
								}
							}
						}
					}
					
					if (numBookshelves > 30) {
						numBookshelves = 30;
					}

					for (int i = 0; i < 3; ++i) {
						this.enchantLevels[i] = EnchantmentHelper.calcItemStackEnchantability(this.rand, i, numBookshelves, stackToEnchant);
					}

					this.detectAndSendChanges();
				}
			}
			else {
				for (int i = 0; i < 3; ++i)
				{
					this.enchantLevels[i] = 0;
				}
			}
		}
	}


    /**
     * enchants the item on the table using the specified slot; also deducts XP from player
     */
    public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = this.tableInventory.getStackInSlot(0);

        if (this.enchantLevels[par2] > 0 && var3 != null && (par1EntityPlayer.experienceLevel >= this.enchantLevels[par2] || par1EntityPlayer.capabilities.isCreativeMode))
        {
            if (!this.worldPointer.isRemote)
            {
                List var4 = EnchantmentHelper.buildEnchantmentList(this.rand, var3, this.enchantLevels[par2]);
                boolean var5 = var3.itemID == Item.book.itemID;

                if (var4 != null)
                {
                    par1EntityPlayer.addExperienceLevel(-this.enchantLevels[par2]);

                    if (var5)
                    {
                        var3.itemID = Item.enchantedBook.itemID;
                    }

                    int var6 = var5 ? this.rand.nextInt(var4.size()) : -1;

                    for (int var7 = 0; var7 < var4.size(); ++var7)
                    {
                        EnchantmentData var8 = (EnchantmentData)var4.get(var7);

                        if (!var5 || var7 == var6)
                        {
                            if (var5)
                            {
                                Item.enchantedBook.func_92115_a(var3, var8);
                            }
                            else
                            {
                                var3.addEnchantment(var8.enchantmentobj, var8.enchantmentLevel);
                            }
                        }
                    }

                    this.onCraftMatrixChanged(this.tableInventory);

                    // FCMOD: Code added
                    worldPointer.playSoundAtEntity( par1EntityPlayer, "random.levelup", 0.25F, worldPointer.rand.nextFloat() * 0.1F + 0.5F );
                    // END FCMOD
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);

        if (!this.worldPointer.isRemote)
        {
            ItemStack var2 = this.tableInventory.getStackInSlotOnClosing(0);

            if (var2 != null)
            {
                par1EntityPlayer.dropPlayerItem(var2);
            }
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.worldPointer.getBlockId(this.posX, this.posY, this.posZ) != Block.enchantmentTable.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(var5, 1, 37, true))
                {
                    return null;
                }
            }
            else
            {
                if (((Slot)this.inventorySlots.get(0)).getHasStack() || !((Slot)this.inventorySlots.get(0)).isItemValid(var5))
                {
                    return null;
                }

                if (var5.hasTagCompound() && var5.stackSize == 1)
                {
                    ((Slot)this.inventorySlots.get(0)).putStack(var5.copy());
                    var5.stackSize = 0;
                }
                else if (var5.stackSize >= 1)
                {
                    ((Slot)this.inventorySlots.get(0)).putStack(new ItemStack(var5.itemID, 1, var5.getItemDamage()));
                    --var5.stackSize;
                }
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}
