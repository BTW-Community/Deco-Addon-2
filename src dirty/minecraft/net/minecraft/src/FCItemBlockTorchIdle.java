package net.minecraft.src;

public class FCItemBlockTorchIdle extends ItemBlock
{
    public FCItemBlockTorchIdle(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockTorchIdle");
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        return this.IsPlayerClickingOnIgniter(var1, var3, var2) ? false : super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
    }

    public boolean GetCanItemBeSetOnFireOnUse(int var1)
    {
        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        return this.IsPlayerClickingOnIgniter(var1, var2, var3) ? this.OnRightClickOnIgniter(var1, var2, var3) : super.onItemRightClick(var1, var2, var3);
    }

    protected ItemStack OnRightClickOnIgniter(ItemStack var1, World var2, EntityPlayer var3)
    {
        int var4 = MathHelper.floor_double(var3.posX);
        int var5 = MathHelper.floor_double(var3.boundingBox.minY);
        int var6 = MathHelper.floor_double(var3.posZ);
        var3.playSound("mob.ghast.fireball", 1.0F, var2.rand.nextFloat() * 0.4F + 0.8F);
        ItemStack var7 = new ItemStack(FCBetterThanWolves.fcBlockTorchNetherBurning, var1.stackSize, 0);
        return var7;
    }

    protected boolean IsPlayerClickingOnIgniter(ItemStack var1, World var2, EntityPlayer var3)
    {
        return this.IsPlayerClickingOnSolidIgniter(var1, var2, var3) || this.IsPlayerClickingOnLavaOrFire(var1, var2, var3);
    }

    private boolean IsPlayerClickingOnSolidIgniter(ItemStack var1, World var2, EntityPlayer var3)
    {
        MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, true);

        if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE)
        {
            Block var5 = Block.blocksList[var2.getBlockId(var4.blockX, var4.blockY, var4.blockZ)];

            if (var5 != null && var5.GetCanBlockLightItemOnFire(var2, var4.blockX, var4.blockY, var4.blockZ))
            {
                return true;
            }
        }

        return false;
    }

    private boolean IsPlayerClickingOnLavaOrFire(ItemStack var1, World var2, EntityPlayer var3)
    {
        MovingObjectPosition var4 = FCUtilsMisc.GetMovingObjectPositionFromPlayerHitWaterAndLavaAndFire(var2, var3, true);

        if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE)
        {
            Material var5 = var2.getBlockMaterial(var4.blockX, var4.blockY, var4.blockZ);

            if (var5 == Material.lava || var5 == Material.fire)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of the given block position.
     */
    public boolean canPlaceItemBlockOnSide(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7)
    {
        return this.IsPlayerClickingOnIgniter(var7, var1, var6) ? true : super.canPlaceItemBlockOnSide(var1, var2, var3, var4, var5, var6, var7);
    }
}
