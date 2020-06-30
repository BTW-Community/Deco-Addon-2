package net.minecraft.src;

public enum EnumToolMaterial
{
    WOOD(0, 10, 1.01F, 0, 0, 20, 2),
    STONE(1, 50, 1.01F, 1, 5, 10, 1),
    IRON(2, 500, 6.0F, 2, 14, 25, 2),
    EMERALD(3, 1561, 8.0F, 3, 14, 30, 2),
    GOLD(0, 32, 12.0F, 0, 22, 30, 3),
    SOULFORGED_STEEL(4, 2250, 12.0F, 4, 0, 30, 4);

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    private final int harvestLevel;

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    private final int maxUses;

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    private final float efficiencyOnProperMaterial;

    /** Damage versus entities. */
    private final int damageVsEntity;

    /** Defines the natural enchantability factor of the material. */
    private final int enchantability;
    private final int m_iInfernalMaxEnchantmentCost;
    private final int m_iInfernalMaxNumEnchants;

    private EnumToolMaterial(int var3, int var4, float var5, int var6, int var7, int var8, int var9)
    {
        this.harvestLevel = var3;
        this.maxUses = var4;
        this.efficiencyOnProperMaterial = var5;
        this.damageVsEntity = var6;
        this.enchantability = var7;
        this.m_iInfernalMaxEnchantmentCost = var8;
        this.m_iInfernalMaxNumEnchants = var9;
    }

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    public int getMaxUses()
    {
        return this.maxUses;
    }

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    public float getEfficiencyOnProperMaterial()
    {
        return this.efficiencyOnProperMaterial;
    }

    /**
     * Damage versus entities.
     */
    public int getDamageVsEntity()
    {
        return this.damageVsEntity;
    }

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    /**
     * Return the natural enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return this.enchantability;
    }

    /**
     * Return the crafting material for this tool material, used to determine the item that can be used to repair a tool
     * with an anvil
     */
    public int getToolCraftingMaterial()
    {
        return this == WOOD ? Block.planks.blockID : (this == STONE ? Block.cobblestone.blockID : (this == GOLD ? Item.ingotGold.itemID : (this == IRON ? Item.ingotIron.itemID : (this == EMERALD ? Item.diamond.itemID : 0))));
    }

    public int GetInfernalMaxEnchantmentCost()
    {
        return this.m_iInfernalMaxEnchantmentCost;
    }

    public int GetInfernalMaxNumEnchants()
    {
        return this.m_iInfernalMaxNumEnchants;
    }
}
