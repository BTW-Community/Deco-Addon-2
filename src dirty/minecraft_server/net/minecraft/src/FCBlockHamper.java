package net.minecraft.src;

public class FCBlockHamper extends FCBlockBasket
{
    public static final FCModelBlockHamper m_model = new FCModelBlockHamper();

    protected FCBlockHamper(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockHamper");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityHamper();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && !FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0, true) && !FCUtilsWorld.IsBlockRestingOnThatBelow(var1, var2, var3 + 1, var4))
        {
            FCTileEntityHamper var10 = (FCTileEntityHamper)var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof EntityPlayerMP)
            {
                FCContainerHamper var11 = new FCContainerHamper(var5.inventory, var10);
                FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var11, FCBetterThanWolves.fcHamperContainerID);
            }
        }

        return true;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || var5 == 1 && !this.GetIsOpen(var1, var2, var3, var4);
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (!var1.isRemote)
        {
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemWickerPiece.itemID, 2, 0, 0.75F);
        }
    }

    public FCModelBlock GetLidModel(int var1)
    {
        return m_model.m_lid;
    }

    public Vec3 GetLidRotationPoint()
    {
        return m_model.GetLidRotationPoint();
    }
}
