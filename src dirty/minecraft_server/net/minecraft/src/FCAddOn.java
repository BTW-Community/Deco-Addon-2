package net.minecraft.src;

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
}
