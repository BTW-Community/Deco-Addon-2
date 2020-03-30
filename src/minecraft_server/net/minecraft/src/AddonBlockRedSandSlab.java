package net.minecraft.src;

import java.util.List;

public class AddonBlockRedSandSlab extends FCBlockSlabFalling
{
    public AddonBlockRedSandSlab(int var1)
    {
        super(var1, Material.sand);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(soundSandFootstep);
        this.setUnlocalizedName("ginger_redSandSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
    	return 0.8F;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (var5.blockID == this.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 == var5.metadata)
            {
                var1.setBlockWithNotify(var2, var3, var4, this.GetCombinedBlockID(var6));
                return true;
            }
        }

        return false;
    }

    public int GetCombinedBlockID(int var1)
    {
        return AddonDefs.redSand.blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, AddonDefs.pileRedSand.itemID, 3, 0, var6);
        return true;
    }

    public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetIsUpsideDown(int var1)
    {
        return false;
    }

    public void SetIsUpsideDown(World var1, int var2, int var3, int var4, boolean var5) {}

    public int SetIsUpsideDown(int var1, boolean var2)
    {
        return var1;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}