package net.minecraft.src;

public class DecoBlockTrapDoorIron extends DecoBlockTrapDoor {

	public DecoBlockTrapDoorIron(int id) {
		super(id);
		this.blockMaterial = Material.iron;
		this.setUnlocalizedName("decoBlockTrapdoorIron");
		this.setStepSound(soundMetalFootstep);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		return false;
	}

    public void onPoweredBlockChange(World world, int x, int y, int z, boolean par5)
    {
        int var6 = world.getBlockMetadata(x, y, z);
        boolean var7 = (var6 & 4) > 0;

        if (var7 != par5)
        {
            world.setBlockMetadataWithNotify(x, y, z, var6 ^ 4);
            if (!world.isRemote) {
            	if ((var6 < 4) || (var6 >= 8 && var6 < 12))
            		world.playAuxSFX(DecoManager.decoTrapdoorIronOpenAuxFXID, x, y, z, 0);
            	else
            		world.playAuxSFX(DecoManager.decoTrapdoorIronCloseAuxFXID, x, y, z, 0);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);

            boolean var9 = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);

            if (var9 || par5 > 0 && Block.blocksList[par5].canProvidePower())
            {
                this.onPoweredBlockChange(par1World, par2, par3, par4, var9);
            }
        }
    }
}