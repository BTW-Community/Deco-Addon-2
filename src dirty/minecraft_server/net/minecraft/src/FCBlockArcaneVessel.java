package net.minecraft.src;

public class FCBlockArcaneVessel extends FCBlockVessel
{
    public FCBlockArcaneVessel(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(3.5F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn(true);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockArcaneVessel");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityArcaneVessel();
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var1.getBlockTileEntity(var2, var3, var4);

        if (var7 != null && var7 instanceof FCTileEntityArcaneVessel)
        {
            FCTileEntityArcaneVessel var8 = (FCTileEntityArcaneVessel)var7;
            var8.EjectContentsOnBlockBreak();
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && var5 instanceof EntityXPOrb)
        {
            this.OnEntityXPOrbCollidedWithBlock(var1, var2, var3, var4, (EntityXPOrb)var5);
        }
    }

    private void OnEntityXPOrbCollidedWithBlock(World var1, int var2, int var3, int var4, EntityXPOrb var5)
    {
        if (!var5.isDead)
        {
            if (!this.GetMechanicallyPoweredFlag(var1, var2, var3, var4))
            {
                float var6 = 1.0F;
                AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB((double)((float)var2), (double)((float)var3 + 1.0F), (double)((float)var4), (double)((float)(var2 + 1)), (double)((float)var3 + 1.0F + 0.05F), (double)((float)(var4 + 1)));

                if (var5.boundingBox.intersectsWith(var7))
                {
                    boolean var8 = false;
                    TileEntity var9 = var1.getBlockTileEntity(var2, var3, var4);

                    if (var9 != null && var9 instanceof FCTileEntityArcaneVessel)
                    {
                        FCTileEntityArcaneVessel var10 = (FCTileEntityArcaneVessel)var9;

                        if (var10.AttemptToSwallowXPOrb(var1, var2, var3, var4, var5))
                        {
                            var1.playAuxSFX(2231, var2, var3, var4, 0);
                        }
                    }
                }
            }
        }
    }
}
