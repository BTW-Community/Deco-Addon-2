package net.minecraft.src;

public class AddonBlockSandStoneMouldingAndDecorative extends FCBlockMouldingAndDecorative
{
    private Icon[] m_IconBySideArray = new Icon[6];
    private String[] textures;

    protected AddonBlockSandStoneMouldingAndDecorative(int var1, String[] textures, int sidingID, String tag)
    {
        super(var1, Material.rock, textures[1], textures[3], sidingID, 0.8F, 1.34F, Block.soundStoneFootstep, tag);
        this.SetPicksEffectiveOn();
        this.textures = textures;
    }
}
