package net.minecraft.src;

import java.util.Random;

public class AddonBlockPumpkinLit extends FCBlockJackOLantern {

	protected AddonBlockPumpkinLit(int id) {
		super(id);
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

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.CheckForExtinguish(var1, var2, var3, var4);
    }

	public int RotateMetadataAroundJAxis(int meta, boolean var2)
	{
		int damage = meta % 4;
		int facing = meta / 4;
		int newFacing = (facing - 1) % 4;
		
		return damage + newFacing * 4;
	}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.CheckForExtinguish(var1, var2, var3, var4);
    }

    private void CheckForExtinguish(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (this.HasWaterToSidesOrTop(var1, var2, var3, var4))
        {
            this.ExtinguishLantern(var1, var2, var3, var4);
        }
    }

    private void ExtinguishLantern(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, AddonDefs.pumpkin.blockID, var5);
        var1.playAuxSFX(2227, var2, var3, var4, 0);
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
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
    	faces[0] = register.registerIcon("ginger_pumpkin1_lit");
    	faces[1] = register.registerIcon("ginger_pumpkin2_lit");
    	faces[2] = register.registerIcon("ginger_pumpkin3_lit");
        this.topIcon = register.registerIcon("pumpkin_top");
        this.sideIcon = register.registerIcon("pumpkin_side");
    }
}
