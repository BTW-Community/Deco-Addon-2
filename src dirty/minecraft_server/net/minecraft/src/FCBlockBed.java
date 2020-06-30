package net.minecraft.src;

public class FCBlockBed extends BlockBed
{
    public FCBlockBed(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var1.isRemote)
        {
            var5.addChatMessage("You are too troubled to rest.");
        }

        return true;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}
}
