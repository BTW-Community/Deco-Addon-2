package net.minecraft.src;

public class AddonBlockRedSandStoneMouldingAndDecorative extends FCBlockMouldingAndDecorative
{
    private Icon[] m_IconBySideArray = new Icon[6];

    protected AddonBlockRedSandStoneMouldingAndDecorative(int var1)
    {
        super(var1, Material.rock, "ginger_redSandstoneDecorative_side", "ginger_redSandstoneDecorative_column", AddonDefs.redSandStoneSidingAndCorner.blockID, 0.8F, 1.34F, Block.soundStoneFootstep, "redSandStoneMoulding");
        this.SetPicksEffectiveOn();
    }
}
