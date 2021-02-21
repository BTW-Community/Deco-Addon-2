package net.minecraft.src;

public class DecoBlockSandStoneSidingAndCornerDecorativeWall extends DecoBlockSidingAndCornerDecorativeWall
{
    private Icon[] m_IconBySideArray = new Icon[6];
    private String[] textures;

    protected DecoBlockSandStoneSidingAndCornerDecorativeWall(int var1, String[] textures, String tag, String name)
    {
        super(var1, Material.rock, textures[1], 0.8F, 1.34F, Block.soundStoneFootstep, tag, name);
        this.SetPicksEffectiveOn();
        this.textures = textures;
    }
}