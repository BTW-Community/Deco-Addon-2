package net.minecraft.src;

public class FCBlockCauldron extends FCBlockCookingVessel
{
    public FCBlockCauldron(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(3.5F);
        this.setResistance(10.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockCauldron");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityCauldron();
    }

    protected void ValidateFireUnderState(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof FCTileEntityCauldron)
            {
                FCTileEntityCauldron var6 = (FCTileEntityCauldron)var5;
                var6.ValidateFireUnderType();
            }
        }
    }

    protected int GetContainerID()
    {
        return FCBetterThanWolves.fcCauldronContainerID;
    }
}
