package net.minecraft.src;

public class FCItemPileSoulSand extends Item
{
    public FCItemPileSoulSand(int var1)
    {
        super(var1);
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(8);
        this.setUnlocalizedName("fcItemPileSoulSand");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var2.isRemote)
        {
            boolean var4 = false;
            double var5 = var3.posX;
            double var7 = var3.posZ;

            if (var2.provider.dimensionId == 0)
            {
                FCSpawnLocationList var9 = var2.GetSpawnLocationList();
                FCSpawnLocation var10 = var9.GetClosestSpawnLocationForPosition(var3.posX, var3.posZ);

                if (var10 != null)
                {
                    var5 = (double)var10.m_iIPos;
                    var7 = (double)var10.m_iKPos;
                    var4 = true;
                }
            }
            else if (var2.provider.dimensionId == -1)
            {
                IChunkProvider var13 = var2.getChunkProvider();

                if (var13 != null && var13 instanceof ChunkProviderServer)
                {
                    ChunkProviderServer var15 = (ChunkProviderServer)var13;
                    var13 = var15.GetCurrentProvider();

                    if (var13 != null && var13 instanceof ChunkProviderHell)
                    {
                        ChunkProviderHell var11 = (ChunkProviderHell)var13;
                        StructureStart var12 = var11.genNetherBridge.GetClosestStructureWithinRangeSq(var3.posX, var3.posZ, 90000.0D);

                        if (var12 != null)
                        {
                            var5 = (double)var12.boundingBox.getCenterX();
                            var7 = (double)var12.boundingBox.getCenterZ();
                            var4 = true;
                        }
                    }
                }
            }

            FCEntitySoulSand var14 = new FCEntitySoulSand(var2, var3.posX, var3.posY + 2.0D - (double)var3.yOffset, var3.posZ);
            var14.MoveTowards(var5, var7);
            var2.spawnEntityInWorld(var14);

            if (var4)
            {
                var2.playAuxSFX(2228, (int)Math.round(var14.posX), (int)Math.round(var14.posY), (int)Math.round(var14.posZ), 0);
            }

            if (!var3.capabilities.isCreativeMode)
            {
                --var1.stackSize;
            }
        }

        return var1;
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 4;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return Block.slowSand.blockID;
    }
}
