package net.minecraft.src;

public class FCItemBlockLeaves extends ItemBlock
{
    public FCItemBlockLeaves(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1 | 4;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int var1)
    {
        return FCBetterThanWolves.fcBlockBloodLeaves.getIcon(0, var1);
    }

    public int getColorFromItemStack(ItemStack var1, int var2)
    {
        return 14163743;
    }
}
