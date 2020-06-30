package net.minecraft.src;

public class FCItemBlockUnfiredPottery extends ItemBlock
{
    public FCItemBlockUnfiredPottery(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcBlockUnfiredPottery");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }
}
