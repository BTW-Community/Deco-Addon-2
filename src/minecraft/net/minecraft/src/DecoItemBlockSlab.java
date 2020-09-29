package net.minecraft.src;

import java.util.List;

public class DecoItemBlockSlab extends FCItemBlockSlab {
	public DecoItemBlockSlab(int var1) {
		super(var1);
	}
	
	public void getSubItems(int var1, CreativeTabs var2, List var3)
	{
		for (int i = 0; i < ((DecoBlockSlabBase) Block.blocksList[var1]).blockTypes.length; ++i)
			var3.add(new ItemStack(this, 1, i));
	}
	
	public String getUnlocalizedName(ItemStack reference)
	{
		return super.getUnlocalizedName() + "." + reference.getItemDamage();
	}
	
    public int getMetadata(int var1)
    {
        return var1;
    }

    public boolean attemptToCombineWithBlock(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, boolean var8)
    {
        if (this.canCombineWithBlock(var3, var4, var5, var6, var1.getItemDamage()))
        {
            int var9 = var3.getBlockId(var4, var5, var6);
            Block var10 = Block.blocksList[var9];

            if (var10 != null && var10 instanceof DecoBlockSlabBase)
            {
            	DecoBlockSlabBase var11 = (DecoBlockSlabBase)var10;
                boolean var12 = var11.GetIsUpsideDown(var3, var4, var5, var6);

                if (!var8 || var7 == 1 && !var12 || var7 == 0 && var12)
                {
                    if (var3.checkNoEntityCollision(Block.GetFulBlockBoundingBoxFromPool(var3, var4, var5, var6)) && this.convertToFullBlock(var3, var4, var5, var6))
                    {
                        var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var11.stepSound.getStepSound(), (var11.stepSound.getVolume() + 1.0F) / 2.0F, var11.stepSound.getPitch() * 0.8F);
                        --var1.stackSize;
                        Block var13 = Block.blocksList[var3.getBlockId(var4, var5, var6)];

                        if (var13 != null)
                        {
                            var3.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(var2, var13, var4, var5, var6);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);

        if (var6 == this.getBlockID())
        {
            Block var7 = Block.blocksList[var6];

            if (var7 instanceof FCBlockSlab)
            {
                int var8 = ((DecoBlockSlabBase)var7).SetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4), false);

                if (var8 == var5)
                {
                    return true;
                }
            }
        }

        return false;
    }
}