package net.minecraft.src;

import net.minecraft.client.Minecraft;

public abstract class FCAddOn
{
    protected FCAddOn()
    {
        FCAddOnHandler.AddMod(this);
    }

    public void PreInitialize() {}

    public abstract void Initialize();

    public void PostInitialize() {}

    public void OnLanguageLoaded(StringTranslate var1) {}

    public String GetLanguageFilePrefix()
    {
        return null;
    }

    public boolean ClientCustomPacketReceived(Minecraft var1, Packet250CustomPayload var2)
    {
        return false;
    }

    public boolean ClientPlayCustomAuxFX(Minecraft var1, World var2, EntityPlayer var3, int var4, int var5, int var6, int var7, int var8)
    {
        return false;
    }
}
