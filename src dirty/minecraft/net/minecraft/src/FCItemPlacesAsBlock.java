package net.minecraft.src;

public class FCItemPlacesAsBlock extends Item
{
    protected int m_iBlockID;
    protected int m_iBlockMetadata;
    protected boolean m_bRequireNoEntitiesInTargetBlock;

    public FCItemPlacesAsBlock(int var1, int var2)
    {
        super(var1);
        this.m_iBlockMetadata = 0;
        this.m_bRequireNoEntitiesInTargetBlock = false;
        this.m_iBlockID = var2;
    }

    public FCItemPlacesAsBlock(int var1, int var2, int var3)
    {
        this(var1, var2);
        this.m_iBlockMetadata = var3;
    }

    public FCItemPlacesAsBlock(int var1, int var2, int var3, String var4)
    {
        this(var1, var2, var3);
        this.setUnlocalizedName(var4);
    }

    protected FCItemPlacesAsBlock(int var1)
    {
        super(var1);
        this.m_iBlockMetadata = 0;
        this.m_bRequireNoEntitiesInTargetBlock = false;
        this.m_iBlockID = var1 + 256;
        this.m_iBlockMetadata = 0;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        int var11 = this.GetBlockIDToPlace(var1.getItemDamage(), var7, var8, var9, var10);

        if (var1.stackSize != 0 && (var2 == null || var2.canPlayerEdit(var4, var5, var6, var7, var1)) && (var5 != 255 || !Block.blocksList[var11].blockMaterial.isSolid()))
        {
            FCUtilsBlockPos var12 = new FCUtilsBlockPos(var4, var5, var6);
            int var13 = var3.getBlockId(var4, var5, var6);
            Block var14 = Block.blocksList[var13];

            if (var14 != null)
            {
                if (var14.IsGroundCover())
                {
                    var7 = 1;
                }
                else if (!var14.blockMaterial.isReplaceable())
                {
                    var12.AddFacingAsOffset(var7);
                }
            }

            if ((!this.m_bRequireNoEntitiesInTargetBlock || this.IsTargetFreeOfObstructingEntities(var3, var12.i, var12.j, var12.k)) && var3.canPlaceEntityOnSide(var11, var12.i, var12.j, var12.k, false, var7, var2, var1))
            {
                Block var15 = Block.blocksList[var11];
                int var16 = this.getMetadata(var1.getItemDamage());
                var16 = var15.onBlockPlaced(var3, var12.i, var12.j, var12.k, var7, var8, var9, var10, var16);
                var16 = var15.PreBlockPlacedBy(var3, var12.i, var12.j, var12.k, var16, var2);

                if (var3.setBlockAndMetadataWithNotify(var12.i, var12.j, var12.k, var11, var16))
                {
                    if (var3.getBlockId(var12.i, var12.j, var12.k) == var11)
                    {
                        var15.onBlockPlacedBy(var3, var12.i, var12.j, var12.k, var2, var1);
                        var15.onPostBlockPlaced(var3, var12.i, var12.j, var12.k, var16);
                        var3.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(var2, var15, var12.i, var12.j, var12.k);
                    }

                    this.PlayPlaceSound(var3, var12.i, var12.j, var12.k, var15);
                    --var1.stackSize;
                }

                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return this.m_iBlockMetadata;
    }

    public boolean CanItemBeUsedByPlayer(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7)
    {
        return this.canPlaceItemBlockOnSide(var1, var2, var3, var4, var5, var6, var7);
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var3, var4, var5, var6);
        int var8 = this.GetTargetFacingPlacedByBlockDispenser(var6);
        int var9 = this.GetBlockIDToPlace(var1.getItemDamage(), var8, 0.5F, 0.25F, 0.5F);
        Block var10 = Block.blocksList[var9];

        if (var10 != null && var2.canPlaceEntityOnSide(var9, var7.i, var7.j, var7.k, true, var8, (Entity)null, var1))
        {
            int var11 = this.getMetadata(var1.getItemDamage());
            var11 = var10.onBlockPlaced(var2, var7.i, var7.j, var7.k, var8, 0.5F, 0.25F, 0.5F, var11);
            var2.setBlockAndMetadataWithNotify(var7.i, var7.j, var7.k, var9, var11);
            var10.onPostBlockPlaced(var2, var7.i, var7.j, var7.k, var11);
            var2.playAuxSFX(2236, var3, var4, var5, var9);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return this.m_iBlockID;
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of the given block position.
     */
    public boolean canPlaceItemBlockOnSide(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7)
    {
        int var8 = var1.getBlockId(var2, var3, var4);
        Block var9 = Block.blocksList[var8];
        FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4);

        if (var9 != null)
        {
            if (var9.IsGroundCover())
            {
                var5 = 1;
            }
            else if (!var9.blockMaterial.isReplaceable())
            {
                var10.AddFacingAsOffset(var5);
            }
        }

        int var11 = this.GetBlockIDToPlace(var7.getItemDamage(), var5, 0.5F, 0.5F, 0.5F);
        return var1.canPlaceEntityOnSide(var11, var10.i, var10.j, var10.k, false, var5, (Entity)null, var7);
    }

    public FCItemPlacesAsBlock SetAssociatedBlockID(int var1)
    {
        this.m_iBlockID = var1;
        return this;
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        return this.getBlockID();
    }

    protected boolean IsTargetFreeOfObstructingEntities(World var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1));
        return var1.checkNoEntityCollision(var5);
    }

    protected void PlayPlaceSound(World var1, int var2, int var3, int var4, Block var5)
    {
        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, var5.stepSound.getPlaceSound(), (var5.stepSound.getVolume() + 1.0F) / 2.0F, var5.stepSound.getPitch() * 0.8F);
    }

    public int GetTargetFacingPlacedByBlockDispenser(int var1)
    {
        return Block.GetOppositeFacing(var1);
    }
}
