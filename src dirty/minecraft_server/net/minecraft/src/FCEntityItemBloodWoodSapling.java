package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntityItemBloodWoodSapling extends EntityItem implements FCIEntityPacketHandler
{
    public FCEntityItemBloodWoodSapling(World var1, double var2, double var4, double var6, ItemStack var8)
    {
        super(var1, var2, var4, var6, var8);
        this.isImmuneToFire = true;
    }

    public FCEntityItemBloodWoodSapling(World var1)
    {
        super(var1);
        this.isImmuneToFire = true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.isDead && !this.worldObj.isRemote && this.onGround)
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.boundingBox.minY - 0.10000000149011612D);
            int var3 = MathHelper.floor_double(this.posZ);
            this.worldObj.getBlockId(var1, var2, var3);
            this.CheckForBloodWoodPlant(var1, var2, var3);
        }
    }

    public int GetTrackerViewDistance()
    {
        return 64;
    }

    public int GetTrackerUpdateFrequency()
    {
        return 20;
    }

    public boolean GetTrackMotion()
    {
        return true;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(4);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeInt(this.getEntityItem().itemID);
            var2.writeInt(this.getEntityItem().stackSize);
            var2.writeInt(this.getEntityItem().getItemDamage());
            var2.writeByte((byte)((int)(this.motionX * 128.0D)));
            var2.writeByte((byte)((int)(this.motionY * 128.0D)));
            var2.writeByte((byte)((int)(this.motionZ * 128.0D)));
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return new Packet250CustomPayload("FC|SE", var1.toByteArray());
    }

    public void CheckForBloodWoodPlant(int var1, int var2, int var3)
    {
        Block var4 = Block.blocksList[this.worldObj.getBlockId(var1, var2 + 1, var3)];

        if (var4 == null || var4.IsAirBlock() || var4.IsGroundCover())
        {
            int var5 = this.worldObj.getBlockId(var1, var2, var3);

            if (var5 == Block.slowSand.blockID || var5 == FCBetterThanWolves.fcPlanter.blockID && ((FCBlockPlanter)((FCBlockPlanter)FCBetterThanWolves.fcPlanter)).GetPlanterType(this.worldObj, var1, var2, var3) == 8)
            {
                this.worldObj.setBlockAndMetadataWithNotify(var1, var2 + 1, var3, FCBetterThanWolves.fcAestheticVegetation.blockID, 2);
                --this.getEntityItem().stackSize;

                if (this.getEntityItem().stackSize <= 0)
                {
                    this.setDead();
                }
            }
        }
    }
}
