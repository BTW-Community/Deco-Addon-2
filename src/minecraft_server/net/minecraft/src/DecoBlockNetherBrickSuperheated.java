package net.minecraft.src;

import java.util.Random;

public class DecoBlockNetherBrickSuperheated extends Block {
	public DecoBlockNetherBrickSuperheated(int id) {
        super(id, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("decoBlockNetherBrickSuperheated");
        this.setTickRandomly(true);
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var3 == 0 ? FCBetterThanWolves.fcBlockNetherBrickLoose.blockID : DecoDefs.netherBrickRedLoose.blockID;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, Block.brick.blockID);
			world.playAuxSFX(2227, x, y, z, 0);
		}
		
		int meta = world.getBlockMetadata(x, y, z);
		int idBelow = world.getBlockId(x, y - 1, z);
		int idCooled = meta == 0 ? Block.netherBrick.blockID : DecoDefs.netherBrick.blockID;
		
		if (idBelow != FCBetterThanWolves.fcBlockFireStoked.blockID) {
			world.setBlockWithNotify(x, y, z, idCooled);
		}
	}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, Block.brick.blockID);
			world.playAuxSFX(2227, x, y, z, 0);
		}
		
		int meta = world.getBlockMetadata(x, y, z);
		int idBelow = world.getBlockId(x, y - 1, z);
		int idCooled = meta == 0 ? Block.netherBrick.blockID : DecoDefs.netherBrick.blockID;
		
		if (idBelow != FCBetterThanWolves.fcBlockFireStoked.blockID) {
			world.setBlockWithNotify(x, y, z, idCooled);
		}
    }

	public void onBlockAdded(World world, int x, int y, int z) {
		if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, Block.brick.blockID);
			world.playAuxSFX(2227, x, y, z, 0);
		}
	}

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        par2EntityPlayer.AddHarvestBlockExhaustion(this.blockID, par3, par4, par5, par6);

        if (this.canSilkHarvest(par6) && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer))
        {
            int var5 = par1World.getBlockMetadata(par3, par4, par5);
            ItemStack var8 = new ItemStack(Block.netherBrick);
            ItemStack var9 = new ItemStack(DecoDefs.netherBrick);

            if (var8 != null)
            {
                this.dropBlockAsItem_do(par1World, par3, par4, par5, var5 == 0 ? var8 : var9);
            }
        }
        else
        {
            int var7 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
            this.dropBlockAsItem(par1World, par3, par4, par5, par6, var7);
        }
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        ItemStack var8 = new ItemStack(Block.netherBrick);
        ItemStack var9 = new ItemStack(DecoDefs.netherBrick);

        return var5 == 0 ? var8 : var9;
    }
}
