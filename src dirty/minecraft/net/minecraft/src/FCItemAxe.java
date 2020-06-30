package net.minecraft.src;

public class FCItemAxe extends FCItemTool
{
    protected FCItemAxe(int var1, EnumToolMaterial var2)
    {
        super(var1, 3, var2);
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        int var7 = this.toolMaterial.getHarvestLevel();
        int var8 = var3.GetEfficientToolLevel(var2, var4, var5, var6);
        return var8 > var7 ? 1.0F : (var3.GetIsProblemToRemove(var2, var4, var5, var6) ? 1.0F : super.getStrVsBlock(var1, var2, var3, var4, var5, var6));
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        int var7 = this.toolMaterial.getHarvestLevel();
        int var8 = var3.GetHarvestToolLevel(var2, var4, var5, var6);
        return var8 > var7 ? false : (var3.GetIsProblemToRemove(var2, var4, var5, var6) ? false : (this.IsToolTypeEfficientVsBlockType(var3) ? true : super.canHarvestBlock(var1, var2, var3, var4, var5, var6)));
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        int var7 = this.toolMaterial.getHarvestLevel();
        int var8 = var3.GetEfficientToolLevel(var2, var4, var5, var6);
        return var8 > var7 ? false : (var3.GetIsProblemToRemove(var2, var4, var5, var6) ? false : super.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6));
    }

    public boolean IsConsumedInCrafting()
    {
        return this.toolMaterial.getHarvestLevel() <= 2;
    }

    public boolean IsDamagedInCrafting()
    {
        return this.toolMaterial.getHarvestLevel() <= 2;
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        PlayChopSoundOnPlayer(var1);
    }

    public void OnBrokenInCrafting(EntityPlayer var1)
    {
        PlayBreakSoundOnPlayer(var1);
    }

    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7)
    {
        if (!this.GetIsDamagedByVegetation())
        {
            Block var8 = Block.blocksList[var3];

            if (var8 != null && var8.blockMaterial.GetAxesTreatAsVegetation())
            {
                return true;
            }
        }

        return super.onBlockDestroyed(var1, var2, var3, var4, var5, var6, var7);
    }

    public float GetExhaustionOnUsedToHarvestBlock(int var1, World var2, int var3, int var4, int var5, int var6)
    {
        if (!this.GetConsumesHungerOnZeroHardnessVegetation())
        {
            Block var7 = Block.blocksList[var1];

            if (var7 != null && (double)var7.getBlockHardness(var2, var3, var4, var5) == 0.0D && var7.blockMaterial.GetAxesTreatAsVegetation())
            {
                return 0.0F;
            }
        }

        return super.GetExhaustionOnUsedToHarvestBlock(var1, var2, var3, var4, var5, var6);
    }

    public boolean IsToolTypeEfficientVsBlockType(Block var1)
    {
        return var1.blockMaterial.GetAxesEfficientOn() || var1.AreAxesEffectiveOn();
    }

    protected boolean CanToolStickInBlock(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        return var2.blockMaterial != FCBetterThanWolves.fcMaterialLog && var2.blockMaterial != FCBetterThanWolves.fcMaterialPlanks ? super.CanToolStickInBlock(var1, var2, var3, var4, var5, var6) : true;
    }

    protected void PlayPlacementSound(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
    {
        var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), "mob.zombie.woodbreak", 0.25F, 1.25F + var3.rand.nextFloat() * 0.25F);
    }

    public boolean GetConsumesHungerOnZeroHardnessVegetation()
    {
        return this.toolMaterial.getHarvestLevel() <= 1;
    }

    public boolean GetIsDamagedByVegetation()
    {
        return this.toolMaterial.getHarvestLevel() <= 2;
    }

    public static void PlayChopSoundOnPlayer(EntityPlayer var0)
    {
        if (var0.m_iTimesCraftedThisTick == 0)
        {
            var0.playSound("mob.zombie.wood", 0.5F, 2.5F);
        }
    }

    public static void PlayBreakSoundOnPlayer(EntityPlayer var0)
    {
        var0.playSound("random.break", 0.8F, 0.8F + var0.worldObj.rand.nextFloat() * 0.4F);
    }
}
