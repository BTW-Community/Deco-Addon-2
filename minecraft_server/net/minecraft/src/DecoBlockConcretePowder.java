package net.minecraft.src;

public class DecoBlockConcretePowder extends FCBlockFallingFullBlock {
	private Icon[] icons = new Icon[16];
	
	public DecoBlockConcretePowder(int id) {
		super(id, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetFilterableProperties(8);
        this.setStepSound(soundSandFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("decoBlockConcretePowder");
		DecoManager.Register(this, DecoUtilsMisc.colorOrder);
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
			world.setBlockWithNotify(x, y, z, DecoDefs.concrete.blockID);
			world.setBlockMetadataWithClient(x, y, z, meta);
			world.playAuxSFX(2227, x, y, z, 0);
		}
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		
		if (this.HasWaterToSidesOrTop(world, x, y, z)) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockWithNotify(x, y, z, DecoDefs.concrete.blockID);
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
                	DecoEntityFallingConcrete var5 = (DecoEntityFallingConcrete) EntityList.createEntityOfType(DecoEntityFallingConcrete.class, var1, (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, this.blockID, var1.getBlockMetadata(var2, var3, var4));
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
}
