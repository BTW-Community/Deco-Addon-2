package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityLightningBolt extends EntityWeatherEffect
{
    private final int m_iMaxTrunkDetectionPenetration = 6;
    private final int m_iMaxTrunkPenetration = 6;
    private int m_iLightningState;
    private int m_iDurationCountdown;
    public long m_lRenderSeed = 0L;

    public FCEntityLightningBolt(World var1, double var2, double var4, double var6)
    {
        super(var1);
        this.setLocationAndAngles(var2, var4, var6, 0.0F, 0.0F);
        this.m_iLightningState = 2;
        this.m_iDurationCountdown = this.rand.nextInt(3) + 1;
        this.m_lRenderSeed = this.rand.nextLong();

        if (!var1.isRemote && !this.IsStrikingLightningRod() && var1.doChunksNearChunkExist(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6), 10))
        {
            int var8 = MathHelper.floor_double(var2);
            int var9 = (int)var4;
            int var10 = MathHelper.floor_double(var6);
            this.OnStrikeBlock(var1, var8, var9, var10);

            if (!this.IsStrikingLightningRod())
            {
                for (int var11 = 0; var11 < 4; ++var11)
                {
                    int var12 = var8 + this.rand.nextInt(3) - 1;
                    int var13 = var9 + this.rand.nextInt(3) - 1;
                    int var14 = var10 + this.rand.nextInt(3) - 1;

                    if (FCBlockFire.CanFireReplaceBlock(var1, var12, var13, var14) && Block.fire.canPlaceBlockAt(var1, var12, var13, var14))
                    {
                        var1.setBlock(var12, var13, var14, Block.fire.blockID);
                    }
                }
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        --this.m_iLightningState;

        if (this.m_iLightningState == 1)
        {
            this.worldObj.func_82739_e(2280, MathHelper.floor_double(this.posX), (int)this.posY - 1, MathHelper.floor_double(this.posZ), 0);

            if (!this.IsStrikingLightningRod())
            {
                double var1 = 3.0D;
                List var3 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB(this.posX - var1, this.posY - var1, this.posZ - var1, this.posX + var1, this.posY + 6.0D + var1, this.posZ + var1));
                Iterator var4 = var3.iterator();

                while (var4.hasNext())
                {
                    Entity var5 = (Entity)var4.next();
                    var5.OnStruckByLightning(this);
                }
            }
        }
        else if (this.m_iLightningState < 0)
        {
            if (this.m_iDurationCountdown == 0)
            {
                this.setDead();
            }
            else if (this.m_iLightningState < -this.rand.nextInt(10))
            {
                --this.m_iDurationCountdown;
                this.m_iLightningState = 1;
                this.m_lRenderSeed = this.rand.nextLong();

                if (!this.worldObj.isRemote && !this.IsStrikingLightningRod() && this.worldObj.doChunksNearChunkExist(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 10))
                {
                    int var6 = MathHelper.floor_double(this.posX);
                    int var2 = (int)this.posY;
                    int var7 = MathHelper.floor_double(this.posZ);

                    if (FCBlockFire.CanFireReplaceBlock(this.worldObj, var6, var2, var7) && Block.fire.canPlaceBlockAt(this.worldObj, var6, var2, var7))
                    {
                        this.worldObj.setBlock(var6, var2, var7, Block.fire.blockID);
                    }
                }
            }
        }

        if (this.m_iLightningState >= 0 && this.worldObj.isRemote)
        {
            this.worldObj.lastLightningBolt = 2;
        }
    }

    protected void entityInit() {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1) {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1) {}

    private boolean IsStrikingLightningRod()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        return this.worldObj.getBlockId(var1, var2 - 1, var3) == FCBetterThanWolves.fcBlockLightningRod.blockID;
    }

    private void OnStrikeBlock(World var1, int var2, int var3, int var4)
    {
        if (this.HasHitTreeTrunk(var1, var2, var3, var4))
        {
            this.BurnTreeTrunk(var1, var2, var3, var4);
        }
        else if (var1.getBlockId(var2, var3 - 1, var4) == Block.leaves.blockID)
        {
            for (int var5 = 0; var5 < 5; ++var5)
            {
                int var6 = var2 + var1.rand.nextInt(5) - 2;
                int var7 = var4 + var1.rand.nextInt(5) - 2;

                if (FCBlockFire.CanFireReplaceBlock(var1, var6, var3, var7) && this.HasHitTreeTrunk(var1, var6, var3, var7))
                {
                    this.BurnTreeTrunk(var1, var6, var3, var7);
                    break;
                }
            }
        }

        Block var8 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var8 != null)
        {
            var8.OnStruckByLightning(var1, var2, var3 - 1, var4);
        }

        if (FCBlockFire.CanFireReplaceBlock(var1, var2, var3, var4) && Block.fire.canPlaceBlockAt(var1, var2, var3, var4))
        {
            var1.setBlock(var2, var3, var4, Block.fire.blockID);
        }
    }

    private boolean HasHitTreeTrunk(World var1, int var2, int var3, int var4)
    {
        int var5 = var3 - 6;
        --var3;

        while (var3 >= var5)
        {
            int var6 = var1.getBlockId(var2, var3, var4);

            if (var6 == Block.wood.blockID)
            {
                return (var1.getBlockMetadata(var2, var3, var4) & 3) != 3;
            }

            if (var6 != Block.leaves.blockID)
            {
                return false;
            }

            --var3;
        }

        return false;
    }

    private boolean BurnTreeTrunk(World var1, int var2, int var3, int var4)
    {
        int var5 = var3 - 6;
        FCBlockLog var6 = (FCBlockLog)Block.wood;
        --var3;

        for (; var3 >= var5; --var3)
        {
            int var7 = var1.getBlockId(var2, var3, var4);

            if (var7 == Block.wood.blockID)
            {
                if ((var1.getBlockMetadata(var2, var3, var4) & 3) == 3)
                {
                    break;
                }

                var6.ConvertToSmouldering(var1, var2, var3, var4);
            }
            else
            {
                if (var7 != Block.leaves.blockID)
                {
                    break;
                }

                if (Block.fire.canPlaceBlockAt(var1, var2, var3, var4))
                {
                    var1.setBlock(var2, var3, var4, Block.fire.blockID);
                }
                else
                {
                    var1.setBlockToAir(var2, var3, var4);
                }
            }
        }

        return false;
    }

    /**
     * Checks using a Vec3d to determine if this entity is within range of that vector to be rendered. Args: vec3D
     */
    public boolean isInRangeToRenderVec3D(Vec3 var1)
    {
        return this.m_iLightningState >= 0;
    }
}
