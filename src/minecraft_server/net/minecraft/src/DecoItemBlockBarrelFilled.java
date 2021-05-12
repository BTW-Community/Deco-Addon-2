package net.minecraft.src;

public class DecoItemBlockBarrelFilled  extends ItemMultiTextureTile
{
	private Block barrel;
	private int metadata;
	
    public DecoItemBlockBarrelFilled(int var1, Block var2, String[] var3, Block barrel, int metadata)
    {
        super(var1, var2, var3);
        this.maxStackSize = 16;
        this.barrel = barrel;
        this.metadata = metadata;
    }

	public void OnUsedInCrafting(int var1, EntityPlayer var2, ItemStack var3)
    {
        if (!var2.worldObj.isRemote)
        {
            FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(barrel, 1, metadata));
        }
    }
}
