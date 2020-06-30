package net.minecraft.src;

public class FCItemChisel extends FCItemTool
{
    protected FCItemChisel(int var1, EnumToolMaterial var2, int var3)
    {
        super(var1, 1, var2);
        this.setMaxDamage(var3);
        this.damageVsEntity = 1;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3)
    {
        return false;
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.CanChiselsHarvest() ? this.toolMaterial.getHarvestLevel() >= var3.GetHarvestToolLevel(var2, var4, var5, var6) : false;
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        if (var3.GetIsProblemToRemove(var2, var4, var5, var6))
        {
            return false;
        }
        else
        {
            int var7 = this.toolMaterial.getHarvestLevel();
            int var8 = var3.GetEfficientToolLevel(var2, var4, var5, var6);
            return var8 > var7 ? false : var3.AreChiselsEffectiveOn(var2, var4, var5, var6);
        }
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }

    protected boolean IsToolTypeEfficientVsBlockType(Block var1)
    {
        return var1.AreChiselsEffectiveOn();
    }

    protected boolean GetCanBePlacedAsBlock()
    {
        return false;
    }

    protected void PlayPlacementSound(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        if (((FCItemTool)pickaxeIron).IsToolTypeEfficientVsBlockType(var2))
        {
            var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), "random.anvil_land", 0.5F, var3.rand.nextFloat() * 0.25F + 1.75F);
        }
        else if (!((FCItemTool)axeIron).IsToolTypeEfficientVsBlockType(var2) && var2.blockMaterial != FCBetterThanWolves.fcMaterialLog && var2.blockMaterial != FCBetterThanWolves.fcMaterialPlanks)
        {
            super.PlayPlacementSound(var1, var2, var3, var4, var5, var6);
        }
        else
        {
            var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), "mob.zombie.woodbreak", 0.25F, 1.25F + var3.rand.nextFloat() * 0.25F);
        }
    }

    protected float GetVisualVerticalOffsetAsBlock()
    {
        return 0.45F;
    }

    protected float GetBlockBoundingBoxHeight()
    {
        return 0.3F;
    }

    protected float GetBlockBoundingBoxWidth()
    {
        return 0.5F;
    }
}
