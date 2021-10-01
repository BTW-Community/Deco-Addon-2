package net.minecraft.src;

public class DecoBlockStoneLooseStairs extends FCBlockMortarReceiverStairs
{
	public final int mortaredID;
	
    protected DecoBlockStoneLooseStairs(int var1, Block owner, Block mortared)
    {
        super(var1, owner, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("stoneLooseStairs");
        mortaredID = mortared.blockID;
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, mortaredID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
    
    //CLIENT ONLY
    public void registerIcons(IconRegister register) {}
}
