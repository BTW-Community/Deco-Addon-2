package net.minecraft.src;

public class FCBlockSand extends FCBlockFallingFullBlock
{
    public FCBlockSand(int var1)
    {
        super(var1, Material.sand);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetFilterableProperties(8);
        this.setStepSound(soundSandFootstep);
        this.setUnlocalizedName("sand");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.8F;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 6, 0, var6);
        return true;
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 2;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return Block.sandStone.blockID;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanCactusGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}
