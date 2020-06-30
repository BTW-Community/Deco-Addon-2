package net.minecraft.src;

public class FCItemWheatSeeds extends FCItemSeeds
{
    public FCItemWheatSeeds(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockWheatCrop.blockID);
        this.SetAsBasicChickenFood();
        this.setUnlocalizedName("fcItemWheatSeeds");
    }
}
