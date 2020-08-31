package net.minecraft.src;

import java.util.Random;

public class DecoBlockNylium extends Block {

	public DecoBlockNylium(int id) {
		super(id, Material.rock);
        this.setHardness(0.6F);
        this.setResistance(0.6666667F);
        this.SetPicksEffectiveOn();
        this.setTickRandomly(true);
        
        //if (AddonManager.getNewSoundsInstalled())
        //	this.setStepSound(AddonDefs.stepSoundNylium);
        //else
        	this.setStepSound(soundStoneFootstep);
        
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("ginger_nylium");
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.netherrack.blockID;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand) {
    	int idAbove = world.getBlockId(x, y, z);
    	Block blockAbove = Block.blocksList[idAbove];
    	
    	if (blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)) {
    		world.setBlockWithNotify(x, y, z, Block.netherrack.blockID);
    	}
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    //CLIENT ONLY
    private Icon crimson_top;
    private Icon crimson_side;
    private Icon warped_top;
    private Icon warped_side;

    @Override
    public Icon getIcon(int side, int meta) {
    	if (side == 0) {
    		return Block.netherrack.blockIcon;
    	}
    	else if (side == 1) {
    		return meta == 0 ? crimson_top : warped_top;
    	}
    	else {
    		return meta == 0 ? crimson_side : warped_side;
    	}
    }
    
    @Override
    public void registerIcons(IconRegister register) {
    	crimson_top = register.registerIcon("ginger_nyliumCrimsonTop");
    	crimson_side = register.registerIcon("ginger_nyliumCrimsonSide");
    	warped_top = register.registerIcon("ginger_nyliumWarpedTop");
    	warped_side = register.registerIcon("ginger_nyliumWarpedSide");
    }
}