package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MerchantRecipeList extends ArrayList
{
    public MerchantRecipeList() {}

    public MerchantRecipeList(NBTTagCompound par1NBTTagCompound)
    {
        this.readRecipiesFromTags(par1NBTTagCompound);
    }

    /**
     * checks if there is a recipie for the same ingredients already on the list, and replaces it. otherwise, adds it
     */
    public void addToListWithCheck(MerchantRecipe par1MerchantRecipe)
    {
        for (int var2 = 0; var2 < this.size(); ++var2)
        {
            MerchantRecipe var3 = (MerchantRecipe)this.get(var2);

            if (par1MerchantRecipe.hasSameIDsAs(var3))
            {
                return;
            }
        }

        this.add(par1MerchantRecipe);
    }

    public void writeRecipiesToStream(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeByte((byte)(this.size() & 255));

        for (int var2 = 0; var2 < this.size(); ++var2)
        {
            MerchantRecipe var3 = (MerchantRecipe)this.get(var2);
            Packet.writeItemStack(var3.getItemToBuy(), par1DataOutputStream);
            Packet.writeItemStack(var3.getItemToSell(), par1DataOutputStream);
            ItemStack var4 = var3.getSecondItemToBuy();
            par1DataOutputStream.writeBoolean(var4 != null);

            if (var4 != null)
            {
                Packet.writeItemStack(var4, par1DataOutputStream);
            }

            par1DataOutputStream.writeBoolean(var3.func_82784_g());
            par1DataOutputStream.writeShort(var3.m_iTradeLevel);
        }
    }

    public static MerchantRecipeList readRecipiesFromStream(DataInputStream par0DataInputStream) throws IOException
    {
        MerchantRecipeList var1 = new MerchantRecipeList();
        int var2 = par0DataInputStream.readByte() & 255;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            ItemStack var4 = Packet.readItemStack(par0DataInputStream);
            ItemStack var5 = Packet.readItemStack(par0DataInputStream);
            ItemStack var6 = null;

            if (par0DataInputStream.readBoolean())
            {
                var6 = Packet.readItemStack(par0DataInputStream);
            }

            boolean var7 = par0DataInputStream.readBoolean();
            MerchantRecipe var8 = new MerchantRecipe(var4, var6, var5);

            if (var7)
            {
                var8.func_82785_h();
            }

            short var9 = par0DataInputStream.readShort();
            var8.m_iTradeLevel = var9;
            var1.add(var8);
        }

        return var1;
    }

    public void readRecipiesFromTags(NBTTagCompound par1NBTTagCompound)
    {
        NBTTagList var2 = par1NBTTagCompound.getTagList("Recipes");

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            this.add(new MerchantRecipe(var4));
        }
    }

    public NBTTagCompound getRecipiesAsTags()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        NBTTagList var2 = new NBTTagList("Recipes");

        for (int var3 = 0; var3 < this.size(); ++var3)
        {
            MerchantRecipe var4 = (MerchantRecipe)this.get(var3);
            var2.appendTag(var4.writeToTags());
        }

        var1.setTag("Recipes", var2);
        return var1;
    }

    /**
     * can par1,par2 be used to in crafting recipe par3
     */
    public MerchantRecipe canRecipeBeUsed(ItemStack par1ItemStack, ItemStack par2ItemStack, int par3)
    {
        if (par3 >= 0 && par3 < this.size())
        {
            MerchantRecipe var6 = (MerchantRecipe)this.get(par3);

            if (this.IsStackValidForFirstSlotOfRecipe(par1ItemStack, var6) && this.IsStackValidForSecondSlotOfRecipe(par2ItemStack, var6))
            {
                return var6;
            }
        }
        else
        {
            for (int var4 = 0; var4 < this.size(); ++var4)
            {
                MerchantRecipe var5 = (MerchantRecipe)this.get(var4);

                if (this.IsStackValidForFirstSlotOfRecipe(par1ItemStack, var5) && this.IsStackValidForSecondSlotOfRecipe(par2ItemStack, var5))
                {
                    return var5;
                }
            }
        }

        return null;
    }

    private boolean IsStackValidForFirstSlotOfRecipe(ItemStack var1, MerchantRecipe var2)
    {
        return var1.itemID == var2.getItemToBuy().itemID && var1.stackSize >= var2.getItemToBuy().stackSize ? (var2.getItemToBuy().getHasSubtypes() ? var1.getItemDamage() == var2.getItemToBuy().getItemDamage() : true) : false;
    }

    private boolean IsStackValidForSecondSlotOfRecipe(ItemStack var1, MerchantRecipe var2)
    {
        if (var2.hasSecondItemToBuy())
        {
            if (var1 != null && var1.itemID == var2.getSecondItemToBuy().itemID && var1.stackSize >= var2.getSecondItemToBuy().stackSize)
            {
                if (var2.getSecondItemToBuy().getHasSubtypes())
                {
                    return var1.getItemDamage() == var2.getSecondItemToBuy().getItemDamage();
                }

                return true;
            }
        }
        else if (var1 == null)
        {
            return true;
        }

        return false;
    }
}
