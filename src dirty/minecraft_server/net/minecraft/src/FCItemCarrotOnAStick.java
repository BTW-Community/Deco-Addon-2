package net.minecraft.src;

public class FCItemCarrotOnAStick extends ItemCarrotOnAStick
{
    public FCItemCarrotOnAStick(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetFilterableProperties(4);
        this.SetAsBasicPigFood();
        this.setUnlocalizedName("carrotOnAStick");
    }
}
