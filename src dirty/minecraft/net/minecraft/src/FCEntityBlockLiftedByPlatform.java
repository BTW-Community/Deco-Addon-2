package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

public class FCEntityBlockLiftedByPlatform extends Entity implements FCIEntityPacketHandler, FCIEntityIgnoreServerValidation
{
    private int m_iBlockID;
    private int m_iBlockMetadata;

    public FCEntityBlockLiftedByPlatform(World var1)
    {
        super(var1);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
    }

    public FCEntityBlockLiftedByPlatform(World var1, int var2, int var3, int var4)
    {
        this(var1);
        int var5 = var1.getBlockId(var2, var3, var4);
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 != Block.railPowered.blockID && var5 != Block.railDetector.blockID && var5 != FCBetterThanWolves.fcDetectorRailWood.blockID && var5 != FCBetterThanWolves.fcBlockDetectorRailSoulforgedSteel.blockID)
        {
            if (var5 == Block.redstoneWire.blockID)
            {
                var6 = 0;
            }
        }
        else
        {
            var6 &= 7;
        }

        this.SetBlockID(var5);
        this.SetBlockMetadata(var6);
        this.setPosition((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F));
        this.lastTickPosX = this.prevPosX = this.posX;
        this.lastTickPosY = this.prevPosY = this.posY;
        this.lastTickPosZ = this.prevPosZ = this.posZ;
        var1.spawnEntityInWorld(this);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    public FCEntityBlockLiftedByPlatform(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.posX = var2;
        this.posY = var4;
        this.posZ = var6;
        this.lastTickPosX = this.prevPosX = this.posX;
        this.lastTickPosY = this.prevPosY = this.posY;
        this.lastTickPosZ = this.prevPosZ = this.posZ;
    }

    public FCEntityBlockLiftedByPlatform(World var1, double var2, double var4, double var6, int var8, int var9)
    {
        this(var1, var2, var4, var6);
        this.m_iBlockID = var8;
        this.m_iBlockMetadata = var9;
    }

    protected void entityInit() {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setInteger("m_iBlockID", this.GetBlockID());
        var1.setInteger("m_iBlockMetaData", this.GetBlockMetadata());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.SetBlockID(var1.getInteger("m_iBlockID"));
        this.SetBlockMetadata(var1.getInteger("m_iBlockMetaData"));
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity var1)
    {
        return null;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return null;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Applies a velocity to each of the entities pushing them away from each other. Args: entity
     */
    public void applyEntityCollision(Entity var1) {}

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.isDead)
        {
            FCEntityMovingPlatform var1 = null;
            List var2 = this.worldObj.getEntitiesWithinAABB(FCEntityMovingPlatform.class, AxisAlignedBB.getAABBPool().getAABB(this.posX - 0.25D, this.posY - 1.25D, this.posZ - 0.25D, this.posX + 0.25D, this.posY - 0.75D, this.posZ + 0.25D));

            if (var2 != null && var2.size() > 0)
            {
                var1 = (FCEntityMovingPlatform)var2.get(0);

                if (!var1.isDead)
                {
                    double var3 = var1.posX;
                    double var5 = var1.posY + 1.0D;
                    double var7 = var1.posZ;
                    this.prevPosX = this.posX;
                    this.prevPosY = this.posY;
                    this.prevPosZ = this.posZ;
                    this.setPosition(var3, var5, var7);
                }
                else
                {
                    var1 = null;
                }
            }

            if (!this.worldObj.isRemote && var1 == null)
            {
                int var9 = MathHelper.floor_double(this.posX);
                int var4 = MathHelper.floor_double(this.posY);
                int var10 = MathHelper.floor_double(this.posZ);
                this.ConvertToBlock(var9, var4, var10);
            }
        }
    }

    /**
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double var1, double var3, double var5)
    {
        this.DestroyBlockWithDrop();
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(8);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeInt(this.GetBlockID());
            var2.writeInt(this.GetBlockMetadata());
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
        return 3;
    }

    public boolean GetTrackMotion()
    {
        return false;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return false;
    }

    public int GetBlockID()
    {
        return this.m_iBlockID;
    }

    public void SetBlockID(int var1)
    {
        this.m_iBlockID = var1;
    }

    public int GetBlockMetadata()
    {
        return this.m_iBlockMetadata;
    }

    public void SetBlockMetadata(int var1)
    {
        this.m_iBlockMetadata = var1;
    }

    public void DestroyBlockWithDrop()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        int var4 = Block.blocksList[this.GetBlockID()].idDropped(0, this.worldObj.rand, 0);

        if (var4 > 0)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(this.worldObj, var1, var2, var3, var4, 0);
        }

        this.setDead();
    }

    private void ConvertToBlock(int var1, int var2, int var3)
    {
        boolean var4 = true;

        if (this.worldObj.getBlockId(var1, var2 - 1, var3) == FCBetterThanWolves.fcPlatform.blockID && FCUtilsWorld.IsReplaceableBlock(this.worldObj, var1, var2, var3))
        {
            this.worldObj.setBlockAndMetadataWithNotify(var1, var2, var3, this.GetBlockID(), this.GetBlockMetadata());
            var4 = false;
        }

        if (var4)
        {
            this.DestroyBlockWithDrop();
        }
        else
        {
            this.setDead();
        }
    }

    public static boolean CanBlockBeConvertedToEntity(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];

        if (var5 != null)
        {
            if (var5 instanceof BlockRailBase)
            {
                int var6 = var0.getBlockMetadata(var1, var2, var3);

                if (var6 >= 2 & var6 <= 5)
                {
                    return false;
                }

                return true;
            }

            if (var4 == Block.redstoneWire.blockID)
            {
                return true;
            }
        }

        return false;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {}
}
