package net.minecraft.src;

public class DecoItemShears extends FCItemShears {
    public DecoItemShears(int id)
    {
        super(id);
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return !var3.blockMaterial.isToolNotRequired() && this.canHarvestBlock(var1, var2, var3, var4, var5, var6) ? true : var3 == Block.cloth || var3 == Block.leaves || var3 == FCBetterThanWolves.fcBlockBloodLeaves || var3 == FCBetterThanWolves.fcWoolSlab || var3 == FCBetterThanWolves.fcBlockWoolSlabTop || var3 == Block.vine || var3 == FCBetterThanWolves.fcBlockHempCrop || 
        		var3 == DecoDefs.carpet || var3 == DecoDefs.cherryLeaves || var3 == DecoDefs.acaciaLeaves || var3 == DecoDefs.autumnLeaves || var3 instanceof DecoBlockHedgeMouldingAndDecorative || var3 instanceof DecoBlockHedgeSidingAndCornerDecorativeWall || var3 instanceof DecoBlockHedge;
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return var3.blockID == DecoDefs.carpet.blockID || super.canHarvestBlock(var1, var2, var3, var4, var5, var6);
    }
}
