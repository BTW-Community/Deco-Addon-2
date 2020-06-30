package net.minecraft.src;

public class FCTileEntityMobSpawner extends TileEntityMobSpawner
{
    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote && this.worldObj.rand.nextInt(1200) == 0 && this.worldObj.checkChunksExist(this.xCoord - 4, this.yCoord - 1, this.zCoord - 4, this.xCoord + 4, this.yCoord + 4, this.zCoord + 4))
        {
            int var1 = this.worldObj.rand.nextInt(9);
            int var2 = this.worldObj.rand.nextInt(6);
            int var3 = this.worldObj.rand.nextInt(9);
            int var4 = this.xCoord - 4 + var1;
            int var5 = this.yCoord - 1 + var2;
            int var6 = this.zCoord - 4 + var3;

            if (this.worldObj.getBlockId(var4, var5, var6) == Block.cobblestone.blockID)
            {
                this.worldObj.setBlockWithNotify(var4, var5, var6, Block.cobblestoneMossy.blockID);
            }
        }

        super.updateEntity();
    }
}
