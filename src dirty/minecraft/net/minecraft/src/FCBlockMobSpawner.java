package net.minecraft.src;

public class FCBlockMobSpawner extends BlockMobSpawner
{
    protected FCBlockMobSpawner(int var1)
    {
        super(var1);
        this.setHardness(5.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("mobSpawner");
        this.disableStats();
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityMobSpawner();
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }
}
