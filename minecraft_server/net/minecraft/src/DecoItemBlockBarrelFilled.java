package net.minecraft.src;

public class DecoItemBlockBarrelFilled  extends ItemMultiTextureTile
{
	private Block barrel;
	private int metadata;
	
    public DecoItemBlockBarrelFilled(int var1, Block var2, String[] var3, Block barrel, int metadata)
    {
        super(var1, var2, var3);
        this.maxStackSize = 16;
        this.barrel = barrel;
        this.metadata = metadata;
    }
}
