package net.minecraft.src;

public class DecoEntityVillager extends FCEntityVillager {
	public DecoEntityVillager(World var1) {
		super(var1);
	}

	public DecoEntityVillager(World var1, int var2) {
		super(var1, var2);
	}

    public FCEntityVillager func_90012_b(EntityAgeable var1)
    {
        FCEntityVillager var2 = new DecoEntityVillager(this.worldObj);
        var2.initCreature();
        return var2;
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
    	if (var1.getCurrentEquippedItem() == null || var1.getCurrentEquippedItem().itemID != DecoDefs.nameTag.itemID)
    		return super.interact(var1);
    	else
    		return false;
    }
}