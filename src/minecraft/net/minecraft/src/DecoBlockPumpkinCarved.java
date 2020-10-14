package net.minecraft.src;

import java.util.Random;

public class DecoBlockPumpkinCarved extends Block{
	protected DecoBlockPumpkinCarved(int id)
	{
		super(id, Material.pumpkin);
		this.setHardness(1.0F);
		this.SetAxesEffectiveOn(true);
		this.SetBuoyant();
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("pumpkin");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World var1, int var2, int var3, int var4) {}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
	{
		int var5 = var1.getBlockId(var2, var3, var4);
		return var5 == 0 || blocksList[var5].blockMaterial.isReplaceable();
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int var1, Random var2, int var3)
	{
		return 0;
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
	{
		super.breakBlock(var1, var2, var3, var4, var5, var6);

		if (!var1.isRemote)
		{
			var1.playAuxSFX(2251, var2, var3, var4, 0);
		}
	}

	public int RotateMetadataAroundJAxis(int meta, boolean var2)
	{
		int damage = meta % 4;
		int facing = meta / 4;
		int newFacing = (facing - 1) % 4;
		
		return damage + newFacing * 4;
	}

	public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
	{
		return var5.CanGrazeOnRoughVegetation();
	}

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int var7 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        int damage = par6ItemStack.getItemDamage();
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 * 4 + damage, 2);
    }

    //CLIENT ONLY
    public Icon sideIcon;
    public Icon topIcon;
    public Icon[] faces;
    
    public Icon getIcon(int side, int meta)
    {
    	int	damage = meta % 4;
    	int facing = meta / 4;
    	
    	switch (side) {
    	case 0:
    	case 1:
    		return topIcon;
    	case 2:
    		return facing == 2 ? faces[damage] : sideIcon;
    	case 3:
    		return facing == 0 ? faces[damage] : sideIcon;
    	case 4:
    		return facing == 1 ? faces[damage] : sideIcon;
    	case 5:
    		return facing == 3 ? faces[damage] : sideIcon;
    	default:
    		return sideIcon;
    	}
    }
    
    public void registerIcons(IconRegister register)
    {
    	faces = new Icon[3];
    	faces[0] = register.registerIcon("decoBlockPumpkin_0");
    	faces[1] = register.registerIcon("decoBlockPumpkin_1");
    	faces[2] = register.registerIcon("decoBlockPumpkin_2");
        this.topIcon = register.registerIcon("pumpkin_top");
        this.sideIcon = register.registerIcon("pumpkin_side");
    }
}