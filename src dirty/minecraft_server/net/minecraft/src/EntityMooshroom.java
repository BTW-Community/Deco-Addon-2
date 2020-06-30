package net.minecraft.src;

public class EntityMooshroom extends FCEntityCow
{
    public EntityMooshroom(World par1World)
    {
        super(par1World);
        this.texture = "/mob/redcow.png";
        this.setSize(0.9F, 1.3F);
    }

    public EntityMooshroom func_94900_c(EntityAgeable par1EntityAgeable)
    {
        return new EntityMooshroom(this.worldObj);
    }

    public FCEntityCow spawnBabyAnimal(EntityAgeable var1)
    {
        return this.func_94900_c(var1);
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.func_94900_c(par1EntityAgeable);
    }

    public void CheckForGrazeSideEffects(int var1, int var2, int var3) {}

    public void ConvertToMooshroom() {}

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            this.CheckForMyceliumSpread();
        }

        super.onLivingUpdate();
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.bowlEmpty.itemID && this.GotMilk())
        {
            this.attackEntityFrom(DamageSource.generic, 0);

            if (!this.worldObj.isRemote)
            {
                this.SetGotMilk(false);
                this.worldObj.playAuxSFX(2254, MathHelper.floor_double(this.posX), (int)this.posY, MathHelper.floor_double(this.posZ), 0);
            }

            if (var2.stackSize == 1)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Item.bowlSoup));
            }
            else if (par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bowlSoup)))
            {
                par1EntityPlayer.inventory.decrStackSize(par1EntityPlayer.inventory.currentItem, 1);
            }

            return true;
        }
        else
        {
            return this.EntityAnimalInteract(par1EntityPlayer);
        }
    }

    private void CheckForMyceliumSpread()
    {
        if (this.worldObj.provider.dimensionId != 1 && this.rand.nextInt(1000) == 0)
        {
            FCBlockMycelium.CheckForMyceliumSpreadToRandomBlockAround(this.worldObj, MathHelper.floor_double(this.posX), (int)this.posY - 1, MathHelper.floor_double(this.posZ));
        }
    }
}
