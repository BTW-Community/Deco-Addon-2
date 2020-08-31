package net.minecraft.src;

import java.util.Random;

public class DecoBlockWhiteStoneBrick extends Block {
	public DecoBlockWhiteStoneBrick(int ID)
	{
		super(ID, Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		this.SetPicksEffectiveOn(true);
		setUnlocalizedName("whiteStoneBrick");
		setCreativeTab(CreativeTabs.tabBlock);
        this.setTickRandomly(true);
		DecoManager.Register(this, new String[] { "regular", "mossy", "cracked", "chiseled" }, new String[] { "White Stone Bricks", "Mossy White Stone Bricks", "Cracked White Stone Bricks", "Chiseled White Stone" });
	}
	
	public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 == 0 && !var1.getBlockMaterial(var2, var3 - 1, var4).blocksMovement())
        {
            int var7 = var1.getBlockId(var2, var3 + 1, var4);

            if (var7 != Block.waterMoving.blockID && var7 != Block.waterStill.blockID)
            {
                if ((var7 == Block.lavaMoving.blockID || var7 == Block.lavaStill.blockID) && var5.nextInt(15) == 0)
                {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, 2);
                    var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                }
            }
            else if (var5.nextInt(15) == 0)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 1);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
        }
    }
	
	public int damageDropped(int Meta)
	{
		return Meta;
	}
//CLIENT ONLY
	public static Icon[] Icons = new Icon[4];
	public void registerIcons(IconRegister Register)
	{
		for (int Index = 0; Index < 4; Index++)
		{
			Icons[Index] = Register.registerIcon("ginger_bricks_white_" + Index);
		}
	}
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
}
