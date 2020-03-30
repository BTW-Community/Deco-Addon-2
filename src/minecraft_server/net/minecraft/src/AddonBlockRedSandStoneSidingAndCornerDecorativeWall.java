package net.minecraft.src;

public class AddonBlockRedSandStoneSidingAndCornerDecorativeWall extends AddonBlockSidingAndCornerDecorativeWall
{
    private Icon[] m_IconBySideArray = new Icon[6];

    protected AddonBlockRedSandStoneSidingAndCornerDecorativeWall(int var1)
    {
        super(var1, Material.rock, "ginger_redSandstoneDecorative_top", 0.8F, 1.34F, Block.soundStoneFootstep, "redSandStoneSiding", "Red Sandstone");
        this.SetPicksEffectiveOn();
    }
}