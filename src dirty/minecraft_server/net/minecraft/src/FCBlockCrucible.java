package net.minecraft.src;

public class FCBlockCrucible extends FCBlockCookingVessel
{
    public FCBlockCrucible(int var1)
    {
        super(var1, Material.glass);
        this.setHardness(0.6F);
        this.setResistance(3.0F);
        this.SetPicksEffectiveOn(true);
        this.setStepSound(soundGlassFootstep);
        this.setUnlocalizedName("fcBlockCrucible");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityCrucible();
    }

    protected void ValidateFireUnderState(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof FCTileEntityCrucible)
            {
                FCTileEntityCrucible var6 = (FCTileEntityCrucible)var5;
                var6.ValidateFireUnderType();
            }
        }
    }

    protected int GetContainerID()
    {
        return FCBetterThanWolves.fcCrucibleContainerID;
    }
}
