package net.minecraft.src;

import java.util.List;

public class FCEntityMovingAnchor extends Entity implements FCIEntityPacketHandler, FCIEntityIgnoreServerValidation
{
    public static final float fMovementSpeed = 0.05F;
    private static final int m_iYMotionDataWatcherID = 22;
    private static final int m_iVehicleSpawnPacketType = 102;
    private FCUtilsBlockPos associatedPulleyPos;
    private int m_iAssociatedPulleyRopeStateCounter;
    private int m_iOldBottomJ;

    public FCEntityMovingAnchor(World var1)
    {
        super(var1);
        this.associatedPulleyPos = new FCUtilsBlockPos();
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 1.98F);
        this.yOffset = 0.5F;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.m_iAssociatedPulleyRopeStateCounter = -1;
        this.m_iOldBottomJ = 0;
    }

    public FCEntityMovingAnchor(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4, var6);
        this.lastTickPosX = this.prevPosX = var2;
        this.lastTickPosY = this.prevPosY = var4;
        this.lastTickPosZ = this.prevPosZ = var6;
        this.m_iOldBottomJ = MathHelper.floor_double(this.posY - (double)this.yOffset);
    }

    public FCEntityMovingAnchor(World var1, double var2, double var4, double var6, FCUtilsBlockPos var8, int var9)
    {
        this(var1);
        this.associatedPulleyPos.i = var8.i;
        this.associatedPulleyPos.j = var8.j;
        this.associatedPulleyPos.k = var8.k;

        if (var9 > 0)
        {
            this.motionY = 0.05000000074505806D;
        }
        else
        {
            this.motionY = -0.05000000074505806D;
        }

        this.setPosition(var2, var4, var6);
        this.lastTickPosX = this.prevPosX = var2;
        this.lastTickPosY = this.prevPosY = var4;
        this.lastTickPosZ = this.prevPosZ = var6;
        int var10 = this.worldObj.getBlockId(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);

        if (var10 == FCBetterThanWolves.fcPulley.blockID)
        {
            FCTileEntityPulley var11 = (FCTileEntityPulley)this.worldObj.getBlockTileEntity(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);

            if (var11 != null)
            {
                this.m_iAssociatedPulleyRopeStateCounter = var11.iUpdateRopeStateCounter;
            }
        }

        this.m_iOldBottomJ = MathHelper.floor_double(this.posY - (double)this.yOffset);
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(22, new Integer(0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setInteger("associatedPulleyPosI", this.associatedPulleyPos.i);
        var1.setInteger("associatedPulleyPosJ", this.associatedPulleyPos.j);
        var1.setInteger("associatedPulleyPosK", this.associatedPulleyPos.k);
        var1.setInteger("m_iAssociatedPulleyRopeStateCounter", this.m_iAssociatedPulleyRopeStateCounter);
        var1.setInteger("m_iOldBottomJ", this.m_iOldBottomJ);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.associatedPulleyPos.i = var1.getInteger("associatedPulleyPosI");
        this.associatedPulleyPos.j = var1.getInteger("associatedPulleyPosJ");
        this.associatedPulleyPos.k = var1.getInteger("associatedPulleyPosK");

        if (var1.hasKey("m_iAssociatedPulleyRopeStateCounter"))
        {
            this.m_iAssociatedPulleyRopeStateCounter = var1.getInteger("m_iAssociatedPulleyRopeStateCounter");
        }

        if (var1.hasKey("m_iOldBottomJ"))
        {
            this.m_iOldBottomJ = var1.getInteger("m_iOldBottomJ");
        }
        else
        {
            this.m_iOldBottomJ = MathHelper.floor_double(this.posY - (double)this.yOffset);
        }
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
        return var1.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY + FCBlockAnchor.m_dAnchorBaseHeight, this.boundingBox.maxZ);
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
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.isDead)
        {
            if (this.worldObj.isRemote)
            {
                this.motionY = this.GetCorseYMotion();
            }

            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posZ);
            byte var3 = 35;

            if (this.worldObj.checkChunksExist(var1 - var3, 0, var2 - var3, var1 + var3, 0, var2 + var3))
            {
                FCTileEntityPulley var4 = null;
                int var5 = this.worldObj.getBlockId(var1, this.m_iOldBottomJ + 1, var2);
                boolean var6 = false;

                if (!this.worldObj.isRemote)
                {
                    int var7 = this.worldObj.getBlockId(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);
                    int var8 = this.worldObj.getBlockId(var1, this.m_iOldBottomJ + 2, var2);
                    boolean var9 = false;

                    if (var7 == FCBetterThanWolves.fcPulley.blockID)
                    {
                        if (var5 == FCBetterThanWolves.fcPulley.blockID || var5 == FCBetterThanWolves.fcRopeBlock.blockID || var8 == FCBetterThanWolves.fcPulley.blockID || var8 == FCBetterThanWolves.fcRopeBlock.blockID)
                        {
                            var4 = (FCTileEntityPulley)this.worldObj.getBlockTileEntity(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);

                            if (this.m_iAssociatedPulleyRopeStateCounter == var4.iUpdateRopeStateCounter)
                            {
                                return;
                            }

                            if (this.motionY > 0.0D)
                            {
                                if (var4.IsLowering())
                                {
                                    this.motionY = -this.motionY;
                                    var6 = true;
                                }
                            }
                            else if (var4.IsRaising())
                            {
                                this.motionY = -this.motionY;
                                var6 = true;
                            }

                            this.m_iAssociatedPulleyRopeStateCounter = var4.iUpdateRopeStateCounter;
                        }

                        this.SetCorseYMotion(this.motionY);
                    }

                    if (this.motionY <= 0.01D && this.motionY >= -0.01D)
                    {
                        this.ConvertToBlock(var1, this.m_iOldBottomJ, var2);
                        return;
                    }
                }

                this.MoveEntityInternal(this.motionX, this.motionY, this.motionZ);
                double var15 = this.posY;
                int var16 = MathHelper.floor_double(var15 - (double)this.yOffset);
                List var10 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox().expand(0.0D, 0.15D, 0.0D));
                int var11;

                if (var10 != null && var10.size() > 0)
                {
                    for (var11 = 0; var11 < var10.size(); ++var11)
                    {
                        Entity var12 = (Entity)var10.get(var11);

                        if (!var12.canBePushed() && !(var12 instanceof EntityItem) && !(var12 instanceof EntityXPOrb))
                        {
                            if (!var12.isDead && var12 instanceof FCEntityMechPower)
                            {
                                FCEntityMechPower var13 = (FCEntityMechPower)var12;
                                var13.DestroyWithDrop();
                            }
                        }
                        else
                        {
                            this.PushEntity(var12);
                        }
                    }
                }

                if (!this.worldObj.isRemote && (this.m_iOldBottomJ != var16 || var6))
                {
                    if (this.motionY > 0.0D)
                    {
                        if (this.worldObj.getBlockId(var1, var16, var2) == FCBetterThanWolves.fcRopeBlock.blockID)
                        {
                            var4.AttemptToRetractRope();
                        }

                        var11 = this.worldObj.getBlockId(var1, var16 + 1, var2);

                        if (var11 != FCBetterThanWolves.fcRopeBlock.blockID || var4 == null || !var4.IsRaising() || var16 + 1 >= this.associatedPulleyPos.j)
                        {
                            this.ConvertToBlock(var1, var16, var2);
                            return;
                        }
                    }
                    else
                    {
                        boolean var17 = false;

                        if (var4 != null)
                        {
                            byte var18 = 2;

                            if (var5 == FCBetterThanWolves.fcPulley.blockID || var5 == FCBetterThanWolves.fcRopeBlock.blockID)
                            {
                                var18 = 1;
                                int var19 = this.worldObj.getBlockId(var1, this.m_iOldBottomJ, var2);

                                if (var19 == FCBetterThanWolves.fcPulley.blockID || var19 == FCBetterThanWolves.fcRopeBlock.blockID)
                                {
                                    var18 = 0;
                                }
                            }

                            if (var4.GetContainedRopeCount() >= var18)
                            {
                                var17 = true;
                            }
                            else
                            {
                                var17 = false;
                            }
                        }

                        int var20 = this.worldObj.getBlockId(var1, var16, var2);
                        boolean var21 = false;

                        if (var4 != null && var4.IsLowering() && var17)
                        {
                            if (!FCUtilsWorld.IsReplaceableBlock(this.worldObj, var1, var16, var2))
                            {
                                if (Block.blocksList[var20].blockMaterial.isSolid() && var20 != Block.web.blockID && var20 != FCBetterThanWolves.fcBlockWeb.blockID)
                                {
                                    var21 = true;
                                }
                                else
                                {
                                    int var14 = this.worldObj.getBlockMetadata(var1, var16, var2);

                                    if (var20 == FCBetterThanWolves.fcRopeBlock.blockID)
                                    {
                                        if (!this.ReturnRopeToPulley())
                                        {
                                            Block.blocksList[var20].dropBlockAsItem(this.worldObj, var1, var16, var2, var14, 0);
                                        }
                                    }
                                    else
                                    {
                                        this.worldObj.playAuxSFX(2252, var1, var16, var2, var20 + (var14 << 12));
                                        Block.blocksList[var20].dropBlockAsItem(this.worldObj, var1, var16, var2, var14, 0);
                                    }

                                    this.worldObj.setBlockWithNotify(var1, var16, var2, 0);
                                }
                            }
                        }
                        else
                        {
                            var21 = true;
                        }

                        if (var21)
                        {
                            this.ConvertToBlock(var1, this.m_iOldBottomJ, var2);
                            return;
                        }

                        if (var4 != null && this.worldObj.getBlockId(var1, var16 + 1, var2) != FCBetterThanWolves.fcRopeBlock.blockID && this.worldObj.getBlockId(var1, var16 + 1, var2) != FCBetterThanWolves.fcPulley.blockID)
                        {
                            var4.AttemptToDispenseRope();
                        }
                    }

                    this.m_iOldBottomJ = var16;
                }
            }
        }
    }

    /**
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double var1, double var3, double var5)
    {
        this.NotifyAssociatedPulleyOfLossOfAnchorEntity();
        this.DestroyAnchorWithDrop();
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        return new Packet23VehicleSpawn(this, GetVehicleSpawnPacketType(), 0);
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

    private double GetCorseYMotion()
    {
        return (double)this.dataWatcher.getWatchableObjectInt(22) / 100.0D;
    }

    private void SetCorseYMotion(double var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf((int)(var1 * 100.0D)));
    }

    public static int GetVehicleSpawnPacketType()
    {
        return 102;
    }

    public void DestroyAnchorWithDrop()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        ItemStack var4 = new ItemStack(FCBetterThanWolves.fcAnchor);
        FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, var1, var2, var3, var4);
        this.setDead();
    }

    private void MoveEntityInternal(double var1, double var3, double var5)
    {
        double var7 = this.posX + var1;
        double var9 = this.posY + var3;
        double var11 = this.posZ + var5;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.setPosition(var7, var9, var11);
        this.TestForBlockCollisions();
    }

    private void TestForBlockCollisions()
    {
        int var1 = MathHelper.floor_double(this.getBoundingBox().minX + 0.001D);
        int var2 = MathHelper.floor_double(this.getBoundingBox().minY + 0.001D);
        int var3 = MathHelper.floor_double(this.getBoundingBox().minZ + 0.001D);
        int var4 = MathHelper.floor_double(this.getBoundingBox().maxX - 0.001D);
        int var5 = MathHelper.floor_double(this.getBoundingBox().maxY - 0.001D);
        int var6 = MathHelper.floor_double(this.getBoundingBox().maxZ - 0.001D);

        if (this.worldObj.checkChunksExist(var1, var2, var3, var4, var5, var6))
        {
            for (int var7 = var1; var7 <= var4; ++var7)
            {
                for (int var8 = var2; var8 <= var5; ++var8)
                {
                    for (int var9 = var3; var9 <= var6; ++var9)
                    {
                        int var10 = this.worldObj.getBlockId(var7, var8, var9);

                        if (var10 > 0)
                        {
                            Block.blocksList[var10].onEntityCollidedWithBlock(this.worldObj, var7, var8, var9, this);
                        }
                    }
                }
            }
        }
    }

    private void PushEntity(Entity var1)
    {
        AxisAlignedBB var2 = this.getBoundingBox();
        double var3 = var2.maxY + 0.075D;
        double var5 = var1.boundingBox.minY;

        if (var5 < var3)
        {
            double var7;

            if (var5 > var2.maxY - 0.25D)
            {
                if (var1 instanceof EntityPlayer)
                {
                    if (this.worldObj.isRemote)
                    {
                        this.ClientPushPlayer(var1);
                    }
                }
                else
                {
                    var7 = var2.maxY + 0.01D - var5;
                    var1.setPosition(var1.posX, var1.posY + var7, var1.posZ);

                    if (var1.riddenByEntity != null)
                    {
                        var1.riddenByEntity.setPosition(var1.riddenByEntity.posX, var1.riddenByEntity.posY + var7, var1.riddenByEntity.posZ);
                    }
                }
            }
            else if (var1 instanceof EntityLiving && this.motionY < 0.0D)
            {
                var7 = var1.boundingBox.maxY;

                if (var2.minY < var7 - 0.25D && var3 > var7)
                {
                    var1.attackEntityFrom(DamageSource.inWall, 1);
                }
            }
        }
    }

    public void ForceStopByPlatform()
    {
        if (!this.isDead)
        {
            int var1;
            int var2;
            int var3;

            if (this.motionY > 0.0D)
            {
                var1 = MathHelper.floor_double(this.posX);
                var2 = MathHelper.floor_double(this.posY) + 1;
                var3 = MathHelper.floor_double(this.posZ);
                int var4 = this.worldObj.getBlockId(var1, var2, var3);

                if (var4 == FCBetterThanWolves.fcRopeBlock.blockID)
                {
                    ((FCBlockRope)((FCBlockRope)FCBetterThanWolves.fcRopeBlock)).BreakRope(this.worldObj, var1, var2, var3);
                }
            }

            var1 = MathHelper.floor_double(this.posX);
            var2 = MathHelper.floor_double(this.posY);
            var3 = MathHelper.floor_double(this.posZ);
            this.ConvertToBlock(var1, var2, var3);
        }
    }

    private void ConvertToBlock(int var1, int var2, int var3)
    {
        boolean var4 = true;
        int var5 = this.worldObj.getBlockId(var1, var2, var3);

        if (!FCUtilsWorld.IsReplaceableBlock(this.worldObj, var1, var2, var3))
        {
            if (var5 == FCBetterThanWolves.fcRopeBlock.blockID)
            {
                if (!this.ReturnRopeToPulley())
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(this.worldObj, var1, var2, var3, FCBetterThanWolves.fcItemRope.itemID, 0);
                }
            }
            else if (Block.blocksList[var5].blockMaterial.isSolid() && var5 != Block.web.blockID && var5 != FCBetterThanWolves.fcBlockWeb.blockID)
            {
                var4 = false;
            }
            else
            {
                int var6 = this.worldObj.getBlockMetadata(var1, var2, var3);
                Block.blocksList[var5].dropBlockAsItem(this.worldObj, var1, var2, var3, var6, 0);
                this.worldObj.playAuxSFX(2252, var1, var2, var3, var5 + (var6 << 12));
            }
        }

        if (var4)
        {
            this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcAnchor.blockID);
            ((FCBlockAnchor)((FCBlockAnchor)FCBetterThanWolves.fcAnchor)).SetFacing(this.worldObj, var1, var2, var3, 1);
        }
        else
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(this.worldObj, var1, var2, var3, FCBetterThanWolves.fcAnchor.blockID, 0);
        }

        this.NotifyAssociatedPulleyOfLossOfAnchorEntity();
        this.setDead();
    }

    public boolean ReturnRopeToPulley()
    {
        int var1 = this.worldObj.getBlockId(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);

        if (var1 == FCBetterThanWolves.fcPulley.blockID)
        {
            FCTileEntityPulley var2 = (FCTileEntityPulley)this.worldObj.getBlockTileEntity(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);

            if (var2 != null)
            {
                var2.AddRopeToInventory();
                return true;
            }
        }

        return false;
    }

    private void NotifyAssociatedPulleyOfLossOfAnchorEntity()
    {
        int var1 = this.worldObj.getBlockId(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);

        if (var1 == FCBetterThanWolves.fcPulley.blockID)
        {
            FCTileEntityPulley var2 = (FCTileEntityPulley)this.worldObj.getBlockTileEntity(this.associatedPulleyPos.i, this.associatedPulleyPos.j, this.associatedPulleyPos.k);
            var2.NotifyOfLossOfAnchorEntity();
        }
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9)
    {
        this.setPosition(var1, var3, var5);
    }

    private void ClientPushPlayer(Entity var1)
    {
        double var2 = var1.boundingBox.minY;
        AxisAlignedBB var4 = this.getBoundingBox();
        double var5 = var4.maxY + 0.01D - var2;
        var1.setPosition(var1.posX, var1.posY + var5, var1.posZ);
        var1.serverPosX = (int)(var1.posX * 32.0D);
        var1.serverPosY = (int)(var1.posY * 32.0D);
        var1.serverPosZ = (int)(var1.posZ * 32.0D);

        if (var1.riddenByEntity != null)
        {
            var1.riddenByEntity.setPosition(var1.riddenByEntity.posX, var1.riddenByEntity.posY + var5, var1.riddenByEntity.posZ);
        }

        var1.onGround = true;
    }
}
