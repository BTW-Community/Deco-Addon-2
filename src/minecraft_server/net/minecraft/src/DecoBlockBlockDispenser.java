package net.minecraft.src;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class DecoBlockBlockDispenser extends FCBlockBlockDispenser {
	boolean wasFertilizerUsed = false;

	public DecoBlockBlockDispenser(int id) {
		super(id);
	}

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
		try {
	    	Method validateBlockDispenser = this.getClass().getSuperclass().getDeclaredMethod("ValidateBlockDispenser", new Class[] {World.class, int.class, int.class, int.class});
	    	Method isReceivingRedstonePower = this.getClass().getSuperclass().getDeclaredMethod("IsReceivingRedstonePower", new Class[] {World.class, int.class, int.class, int.class});
			Method setRedstoneOn = this.getClass().getSuperclass().getDeclaredMethod("SetRedstoneOn", new Class[] {World.class, int.class, int.class, int.class, boolean.class});
	    	Method consumeFacingBlock = this.getClass().getSuperclass().getDeclaredMethod("ConsumeFacingBlock", new Class[] {World.class, int.class, int.class, int.class});
	    	
	    	validateBlockDispenser.setAccessible(true);
	    	isReceivingRedstonePower.setAccessible(true);
	    	setRedstoneOn.setAccessible(true);
	    	consumeFacingBlock.setAccessible(true);
	    	
	        validateBlockDispenser.invoke(this, new Object[] {var1, var2, var3, var4});
	        boolean var6 = (Boolean) isReceivingRedstonePower.invoke(this, new Object[] {var1, var2, var3, var4});

	        if (var6)
	        {
	            if (!this.IsRedstoneOn(var1, var2, var3, var4))
	            {
	            	setRedstoneOn.invoke(this, new Object[] {var1, var2, var3, var4, true});
	                this.DispenseBlockOrItem(var1, var2, var3, var4);
	            }
	        }
	        else if (this.IsRedstoneOn(var1, var2, var3, var4))
	        {
	        	setRedstoneOn.invoke(this, new Object[] {var1, var2, var3, var4, false});
	        	
	        	if (!wasFertilizerUsed)
	        		consumeFacingBlock.invoke(this, new Object[] {var1, var2, var3, var4});
	        }
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }

    private boolean DispenseBlockOrItem(World var1, int var2, int var3, int var4)
    {
    	try {
			Method validateBlockDispenser = this.getClass().getSuperclass().getDeclaredMethod("ValidateBlockDispenser", new Class[] {World.class, int.class, int.class, int.class});
	    	validateBlockDispenser.setAccessible(true);
	        validateBlockDispenser.invoke(this, new Object[] {var1, var2, var3, var4});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        Block var7 = Block.blocksList[var1.getBlockId(var6.i, var6.j, var6.k)];

        if (var7 == null || var7.blockMaterial.isReplaceable() || !var7.blockMaterial.isSolid())
        {
            FCTileEntityBlockDispenser var8 = (FCTileEntityBlockDispenser)var1.getBlockTileEntity(var2, var3, var4);
            ItemStack var9 = var8.GetCurrentItemToDispense();

            if (var9 != null)
            {
                Object var10 = null;
                boolean var11 = true;

                if (var9.getItem().OnItemUsedByBlockDispenser(var9, var1, var2, var3, var4, var5))
                {
                	wasFertilizerUsed = var9.getItem().itemID == DecoDefs.fertilizer.itemID;
                	
                    var1.playAuxSFX(2241, var2, var3, var4, var5);
                    var8.OnDispenseCurrentSlot();
                    return true;
                }
                else {
                	wasFertilizerUsed = false;
                }
            }
        }

        var1.playAuxSFX(2238, var2, var3, var4, 0);
        return false;
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory(((IInventory) par1World.getBlockTileEntity(par2, par3, par4)));
    }
}
