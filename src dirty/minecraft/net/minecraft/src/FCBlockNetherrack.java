package net.minecraft.src;

public class FCBlockNetherrack extends FCBlockFullBlock
{
    public FCBlockNetherrack(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(0.6F);
        this.setResistance(0.6666667F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("hellrock");
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.0F;
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);

        if (!var1.provider.isHellWorld)
        {
            var1.setBlock(var2, var3, var4, FCBetterThanWolves.fcBlockNetherrackFalling.blockID, 0, 2);
        }
    }
}
