package net.minecraft.src;

public class DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom extends DecoBlockSidingAndCornerDecorativeWall
{
    private Icon[] m_IconBySideArray = new Icon[6];
    private String[] textures;

    protected DecoBlockSidingAndCornerDecorativeWallWithTopAndBottom(int var1, String[] textures, float hardness, float resistance, StepSound stepSound, String tag, String name)
    {
        super(var1, Material.rock, textures[1], hardness, resistance, stepSound, tag, name);
        this.SetPicksEffectiveOn();
        this.textures = textures;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconBySideArray[0] = var1.registerIcon(textures[0]);
        this.m_IconBySideArray[1] = var1.registerIcon(textures[1]);
        Icon var2 = var1.registerIcon(textures[2]);
        this.m_IconBySideArray[2] = var2;
        this.m_IconBySideArray[3] = var2;
        this.m_IconBySideArray[4] = var2;
        this.m_IconBySideArray[5] = var2;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconBySideArray[var1];
    }
}