package net.minecraft.src;

public class FCBlockArcaneVessel extends FCBlockVessel
{
    private Icon m_IconContents;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockVessel_side");
        this.blockIcon = var2;
        this.m_IconWideBandBySideArray[0] = this.m_IconCenterColumnBySideArray[0] = var1.registerIcon("fcBlockVessel_bottom");
        this.m_IconCenterColumnBySideArray[1] = var1.registerIcon("fcBlockVessel_top");
        this.m_IconWideBandBySideArray[1] = var1.registerIcon("fcBlockVesselWideBand_top");
        this.m_IconWideBandBySideArray[2] = this.m_IconCenterColumnBySideArray[2] = var2;
        this.m_IconWideBandBySideArray[3] = this.m_IconCenterColumnBySideArray[3] = var2;
        this.m_IconWideBandBySideArray[4] = this.m_IconCenterColumnBySideArray[4] = var2;
        this.m_IconWideBandBySideArray[5] = this.m_IconCenterColumnBySideArray[5] = var2;
        this.m_IconInteriorBySideArray[0] = this.m_IconWideBandBySideArray[0];
        this.m_IconInteriorBySideArray[1] = this.m_IconWideBandBySideArray[0];
        this.m_IconInteriorBySideArray[2] = this.m_IconWideBandBySideArray[0];
        this.m_IconInteriorBySideArray[3] = this.m_IconWideBandBySideArray[0];
        this.m_IconInteriorBySideArray[4] = this.m_IconWideBandBySideArray[0];
        this.m_IconInteriorBySideArray[5] = this.m_IconWideBandBySideArray[0];
        this.m_IconContents = var1.registerIcon("fcBlockVessel_xp", new FCClientTextureVesselXP("fcBlockVessel_xp"));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        super.RenderBlock(var1, var2, var3, var4);
        IBlockAccess var5 = var1.blockAccess;

        if (this.GetFacing(var5, var2, var3, var4) == 1)
        {
            TileEntity var6 = var5.getBlockTileEntity(var2, var3, var4);

            if (var6 instanceof FCTileEntityArcaneVessel)
            {
                FCTileEntityArcaneVessel var7 = (FCTileEntityArcaneVessel)var6;
                int var8 = var7.GetVisualExperienceLevel();

                if (var8 > 0)
                {
                    float var9 = (float)var8 / 10.0F;
                    float var10 = 0.1875F;
                    float var11 = var10 + 0.0625F + (0.875F - (var10 + 0.0625F)) * var9;
                    var1.setRenderBounds(0.125D, (double)var10, 0.125D, 0.875D, (double)var11, 0.875D);
                    Tessellator var12 = Tessellator.instance;
                    var12.setBrightness(240);
                    var1.renderFaceYPos(this, (double)var2, (double)var3, (double)var4, this.m_IconContents);
                }
            }
        }

        return true;
    }
}
