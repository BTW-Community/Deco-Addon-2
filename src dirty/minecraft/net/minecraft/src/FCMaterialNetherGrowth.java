package net.minecraft.src;

public class FCMaterialNetherGrowth extends Material
{
    public FCMaterialNetherGrowth(MapColor var1)
    {
        super(var1);
        this.setNoPushMobility();
    }

    /**
     * Returns if this material is considered solid or not
     */
    public boolean blocksMovement()
    {
        return false;
    }
}
