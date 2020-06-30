package net.minecraft.src;

public class FCItemChiselWood extends FCItemChisel
{
    protected FCItemChiselWood(int var1)
    {
        super(var1, EnumToolMaterial.WOOD, 2);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT.m_iBurnTime / 2);
        this.SetFilterableProperties(4);
        this.efficiencyOnProperMaterial /= 4.0F;
        this.setUnlocalizedName("fcItemChiselWood");
    }
}
