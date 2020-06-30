package net.minecraft.src;

public class FCBlockPressurePlateStone extends FCBlockPressurePlate
{
    protected FCBlockPressurePlateStone(int var1)
    {
        super(var1, "stone", Material.rock, EnumMobType.mobs);
        this.setHardness(1.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("pressurePlate");
    }
}
