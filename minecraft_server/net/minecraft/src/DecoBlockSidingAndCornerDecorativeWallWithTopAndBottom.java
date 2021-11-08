package net.minecraft.src;

public class DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom extends DecoBlockSidingAndCornerDecorativeWall
{
    private Icon[] m_IconBySideArray = new Icon[6];
    private String[] textures;

    protected DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(int var1, Material material, String[] textures, float hardness, float resistance, StepSound stepSound, String tag, String name)
    {
        super(var1, material, textures[1], hardness, resistance, stepSound, tag, name);
        this.SetPicksEffectiveOn();
        this.textures = textures;
    }
}