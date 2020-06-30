package net.minecraft.src;

import java.util.List;

public class FCBlockSlabSandAndGravel extends FCBlockSlabFalling
{
    public static final int m_iSubtypeGravel = 0;
    public static final int m_iSubtypeSand = 1;
    private Icon m_IconSand;

    public FCBlockSlabSandAndGravel(int var1)
    {
        super(var1, Material.sand);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("fcBlockSlabFalling");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        int var2 = this.GetSubtypeFromMetadata(var1);
        return var2;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 == 0)
        {
            var5 = 1.2F;
        }
        else if (var6 == 1)
        {
            var5 = 0.8F;
        }

        return var5;
    }

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 == 1 ? soundSandFootstep : this.stepSound;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (var5.blockID == this.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 == var5.metadata)
            {
                var1.setBlockWithNotify(var2, var3, var4, this.GetCombinedBlockID(var6));
                return true;
            }
        }

        return false;
    }

    public int GetCombinedBlockID(int var1)
    {
        return var1 == 1 ? Block.sand.blockID : Block.gravel.blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        int var7 = FCBetterThanWolves.fcItemPileGravel.itemID;

        if (this.GetSubtypeFromMetadata(var5) == 1)
        {
            var7 = FCBetterThanWolves.fcItemPileSand.itemID;
        }

        this.DropItemsIndividualy(var1, var2, var3, var4, var7, 3, 0, var6);
        return true;
    }

    public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetIsUpsideDown(int var1)
    {
        return false;
    }

    public void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5) {}

    public int SetIsUpsideDown(int var1, boolean var2)
    {
        return var1;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetFilterableProperties(ItemStack var1)
    {
        return var1.getItemDamage() == 0 ? 2 : 8;
    }

    public int GetSubtype(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetSubtypeFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetSubtypeFromMetadata(int var1)
    {
        return var1;
    }

    public void SetSubtype(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 0;
        var6 |= var5;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("gravel");
        this.m_IconSand = var1.registerIcon("sand");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = this.GetSubtypeFromMetadata(var2);
        return var3 == 1 ? this.m_IconSand : this.blockIcon;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
    }
}
