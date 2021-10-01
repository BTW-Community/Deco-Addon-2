package net.minecraft.src;

import java.util.Random;

public class DecoBlockAsh extends Block {

	public DecoBlockAsh(int id) {
		super(id, DecoDefs.materialAshBlock);
		
		this.SetShovelsEffectiveOn();
        this.setHardness(0.5F);
        this.SetFilterableProperties(8);
        this.setStepSound(Block.soundSandFootstep);
        this.setUnlocalizedName("decoBlockAsh");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6) {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPotash.itemID, 6, 0, var6);
        return true;
    }
	
	@Override
    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4) {
        return true;
    }
	
	//CLIENT ONLY
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("fcBlockAshGroundCover");
	}

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
	@Override
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        super.randomDisplayTick(var1, var2, var3, var4, var5);

        if (var5.nextInt(10) == 0) {
            double var6 = (double)var3 + 1.125D;
            var1.spawnParticle("townaura", (double)var2 + var5.nextDouble(), var6, (double)var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}