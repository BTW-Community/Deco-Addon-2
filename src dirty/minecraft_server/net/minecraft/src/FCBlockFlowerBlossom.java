package net.minecraft.src;

public class FCBlockFlowerBlossom extends BlockFlower
{
    protected FCBlockFlowerBlossom(int var1)
    {
        super(var1);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.DAMP_VEGETATION);
        this.SetFilterableProperties(2);
        this.setStepSound(soundGrassFootstep);
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return true;
    }
}
