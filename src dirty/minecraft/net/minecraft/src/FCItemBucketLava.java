package net.minecraft.src;

public class FCItemBucketLava extends Item
{
    public FCItemBucketLava(int var1)
    {
        super(var1);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.LAVA_BUCKET);
        this.setUnlocalizedName("bucketLava");
    }
}
