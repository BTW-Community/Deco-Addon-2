package net.minecraft.src;

import java.util.List;

public class FCExplosionMining
{
    public static final float m_fExplosionStrength = 20.0F;
    private World worldObj;
    public double explosionX;
    public double explosionY;
    public double explosionZ;
    public int m_iFacing;

    public FCExplosionMining(World var1, double var2, double var4, double var6, int var8)
    {
        this.worldObj = var1;
        this.explosionX = var2;
        this.explosionY = var4;
        this.explosionZ = var6;
        this.m_iFacing = var8;
    }

    public void doExplosion()
    {
        this.DamageEntities();
        this.DestroyBlocks();
        this.worldObj.playAuxSFX(2229, MathHelper.floor_double(this.explosionX), MathHelper.floor_double(this.explosionY), MathHelper.floor_double(this.explosionZ), 0);
    }

    private void DestroyBlocks()
    {
        int var1 = MathHelper.floor_double(this.explosionX);
        int var2 = MathHelper.floor_double(this.explosionY);
        int var3 = MathHelper.floor_double(this.explosionZ);
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (var4 <= 0 || Block.blocksList[var4].getExplosionResistance((Entity)null, this.worldObj, var1, var2, var3) < 20.0F)
        {
            FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);
            var5.AddFacingAsOffset(this.m_iFacing);
            int var6 = this.worldObj.getBlockId(var5.i, var5.j, var5.k);

            if (var6 > 0 && Block.blocksList[var6].getExplosionResistance((Entity)null, this.worldObj, var5.i, var5.j, var5.k) >= 20.0F)
            {
                var5 = new FCUtilsBlockPos(var1, var2, var3);
            }

            this.DestroyCentralBlocks(var5.i, var5.j, var5.k);
            var5.AddFacingAsOffset(this.m_iFacing);
            var6 = this.worldObj.getBlockId(var5.i, var5.j, var5.k);

            if (var6 <= 0 || Block.blocksList[var6].getExplosionResistance((Entity)null, this.worldObj, var5.i, var5.j, var5.k) < 20.0F)
            {
                var5.AddFacingAsOffset(this.m_iFacing);
                var6 = this.worldObj.getBlockId(var5.i, var5.j, var5.k);

                if (var6 > 0 && Block.blocksList[var6].getExplosionResistance((Entity)null, this.worldObj, var5.i, var5.j, var5.k) < 20.0F)
                {
                    this.DestroyBlock(var5.i, var5.j, var5.k);
                }
            }
        }
    }

    private void DestroyCentralBlocks(int var1, int var2, int var3)
    {
        for (int var4 = var1 - 1; var4 <= var1 + 1; ++var4)
        {
            for (int var5 = var2 - 1; var5 <= var2 + 1; ++var5)
            {
                for (int var6 = var3 - 1; var6 <= var3 + 1; ++var6)
                {
                    int var7 = this.worldObj.getBlockId(var4, var5, var6);

                    if (var7 > 0 && Block.blocksList[var7].getExplosionResistance((Entity)null, this.worldObj, var4, var5, var6) < 20.0F)
                    {
                        this.DestroyBlock(var4, var5, var6);
                    }
                }
            }
        }
    }

    private void DamageEntities()
    {
        float var1 = 6.0F;
        int var2 = MathHelper.floor_double(this.explosionX - (double)var1 - 1.0D);
        int var3 = MathHelper.floor_double(this.explosionX + (double)var1 + 1.0D);
        int var4 = MathHelper.floor_double(this.explosionY - (double)var1 - 1.0D);
        int var5 = MathHelper.floor_double(this.explosionY + (double)var1 + 1.0D);
        int var6 = MathHelper.floor_double(this.explosionZ - (double)var1 - 1.0D);
        int var7 = MathHelper.floor_double(this.explosionZ + (double)var1 + 1.0D);
        List var8 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var4, (double)var6, (double)var3, (double)var5, (double)var7));
        Vec3 var9 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

        for (int var10 = 0; var10 < var8.size(); ++var10)
        {
            Entity var11 = (Entity)var8.get(var10);
            double var12 = var11.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)var1;

            if (var12 <= 1.0D)
            {
                double var14 = var11.posX - this.explosionX;
                double var16 = var11.posY - this.explosionY;
                double var18 = var11.posZ - this.explosionZ;
                double var20 = (double)MathHelper.sqrt_double(var14 * var14 + var16 * var16 + var18 * var18);
                var14 /= var20;
                var16 /= var20;
                var18 /= var20;
                double var22 = (double)this.worldObj.getBlockDensity(var9, var11.boundingBox);
                double var24 = (1.0D - var12) * var22;

                if (var11 instanceof EntityItem)
                {
                    EntityItem var26 = (EntityItem)var11;
                    int var27 = var26.getEntityItem().itemID;

                    if (var27 != Item.coal.itemID && var27 != Item.redstone.itemID && var27 != Item.dyePowder.itemID && var27 != Item.flint.itemID && var27 != Block.oreGold.blockID && var27 != Block.oreIron.blockID)
                    {
                        int var28 = (int)((var24 * var24 + var24) / 2.0D * 8.0D * (double)var1 + 1.0D);

                        if (var28 > 2)
                        {
                            var28 = 3;
                        }

                        var11.attackEntityFrom(DamageSource.setExplosionSource((Explosion)null), var28);
                    }
                }
                else
                {
                    var11.attackEntityFrom(DamageSource.setExplosionSource((Explosion)null), (int)((var24 * var24 + var24) / 2.0D * 8.0D * (double)var1 + 1.0D));
                }

                if (!(var11 instanceof FCEntityMiningCharge))
                {
                    var11.motionX += var14 * var24;
                    var11.motionY += var16 * var24;
                    var11.motionZ += var18 * var24;
                }
            }
        }
    }

    private void DestroyBlock(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (var4 > 0)
        {
            Block var5 = Block.blocksList[var4];

            if (var4 == Block.cobblestone.blockID)
            {
                var5.dropBlockAsItem_do(this.worldObj, var1, var2, var3, new ItemStack(Block.gravel.blockID, 1, 0));
            }
            else
            {
                var5.DropItemsOnDestroyedByExplosion(this.worldObj, var1, var2, var3, (Explosion)null);
            }

            var5.onBlockDestroyedByExplosion(this.worldObj, var1, var2, var3, (Explosion)null);
            this.worldObj.setBlockWithNotify(var1, var2, var3, 0);
        }
    }
}
