package net.minecraft.src;

public class DecoBlockMouldingAndDecorativeWallWithTopAndBottom extends DecoBlockMouldingAndDecorativeWall
{
    private Icon[] m_IconBySideArray = new Icon[6];
    private String[] textures;

    protected DecoBlockMouldingAndDecorativeWallWithTopAndBottom(int var1, String[] textures, int sidingID, float hardness, float resistance, StepSound stepSound, String tag)
    {
        super(var1, Material.rock, textures[1], textures[3], sidingID, hardness, resistance, stepSound, tag);
        this.SetPicksEffectiveOn();
        this.textures = textures;
    }
}
