package net.minecraft.src;

public class FCBlockWorkStump extends Block
{
    protected FCBlockWorkStump(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(1.25F);
        this.SetFireProperties(FCEnumFlammability.LOGS);
        this.setUnlocalizedName("fcBlockWorkStump");
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        return super.getBlockHardness(var1, var2, var3, var4) * 3.0F;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0))
        {
            var5.displayGUIWorkbench(var2, var3, var4);
        }

        return true;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 6, 0, var6);
        return true;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcItemSawDust, 3, 0));
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(Block.planks.blockID, 1, 0));
        }
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        int var8 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(0);
        var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockLogDamaged.blockID, var8);

        if (!var2.isRemote)
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, var7 & 3), var6);
        }

        return true;
    }

    public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return Block.wood.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4);
    }
}
