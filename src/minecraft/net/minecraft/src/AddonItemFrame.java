package net.minecraft.src;

public class AddonItemFrame extends ItemHangingEntity {

	public AddonItemFrame(int id) {
		super(id, AddonItemFrame.class);
	}

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 == 0)
        {
            return false;
        }
        else if (par7 == 1)
        {
            return false;
        }
        else
        {
            int var11 = Direction.facingToDirection[par7];
            EntityHanging entityHanging = this.createHangingEntity(world, par4, par5, par6, var11);

            if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
            {
                return false;
            }
            else
            {
                if (entityHanging != null && entityHanging.onValidSurface())
                {
                    if (!world.isRemote)
                    {
                        world.spawnEntityInWorld(entityHanging);
                    	AddonUtilsSound.playSoundAtEntityWithNullFallback(world, entityHanging, "deco.misc.itemFrame.place", 1, 1);
                    }

                    --par1ItemStack.stackSize;
                }

                return true;
            }
        }
    }

    /**
     * Create the hanging entity associated to this item.
     */
    private EntityHanging createHangingEntity(World world, int x, int y, int z, int par5)
    {
        return new AddonEntityItemFrame(world, x, y, z, par5);
    }
}