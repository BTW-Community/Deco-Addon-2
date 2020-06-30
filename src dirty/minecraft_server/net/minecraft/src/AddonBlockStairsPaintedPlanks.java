package net.minecraft.src;

public class AddonBlockStairsPaintedPlanks extends FCBlockStairs
{
	private int sawedIndex;
	
    protected AddonBlockStairsPaintedPlanks(int var1, Block var2, int var3, int sawedIndex)
    {
        super(var1, var2, var3);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.sawedIndex = sawedIndex;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }
    
    @Override
    public boolean OnBlockSawed(World var1, int var2, int var3, int var4) {
        super.OnBlockSawed(var1, var2, var3, var4);
        
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, AddonDefs.paintedPlanksMouldingAndDecorative[sawedIndex].blockID, 0);
    	
    	return true;
    }

	@Override
    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
		return AddonDefs.paintedPlanksSidingAndCorner[sawedIndex].blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return this.m_referenceBlock.GetFurnaceBurnTime(this.m_iReferenceBlockMetadata) * 3 / 4;
    }
}
