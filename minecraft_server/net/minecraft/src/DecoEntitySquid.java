package net.minecraft.src;

public class DecoEntitySquid extends FCEntitySquid {
	public DecoEntitySquid(World world) {
		super(world);
	}

	@Override
    protected String getLivingSound() {
    	return DecoManager.getNewSoundsInstalled() ? "deco.mob.squid.say" : "";
    }

    @Override
    protected String getHurtSound() {
    	return DecoManager.getNewSoundsInstalled() ? "deco.mob.squid.hurt" : "";
    }

    @Override
    protected String getDeathSound() {
    	return DecoManager.getNewSoundsInstalled() ? "deco.mob.squid.death" : "";
    }
    
    @Override
    protected void dropFewItems(boolean killedByPlayer, int lootingModifier) {
        int numInkSacs = this.rand.nextInt(3 + lootingModifier) + 1;

        for (int i = 0; i < numInkSacs; ++i) {
            entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0F);
        }
        
        int numPrismarine = this.rand.nextInt(3 + lootingModifier) + 1;

        for (int i = 0; i < numPrismarine; ++i) {
            entityDropItem(new ItemStack(DecoDefs.prismarine), 0F);
        }
        
        if (rand.nextInt(8) - lootingModifier <= 0) {
            dropItem( FCBetterThanWolves.fcItemMysteriousGland.itemID, 1 );
        }
    }
}