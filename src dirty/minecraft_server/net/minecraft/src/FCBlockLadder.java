package net.minecraft.src;

public class FCBlockLadder extends FCBlockLadderBase
{
    protected FCBlockLadder(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockLadder");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBeSetOnFireDirectlyByItem(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLadderOnFire.SetFacing(0, this.GetFacing(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLadderOnFire.blockID, var5);
        return true;
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 60;
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (!var1.isRemote)
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        }
    }
}
