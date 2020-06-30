package net.minecraft.src;

import java.util.List;
import java.util.Random;

public abstract class FCBlockCookingVessel extends FCBlockVessel
{
    public FCBlockCookingVessel(int var1, Material var2)
    {
        super(var1, var2);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, (IInventory)var1.getBlockTileEntity(var2, var3, var4));
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote)
        {
            List var6 = null;

            if (!this.GetMechanicallyPoweredFlag(var1, var2, var3, var4))
            {
                var6 = var1.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)var2), (double)((float)var3) + 1.0D, (double)((float)var4), (double)((float)(var2 + 1)), (double)((float)var3) + 1.0D + 0.05000000074505806D, (double)((float)(var4 + 1))));

                if (var6 != null && var6.size() > 0)
                {
                    TileEntity var7 = var1.getBlockTileEntity(var2, var3, var4);

                    if (!(var7 instanceof IInventory))
                    {
                        return;
                    }

                    IInventory var8 = (IInventory)var7;

                    for (int var9 = 0; var9 < var6.size(); ++var9)
                    {
                        EntityItem var10 = (EntityItem)var6.get(var9);

                        if (!var10.isDead && FCUtilsInventory.AddItemStackToInventory(var8, var10.getEntityItem()))
                        {
                            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.pop", 0.25F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                            var10.setDead();
                        }
                    }
                }
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.ValidateFireUnderState(var1, var2, var3, var4);
        super.updateTick(var1, var2, var3, var4, var5);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.ValidateFireUnderState(var1, var2, var3, var4);
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!this.IsOpenSideBlocked(var1, var2, var3, var4) && !var1.isRemote)
        {
            TileEntity var10 = var1.getBlockTileEntity(var2, var3, var4);

            if (var10 instanceof FCTileEntityCookingVessel)
            {
                FCTileEntityCookingVessel var11 = (FCTileEntityCookingVessel)var1.getBlockTileEntity(var2, var3, var4);

                if (var5 instanceof EntityPlayerMP)
                {
                    FCContainerCookingVessel var12 = new FCContainerCookingVessel(var5.inventory, var11);
                    FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var12, this.GetContainerID());
                }
            }
        }

        return true;
    }

    protected abstract void ValidateFireUnderState(World var1, int var2, int var3, int var4);

    protected abstract int GetContainerID();
}
