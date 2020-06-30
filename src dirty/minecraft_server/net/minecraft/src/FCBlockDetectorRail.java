package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockDetectorRail extends BlockDetectorRail
{
    public FCBlockDetectorRail(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn(true);
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.isRemote && this.IsOn(var1, var2, var3, var4))
        {
            this.SetStateIfMinecartInteractsWithRailLocal(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && !this.IsOn(var1, var2, var3, var4))
        {
            this.SetStateIfMinecartInteractsWithRailLocal(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
        }
    }

    private boolean IsOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsOnFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    private boolean IsOnFromMetadata(int var1)
    {
        return (var1 & 8) > 0;
    }

    private void SetIsOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        var6 = this.SetIsOnInMetadata(var6, var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
        var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this.blockID);
    }

    private int SetIsOnInMetadata(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 8;
        }
        else
        {
            var1 &= -9;
        }

        return var1;
    }

    private void SetStateIfMinecartInteractsWithRailLocal(World var1, int var2, int var3, int var4, int var5)
    {
        boolean var6 = this.IsOnFromMetadata(var5);
        boolean var7 = false;
        float var8 = 0.125F;
        List var9 = var1.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)var2 + var8), (double)var3, (double)((float)var4 + var8), (double)((float)(var2 + 1) - var8), (double)((float)(var3 + 1) - var8), (double)((float)(var4 + 1) - var8)));

        if (var9 != null && !var9.isEmpty())
        {
            for (int var10 = 0; var10 < var9.size(); ++var10)
            {
                EntityMinecart var11 = (EntityMinecart)var9.get(var10);

                if (this.ShouldPlateActivateBasedOnMinecart(var1, var2, var3, var4, var11.getMinecartType(), var11.riddenByEntity))
                {
                    var7 = true;
                    break;
                }
            }
        }

        if (var7 != var6)
        {
            this.SetIsOn(var1, var2, var3, var4, var7);
        }

        if (var7)
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public boolean ShouldPlateActivateBasedOnMinecart(World var1, int var2, int var3, int var4, int var5, Entity var6)
    {
        int var7 = var1.getBlockId(var2, var3, var4);

        if (var7 == FCBetterThanWolves.fcDetectorRailWood.blockID)
        {
            return true;
        }
        else
        {
            if (var7 == FCBetterThanWolves.fcBlockDetectorRailSoulforgedSteel.blockID)
            {
                if (var6 != null && var6 instanceof EntityPlayer)
                {
                    return true;
                }
            }
            else if (var7 == Block.railDetector.blockID && (var5 > 0 || var6 != null))
            {
                return true;
            }

            return false;
        }
    }
}
