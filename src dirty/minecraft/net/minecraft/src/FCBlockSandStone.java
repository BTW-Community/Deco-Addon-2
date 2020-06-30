package net.minecraft.src;

public class FCBlockSandStone extends BlockSandStone
{
    public FCBlockSandStone(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn();
        this.setHardness(1.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("sandStone");
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 16, 0, var6);
        return true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderStandardFullBlock(this, var2, var3, var4);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return true;
    }

    public void RenderBlockMovedByPiston(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.RenderStandardFullBlockMovedByPiston(this, var2, var3, var4);
    }
}
