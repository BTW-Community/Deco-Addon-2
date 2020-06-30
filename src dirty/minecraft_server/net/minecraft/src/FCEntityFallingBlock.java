package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;

public class FCEntityFallingBlock extends EntityFallingSand
{
    protected boolean m_bHasBlockBrokenOnLand = false;
    protected boolean m_bHurtsEntities = false;
    protected float m_fHurtsAmount = 2.0F;
    protected int m_fHurtsMaxDamage = 40;

    public FCEntityFallingBlock(World var1, double var2, double var4, double var6, int var8, int var9)
    {
        super(var1, var2, var4, var6, var8, var9);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("HurtEntities"))
        {
            this.m_bHurtsEntities = var1.getBoolean("HurtEntities");
            this.m_fHurtsAmount = var1.getFloat("FallHurtAmount");
            this.m_fHurtsMaxDamage = var1.getInteger("FallHurtMax");
        }
        else if (this.blockID == Block.anvil.blockID)
        {
            this.m_bHurtsEntities = true;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.fallTime;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (!this.worldObj.isRemote)
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posY);
            int var3 = MathHelper.floor_double(this.posZ);

            if (this.fallTime == 1)
            {
                if (this.worldObj.getBlockId(var1, var2, var3) != this.blockID)
                {
                    this.setDead();
                    return;
                }

                this.worldObj.setBlockToAir(var1, var2, var3);
            }

            if (this.onGround)
            {
                this.motionX *= 0.699999988079071D;
                this.motionZ *= 0.699999988079071D;
                this.motionY *= -0.5D;
                int var4 = this.worldObj.getBlockId(var1, var2 - 1, var3);
                Block var5 = Block.blocksList[var4];

                if (var5 != null && var5.CanBeCrushedByFallingEntity(this.worldObj, var1, var2 - 1, var3, this))
                {
                    var5.OnCrushedByFallingEntity(this.worldObj, var1, var2 - 1, var3, this);
                    this.worldObj.setBlockToAir(var1, var2 - 1, var3);
                }
                else if (this.worldObj.getBlockId(var1, var2, var3) != Block.pistonMoving.blockID)
                {
                    this.setDead();

                    if (this.AttemptToReplaceBlockAtPosition(var1, var2, var3))
                    {
                        Block.blocksList[this.blockID].onFinishFalling(this.worldObj, var1, var2, var3, this.metadata);
                    }
                    else if (this.shouldDropItem && !this.m_bHasBlockBrokenOnLand)
                    {
                        int var6 = this.worldObj.getBlockId(var1, var2, var3);

                        if (var6 != 0 && Block.blocksList[var6].AttemptToCombineWithFallingEntity(this.worldObj, var1, var2, var3, this))
                        {
                            return;
                        }

                        Block.blocksList[this.blockID].OnBlockDestroyedLandingFromFall(this.worldObj, var1, var2, var3, this.metadata);
                    }
                }
            }
            else if (this.fallTime > 100 && (var2 < 1 || var2 > 256) || this.fallTime > 600)
            {
                if (this.shouldDropItem)
                {
                    this.entityDropItem(new ItemStack(this.blockID, 1, Block.blocksList[this.blockID].damageDropped(this.metadata)), 0.0F);
                }

                this.setDead();
            }
        }

        if (this.isEntityAlive())
        {
            Block.blocksList[this.blockID].OnFallingUpdate(this);
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        if (this.m_bHurtsEntities)
        {
            int var2 = MathHelper.ceiling_float_int(var1 - 1.0F);

            if (var2 > 0)
            {
                ArrayList var3 = new ArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox));
                DamageSource var4 = this.blockID == Block.anvil.blockID ? FCDamageSourceCustom.m_DamageSourceDeadWeight : DamageSource.fallingBlock;
                Iterator var5 = var3.iterator();

                while (var5.hasNext())
                {
                    Entity var6 = (Entity)var5.next();
                    var6.attackEntityFrom(var4, Math.min(MathHelper.floor_float((float)var2 * this.m_fHurtsAmount), this.m_fHurtsMaxDamage));
                }
            }
        }

        Block var7 = Block.blocksList[this.blockID];

        if (var7 != null && !var7.OnFinishedFalling(this, var1))
        {
            this.m_bHasBlockBrokenOnLand = true;
        }
    }

    public void setIsAnvil(boolean var1)
    {
        super.setIsAnvil(var1);
        this.m_bHurtsEntities = var1;
    }

    private boolean AttemptToReplaceBlockAtPosition(int var1, int var2, int var3)
    {
        if (!this.m_bHasBlockBrokenOnLand && this.CanReplaceBlockAtPosition(var1, var2, var3) && !Block.blocksList[this.blockID].CanFallIntoBlockAtPos(this.worldObj, var1, var2 - 1, var3))
        {
            Block var4 = Block.blocksList[this.worldObj.getBlockId(var1, var2, var3)];

            if (var4 != null)
            {
                var4.OnCrushedByFallingEntity(this.worldObj, var1, var2, var3, this);
            }

            return this.worldObj.setBlock(var1, var2, var3, this.blockID, this.metadata, 3);
        }
        else
        {
            return false;
        }
    }

    private boolean CanReplaceBlockAtPosition(int var1, int var2, int var3)
    {
        if (this.worldObj.canPlaceEntityOnSide(this.blockID, var1, var2, var3, true, 1, (Entity)null, (ItemStack)null))
        {
            return true;
        }
        else
        {
            Block var4 = Block.blocksList[this.worldObj.getBlockId(var1, var2, var3)];
            return var4 != null && var4.CanBeCrushedByFallingEntity(this.worldObj, var1, var2, var3, this);
        }
    }
}
