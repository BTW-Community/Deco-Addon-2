package net.minecraft.src;

public class FCBlockObsidian extends BlockObsidian
{
    public FCBlockObsidian(int var1)
    {
        super(var1);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("obsidian");
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 2;
    }
}
