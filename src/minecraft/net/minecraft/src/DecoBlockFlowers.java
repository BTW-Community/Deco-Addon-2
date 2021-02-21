package net.minecraft.src;

public class DecoBlockFlowers extends BlockFlower
{
	//CLIENT ONLY
	Icon[] icons;
	//
	String TextureTag;
	
	public DecoBlockFlowers(int ID, String Texture, int numFlowers)
	{
		super(ID);
		this.TextureTag = Texture;
		this.icons = new Icon[numFlowers];
		this.setUnlocalizedName(this.TextureTag);
		this.setStepSound(soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.0F);
		this.SetBuoyant();
		this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.DAMP_VEGETATION);
		this.SetFilterableProperties(2);
	}
	@Override public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		return Block.plantRed.canBlockStay(par1World, par2, par3, par4);
	}
	@Override public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return Block.plantRed.canPlaceBlockAt(par1World, par2, par3, par4);
	}
	@Override public int damageDropped(int Meta)
	{
		return Meta;
	}
	//CLIENT ONLY METHODS
	@Override public Icon getIcon(int Side, int Meta)
	{
		return this.icons[Meta];
	}
	@Override public void registerIcons(IconRegister Register)
	{
		for (int i = 0; i < icons.length; i++)
		{
			this.icons[i] = Register.registerIcon(TextureTag+"_" + i);
		}
	}
	//
}