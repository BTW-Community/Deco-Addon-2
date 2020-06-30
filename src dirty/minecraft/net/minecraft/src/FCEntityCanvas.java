package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FCEntityCanvas extends Entity implements FCIEntityPacketHandler
{
    private int tickCounter1;
    public int direction;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public FCEnumCanvasArt m_art;

    public FCEntityCanvas(World var1)
    {
        super(var1);
        this.tickCounter1 = 0;
        this.direction = 0;
        this.yOffset = 0.0F;
        this.setSize(0.5F, 0.5F);
    }

    public FCEntityCanvas(World var1, int var2, int var3, int var4, int var5)
    {
        this(var1);
        this.xPosition = var2;
        this.yPosition = var3;
        this.zPosition = var4;
        ArrayList var6 = new ArrayList();
        FCEnumCanvasArt[] var7 = FCEnumCanvasArt.values();
        int var8 = var7.length;

        for (int var9 = 0; var9 < var8; ++var9)
        {
            FCEnumCanvasArt var10 = var7[var9];
            this.m_art = var10;
            this.func_412_b(var5);

            if (this.onValidSurface())
            {
                var6.add(var10);
            }
        }

        if (var6.size() > 0)
        {
            this.m_art = (FCEnumCanvasArt)var6.get(this.rand.nextInt(var6.size()));
        }

        this.func_412_b(var5);
    }

    public FCEntityCanvas(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        this(var1);
        this.xPosition = var2;
        this.yPosition = var3;
        this.zPosition = var4;
        FCEnumCanvasArt[] var7 = FCEnumCanvasArt.values();
        this.m_art = var7[var6];
        this.func_412_b(var5);
    }

    public FCEntityCanvas(World var1, int var2, int var3, int var4, int var5, String var6)
    {
        this(var1);
        this.xPosition = var2;
        this.yPosition = var3;
        this.zPosition = var4;
        FCEnumCanvasArt[] var7 = FCEnumCanvasArt.values();
        int var8 = var7.length;

        for (int var9 = 0; var9 < var8; ++var9)
        {
            FCEnumCanvasArt var10 = var7[var9];

            if (var10.m_sTitle.equals(var6))
            {
                this.m_art = var10;
                break;
            }
        }

        this.func_412_b(var5);
    }

    protected void entityInit() {}

    public void func_412_b(int var1)
    {
        this.direction = var1;
        this.prevRotationYaw = this.rotationYaw = (float)(var1 * 90);
        float var2 = (float)this.m_art.m_iSizeX;
        float var3 = (float)this.m_art.m_iSizeY;
        float var4 = (float)this.m_art.m_iSizeX;

        if (var1 != 0 && var1 != 2)
        {
            var2 = 0.5F;
        }
        else
        {
            var4 = 0.5F;
        }

        var2 /= 32.0F;
        var3 /= 32.0F;
        var4 /= 32.0F;
        float var5 = (float)this.xPosition + 0.5F;
        float var6 = (float)this.yPosition + 0.5F;
        float var7 = (float)this.zPosition + 0.5F;
        float var8 = 0.5625F;

        if (var1 == 0)
        {
            var7 -= var8;
            var5 -= this.ComputeBlockOffset(this.m_art.m_iSizeX);
        }
        else if (var1 == 1)
        {
            var5 -= var8;
            var7 += this.ComputeBlockOffset(this.m_art.m_iSizeX);
        }
        else if (var1 == 2)
        {
            var7 += var8;
            var5 += this.ComputeBlockOffset(this.m_art.m_iSizeX);
        }
        else if (var1 == 3)
        {
            var5 += var8;
            var7 -= this.ComputeBlockOffset(this.m_art.m_iSizeX);
        }

        var6 += this.ComputeBlockOffset(this.m_art.m_iSizeY);
        this.setPosition((double)var5, (double)var6, (double)var7);
        float var9 = -0.00625F;
        this.boundingBox.setBounds((double)(var5 - var2 - var9), (double)(var6 - var3 - var9), (double)(var7 - var4 - var9), (double)(var5 + var2 + var9), (double)(var6 + var3 + var9), (double)(var7 + var4 + var9));
    }

    private float ComputeBlockOffset(int var1)
    {
        return var1 % 32 == 0 ? 0.5F : 0.0F;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (this.tickCounter1++ == 100 && !this.worldObj.isRemote)
        {
            this.tickCounter1 = 0;

            if (!this.isDead && !this.onValidSurface())
            {
                this.setDead();
                this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(FCBetterThanWolves.fcItemCanvas)));
            }
        }
    }

    public boolean onValidSurface()
    {
        if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() > 0)
        {
            return false;
        }
        else
        {
            int var1 = this.m_art.m_iSizeX / 16;
            int var2 = this.m_art.m_iSizeY / 16;
            int var3 = this.xPosition;
            int var4 = this.yPosition;
            int var5 = this.zPosition;

            if (this.direction == 0)
            {
                var3 = MathHelper.floor_double(this.posX - (double)((float)this.m_art.m_iSizeX / 32.0F));
            }

            if (this.direction == 1)
            {
                var5 = MathHelper.floor_double(this.posZ - (double)((float)this.m_art.m_iSizeX / 32.0F));
            }

            if (this.direction == 2)
            {
                var3 = MathHelper.floor_double(this.posX - (double)((float)this.m_art.m_iSizeX / 32.0F));
            }

            if (this.direction == 3)
            {
                var5 = MathHelper.floor_double(this.posZ - (double)((float)this.m_art.m_iSizeX / 32.0F));
            }

            var4 = MathHelper.floor_double(this.posY - (double)((float)this.m_art.m_iSizeY / 32.0F));
            int var7;

            for (int var6 = 0; var6 < var1; ++var6)
            {
                for (var7 = 0; var7 < var2; ++var7)
                {
                    Material var8;

                    if (this.direction != 0 && this.direction != 2)
                    {
                        var8 = this.worldObj.getBlockMaterial(this.xPosition, var4 + var7, var5 + var6);
                    }
                    else
                    {
                        var8 = this.worldObj.getBlockMaterial(var3 + var6, var4 + var7, this.zPosition);
                    }

                    if (!var8.isSolid())
                    {
                        return false;
                    }
                }
            }

            List var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);

            for (var7 = 0; var7 < var9.size(); ++var7)
            {
                if (var9.get(var7) instanceof EntityPainting || var9.get(var7) instanceof FCEntityCanvas)
                {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (!this.isDead && !this.worldObj.isRemote)
        {
            this.setDead();
            this.setBeenAttacked();
            this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(FCBetterThanWolves.fcItemCanvas)));
        }

        return true;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setByte("Dir", (byte)this.direction);
        var1.setString("Motive", this.m_art.m_sTitle);
        var1.setInteger("TileX", this.xPosition);
        var1.setInteger("TileY", this.yPosition);
        var1.setInteger("TileZ", this.zPosition);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        this.direction = var1.getByte("Dir");
        this.xPosition = var1.getInteger("TileX");
        this.yPosition = var1.getInteger("TileY");
        this.zPosition = var1.getInteger("TileZ");
        String var2 = var1.getString("Motive");
        FCEnumCanvasArt[] var3 = FCEnumCanvasArt.values();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            FCEnumCanvasArt var6 = var3[var5];

            if (var6.m_sTitle.equals(var2))
            {
                this.m_art = var6;
            }
        }

        if (this.m_art == null)
        {
            this.m_art = FCEnumCanvasArt.Icarus;
        }

        this.func_412_b(this.direction);
    }

    /**
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double var1, double var3, double var5)
    {
        if (!this.worldObj.isRemote && !this.isDead && var1 * var1 + var3 * var3 + var5 * var5 > 0.0D)
        {
            this.setDead();
            this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(FCBetterThanWolves.fcItemCanvas)));
        }
    }

    /**
     * Adds to the current velocity of the entity. Args: x, y, z
     */
    public void addVelocity(double var1, double var3, double var5)
    {
        if (!this.worldObj.isRemote && !this.isDead && var1 * var1 + var3 * var3 + var5 * var5 > 0.0D)
        {
            this.setDead();
            this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(FCBetterThanWolves.fcItemCanvas)));
        }
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(0);
            var2.writeInt(this.entityId);
            var2.writeInt(this.xPosition);
            var2.writeInt(this.yPosition);
            var2.writeInt(this.zPosition);
            var2.writeInt(this.direction);
            var2.writeInt(this.m_art.ordinal());
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return new Packet250CustomPayload("FC|SE", var1.toByteArray());
    }

    public int GetTrackerViewDistance()
    {
        return 160;
    }

    public int GetTrackerUpdateFrequency()
    {
        return Integer.MAX_VALUE;
    }

    public boolean GetTrackMotion()
    {
        return false;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return true;
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }
}
