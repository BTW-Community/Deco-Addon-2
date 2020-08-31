package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class DecoPlayerControllerMP extends PlayerControllerMP {
    /** The Minecraft instance. */
    private final Minecraft mc;
    private final NetClientHandler netClientHandler;
    
    private Field blockHitDelayField;
    private Field currentGameTypeField;
    private Field blockYField;
    private Field currentPlayerItemField;

	public DecoPlayerControllerMP(Minecraft par1Minecraft, NetClientHandler par2NetClientHandler) {
		super(par1Minecraft, par2NetClientHandler);
        this.mc = par1Minecraft;
        this.netClientHandler = par2NetClientHandler;
        
        setReflectionFields();
	}
	
	private void setReflectionFields() {
		try {
			if (DawnUtilsReflection.isObfuscated()) {
				blockHitDelayField = this.getClass().getSuperclass().getDeclaredField("i");
				currentGameTypeField = this.getClass().getSuperclass().getDeclaredField("k");
				blockYField = this.getClass().getSuperclass().getDeclaredField("d");
				currentPlayerItemField = this.getClass().getSuperclass().getDeclaredField("l");
			}
			else {
				blockHitDelayField = this.getClass().getSuperclass().getDeclaredField("blockHitDelay");
				currentGameTypeField = this.getClass().getSuperclass().getDeclaredField("currentGameType");
				blockYField = this.getClass().getSuperclass().getDeclaredField("currentBlockY");
				currentPlayerItemField = this.getClass().getSuperclass().getDeclaredField("currentPlayerItem");
			}

			blockHitDelayField.setAccessible(true);
			currentGameTypeField.setAccessible(true);
			blockYField.setAccessible(true);
			currentPlayerItemField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			if (DawnUtilsReflection.isObfuscated()) {
				e.printStackTrace();
			}
			else {
				DawnUtilsReflection.setObfuscated(true);
				setReflectionFields();
			}
			e.printStackTrace();
		}
	}

    public EntityClientPlayerMP func_78754_a(World par1World)
    {
        return new EntityClientPlayerMP(this.mc, par1World, this.mc.session, this.netClientHandler);
    }

    /**
     * Called when a player completes the destruction of a block
     */
    public boolean onPlayerDestroyBlock(int par1, int par2, int par3, int par4)
    {
        if (this.getCurrentGameType().isAdventure() && !this.mc.thePlayer.canCurrentToolHarvestBlock(par1, par2, par3))
        {
            return false;
        }
        else if (this.getBlockHitDelay() > 0 && !this.getCurrentGameType().isCreative())
        {
            return false;
        }
        else
        {
            WorldClient var5 = this.mc.theWorld;
            Block var6 = Block.blocksList[var5.getBlockId(par1, par2, par3)];

            if (var6 == null)
            {
                return false;
            }
            else
            {
                int var7 = var5.getBlockMetadata(par1, par2, par3);
                boolean var8 = false;

                if (!this.getCurrentGameType().isCreative() && !this.mc.thePlayer.canHarvestBlock(var6, par1, par2, par3) && var6.CanConvertBlock(this.mc.thePlayer.getCurrentEquippedItem(), var5, par1, par2, par3))
                {
                    var5.playAuxSFX(DecoManager.decoCustomBlockConvertAuxFXID, par1, par2, par3, var6.blockID + (var5.getBlockMetadata(par1, par2, par3) << 12));
                    var8 = var6.ConvertBlock(this.mc.thePlayer.getCurrentEquippedItem(), var5, par1, par2, par3, par4);
                }

                if (!var8)
                {
                    var5.playAuxSFX(DecoManager.decoCustomBlockBreakAuxFXID, par1, par2, par3, var6.blockID + (var5.getBlockMetadata(par1, par2, par3) << 12));
                    var8 = var5.setBlockToAir(par1, par2, par3);

                    if (var8)
                    {
                        var6.onBlockDestroyedByPlayer(var5, par1, par2, par3, var7);
                    }
                }

                if (var8)
                {
                    this.OnBlockBrokenClientSide(var6.blockID, par1, par2, par3, var7);
                    this.setBlockHitDelay(5);
                }

                this.setBlockY(-1);

                if (!this.getCurrentGameType().isCreative())
                {
                    ItemStack var9 = this.mc.thePlayer.getCurrentEquippedItem();

                    if (var9 != null)
                    {
                        var9.onBlockDestroyed(var5, var6.blockID, par1, par2, par3, this.mc.thePlayer);

                        if (var9.stackSize == 0)
                        {
                            this.mc.thePlayer.destroyCurrentEquippedItem();
                        }
                    }
                }

                return var8;
            }
        }
    }

    private void OnBlockBrokenClientSide(int var1, int var2, int var3, int var4, int var5)
    {
        float var6 = 1.0F;
        ItemStack var7 = this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem];

        if (var7 != null)
        {
            var6 = var7.getItem().GetExhaustionOnUsedToHarvestBlock(var1, this.mc.theWorld, var2, var3, var4, var5);
        }

        if (var6 > 0.0F)
        {
            this.mc.thePlayer.m_bExhaustionAddedSinceLastGuiUpdate = true;
        }
    }

    /**
     * Handles a players right click. Args: player, world, x, y, z, side, hitVec
     */
    public boolean onPlayerRightClick(EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack, int par4, int par5, int par6, int par7, Vec3 par8Vec3)
    {
        this.syncCurrentPlayItem();
        float var9 = (float)par8Vec3.xCoord - (float)par4;
        float var10 = (float)par8Vec3.yCoord - (float)par5;
        float var11 = (float)par8Vec3.zCoord - (float)par6;
        boolean var12 = false;
        int lookedAtBlockID = par2World.getBlockId(par4, par5, par6);
        int lookedAtBlockMeta = par2World.getBlockMetadata(par4, par5, par6);

        if (!par1EntityPlayer.isSneaking() || par1EntityPlayer.getHeldItem() == null)
        {
            if (lookedAtBlockID > 0 && Block.blocksList[lookedAtBlockID].onBlockActivated(par2World, par4, par5, par6, par1EntityPlayer, par7, var9, var10, var11))
            {
                var12 = true;
            }
        }

        if (!var12 && par3ItemStack != null && !par3ItemStack.getItem().CanItemBeUsedByPlayer(par2World, par4, par5, par6, par7, par1EntityPlayer, par3ItemStack))
        {
        	System.out.println("Unable to place item");
            return false;
        }
        else if (DecoUtilsBlock.canBlocksBePlacedAgainstGivenBlock(lookedAtBlockID, lookedAtBlockMeta))
        {
            this.netClientHandler.addToSendQueue(new Packet15Place(par4, par5, par6, par7, par1EntityPlayer.inventory.getCurrentItem(), var9, var10, var11));

            if (var12)
            {
                return true;
            }
            else if (par3ItemStack == null)
            {
                return false;
            }
            else if (this.getCurrentGameType().isCreative())
            {
                int placedBlockMetadata = par3ItemStack.getItemDamage();
                int var14 = par3ItemStack.stackSize;
                boolean var15 = par3ItemStack.tryPlaceItemIntoWorld(par1EntityPlayer, par2World, par4, par5, par6, par7, var9, var10, var11);
                par3ItemStack.setItemDamage(placedBlockMetadata);
                par3ItemStack.stackSize = var14;
                return var15;
            }
            else
            {
                return par3ItemStack.tryPlaceItemIntoWorld(par1EntityPlayer, par2World, par4, par5, par6, par7, var9, var10, var11);
            }
        }
        else {
        	return false;
        }
    }

    /**
     * Syncs the current player item with the server
     */
    private void syncCurrentPlayItem()
    {
        int var1 = this.mc.thePlayer.inventory.currentItem;

        if (var1 != this.getCurrentPlayerItem())
        {
            this.setCurrentPlayerItem(var1);
            this.netClientHandler.addToSendQueue(new Packet16BlockItemSwitch(this.getCurrentPlayerItem()));
        }
    }
    
    public EnumGameType getCurrentGameType() {
    	try {
			return (EnumGameType) currentGameTypeField.get(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    public int getBlockHitDelay() {
    	try {
			return (Integer) blockHitDelayField.get(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
    
    public int getCurrentPlayerItem() {
    	try {
			return (Integer) currentPlayerItemField.get(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
    
    public void setBlockHitDelay(int delay) {
    	try {
			blockHitDelayField.set(this, delay);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void setBlockY(int y) {
    	try {
			blockYField.set(this, y);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    public void setCurrentPlayerItem(int id) {
    	try {
    		currentPlayerItemField.set(this, id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
}