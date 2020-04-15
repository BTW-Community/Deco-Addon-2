package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.Minecraft;

public class AddonBlockGlassStained extends FCBlockGlass
{
	public AddonBlockGlassStained(int ID)
	{
		super(ID,Material.glass,false);
		setCreativeTab((CreativeTabs)null);
		setHardness(0.3F);
		setStepSound(soundGlassFootstep);
		setUnlocalizedName("ginger_glass_");
		AddonManager.Register(this, new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" },
				new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" }, " Stained Glass Block");
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving,
			ItemStack par6ItemStack) {
		if (par5EntityLiving instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer)par5EntityLiving;
			if (ep.inventory.getCurrentItem().itemID != 3003)
				return;
		}

		sendClientOldGlassBlockMessage(par6ItemStack.stackSize-1, par6ItemStack.getItemDamage());

		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving, par6ItemStack);
	}

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        return new ItemStack(AddonDefs.stainedGlassItem, 1, var5);
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        par2EntityPlayer.AddHarvestBlockExhaustion(this.blockID, par3, par4, par5, par6);

        if (this.canSilkHarvest(par6) && EnchantmentHelper.getSilkTouchModifier(par2EntityPlayer))
        {
            int var5 = par1World.getBlockMetadata(par3, par4, par5);
            ItemStack var8 = new ItemStack(AddonDefs.stainedGlassItem, 1, var5);

            if (var8 != null)
            {
                this.dropBlockAsItem_do(par1World, par3, par4, par5, var8);
            }
        }
        else
        {
            int var7 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
            this.dropBlockAsItem(par1World, par3, par4, par5, par6, var7);
        }
    }

	public void sendClientOldGlassBlockMessage(int size, int damage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.writeInt(size);
			dos.writeInt(damage);
			Packet250CustomPayload packet = new Packet250CustomPayload("DECO|OLDGLASS", baos.toByteArray());
			Minecraft.getMinecraft().getNetHandler().addToSendQueue(packet);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
	public int getRenderBlockPass()
	{
		return 1;
	}
//CLIENT ONLY METHODS
	public static Icon[] PaneSideIcons = new Icon[16], Icons = new Icon[16];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		for (int Index = 0; Index < 16; Index++)
		{
			Icons[Index] = Register.registerIcon("ginger_glass_" + Index);
			PaneSideIcons[Index] = Register.registerIcon("ginger_glass_pane_top_" + Index);
		}
	}
//
}