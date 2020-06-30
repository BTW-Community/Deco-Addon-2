package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class FCBlockEnderChest extends BlockEnderChest
{
    protected FCBlockEnderChest(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityEnderChest();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && !var1.isBlockNormalCube(var2, var3 + 1, var4))
        {
            FCTileEntityEnderChest var10 = (FCTileEntityEnderChest)var1.getBlockTileEntity(var2, var3, var4);

            if (var10 != null)
            {
                InventoryEnderChest var11 = null;
                int var12 = this.ComputeLevelOfEnderChestsAntenna(var1, var2, var3, var4);

                if (var12 == 0)
                {
                    var11 = var10.GetLocalEnderChestInventory();
                }
                else if (var12 == 1)
                {
                    var11 = var1.GetLocalLowPowerEnderChestInventory();
                }
                else if (var12 == 2)
                {
                    var11 = var1.GetLocalEnderChestInventory();
                }
                else if (var12 == 3)
                {
                    var11 = MinecraftServer.getServer().worldServers[0].worldInfo.GetGlobalEnderChestInventory();
                }
                else
                {
                    var11 = var5.getInventoryEnderChest();
                }

                if (var11 != null)
                {
                    var11.setAssociatedChest(var10);
                    var5.displayGUIChest(var11);

                    if (var12 >= 1)
                    {
                        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.endermen.stare", 0.25F * (float)var12, var1.rand.nextFloat() * 0.4F + 1.2F);
                    }
                }
            }
        }

        return true;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!var1.isRemote)
        {
            FCTileEntityEnderChest var7 = (FCTileEntityEnderChest)var1.getBlockTileEntity(var2, var3, var4);

            if (var7 != null)
            {
                InventoryEnderChest var8 = var7.GetLocalEnderChestInventory();

                if (var8 != null)
                {
                    FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, var8);
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    private int ComputeLevelOfEnderChestsAntenna(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcAestheticOpaque.blockID && var1.getBlockMetadata(var2, var3 - 1, var4) == 14 ? this.GetLevelOfAntennaBeaconBlockIsPartOf(var1, var2, var3 - 1, var4) : 0;
    }

    private int GetLevelOfAntennaBeaconBlockIsPartOf(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 4; var5 >= 1; --var5)
        {
            int var6 = var3 + var5;

            for (int var7 = var2 - var5; var7 <= var2 + var5; ++var7)
            {
                for (int var8 = var4 - var5; var8 <= var4 + var5; ++var8)
                {
                    if (var1.getBlockId(var7, var6, var8) == Block.beacon.blockID)
                    {
                        FCTileEntityBeacon var9 = (FCTileEntityBeacon)var1.getBlockTileEntity(var7, var6, var8);

                        if (var9 != null && var9.getLevels() >= var5)
                        {
                            return var5;
                        }
                    }
                }
            }
        }

        return 0;
    }
}
