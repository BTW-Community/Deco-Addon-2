package net.minecraft.src;

public class DecoItemInWorldManager extends ItemInWorldManager {
	public DecoItemInWorldManager(World par1World) {
		super(par1World);
	}

    /**
     * Removes a block and triggers the appropriate events
     */
    private boolean removeBlock(int par1, int par2, int par3)
    {
        Block var4 = Block.blocksList[this.theWorld.getBlockId(par1, par2, par3)];
        int var5 = this.theWorld.getBlockMetadata(par1, par2, par3);

        if (var4 != null)
        {
            var4.onBlockHarvested(this.theWorld, par1, par2, par3, var5, this.thisPlayerMP);
        }

        boolean var6 = this.theWorld.setBlockToAir(par1, par2, par3);

        if (var4 != null && var6)
        {
            var4.onBlockDestroyedByPlayer(this.theWorld, par1, par2, par3, var5);
            this.theWorld.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(this.thisPlayerMP, var4, par1, par2, par3);
        }

        return var6;
    }

    public boolean SurvivalTryHarvestBlock(int var1, int var2, int var3, int var4)
    {
        int var5 = this.theWorld.getBlockId(var1, var2, var3);
        Block var6 = Block.blocksList[var5];

        if (var6 == null)
        {
            return false;
        }
        else
        {
            int var7 = this.theWorld.getBlockMetadata(var1, var2, var3);
            ItemStack var8 = this.thisPlayerMP.getCurrentEquippedItem();
            boolean var9 = true;
            boolean var10 = false;
            boolean var11 = this.thisPlayerMP.canHarvestBlock(Block.blocksList[var5], var1, var2, var3);

            if (!var11)
            {
                var10 = var6.CanConvertBlock(var8, this.theWorld, var1, var2, var3);

                if (var10)
                {
                    this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, DecoManager.decoCustomBlockConvertAuxFXID, var1, var2, var3, var5 + (var7 << 12));
                    var9 = !var6.ConvertBlock(var8, this.theWorld, var1, var2, var3, var4);
                }
            }

            if (var9)
            {
                this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, DecoManager.decoCustomBlockBreakAuxFXID, var1, var2, var3, var5 + (var7 << 12));
                var9 = this.removeBlock(var1, var2, var3);

                if (var9 && !var10)
                {
                    if (var11)
                    {
                        Block.blocksList[var5].harvestBlock(this.theWorld, this.thisPlayerMP, var1, var2, var3, var7);
                    }
                    else
                    {
                        Block.blocksList[var5].OnBlockDestroyedWithImproperTool(this.theWorld, this.thisPlayerMP, var1, var2, var3, var7);
                    }
                }
            }

            if (var8 != null)
            {
                var8.onBlockDestroyed(this.theWorld, var5, var1, var2, var3, this.thisPlayerMP);

                if (var8.stackSize == 0)
                {
                    this.thisPlayerMP.destroyCurrentEquippedItem();
                }
            }

            return var9;
        }
    }
}