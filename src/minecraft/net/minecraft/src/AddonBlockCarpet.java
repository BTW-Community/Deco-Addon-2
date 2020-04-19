package net.minecraft.src;

import java.util.List;

public class AddonBlockCarpet extends Block {
	public AddonBlockCarpet(int id) {
		super(id, Material.cloth);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
        this.setHardness(0.8F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.CLOTH);
        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("carpet");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        return -0.9375F;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -0.9375F;
    }

    public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
    	return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    //Client ONLY
    @Override
    public Icon getIcon(int side, int meta) {
    	return Block.cloth.getIcon(side,  15 - meta);
    }

    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
    	return true;
    }
    
    public void registerIcons(IconRegister register) {}
}
