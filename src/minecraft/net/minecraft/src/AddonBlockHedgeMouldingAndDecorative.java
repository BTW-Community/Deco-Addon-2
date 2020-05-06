package net.minecraft.src;

import java.util.Random;

public class AddonBlockHedgeMouldingAndDecorative extends FCBlockMouldingAndDecorative {
	private boolean shouldColor;
	private Block owner;
	private int ownerMeta;

	protected AddonBlockHedgeMouldingAndDecorative(int var1, Material var2, String var3, String var4, int var5, float var6, float var7, StepSound var8, String var9, boolean shouldColor, Block owner, int ownerMeta) {
		super(var1, var2, var3, var4, var5, var6, var7, var8, var9);
		this.blockMaterial = AddonDefs.materialHedge;
		this.SetBuoyant();
		this.SetFireProperties(FCEnumFlammability.LEAVES);
		this.shouldColor = shouldColor;
	}

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.5F;
    }
	
	public boolean GetCanGrassGrowUnderBlock(World world, int x, int y, int z, boolean var5) {
		return true;
	}

	public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
	{
		super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
		this.GenerateAshOnBurn(var1, var2, var3, var4);
	}

	protected void GenerateAshOnBurn(World var1, int var2, int var3, int var4)
	{
		if (this.owner != FCBetterThanWolves.fcBlockBloodLeaves) {
			for (int var5 = var3; var5 > 0; --var5)
			{
				if (FCBlockAshGroundCover.CanAshReplaceBlock(var1, var2, var5, var4))
				{
					int var6 = var1.getBlockId(var2, var5 - 1, var4);
					Block var7 = Block.blocksList[var6];

					if (var7 != null && var7.CanGroundCoverRestOnBlock(var1, var2, var5 - 1, var4))
					{
						var1.setBlockWithNotify(var2, var5, var4, FCBetterThanWolves.fcBlockAshGroundCover.blockID);
						break;
					}
				}
				else if (var1.getBlockId(var2, var5, var4) != Block.fire.blockID)
				{
					break;
				}
			}
		}
	}

	@Override public boolean isOpaqueCube()
	{
		return false;
	}

	//CLIENT ONLY
	public boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
	{
		return true;
	}

	public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
	{
		return true;
	}

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.IsRainingAtPos(var2, var3 + 1, var4) && !var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) && var5.nextInt(15) == 1)
        {
            var1.spawnParticle("dripWater", (double)var2 + var5.nextDouble(), (double)var3 - 0.05D, (double)var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
    public int getBlockColor()
    {
    	if (!shouldColor)
    		return super.getBlockColor();
    	
    	if (owner == FCBetterThanWolves.fcBlockBloodLeaves)
    		return 14163743;
    	
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerFoliage.getFoliageColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
    	if (!shouldColor)
    		return super.getRenderColor(par1);
    		
    	if (owner == FCBetterThanWolves.fcBlockBloodLeaves)
    		return 14163743;
    	
        return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	if (!shouldColor)
    		return super.colorMultiplier(par1IBlockAccess, par2, par3, par4);
    	
    	if (owner == FCBetterThanWolves.fcBlockBloodLeaves)
    		return 14163743;
    	
        if ((ownerMeta & 3) == 1)
        {
            return ColorizerFoliage.getFoliageColorPine();
        }
        else if ((ownerMeta & 3) == 2)
        {
            return ColorizerFoliage.getFoliageColorBirch();
        }
        else
        {
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;

            for (int var9 = -1; var9 <= 1; ++var9)
            {
                for (int var10 = -1; var10 <= 1; ++var10)
                {
                    int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
                    var6 += (var11 & 16711680) >> 16;
                    var7 += (var11 & 65280) >> 8;
                    var8 += var11 & 255;
                }
            }

            return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
        }
    }
}