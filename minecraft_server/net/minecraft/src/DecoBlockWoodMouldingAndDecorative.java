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
}