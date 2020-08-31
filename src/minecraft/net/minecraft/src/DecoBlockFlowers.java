package net.minecraft.src;

public class DecoBlockFlowers extends BlockFlower
{
	//CLIENT ONLY
			Icon[] icons;
	//
			String TextureTag;
			public DecoBlockFlowers(int ID, String Texture, String[] Names, String[] Titles){this(ID,Texture, Names, "",Titles,"");}
			public DecoBlockFlowers(int ID, String Texture, String[] Names, String[] Titles, String PostTitle){this(ID,Texture, Names, "",Titles,PostTitle);}
			public DecoBlockFlowers(int ID, String Texture, String[] Names, String PreTitle, String[] Titles){this(ID,Texture, Names, PreTitle ,Titles,"");}
			public DecoBlockFlowers(int ID, String Texture, String[] Names, String PreTitle, String[] Titles, String PostTitle)
			{
				super(ID);
				TextureTag = Texture;
				this.icons = new Icon[Names.length];
				this.setUnlocalizedName("deco"+Texture);
				this.setStepSound(soundGrassFootstep);
				this.setCreativeTab(CreativeTabs.tabDecorations);
		        this.setHardness(0.0F);
		        this.SetBuoyant();
		        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.DAMP_VEGETATION);
		        this.SetFilterableProperties(2);
				DecoManager.Register(this, Names, PreTitle, Titles, PostTitle);
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
					this.icons[i] = Register.registerIcon("ginger_"+TextureTag+"_" + i);
				}
			}
	//
		}