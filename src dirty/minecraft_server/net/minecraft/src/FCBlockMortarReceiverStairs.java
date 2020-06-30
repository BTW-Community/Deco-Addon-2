package net.minecraft.src;

public abstract class FCBlockMortarReceiverStairs extends FCBlockStairsFalling
{
    protected FCBlockMortarReceiverStairs(int var1, Block var2, int var3)
    {
        super(var1, var2, var3);
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (this.HasNeighborWithMortarInContact(var1, var2, var3, var4))
        {
            var1.playAuxSFX(2275, var2, var3, var4, 0);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 40);
        }
        else
        {
            this.ScheduleCheckForFall(var1, var2, var3, var4);
        }
    }

    protected int ValidateMetadataForLocation(World var1, int var2, int var3, int var4, int var5)
    {
        if (this.GetIsUpsideDown(var5))
        {
            int var6 = this.ConvertDirectionToFacing(this.GetDirection(var5));

            if (!this.HasNeighborWithMortarInContact(var1, var2, var3, var4, var6, true))
            {
                var5 = this.SetIsUpsideDown(var5, false);
            }
        }

        return var5;
    }
}
