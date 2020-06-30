package net.minecraft.src;

public class FCBlockBeacon extends BlockBeacon
{
    private static final long m_lRespawnCooldownDuration = 60L;

    protected FCBlockBeacon(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn(true);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityBeacon();
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCTileEntityBeacon var7 = (FCTileEntityBeacon)var1.getBlockTileEntity(var2, var3, var4);

        if (var7 != null)
        {
            FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, var7);

            if (var7.getLevels() > 0)
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.wither.death", 1.0F + var1.rand.nextFloat() * 0.1F, 1.0F + var1.rand.nextFloat() * 0.1F);
            }

            var7.SetPowerState(false, 0, 0);
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        FCTileEntityBeacon var10 = (FCTileEntityBeacon)var1.getBlockTileEntity(var2, var3, var4);

        if (var10 != null)
        {
            int var11 = var10.getPrimaryEffect();

            if (var11 == 22223)
            {
                int var12 = var10.getLevels();

                if (var12 > 0)
                {
                    if (!var1.isRemote && (var1.getWorldTime() < var5.m_lRespawnAssignmentCooldownTimer || var1.getWorldTime() - var5.m_lRespawnAssignmentCooldownTimer > 60L))
                    {
                        var5.AddRawChatMessage("You have been bound to this beacon");
                        ChunkCoordinates var13 = new ChunkCoordinates(var2, var3, var4);
                        var5.setSpawnChunk(var13, false, var1.provider.dimensionId);
                        var5.m_lRespawnAssignmentCooldownTimer = var1.getWorldTime();
                        var1.playAuxSFX(2225, var2, var3, var4, 1);
                        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.wither.spawn", 1.0F + var1.rand.nextFloat() * 0.1F, 1.0F + var1.rand.nextFloat() * 0.1F);
                    }

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 != 0 ? true : !var1.isBlockOpaqueCube(var2, var3, var4);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.RenderBlockBeacon(this, var2, var3, var4);
    }
}
