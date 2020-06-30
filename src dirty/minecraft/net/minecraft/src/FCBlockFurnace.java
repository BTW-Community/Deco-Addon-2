package net.minecraft.src;

import java.util.Random;

public class FCBlockFurnace extends BlockFurnace
{
    protected Icon m_IconFullFront;
    protected Icon m_IconFullFrontLit;

    protected FCBlockFurnace(int var1, boolean var2)
    {
        super(var1, var2);
        this.setStepSound(soundStoneFootstep);
        this.setHardness(3.0F);
        this.setResistance(5.83F);

        if (!var2)
        {
            this.setCreativeTab(CreativeTabs.tabDecorations);
        }
        else
        {
            this.setLightValue(0.875F);
        }

        this.setUnlocalizedName("furnace");
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 12 + var1.nextInt(5);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemStone.itemID;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        var2.addExhaustion(0.025F);

        if (EnchantmentHelper.getSilkTouchModifier(var2))
        {
            ItemStack var7 = new ItemStack(this.IDDroppedSilkTouch(), 1, 0);

            if (var7 != null)
            {
                this.dropBlockAsItem_do(var1, var3, var4, var5, var7);
            }
        }
        else
        {
            int var8 = EnchantmentHelper.getFortuneModifier(var2);
            this.dropBlockAsItem(var1, var3, var4, var5, var6, var8);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.isActive;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = var1 & 7;
        var3 = Block.RotateFacingAroundJ(var3, var2);
        return var1 & -8 | var3;
    }

    protected int IDDroppedSilkTouch()
    {
        return Block.furnaceIdle.blockID;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = var2 & 7;
        boolean var4 = (var2 & 8) != 0;
        return var3 == var1 && var4 ? (this.isActive ? this.m_IconFullFrontLit : this.m_IconFullFront) : super.getIcon(var1, var3);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconFullFront = var1.registerIcon("fcBlockFurnaceFullFront");
        this.m_IconFullFrontLit = var1.registerIcon("fcBlockFurnaceFullFrontLit");
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.isActive && var5.nextInt(3) == 0)
        {
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "fire.fire", 0.25F + var5.nextFloat() * 0.25F, 0.5F + var5.nextFloat() * 0.25F, false);
        }

        super.randomDisplayTick(var1, var2, var3, var4, var5);
    }
}
