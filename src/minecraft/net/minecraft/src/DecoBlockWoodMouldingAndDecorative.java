package net.minecraft.src;

public class DecoBlockWoodMouldingAndDecorative extends FCBlockWoodMouldingAndDecorative {
	protected DecoBlockWoodMouldingAndDecorative(int var1, String var2, String var3, int var4, String var5) {
		super(var1, var2, var3, var4, var5);
	}

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        byte var2;

        if (this.blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID)
        {
            var2 = 0;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockID)
        {
            var2 = 1;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockID)
        {
            var2 = 2;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockID)
        {
            var2 = 3;
        }
        else if (this.blockID == FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative.blockID)
        {
            var2 = 4;
        }
        else if (this.blockID == DecoDefs.cherryMouldingAndDecorative.blockID)
        {
            var2 = 5;
        }
        else
        {
        	var2 = 6;
        }

        if (!this.IsDecorative(var1))
        {
            return var2;
        }
        else
        {
            byte var3;

            if (var1 == 12)
            {
                var3 = 0;
            }
            else if (var1 != 13 && var1 != 14)
            {
                var3 = 2;
            }
            else
            {
                var3 = 1;
            }

            return FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType(var2, var3);
        }
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.IsDecorative(var1, var2, var3, var4) ? super.GetItemIDDroppedOnSaw(var1, var2, var3, var4) : FCBetterThanWolves.fcBlockWoodCornerItemStubID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.IsDecorative(var1, var2, var3, var4) ? super.GetItemCountDroppedOnSaw(var1, var2, var3, var4) : 2;
    }

    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.IsDecorative(var5) ? super.GetItemDamageDroppedOnSaw(var1, var2, var3, var4) : this.damageDropped(var5);
    }

	@Override
    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        if (this.blockID == FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID)
        {
            int var5 = FCItemBlockWoodMouldingDecorativeStub.GetBlockType(var2);
            int var6 = FCItemBlockWoodMouldingDecorativeStub.GetWoodType(var2);
            byte var7;

            if (var5 == 0)
            {
                var7 = 12;
            }
            else if (var5 == 1)
            {
                var7 = 13;
            }
            else
            {
                var7 = 15;
            }

            Block var4;

            if (var6 == 0)
            {
                var4 = FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative;
            }
            else if (var6 == 1)
            {
                var4 = FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative;
            }
            else if (var6 == 2)
            {
                var4 = FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative;
            }
            else if (var6 == 3)
            {
                var4 = FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative;
            }
            else if (var6 == 4)
            {
                var4 = FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative;
            }
            else if (var6 == 5)
            {
            	var4 = DecoDefs.cherryMouldingAndDecorative;
            }
            else
            {
            	var4 = DecoDefs.acaciaMouldingAndDecorative;
            }

            if (var7 == 15 && var4.blockID == FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockID)
            {
                RenderOakTableInvBlock(var1, var4);
            }
            else
            {
                this.RenderDecorativeInvBlock(var1, var4, var7, var3);
            }
        }
        else
        {
            var1.setRenderBounds(this.GetBlockBoundsFromPoolForItemRender(var2));
            Icon var8;

            switch (var2)
            {
                case 1:
                    var8 = FCBetterThanWolves.fcBlockWoodSpruceMouldingAndDecorative.blockIcon;
                    break;
                case 2:
                    var8 = FCBetterThanWolves.fcBlockWoodBirchMouldingAndDecorative.blockIcon;
                    break;
                case 3:
                    var8 = FCBetterThanWolves.fcBlockWoodJungleMouldingAndDecorative.blockIcon;
                    break;
                case 4:
                    var8 = FCBetterThanWolves.fcBlockWoodBloodMouldingAndDecorative.blockIcon;
                    break;
                case 5:
                	var8 = DecoDefs.cherryMouldingAndDecorative.blockIcon;
                case 6:
                	var8 = DecoDefs.acaciaMouldingAndDecorative.blockIcon;
                	break;
                default:
                    var8 = FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative.blockIcon;
            }

            FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.5F, -0.5F, var8);
        }
    }
}