package net.minecraft.src;

import java.util.Random;

public class FCBlockDirt extends FCBlockFullBlock
{
    protected FCBlockDirt(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetHoesEffectiveOn();
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("dirt");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockDirtLoose.blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 6, 0, var6);
        return true;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        super.OnBlockDestroyedWithImproperTool(var1, var2, var3, var4, var5, var6);
        this.OnDirtDugWithImproperTool(var1, var3, var4, var5);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        super.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);
        this.OnDirtDugWithImproperTool(var1, var2, var3, var4);
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
    }

    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
        return var5 == null || var5.GetCanGrassGrowUnderBlock(var1, var2, var3 + 1, var4, false);
    }

    public boolean SpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, Block.grass.blockID);
        return true;
    }

    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0);
    }

    public boolean SpreadMyceliumToBlock(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, Block.mycelium.blockID);
        return true;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlightSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return var1 != null && var1.getItem() instanceof FCItemHoe;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);

        if (!var2.isRemote)
        {
            var2.playAuxSFX(2001, var3, var4, var5, this.blockID);
        }

        return true;
    }
}
