package net.minecraft.src;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class AddonBlockSaw extends FCBlockSaw {
	private FCBlockSaw original;

	public AddonBlockSaw(FCBlockSaw original, int ID) {
		super(ID);
		this.original = original;
	}

    protected void SawBlockToFront(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4, var6);

        if (!var1.isAirBlock(var7.i, var7.j, var7.k) && !this.HandleSawingExceptionCases(var1, var7.i, var7.j, var7.k, var2, var3, var4, var6, var5))
        {
            Block var8 = Block.blocksList[var1.getBlockId(var7.i, var7.j, var7.k)];

            if (var8 != null)
            {
                if (var8.DoesBlockBreakSaw(var1, var7.i, var7.j, var7.k))
                {
                    this.BreakSaw(var1, var2, var3, var4);
                }
                else if (var8.OnBlockSawed(var1, var7.i, var7.j, var7.k, var2, var3, var4))
                {
                    this.EmitSawParticles(var1, var7.i, var7.j, var7.k, var5);
                }
            }
        }
    }

	private boolean HandleSawingExceptionCases(World var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, Random var9)
	{
		int id = var1.getBlockId(var2, var3, var4);

		if (id == Block.pistonMoving.blockID)
			return true;

		int metadata = var1.getBlockMetadata(var2,  var3,  var4);

		if (id == AddonDefs.cherryStairs.blockID) {
			FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 5);
			FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 5);

            this.EmitSawParticles(var1, var2, var3, var4, var9);
            var1.setBlockToAir(var2, var3, var4);
			
			return true;
		}
		else {
			try {
				Method sawExceptionsMethodSuperclass = FCBlockSaw.class.getDeclaredMethod("HandleSawingExceptionCases", new Class[] {World.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, Random.class});
				sawExceptionsMethodSuperclass.setAccessible(true);
				return (Boolean) sawExceptionsMethodSuperclass.invoke(original, new Object[] {var1, var2, var3, var4, var5, var6, var7, var8, var9});
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
			return false;
		}
	}
}
