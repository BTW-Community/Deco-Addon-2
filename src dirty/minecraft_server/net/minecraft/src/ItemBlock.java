package net.minecraft.src;

public class ItemBlock extends FCItemPlacesAsBlock
{
    /** The block ID of the Block associated with this ItemBlock */
    private int blockID;
    private boolean m_bHasOldNamePrefix = false;

    public ItemBlock(int par1)
    {
        super(par1);
        this.blockID = par1 + 256;
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return this.blockID;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return Block.blocksList[this.blockID].getUnlocalizedName();
    }

    /**
     * Returns the unlocalized name of this item.
     */
    public String getUnlocalizedName()
    {
        return Block.blocksList[this.blockID].getUnlocalizedName();
    }

    public float GetBuoyancy(int var1)
    {
        return Block.blocksList[this.blockID].GetBuoyancy(var1);
    }

    public Item SetBuoyancy(float var1)
    {
        Block.blocksList[this.blockID].SetBuoyancy(var1);
        return super.SetBuoyancy(var1);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return Block.blocksList[this.blockID].IsPistonPackable(var1);
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return Block.blocksList[this.blockID].GetRequiredItemCountToPistonPack(var1);
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return Block.blocksList[this.blockID].GetResultingBlockIDOnPistonPack(var1);
    }

    public int GetResultingBlockMetadataOnPistonPack(ItemStack var1)
    {
        return Block.blocksList[this.blockID].GetResultingBlockMetadataOnPistonPack(var1);
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return Block.blocksList[this.blockID].GetFurnaceBurnTime(var1);
    }

    public Item SetFurnaceBurnTime(int var1)
    {
        Block.blocksList[this.blockID].SetFurnaceBurnTime(var1);
        return super.SetFurnaceBurnTime(var1);
    }

    public int GetHerbivoreFoodValue(int var1)
    {
        return Block.blocksList[this.blockID].GetHerbivoreItemFoodValue(var1);
    }

    public Item SetHerbivoreFoodValue(int var1)
    {
        Block.blocksList[this.blockID].SetHerbivoreItemFoodValue(var1);
        return super.SetHerbivoreFoodValue(var1);
    }

    public int GetChickenFoodValue(int var1)
    {
        return Block.blocksList[this.blockID].GetChickenItemFoodValue(var1);
    }

    public Item SetChickenFoodValue(int var1)
    {
        Block.blocksList[this.blockID].SetChickenItemFoodValue(var1);
        return super.SetChickenFoodValue(var1);
    }

    public int GetPigFoodValue(int var1)
    {
        return Block.blocksList[this.blockID].GetPigItemFoodValue(var1);
    }

    public Item SetPigFoodValue(int var1)
    {
        Block.blocksList[this.blockID].SetPigItemFoodValue(var1);
        return super.SetPigFoodValue(var1);
    }

    public boolean IsIncineratedInCrucible()
    {
        return Block.blocksList[this.blockID].IsIncineratedInCrucible();
    }

    public FCItemPlacesAsBlock SetAssociatedBlockID(int var1)
    {
        this.blockID = var1;
        return super.SetAssociatedBlockID(var1);
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        return Block.blocksList[this.blockID].CanItemPassIfFilter(var1);
    }

    public int GetFilterableProperties(ItemStack var1)
    {
        return Block.blocksList[this.blockID].GetFilterableProperties(var1);
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return Block.blocksList[this.blockID].CanTransformItemIfFilter(var1);
    }
}
