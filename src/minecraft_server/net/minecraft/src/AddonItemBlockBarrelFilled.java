package net.minecraft.src;

public class AddonItemBlockBarrelFilled  extends ItemMultiTextureTile
{
    public AddonItemBlockBarrelFilled(int var1, Block var2, String[] var3, String name)
    {
        super(var1, var2, var3);
    }

	public void OnUsedInCrafting(int var1, EntityPlayer var2, ItemStack var3)
    {
        if (!var2.worldObj.isRemote)
        {
            if (this.m_iBlockID == AddonDefs.barrelFullOak.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(AddonDefs.barrelEmpty, 1, 0));
            }
            else if (this.m_iBlockID == AddonDefs.barrelFullSpruce.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(AddonDefs.barrelEmpty, 1, 1));
            }
            else if (this.m_iBlockID == AddonDefs.barrelFullBirch.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(AddonDefs.barrelEmpty, 1, 2));
            }
            else if (this.m_iBlockID == AddonDefs.barrelFullJungle.blockID) {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(AddonDefs.barrelEmpty, 1, 3));
            }
        }
    }
}
