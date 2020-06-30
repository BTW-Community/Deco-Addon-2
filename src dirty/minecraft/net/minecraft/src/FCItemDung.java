package net.minecraft.src;

public class FCItemDung extends Item
{
    public FCItemDung(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemDung");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Called when a player right clicks an entity with an item.
     */
    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2)
    {
        if (var2 instanceof FCEntitySheep)
        {
            var2.attackEntityFrom(DamageSource.generic, 0);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 9;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID;
    }

    public int GetResultingBlockMetadataOnPistonPack(ItemStack var1)
    {
        return 7;
    }
}
