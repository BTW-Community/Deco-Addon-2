package net.minecraft.src;

public abstract class FCItemTool extends Item
{
    protected float efficiencyOnProperMaterial = 4.0F;
    protected int damageVsEntity;
    protected EnumToolMaterial toolMaterial;

    protected FCItemTool(int var1, int var2, EnumToolMaterial var3)
    {
        super(var1);
        this.maxStackSize = 1;
        this.toolMaterial = var3;
        this.setMaxDamage(var3.getMaxUses());
        this.efficiencyOnProperMaterial = var3.getEfficiencyOnProperMaterial();
        this.damageVsEntity = var2 + var3.getDamageVsEntity();

        if (this.toolMaterial == EnumToolMaterial.WOOD)
        {
            this.SetBuoyant();
            this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_TOOLS);
            this.SetIncineratedInCrucible();
        }

        this.SetInfernalMaxEnchantmentCost(this.toolMaterial.GetInfernalMaxEnchantmentCost());
        this.SetInfernalMaxNumEnchants(this.toolMaterial.GetInfernalMaxNumEnchants());
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        var1.damageItem(2, var3);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7)
    {
        if (Block.blocksList[var3].getBlockHardness(var2, var4, var5, var6) > 0.0F)
        {
            var1.damageItem(1, var7);
        }

        return true;
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity var1)
    {
        return this.damageVsEntity;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.digger ? true : super.IsEnchantmentApplicable(var1);
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return this.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(var1, var2, var3, var4, var5, var6);
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return !var3.blockMaterial.isToolNotRequired() && this.canHarvestBlock(var1, var2, var3, var4, var5, var6) ? true : this.IsToolTypeEfficientVsBlockType(var3);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            if (this.toolMaterial == EnumToolMaterial.WOOD)
            {
                var3.playSound("mob.zombie.woodbreak", 0.1F, 1.25F + var2.rand.nextFloat() * 0.25F);
            }
            else if (this.toolMaterial == EnumToolMaterial.STONE)
            {
                var3.playSound("random.anvil_land", 0.5F, var2.rand.nextFloat() * 0.25F + 1.75F);
            }
            else
            {
                var3.playSound("random.anvil_use", 0.5F, var2.rand.nextFloat() * 0.25F + 1.25F);
            }
        }

        super.onCreated(var1, var2, var3);
    }

    public boolean CanItemBeUsedByPlayer(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7)
    {
        return !var6.IsLocalPlayerAndHittingBlock();
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var2 != null && var2.canPlayerEdit(var4, var5, var6, var7, var1) && this.GetCanBePlacedAsBlock())
        {
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var4, var5, var6);
            FCUtilsBlockPos var12 = new FCUtilsBlockPos(var4, var5, var6);

            if (!FCUtilsWorld.IsReplaceableBlock(var3, var4, var5, var6))
            {
                var11.AddFacingAsOffset(var7);
            }
            else
            {
                var7 = 1;
                var12.AddFacingAsOffset(0);
            }

            if (FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var3, var12.i, var12.j, var12.k, var7, true) && FCBetterThanWolves.fcBlockToolPlaced.canPlaceBlockAt(var3, var11.i, var11.j, var11.k))
            {
                Block var13 = Block.blocksList[var3.getBlockId(var12.i, var12.j, var12.k)];

                if (var13 != null && this.CanToolStickInBlock(var1, var13, var3, var12.i, var12.j, var12.k))
                {
                    int var14;
                    int var15;

                    if (var7 >= 2)
                    {
                        var14 = Block.GetOppositeFacing(var7);
                        var15 = 2;
                    }
                    else
                    {
                        var14 = FCUtilsMisc.ConvertOrientationToFlatBlockFacing(var2);
                        var15 = Block.GetOppositeFacing(var7);
                    }

                    int var16 = FCBetterThanWolves.fcBlockToolPlaced.SetFacing(0, var14);
                    var16 = FCBetterThanWolves.fcBlockToolPlaced.SetVerticalOrientation(var16, var15);
                    var3.setBlockAndMetadataWithNotify(var11.i, var11.j, var11.k, FCBetterThanWolves.fcBlockToolPlaced.blockID, var16);
                    TileEntity var17 = var3.getBlockTileEntity(var11.i, var11.j, var11.k);

                    if (var17 != null && var17 instanceof FCTileEntityToolPlaced)
                    {
                        ((FCTileEntityToolPlaced)var17).SetToolStack(var1);

                        if (!var3.isRemote)
                        {
                            this.PlayPlacementSound(var1, var13, var3, var11.i, var11.j, var11.k);
                        }

                        --var1.stackSize;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected abstract boolean IsToolTypeEfficientVsBlockType(Block var1);

    public FCItemTool SetDamageVsEntity(int var1)
    {
        this.damageVsEntity = var1;
        return this;
    }

    protected boolean CanToolStickInBlock(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        return this.IsEfficientVsBlock(var1, var3, var2, var4, var5, var6);
    }

    protected void PlayPlacementSound(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var2.stepSound.getStepSound(), (var2.stepSound.getVolume() + 1.0F) / 2.0F, var2.stepSound.getPitch() * 0.8F);
    }

    protected boolean GetCanBePlacedAsBlock()
    {
        return true;
    }

    protected float GetVisualVerticalOffsetAsBlock()
    {
        return 0.75F;
    }

    protected float GetVisualHorizontalOffsetAsBlock()
    {
        return 0.5F;
    }

    protected float GetVisualRollOffsetAsBlock()
    {
        return 0.0F;
    }

    protected float GetBlockBoundingBoxHeight()
    {
        return 0.75F;
    }

    protected float GetBlockBoundingBoxWidth()
    {
        return 0.75F;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }
}
