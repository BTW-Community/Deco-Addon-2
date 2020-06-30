package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class FCBlockGourd extends FCBlockFalling
{
    private static final double m_dArrowSpeedSquaredToExplode = 1.1D;
    protected Icon m_IconTop;

    protected FCBlockGourd(int var1)
    {
        super(var1, Material.pumpkin);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);

        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            this.ValidateConnectionState(var1, var2, var3, var4);
        }
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 0;
    }

    public void OnArrowImpact(World var1, int var2, int var3, int var4, EntityArrow var5)
    {
        if (!var1.isRemote)
        {
            double var6 = var5.motionX * var5.motionX + var5.motionY * var5.motionY + var5.motionZ * var5.motionZ;

            if (var6 >= 1.1D)
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
                this.Explode(var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D);
            }
            else
            {
                var1.playAuxSFX(2251, var2, var3, var4, 0);
            }
        }
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(this.AuxFXIDOnExplode(), var3, var4, var5, 0);
    }

    public boolean OnFinishedFalling(EntityFallingSand var1, float var2)
    {
        var1.metadata = 0;

        if (!var1.worldObj.isRemote)
        {
            int var3 = MathHelper.floor_double(var1.posX);
            int var4 = MathHelper.floor_double(var1.posY);
            int var5 = MathHelper.floor_double(var1.posZ);
            int var6 = MathHelper.ceiling_float_int(var1.fallDistance - 5.0F);

            if (var6 >= 0)
            {
                this.DamageCollidingEntitiesOnFall(var1, var2);

                if (!Material.water.equals(var1.worldObj.getBlockMaterial(var3, var4, var5)) && var1.rand.nextInt(10) < var6)
                {
                    this.Explode(var1.worldObj, (double)var3 + 0.5D, (double)var4 + 0.5D, (double)var5 + 0.5D);
                    return false;
                }
            }

            var1.worldObj.playAuxSFX(2251, var3, var4, var5, 0);
        }

        return true;
    }

    public int AdjustMetadataForPistonMove(int var1)
    {
        boolean var2 = false;
        return 0;
    }

    public boolean IsBlockAttachedToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return var6 >= 2 && var5 == var6;
    }

    public void AttachToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2 && var5 <= 5)
        {
            var1.setBlockMetadataWithClient(var2, var3, var4, var5);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        this.ValidateConnectionState(var1, var2, var3, var4);
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return var5.CanGrazeOnRoughVegetation();
    }

    protected abstract Item ItemToDropOnExplode();

    protected abstract int ItemCountToDropOnExplode();

    protected abstract int AuxFXIDOnExplode();

    protected abstract DamageSource GetFallDamageSource();

    private void Explode(World var1, double var2, double var4, double var6)
    {
        Item var8 = this.ItemToDropOnExplode();

        if (var8 != null)
        {
            for (int var9 = 0; var9 < this.ItemCountToDropOnExplode(); ++var9)
            {
                ItemStack var10 = new ItemStack(var8, 1, 0);
                double var11 = var2 + (var1.rand.nextDouble() - 0.5D) * 2.0D;
                double var13 = var4 + 0.5D;
                double var15 = var6 + (var1.rand.nextDouble() - 0.5D) * 2.0D;
                EntityItem var17 = new EntityItem(var1, var11, var13, var15, var10);
                var17.motionX = (var1.rand.nextDouble() - 0.5D) * 0.5D;
                var17.motionY = 0.2D + var1.rand.nextDouble() * 0.3D;
                var17.motionZ = (var1.rand.nextDouble() - 0.5D) * 0.5D;
                var17.delayBeforeCanPickup = 10;
                var1.spawnEntityInWorld(var17);
            }
        }

        this.NotifyNearbyAnimalsFinishedFalling(var1, MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6));
        var1.playAuxSFX(this.AuxFXIDOnExplode(), MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6), 0);
    }

    private void DamageCollidingEntitiesOnFall(EntityFallingSand var1, float var2)
    {
        int var3 = MathHelper.ceiling_float_int(var2 - 1.0F);

        if (var3 > 0)
        {
            ArrayList var4 = new ArrayList(var1.worldObj.getEntitiesWithinAABBExcludingEntity(var1, var1.boundingBox));
            DamageSource var5 = this.GetFallDamageSource();
            Iterator var6 = var4.iterator();

            while (var6.hasNext())
            {
                Entity var7 = (Entity)var6.next();
                var7.attackEntityFrom(var5, 1);
            }
        }
    }

    protected void ValidateConnectionState(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 > 0)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);

            if (var5 >= 2 && var5 <= 5)
            {
                var6.AddFacingAsOffset(var5);
                int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

                if (Block.blocksList[var7] == null || !(Block.blocksList[var7] instanceof FCBlockStem) || var1.getBlockMetadata(var6.i, var6.j, var6.k) != 15)
                {
                    var1.setBlockMetadata(var2, var3, var4, 0);
                }
            }
            else
            {
                var1.setBlockMetadata(var2, var3, var4, 0);
            }
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 != 1 && var1 != 0 ? this.blockIcon : this.m_IconTop;
    }
}
