package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class AddonPlayerControllerMP extends PlayerControllerMP {
    /** The Minecraft instance. */
    private final Minecraft mc;
    private final NetClientHandler netClientHandler;
    
    private Field blockHitDelayField;
    private Field currentGameTypeField;
    private Field blockYField;

	public AddonPlayerControllerMP(Minecraft par1Minecraft, NetClientHandler par2NetClientHandler) {
		super(par1Minecraft, par2NetClientHandler);
        this.mc = par1Minecraft;
        this.netClientHandler = par2NetClientHandler;
        
        setReflectionFields();
	}
	
	private void setReflectionFields() {
		try {
			if (AddonManager.isObfuscated()) {
				blockHitDelayField = this.getClass().getSuperclass().getDeclaredField("i");
				currentGameTypeField = this.getClass().getSuperclass().getDeclaredField("k");
				blockYField = this.getClass().getSuperclass().getDeclaredField("d");
			}
			else {
				blockHitDelayField = this.getClass().getSuperclass().getDeclaredField("blockHitDelay");
				currentGameTypeField = this.getClass().getSuperclass().getDeclaredField("currentGameType");
				blockYField = this.getClass().getSuperclass().getDeclaredField("currentBlockY");
			}

			blockHitDelayField.setAccessible(true);
			currentGameTypeField.setAccessible(true);
			blockYField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			if (AddonManager.isObfuscated()) {
				e.printStackTrace();
			}
			else {
				AddonManager.setObfuscated(true);
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
                    var5.playAuxSFX(AddonManager.addonCustomBlockConvertAuxFXID, par1, par2, par3, var6.blockID + (var5.getBlockMetadata(par1, par2, par3) << 12));
                    var8 = var6.ConvertBlock(this.mc.thePlayer.getCurrentEquippedItem(), var5, par1, par2, par3, par4);
                }

                if (!var8)
                {
                    var5.playAuxSFX(AddonManager.addonCustomBlockBreakAuxFXID, par1, par2, par3, var6.blockID + (var5.getBlockMetadata(par1, par2, par3) << 12));
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
}