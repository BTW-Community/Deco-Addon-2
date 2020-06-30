package net.minecraft.src;

public class FCBlockLog extends BlockLog
{
    public static final String[] trunkTextureTypes = new String[] {"fcBlockTrunkOak", "fcBlockTrunkSpruce", "fcBlockTrunkBirch", "fcBlockTrunkJungle"};
    private Icon[] trunkIconArray;
    private Icon trunkTopIcon;

    protected FCBlockLog(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(1.25F);
        this.setResistance(3.33F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.SetCanBeCookedByKiln(true);
        this.SetItemIndexDroppedWhenCookedByKiln(263);
        this.SetItemDamageDroppedWhenCookedByKiln(1);
        this.SetFireProperties(FCEnumFlammability.LOGS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("log");
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        float var5 = super.getBlockHardness(var1, var2, var3, var4);
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (this.GetIsStump(var6))
        {
            var5 *= 3.0F;
        }

        return var5;
    }

    public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsStump(var1, var2, var3, var4);
    }

    public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsStump(var1, var2, var3, var4);
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        byte var8 = 0;
        int var10;

        if (this.GetIsStump(var7))
        {
            if (this.IsWorkStumpItemConversionTool(var1, var2, var3, var4, var5))
            {
                var2.playAuxSFX(2268, var3, var4, var5, 0);
                var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockWorkStump.blockID, var7 & 3);
                return true;
            }

            var10 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(var8);
        }
        else
        {
            int var9 = var7 >> 2 & 3;
            var10 = FCBetterThanWolves.fcBlockLogDamaged.SetOrientation(var8, var9);
        }

        var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockLogDamaged.blockID, var10);

        if (!var2.isRemote)
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, var7 & 3), var6);
        }

        return true;
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return !this.GetIsStump(var1, var2, var3, var4);
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 6, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, var5 & 3, var6);
        return true;
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.ConvertToSmouldering(var1, var2, var3, var4);
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = var1 & 12;

        if (var3 != 0)
        {
            if (var3 == 4)
            {
                var3 = 8;
            }
            else if (var3 == 8)
            {
                var3 = 4;
            }

            var1 = var1 & -13 | var3;
        }

        return var1;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return FCBlockPlanks.GetFurnaceBurnTimeByWoodType(var1) * 4;
    }

    public void ConvertToSmouldering(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLogSmouldering.SetIsStump(0, this.GetIsStump(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLogSmouldering.blockID, var5);
    }

    public boolean GetIsStump(int var1)
    {
        return (var1 & 12) == 12;
    }

    public boolean GetIsStump(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == Block.wood.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (this.GetIsStump(var6))
            {
                return true;
            }
        }

        return false;
    }

    public boolean IsDeadStump(World var1, int var2, int var3, int var4)
    {
        if (this.GetIsStump(var1, var2, var3, var4))
        {
            int var5 = var1.getBlockMetadata(var2, var3 + 1, var4);

            if (var5 != Block.wood.blockID)
            {
                return true;
            }
        }

        return false;
    }

    public boolean IsWorkStumpItemConversionTool(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 != null && var1.getItem() instanceof FCItemChisel)
        {
            int var6 = ((FCItemChisel)var1.getItem()).toolMaterial.getHarvestLevel();
            return var6 >= 2;
        }
        else
        {
            return false;
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return (var2 & 12) == 12 ? (var1 > 1 ? this.trunkIconArray[var2 & 3] : this.trunkTopIcon) : super.getIcon(var1, var2);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.trunkTopIcon = var1.registerIcon("fcBlockTrunkTop");
        this.trunkIconArray = new Icon[trunkTextureTypes.length];

        for (int var2 = 0; var2 < this.trunkIconArray.length; ++var2)
        {
            this.trunkIconArray[var2] = var1.registerIcon(trunkTextureTypes[var2]);
        }

        super.registerIcons(var1);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        return var1.renderBlockLog(this, var2, var3, var4);
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }
}
