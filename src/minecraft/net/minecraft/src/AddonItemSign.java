package net.minecraft.src;

import java.util.List;

public class AddonItemSign extends Item
{
    public static final int m_iNumSubtypes = 6;
    private String[] m_sNameExtensionsBySubtype = new String[] {"oak", "spruce", "birch", "jungle", "bloodwood", "cherry"};
    private Icon[] m_IconBySubtype = new Icon[m_iNumSubtypes];
    private String[] m_sIconNamesBySubtype = new String[] {"sign", "ginger_signSpruce", "ginger_signBirch", "ginger_signJungle", "ginger_signBlood", "ginger_signCherry"};
    
    private Block[] signPosts = {Block.signPost, AddonDefs.signSpruce, AddonDefs.signBirch, AddonDefs.signJungle, AddonDefs.signBlood, AddonDefs.signCherry};
    private Block[] signWalls = {Block.signWall, AddonDefs.signSpruceWall, AddonDefs.signBirchWall, AddonDefs.signJungleWall, AddonDefs.signBloodWall, AddonDefs.signCherryWall};
    
    public AddonItemSign(int par1)
    {
        super(par1);
        this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHasSubtypes(true);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
	    if (!world.isRemote)
	    	world.playAuxSFX(2252, x, y, z, signPosts[itemStack.getItemDamage()].blockID);
	    
        if (side == 0)
        {
            return false;
        }
        else if (!world.getBlockMaterial(x, y, z).isSolid())
        {
            return false;
        }
        else
        {
            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }

            if (!player.canPlayerEdit(x, y, z, side, itemStack))
            {
                return false;
            }
            else if (!signPosts[itemStack.getItemDamage()].canPlaceBlockAt(world, x, y, z))
            {
                return false;
            }
            else
            {
                if (side == 1)
                {
                    int var11 = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                    world.setBlock(x, y, z, signPosts[itemStack.getItemDamage()].blockID, var11, 2);
                }
                else
                {
                    world.setBlock(x, y, z, signWalls[itemStack.getItemDamage()].blockID, side, 2);
                }

                --itemStack.stackSize;

                return true;
            }
        }
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, m_iNumSubtypes - 1);
        return super.getUnlocalizedName() + "." + this.m_sNameExtensionsBySubtype[var2];
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < m_iNumSubtypes; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
    
    //CLIENT ONLY
    public void registerIcons(IconRegister var1)
    {
        for (int var2 = 0; var2 < this.m_sIconNamesBySubtype.length; ++var2)
        {
            this.m_IconBySubtype[var2] = var1.registerIcon(this.m_sIconNamesBySubtype[var2]);
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int var1)
    {
        int var2 = MathHelper.clamp_int(var1, 0, m_iNumSubtypes - 1);
        return this.m_IconBySubtype[var2];
    }
}
