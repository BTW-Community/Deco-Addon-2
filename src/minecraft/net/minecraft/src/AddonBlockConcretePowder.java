package net.minecraft.src;

public class AddonBlockConcretePowder extends FCBlockFallingFullBlock {
	public static final String[] tags = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
	public static final String[] names = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
	
	private String nameTag;
	private Icon[] icons = new Icon[16];
	
	public AddonBlockConcretePowder(int id, String tag, String name) {
		super(id, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetFilterableProperties(8);
        this.setStepSound(soundSandFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
		this.nameTag = tag;
		this.setUnlocalizedName(tag);
		AddonManager.Register(this,tags, names," " + name);
	}

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileGravel.itemID, 3, 0, var6);
        return true;
    }

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		
		if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockWithNotify(x, y, z, AddonDefs.concrete.blockID);
			world.setBlockMetadataWithClient(x, y, z, meta);
			world.playAuxSFX(2227, x, y, z, 0);
		}
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		
		if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockWithNotify(x, y, z, AddonDefs.concrete.blockID);
			world.setBlockMetadataWithClient(x, y, z, meta);
			world.playAuxSFX(2227, x, y, z, 0);
		}
	}

    protected boolean CheckForFall(World var1, int var2, int var3, int var4)
    {
        if (this.CanFallIntoBlockAtPos(var1, var2, var3 - 1, var4) && var3 >= 0)
        {
            if (!BlockSand.fallInstantly && var1.checkChunksExist(var2 - 32, var3 - 32, var4 - 32, var2 + 32, var3 + 32, var4 + 32))
            {
                if (!var1.isRemote)
                {
                	AddonEntityFallingConcrete var5 = new AddonEntityFallingConcrete(var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, this.blockID, var1.getBlockMetadata(var2, var3, var4));
                    this.onStartFalling(var5);
                    var1.spawnEntityInWorld(var5);
                }

                return true;
            }
            else
            {
                var1.setBlockToAir(var2, var3, var4);

                while (this.CanFallIntoBlockAtPos(var1, var2, var3 - 1, var4) && var3 > 0)
                {
                    --var3;
                }

                if (var3 > 0)
                {
                    var1.setBlock(var2, var3, var4, this.blockID);
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
	}
	
	//CLIENT ONLY
	@Override
	public Icon getIcon(int side, int metadata){
		return this.icons[metadata];
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < 16; i++)
			this.icons[i] = iconRegister.registerIcon(nameTag+"_" + i);
	}
}
