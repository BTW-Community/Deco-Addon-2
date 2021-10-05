package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSaplingCherry extends BlockFlower
{
    private static final double m_dWidth = 0.8D;
    private static final double m_dHalfWidth = 0.4D;
    private Icon[][] m_IconArray = new Icon[4][4];
    public static final String[] m_sBaseTextureNames = new String[] {"decoBlockSaplingCherry_0"};

    protected DecoBlockSaplingCherry(int var1)
    {
        super(var1);
        float var2 = 0.4F;
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING);
        this.SetFilterableProperties(0);
        this.InitBlockBounds(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
        this.SetBuoyant();
        this.setUnlocalizedName("decoBlockSaplingCherry");
        this.setStepSound(Block.soundGrassFootstep);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    /*
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        growTree(par1World, par2, par3, par4, new Random());
        return true;
    }
    */

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.checkFlowerChange(var1, var2, var3, var4);

        if (var1.provider.dimensionId != 1 && var1.getBlockId(var2, var3, var4) == this.blockID && var1.getBlockLightValue(var2, var3 + 1, var4) >= 9 && var5.nextInt(64) == 0)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            int var7 = (var6 & -4) >> 2;

            if (var7 < 3)
            {
                ++var7;
                var6 = var6 & 3 | var7 << 2;
                var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
            }
            else
            {
                this.growTree(var1, var2, var3, var4, var5);
            }
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 3;
        boolean var7 = false;
        int var8 = var1.getBlockId(var2, var3 - 1, var4);
        int var9;

        if (var8 == FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID)
        {
            var9 = var1.getBlockMetadata(var2, var3 - 1, var4);

            if (((FCBlockAestheticOpaqueEarth)FCBetterThanWolves.fcBlockAestheticOpaqueEarth).IsBlightFromMetadata(var9))
            {
                ;
            }
        }

        var9 = 0;
        int var10 = 0;
        boolean var11 = false;
        
        var7 = DecoUtilsTrees.generateCherry(var1, var5, var2, var3, var4);
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanSaplingsGrowOnBlock(var1, var2, var3, var4);
    }

    public int GetSaplingGrowthStage(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = (var5 & -4) >> 2;
        return var6;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        for (int var2 = 0; var2 < m_sBaseTextureNames.length; ++var2)
        {
            for (int var3 = 0; var3 < 4; ++var3)
            {
                this.m_IconArray[var2][var3] = var1.registerIcon(m_sBaseTextureNames[var2] + var3);
            }
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = var2 & 3;
        int var4 = (var2 & -4) >> 2;
        return this.m_IconArray[var3][var4];
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        //par3List.add(new ItemStack(par1, 1, 1));
        //par3List.add(new ItemStack(par1, 1, 2));
        //par3List.add(new ItemStack(par1, 1, 3));
    }
}
