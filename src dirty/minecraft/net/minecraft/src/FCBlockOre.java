package net.minecraft.src;

import java.util.List;

public class FCBlockOre extends BlockOre
{
    private Icon[] m_IconByMetadataArray = new Icon[16];

    public FCBlockOre(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn();
    }

    public boolean HasStrata()
    {
        return true;
    }

    public int GetMetadataConversionForStrataLevel(int var1, int var2)
    {
        return var1;
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 != 0 ? (var5 == 1 ? 4.0F : 6.0F) : super.getBlockHardness(var1, var2, var3, var4);
    }

    public float getExplosionResistance(Entity var1, World var2, int var3, int var4, int var5)
    {
        int var6 = this.GetStrata(var2, var3, var4, var5);
        return var6 != 0 ? (var6 == 1 ? 4.2000003F : 6.0F) : super.getExplosionResistance(var1, var2, var3, var4, var5);
    }

    public int GetStrata(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetStrata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetStrata(int var1)
    {
        return var1 & 3;
    }

    public int GetRequiredToolLevelForStrata(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 > 1 ? var5 + 1 : 2;
    }

    public boolean IsNaturalStone(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconByMetadataArray[0] = this.blockIcon;
        this.m_IconByMetadataArray[1] = var1.registerIcon("fcBlock" + this.getUnlocalizedName2() + "Strata_1");
        this.m_IconByMetadataArray[2] = var1.registerIcon("fcBlock" + this.getUnlocalizedName2() + "Strata_2");

        for (int var2 = 3; var2 < 16; ++var2)
        {
            this.m_IconByMetadataArray[var2] = this.blockIcon;
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconByMetadataArray[var2];
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderStandardFullBlock(this, var2, var3, var4);
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return true;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
    }

    public void RenderBlockMovedByPiston(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.RenderStandardFullBlockMovedByPiston(this, var2, var3, var4);
    }
}
