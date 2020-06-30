package net.minecraft.src;

public abstract class FCItemArmorMod extends FCItemArmor
{
    public FCItemArmorMod(int var1, EnumArmorMaterial var2, int var3, int var4, int var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    /**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean hasColor(ItemStack var1)
    {
        return this.HasCustomColors() && var1.hasTagCompound() && var1.getTagCompound().hasKey("display") && var1.getTagCompound().getCompoundTag("display").hasKey("color");
    }

    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack var1)
    {
        if (this.HasCustomColors())
        {
            NBTTagCompound var2 = var1.getTagCompound();

            if (var2 != null)
            {
                NBTTagCompound var3 = var2.getCompoundTag("display");

                if (var3 != null && var3.hasKey("color"))
                {
                    return var3.getInteger("color");
                }
            }

            return this.GetDefaultColor();
        }
        else
        {
            return -1;
        }
    }

    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void removeColor(ItemStack var1)
    {
        if (this.HasCustomColors())
        {
            NBTTagCompound var2 = var1.getTagCompound();

            if (var2 != null)
            {
                NBTTagCompound var3 = var2.getCompoundTag("display");

                if (var3.hasKey("color"))
                {
                    var3.removeTag("color");
                }
            }
        }
    }

    public void func_82813_b(ItemStack var1, int var2)
    {
        if (!this.HasCustomColors())
        {
            throw new UnsupportedOperationException("Can\'t dye this shiznit fo\'shnizzle!");
        }
        else
        {
            NBTTagCompound var3 = var1.getTagCompound();

            if (var3 == null)
            {
                var3 = new NBTTagCompound();
                var1.setTagCompound(var3);
            }

            NBTTagCompound var4 = var3.getCompoundTag("display");

            if (!var3.hasKey("display"))
            {
                var3.setCompoundTag("display", var4);
            }

            var4.setInteger("color", var2);
        }
    }

    public boolean HasCustomColors()
    {
        return false;
    }

    public boolean HasSecondRenderLayerWhenWorn()
    {
        return false;
    }

    public int GetDefaultColor()
    {
        return 0;
    }

    public String GetWornTextureDirectory()
    {
        return "/btwmodtex/";
    }

    public abstract String GetWornTexturePrefix();

    public boolean requiresMultipleRenderPasses()
    {
        return false;
    }

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public Icon getIconFromDamageForRenderPass(int var1, int var2)
    {
        return this.getIconFromDamage(var1);
    }
}
