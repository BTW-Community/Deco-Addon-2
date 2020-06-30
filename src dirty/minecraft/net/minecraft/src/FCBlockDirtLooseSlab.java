package net.minecraft.src;

public class FCBlockDirtLooseSlab extends FCBlockSlabFalling
{
    public FCBlockDirtLooseSlab(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("fcBlockDirtLooseSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 3, 0, var6);
        return true;
    }

    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
        return var5 == null || var5.GetCanGrassGrowUnderBlock(var1, var2, var3 + 1, var4, true);
    }

    public boolean SpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtSlab.blockID, 2);
        return true;
    }

    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean SpreadMyceliumToBlock(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockMyceliumSlab.blockID);
        return true;
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockDirtLoose.blockID;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockDirtLoose");
    }
}
