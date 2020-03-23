package net.minecraft.src;

public class AddonBlockGearbox extends FCBlockGearBox {
	private String[] textures;
	private Icon iconInput;
	private Icon iconOutput;
	
	protected AddonBlockGearbox(int ID, String[] textures, String tag, String name) {
		super(ID);
		this.setUnlocalizedName(tag);
		AddonManager.Register(this, name);
	}
	
	//CLIENT ONLY

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.iconInput = var1.registerIcon(this.getUnlocalizedName2() + "_input");
        this.iconOutput = var1.registerIcon(this.getUnlocalizedName2() + "_output");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 3 ? this.iconInput : this.blockIcon;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);

        if (var5 == var6)
        {
            return this.iconInput;
        }
        else
        {
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
            var7.AddFacingAsOffset(var5);

            if (var1.getBlockId(var7.i, var7.j, var7.k) == FCBetterThanWolves.fcBlockAxle.blockID && ((FCBlockAxle)FCBetterThanWolves.fcBlockAxle).IsAxleOrientedTowardsFacing(var1, var7.i, var7.j, var7.k, var5))
            {
                return this.iconOutput;
            }
            else if (var5 == Block.GetOppositeFacing(var6))
            {
                for (int var8 = 0; var8 <= 5; ++var8)
                {
                    if (var8 != var6 && FCUtilsMechPower.DoesBlockHaveFacingAxleToSide(var1, var2, var3, var4, var8))
                    {
                        return this.blockIcon;
                    }
                }

                return this.iconOutput;
            }
            else
            {
                return this.blockIcon;
            }
        }
    }
}
