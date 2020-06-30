package net.minecraft.src;

public class FCBlockBedrock extends FCBlockFullBlock
{
    public FCBlockBedrock(int var1)
    {
        super(var1, Material.rock);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("bedrock");
        this.disableStats();
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 2;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }
}
