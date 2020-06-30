package net.minecraft.src;

public class FCItemPickaxe extends FCItemTool
{
    protected FCItemPickaxe(int var1, EnumToolMaterial var2)
    {
        super(var1, 2, var2);
    }

    protected FCItemPickaxe(int var1, EnumToolMaterial var2, int var3)
    {
        super(var1, 2, var2);
        this.setMaxDamage(var3);
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        int var7 = this.toolMaterial.getHarvestLevel();
        int var8 = var3.GetHarvestToolLevel(var2, var4, var5, var6);
        return var8 > var7 ? false : (var3 == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (var3 != Block.blockDiamond && var3 != Block.blockEmerald && var3 != Block.blockGold ? (var3 != Block.blockIron && var3 != Block.blockLapis ? var3.blockMaterial == Material.rock || var3.blockMaterial == Material.iron || var3.blockMaterial == Material.anvil || var3.blockMaterial == FCBetterThanWolves.fcMaterialNetherRock : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2));
    }

    public boolean IsToolTypeEfficientVsBlockType(Block var1)
    {
        return var1.ArePicksEffectiveOn();
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        int var7 = this.toolMaterial.getHarvestLevel();
        int var8 = var3.GetEfficientToolLevel(var2, var4, var5, var6);

        if (var8 > var7)
        {
            return 1.0F;
        }
        else
        {
            Material var9 = var3.blockMaterial;
            return var9 != Material.iron && var9 != Material.rock && var3.blockMaterial != Material.anvil && var9 != FCBetterThanWolves.fcMaterialNetherRock ? super.getStrVsBlock(var1, var2, var3, var4, var5, var6) : this.efficiencyOnProperMaterial;
        }
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        int var7 = this.toolMaterial.getHarvestLevel();
        int var8 = var3.GetEfficientToolLevel(var2, var4, var5, var6);
        return var8 > var7 ? false : super.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6);
    }

    protected float GetVisualVerticalOffsetAsBlock()
    {
        return 0.72F;
    }

    protected float GetVisualHorizontalOffsetAsBlock()
    {
        return 0.35F;
    }

    protected float GetVisualRollOffsetAsBlock()
    {
        return 20.0F;
    }

    protected float GetBlockBoundingBoxHeight()
    {
        return 0.65F;
    }

    protected float GetBlockBoundingBoxWidth()
    {
        return 1.0F;
    }

    protected void PlayPlacementSound(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), "random.anvil_land", 0.5F, var3.rand.nextFloat() * 0.25F + 1.75F);
    }

    protected boolean CanToolStickInBlock(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        return var2.blockMaterial == Material.glass ? false : super.CanToolStickInBlock(var1, var2, var3, var4, var5, var6);
    }
}
