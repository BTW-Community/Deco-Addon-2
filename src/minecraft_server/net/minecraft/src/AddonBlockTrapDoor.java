package net.minecraft.src;

public class AddonBlockTrapDoor extends FCBlockTrapDoor {

	private boolean flagBetterPlaceRule;
	private boolean betterPlaceRuleIsLower;

	protected AddonBlockTrapDoor(int ID) {
		super(ID);
		this.setUnlocalizedName("trapdoor");
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (this.blockMaterial == Material.iron)
        {
            return true;
        }
        else
        {
            int var10 = world.getBlockMetadata(x, y, z);
            world.setBlockMetadataWithNotify(x, y, z, var10 ^ 4);
            //world.playAuxSFXAtEntity(player, 1003, x, y, z, 0);
            
            if ((var10 < 4) || (var10 >= 8 && var10 < 12))
            	AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
            else
            	AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.trapdoorClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
            
            return true;
        }
    }

	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		int var10 = 0;

		if (side == 2)
		{
			var10 = 0;
		}

		if (side == 3)
		{
			var10 = 1;
		}

		if (side == 4)
		{
			var10 = 2;
		}

		if (side == 5)
		{
			var10 = 3;
		}

		if (side != 1 && side != 0 && hitY > 0.5F)
		{
			var10 += 8;
		}

		if (side == 0 || side == 1) {
			flagBetterPlaceRule = true;
			betterPlaceRuleIsLower = (side == 1 ? true : false);
		}

		return var10;
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		if (flagBetterPlaceRule) {
			int var7 = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			int metadataMod = (betterPlaceRuleIsLower ? 0 : 8);

			if (var7 == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 0);
			}

			if (var7 == 1)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 3);
			}

			if (var7 == 2)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 1);
			}

			if (var7 == 3)
			{
				world.setBlockMetadataWithNotify(x, y, z, metadataMod + 2);
			}

			flagBetterPlaceRule = false;
		}
	}

	public boolean IsBlockClimbable(World var1, int var2, int var3, int var4)
	{
		return var1.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcBlockLadder.blockID || var1.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcBlockLadderOnFire.blockID;
	}
	
	public boolean GetCanGrassGrowUnderBlock(World world, int x, int y, int z, boolean var5) {
		return true;
	}
	
	@Override
    public boolean HasLargeCenterHardPointToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing, boolean var6) {
		int metadata = blockAccess.getBlockMetadata(x,  y,  z);
		int facingOpposite = Facing.oppositeSide[facing];
		
		switch (facingOpposite) {
		case 0:
			return metadata >= 8 && metadata < 12;
		case 1:
			return metadata < 4;
		case 2:
			return metadata == 4 || metadata == 12;
		case 3:
			return metadata == 5 || metadata == 13;
		case 4:
			return metadata == 6 || metadata == 14;
		case 5:
			return metadata == 7 || metadata == 15;
		default:
			return false;	
		}
	}
}