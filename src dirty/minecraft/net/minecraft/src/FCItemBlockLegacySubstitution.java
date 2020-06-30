package net.minecraft.src;

public class FCItemBlockLegacySubstitution extends ItemBlock
{
    protected int m_iSubstituteBlockID;

    public FCItemBlockLegacySubstitution(int var1, int var2)
    {
        super(var1);
        this.m_iSubstituteBlockID = var2;
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        return this.m_iSubstituteBlockID;
    }

    public String getItemDisplayName(ItemStack var1)
    {
        return "Old " + super.getItemDisplayName(var1);
    }
}
