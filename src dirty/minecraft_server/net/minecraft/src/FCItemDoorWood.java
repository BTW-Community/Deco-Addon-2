package net.minecraft.src;

public class FCItemDoorWood extends FCItemDoor
{
    public FCItemDoorWood(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.setUnlocalizedName("doorWood");
    }

    public Block GetDoorBlock()
    {
        return FCBetterThanWolves.fcBlockDoorWood;
    }
}
