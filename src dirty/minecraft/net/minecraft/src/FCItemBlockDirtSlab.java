package net.minecraft.src;

public class FCItemBlockDirtSlab extends FCItemBlockSlab
{
    public FCItemBlockDirtSlab(int var1)
    {
        super(var1);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1 << 1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        switch (var1.getItemDamage())
        {
            case 1:
                return super.getUnlocalizedName() + "." + "grass";

            case 3:
                return super.getUnlocalizedName() + "." + "packed";

            default:
                return super.getUnlocalizedName();
        }
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);

        if (var6 == FCBetterThanWolves.fcBlockDirtSlab.blockID)
        {
            int var7 = FCBetterThanWolves.fcBlockDirtSlab.GetSubtype(var1, var2, var3, var4);

            if (var7 != 3 && var5 != 3)
            {
                return true;
            }

            if (var7 == var5)
            {
                return true;
            }
        }
        else if (var6 == FCBetterThanWolves.fcBlockMyceliumSlab.blockID && var5 != 3)
        {
            return true;
        }

        return false;
    }

    public boolean convertToFullBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == FCBetterThanWolves.fcBlockDirtSlab.blockID)
        {
            FCBlockDirtSlab var6 = FCBetterThanWolves.fcBlockDirtSlab;
            boolean var7 = var6.GetIsUpsideDown(var1, var2, var3, var4);
            int var8 = var6.GetSubtype(var1, var2, var3, var4);
            int var9 = Block.dirt.blockID;
            byte var10 = 0;

            if (var8 == 3)
            {
                var9 = FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID;
                var10 = 6;
            }
            else if (var7 && var8 == 1)
            {
                var9 = Block.grass.blockID;
            }

            return var1.setBlockAndMetadataWithNotify(var2, var3, var4, var9, var10);
        }
        else
        {
            return var5 == FCBetterThanWolves.fcBlockMyceliumSlab.blockID ? (((FCBlockMyceliumSlab)FCBetterThanWolves.fcBlockMyceliumSlab).GetIsUpsideDown(var1, var2, var3, var4) ? var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.mycelium.blockID, 0) : var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.dirt.blockID, 0)) : false;
        }
    }
}
