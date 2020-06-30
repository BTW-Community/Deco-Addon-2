package net.minecraft.src;

import java.util.Random;

public class FCBlockComparator extends BlockComparator
{
    public FCBlockComparator(int var1, boolean var2)
    {
        super(var1, var2);
        this.isBlockContainer = false;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    protected int func_94481_j_(int var1)
    {
        return 0;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 0;
    }

    protected boolean func_96470_c(int var1)
    {
        return false;
    }

    protected int func_94480_d(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return 0;
    }

    public boolean func_94490_c(int var1)
    {
        return false;
    }

    protected boolean func_94478_d(World var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    /**
     * Returns the signal strength at one input of the block. Args: world, X, Y, Z, side
     */
    protected int getInputStrength(World var1, int var2, int var3, int var4, int var5)
    {
        return 0;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        return false;
    }

    protected void func_94479_f(World var1, int var2, int var3, int var4, int var5) {}

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {}

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4) {}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6) {}

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return null;
    }
}
