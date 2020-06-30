package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockWoolSlab extends FCBlockSlab
{
    private boolean m_bIsUpsideDown;
    public static final int m_iNumSubtypes = 16;
    private Icon[] m_IconByColorArray;

    public FCBlockWoolSlab(int var1, boolean var2)
    {
        super(var1, Material.cloth);
        this.setHardness(0.8F);
        this.SetBuoyancy(1.0F);
        this.m_bIsUpsideDown = var2;

        if (!var2)
        {
            this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        }
        else
        {
            this.InitBlockBounds(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
        }

        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("fcBlockWoolSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcWoolSlab.blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_bIsUpsideDown;
    }

    public boolean GetIsUpsideDown(int var1)
    {
        return this.m_bIsUpsideDown;
    }

    public void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (this.m_bIsUpsideDown != var5)
        {
            int var6 = FCBetterThanWolves.fcBlockWoolSlabTop.blockID;
            int var7 = var1.getBlockMetadata(var2, var3, var4);

            if (this.blockID == FCBetterThanWolves.fcBlockWoolSlabTop.blockID)
            {
                var6 = FCBetterThanWolves.fcWoolSlab.blockID;
            }

            var1.setBlockAndMetadataWithNotify(var2, var3, var4, var6, var7);
        }
    }

    public int SetIsUpsideDown(int var1, boolean var2)
    {
        return var1;
    }

    public int GetCombinedBlockID(int var1)
    {
        return Block.cloth.blockID;
    }

    public int GetCombinedMetadata(int var1)
    {
        return var1;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.m_IconByColorArray = new Icon[16];

        for (int var2 = 0; var2 < this.m_IconByColorArray.length; ++var2)
        {
            this.m_IconByColorArray[var2] = var1.registerIcon("cloth_" + var2);
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconByColorArray[var2 % this.m_IconByColorArray.length];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        if (!this.m_bIsUpsideDown)
        {
            for (int var4 = 0; var4 < 16; ++var4)
            {
                var3.add(new ItemStack(var1, 1, var4));
            }
        }
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }
}
