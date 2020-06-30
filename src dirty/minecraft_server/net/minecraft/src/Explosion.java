package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Explosion
{
    /** whether or not the explosion sets fire to blocks around it */
    public boolean isFlaming = false;

    /** whether or not this explosion spawns smoke particles */
    public boolean isSmoking = true;
    private int field_77289_h = 16;
    private Random explosionRNG = new Random();
    private World worldObj;
    public double explosionX;
    public double explosionY;
    public double explosionZ;
    public Entity exploder;
    public float explosionSize;

    /** A list of ChunkPositions of blocks affected by this explosion */
    public List affectedBlockPositions = new ArrayList();
    private Map field_77288_k = new HashMap();
    public boolean m_bSuppressFX = false;
    public List m_SecondaryExplosionList = null;

    public Explosion(World par1World, Entity par2Entity, double par3, double par5, double par7, float par9)
    {
        this.worldObj = par1World;
        this.exploder = par2Entity;
        this.explosionSize = par9;
        this.explosionX = par3;
        this.explosionY = par5;
        this.explosionZ = par7;
    }

    /**
     * Does the first part of the explosion (destroy blocks)
     */
    public void doExplosionA()
    {
        float var1 = this.explosionSize;
        HashSet var2 = new HashSet();
        int var3;
        int var4;
        int var5;
        double var6;
        double var8;
        double var10;

        for (var3 = 0; var3 < this.field_77289_h; ++var3)
        {
            for (var4 = 0; var4 < this.field_77289_h; ++var4)
            {
                for (var5 = 0; var5 < this.field_77289_h; ++var5)
                {
                    if (var3 == 0 || var3 == this.field_77289_h - 1 || var4 == 0 || var4 == this.field_77289_h - 1 || var5 == 0 || var5 == this.field_77289_h - 1)
                    {
                        double var12 = (double)((float)var3 / ((float)this.field_77289_h - 1.0F) * 2.0F - 1.0F);
                        double var14 = (double)((float)var4 / ((float)this.field_77289_h - 1.0F) * 2.0F - 1.0F);
                        double var16 = (double)((float)var5 / ((float)this.field_77289_h - 1.0F) * 2.0F - 1.0F);
                        double var18 = Math.sqrt(var12 * var12 + var14 * var14 + var16 * var16);
                        var12 /= var18;
                        var14 /= var18;
                        var16 /= var18;
                        float var20 = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
                        var6 = this.explosionX;
                        var8 = this.explosionY;
                        var10 = this.explosionZ;

                        for (float var21 = 0.3F; var20 > 0.0F; var20 -= var21 * 0.75F)
                        {
                            int var22 = MathHelper.floor_double(var6);
                            int var23 = MathHelper.floor_double(var8);
                            int var24 = MathHelper.floor_double(var10);
                            int var25 = this.worldObj.getBlockId(var22, var23, var24);

                            if (var25 > 0)
                            {
                                Block var26 = Block.blocksList[var25];
                                float var27 = this.exploder != null ? this.exploder.func_82146_a(this, this.worldObj, var22, var23, var24, var26) : var26.getExplosionResistance(this.exploder, this.worldObj, var22, var23, var24);
                                var20 -= (var27 + 0.3F) * var21;
                            }

                            if (var20 > 0.0F && (this.exploder == null || this.exploder.func_96091_a(this, this.worldObj, var22, var23, var24, var25, var20)))
                            {
                                var2.add(new ChunkPosition(var22, var23, var24));
                            }

                            var6 += var12 * (double)var21;
                            var8 += var14 * (double)var21;
                            var10 += var16 * (double)var21;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(var2);
        this.explosionSize *= 2.0F;
        var3 = MathHelper.floor_double(this.explosionX - (double)this.explosionSize - 1.0D);
        var4 = MathHelper.floor_double(this.explosionX + (double)this.explosionSize + 1.0D);
        var5 = MathHelper.floor_double(this.explosionY - (double)this.explosionSize - 1.0D);
        int var29 = MathHelper.floor_double(this.explosionY + (double)this.explosionSize + 1.0D);
        int var13 = MathHelper.floor_double(this.explosionZ - (double)this.explosionSize - 1.0D);
        int var30 = MathHelper.floor_double(this.explosionZ + (double)this.explosionSize + 1.0D);
        List var15 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getAABBPool().getAABB((double)var3, (double)var5, (double)var13, (double)var4, (double)var29, (double)var30));
        Vec3 var31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.explosionX, this.explosionY, this.explosionZ);

        for (int var17 = 0; var17 < var15.size(); ++var17)
        {
            Entity var32 = (Entity)var15.get(var17);
            double var19 = var32.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)this.explosionSize;

            if (var19 <= 1.0D)
            {
                var6 = var32.posX - this.explosionX;
                var8 = var32.posY + (double)var32.getEyeHeight() - this.explosionY;
                var10 = var32.posZ - this.explosionZ;
                double var33 = (double)MathHelper.sqrt_double(var6 * var6 + var8 * var8 + var10 * var10);

                if (var33 != 0.0D)
                {
                    var6 /= var33;
                    var8 /= var33;
                    var10 /= var33;
                    double var34 = (double)this.worldObj.getBlockDensity(var31, var32.boundingBox);
                    double var35 = (1.0D - var19) * var34;
                    var32.attackEntityFrom(DamageSource.setExplosionSource(this), (int)((var35 * var35 + var35) / 2.0D * 8.0D * (double)this.explosionSize + 1.0D));
                    double var36 = EnchantmentProtection.func_92092_a(var32, var35);
                    var32.motionX += var6 * var36;
                    var32.motionY += var8 * var36;
                    var32.motionZ += var10 * var36;

                    if (var32 instanceof EntityPlayer)
                    {
                        this.field_77288_k.put((EntityPlayer)var32, this.worldObj.getWorldVec3Pool().getVecFromPool(var6 * var35, var8 * var35, var10 * var35));
                    }
                }
            }
        }

        this.explosionSize = var1;
    }

    /**
     * Does the second part of the explosion (sound, particles, drop spawn)
     */
    public void doExplosionB(boolean par1)
    {
        if (!this.m_bSuppressFX)
        {
            this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

            if (this.explosionSize >= 2.0F && this.isSmoking)
            {
                this.worldObj.spawnParticle("hugeexplosion", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
            }
            else
            {
                this.worldObj.spawnParticle("largeexplode", this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
            }
        }

        Iterator var2;
        ChunkPosition var3;

        if (this.isSmoking)
        {
            var2 = this.affectedBlockPositions.iterator();

            while (var2.hasNext())
            {
                var3 = (ChunkPosition)var2.next();
                int var4 = var3.x;
                int var5 = var3.y;
                int var6 = var3.z;
                int var7 = this.worldObj.getBlockId(var4, var5, var6);

                if (par1)
                {
                    double var8 = (double)((float)var4 + this.worldObj.rand.nextFloat());
                    double var10 = (double)((float)var5 + this.worldObj.rand.nextFloat());
                    double var12 = (double)((float)var6 + this.worldObj.rand.nextFloat());
                    double var14 = var8 - this.explosionX;
                    double var16 = var10 - this.explosionY;
                    double var18 = var12 - this.explosionZ;
                    double var20 = (double)MathHelper.sqrt_double(var14 * var14 + var16 * var16 + var18 * var18);
                    var14 /= var20;
                    var16 /= var20;
                    var18 /= var20;
                    double var22 = 0.5D / (var20 / (double)this.explosionSize + 0.1D);
                    var22 *= (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
                    var14 *= var22;
                    var16 *= var22;
                    var18 *= var22;
                    this.worldObj.spawnParticle("explode", (var8 + this.explosionX * 1.0D) / 2.0D, (var10 + this.explosionY * 1.0D) / 2.0D, (var12 + this.explosionZ * 1.0D) / 2.0D, var14, var16, var18);
                    this.worldObj.spawnParticle("smoke", var8, var10, var12, var14, var16, var18);
                }

                if (var7 > 0)
                {
                    Block var24 = Block.blocksList[var7];
                    var24.DropItemsOnDestroyedByExplosion(this.worldObj, var4, var5, var6, this);
                    var24.onBlockDestroyedByExplosion(this.worldObj, var4, var5, var6, this);
                    this.worldObj.setBlock(var4, var5, var6, 0, 0, 3);
                }
            }
        }

        if (this.isFlaming)
        {
            var2 = this.affectedBlockPositions.iterator();

            while (var2.hasNext())
            {
                var3 = (ChunkPosition)var2.next();

                if (this.explosionRNG.nextInt(3) == 0 && FCBlockFire.CanFireReplaceBlock(this.worldObj, var3.x, var3.y, var3.z) && Block.fire.canPlaceBlockAt(this.worldObj, var3.x, var3.y, var3.z))
                {
                    this.worldObj.setBlock(var3.x, var3.y, var3.z, Block.fire.blockID);
                }
            }
        }

        this.PerformSecondaryExplosions();
    }

    public Map func_77277_b()
    {
        return this.field_77288_k;
    }

    public EntityLiving func_94613_c()
    {
        return this.exploder == null ? null : (this.exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : (this.exploder instanceof EntityLiving ? (EntityLiving)this.exploder : null));
    }

    public void AddSecondaryExplosionNoFX(double var1, double var3, double var5, float var7, boolean var8, boolean var9)
    {
        if (this.m_SecondaryExplosionList == null)
        {
            this.m_SecondaryExplosionList = new ArrayList();
        }

        Explosion var10 = new Explosion(this.worldObj, (Entity)null, var1, var3, var5, var7);
        var10.isFlaming = var8;
        var10.isSmoking = var9;
        var10.m_bSuppressFX = true;
        this.m_SecondaryExplosionList.add(var10);
    }

    private void PerformSecondaryExplosions()
    {
        if (this.m_SecondaryExplosionList != null)
        {
            Iterator var1 = this.m_SecondaryExplosionList.iterator();

            while (var1.hasNext())
            {
                Explosion var2 = (Explosion)var1.next();
                var2.doExplosionA();
                var2.doExplosionB(false);
            }
        }
    }
}
