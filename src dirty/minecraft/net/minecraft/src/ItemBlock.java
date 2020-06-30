package net.minecraft.src;

import java.util.List;

public class ItemBlock extends FCItemPlacesAsBlock
{
    /** The block ID of the Block associated with this ItemBlock */
    private int blockID;
    private Icon field_94588_b;
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
     * Returns 0 for /terrain.png, 1 for /gui/items.png
     */
    public int getSpriteNumber()
    {
        return Block.blocksList[this.blockID].getItemIconName() != null ? 1 : 0;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return this.field_94588_b != null ? this.field_94588_b : Block.blocksList[this.blockID].getBlockTextureFromSide(1);
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

    /**
     * gets the CreativeTab this item is displayed on
     */
    public CreativeTabs getCreativeTab()
    {
        return Block.blocksList[this.blockID].getCreativeTabToDisplayOn();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        Block.blocksList[this.blockID].getSubBlocks(par1, par2CreativeTabs, par3List);
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        String var2 = Block.blocksList[this.blockID].getItemIconName();

        if (var2 != null)
        {
            this.field_94588_b = par1IconRegister.registerIcon(var2);
        }
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

    public Icon GetHopperFilterIcon()
    {
        return Block.blocksList[this.blockID].GetHopperFilterIcon();
    }
}
