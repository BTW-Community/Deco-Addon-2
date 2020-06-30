package net.minecraft.src;

import java.util.Random;

public class FCBlockBBQ extends Block
{
    private static final int m_iTickRate = 4;

    public FCBlockBBQ(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(3.5F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("fcBlockHibachi");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 4;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsGettingPowered(var1, var2, var3, var4);
        int var7;

        if (var6)
        {
            if (!this.IsLit(var1, var2, var3, var4))
            {
                this.Ignite(var1, var2, var3, var4);
            }
            else
            {
                var7 = var1.getBlockId(var2, var3 + 1, var4);

                if (var7 != Block.fire.blockID && var7 != FCBetterThanWolves.fcBlockFireStoked.blockID && this.ShouldIgniteAbove(var1, var2, var3, var4))
                {
                    var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", 1.0F, var1.rand.nextFloat() * 0.4F + 0.8F);
                    var1.setBlockWithNotify(var2, var3 + 1, var4, fire.blockID);
                }
            }
        }
        else if (this.IsLit(var1, var2, var3, var4))
        {
            this.Extinguish(var1, var2, var3, var4);
        }
        else
        {
            var7 = var1.getBlockId(var2, var3 + 1, var4);

            if (var7 == Block.fire.blockID || var7 == FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                var1.setBlockWithNotify(var2, var3 + 1, var4, 0);
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public boolean DoesExtinguishFireAbove(World var1, int var2, int var3, int var4)
    {
        return !this.IsLit(var1, var2, var3, var4);
    }

    public boolean DoesInfiniteBurnToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 1 ? this.IsLit(var1, var2, var3, var4) : false;
    }

    public boolean IsLit(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 4) > 0;
    }

    private void SetLitFlag(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5 | 4);
    }

    private void ClearLitFlag(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5 & -5);
    }

    private boolean IsGettingPowered(World var1, int var2, int var3, int var4)
    {
        boolean var5 = var1.isBlockGettingPowered(var2, var3, var4) || var1.isBlockGettingPowered(var2, var3 + 1, var4);
        return var5;
    }

    private boolean ShouldIgniteAbove(World var1, int var2, int var3, int var4)
    {
        return var1.isAirBlock(var2, var3 + 1, var4) || this.CanIncinerateBlock(var1, var2, var3 + 1, var4);
    }

    private boolean CanIncinerateBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 == null || var5.GetCanBlockBeIncinerated(var1, var2, var3, var4);
    }

    private void Ignite(World var1, int var2, int var3, int var4)
    {
        this.SetLitFlag(var1, var2, var3, var4);
        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", 1.0F, var1.rand.nextFloat() * 0.4F + 1.0F);

        if (this.ShouldIgniteAbove(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3 + 1, var4, fire.blockID);
        }
    }

    private void Extinguish(World var1, int var2, int var3, int var4)
    {
        this.ClearLitFlag(var1, var2, var3, var4);
        var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);
        boolean var5 = var1.getBlockId(var2, var3 + 1, var4) == fire.blockID || var1.getBlockId(var2, var3 + 1, var4) == FCBetterThanWolves.fcBlockFireStoked.blockID;

        if (var5)
        {
            var1.setBlockWithNotify(var2, var3 + 1, var4, 0);
        }
    }

    public boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsGettingPowered(var1, var2, var3, var4);

        if (this.IsLit(var1, var2, var3, var4) != var5)
        {
            return false;
        }
        else
        {
            if (this.IsLit(var1, var2, var3, var4))
            {
                int var6 = var1.getBlockId(var2, var3 + 1, var4);

                if (var6 != Block.fire.blockID && var6 != FCBetterThanWolves.fcBlockFireStoked.blockID && this.ShouldIgniteAbove(var1, var2, var3, var4))
                {
                    return false;
                }
            }

            return true;
        }
    }
}
