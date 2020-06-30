package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockAestheticOpaqueEarth extends Block
{
    public static final int m_iSubtypeBlightLevel0 = 0;
    public static final int m_iSubtypeBlightLevel1 = 1;
    public static final int m_iSubtypeBlightLevel2 = 2;
    public static final int m_iSubtypeBlightRootsLevel2 = 3;
    public static final int m_iSubtypeBlightLevel3 = 4;
    public static final int m_iSubtypeBlightRootsLevel3 = 5;
    public static final int m_iSubtypePackedEarth = 6;
    public static final int m_iSubtypeDung = 7;
    public static final int[] m_iSubtypeToBlightLevel = new int[] {0, 1, 2, 2, 3, 3, -1, -1};
    public static final int[] m_iBlightLevelToSubtype = new int[] {0, 1, 2, 4};
    public static final int m_iNumSubtypes = 8;
    private Icon m_IconDung;
    private Icon m_IconPackedEarth;
    private Icon[] m_IconBlightLevel0SideArray = new Icon[6];
    private Icon[] m_IconBlightLevel1SideArray = new Icon[6];
    private Icon[] m_IconBlightLevel2SideArray = new Icon[6];
    private Icon[] m_IconBlightLevel3SideArray = new Icon[6];
    private Icon[] m_IconBlightRootsLevel2SideArray = new Icon[6];
    private Icon m_IconBlightRootsLevel3;

    public FCBlockAestheticOpaqueEarth(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn(true);
        this.SetHoesEffectiveOn();
        this.setTickRandomly(true);
        this.setStepSound(soundGravelFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("fcBlockAestheticOpaqueEarth");
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return this.IsBlightFromMetadata(var1) ? 0 : var1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.IsBlightFromMetadata(var1) ? FCBetterThanWolves.fcBlockDirtLoose.blockID : this.blockID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (this.IsBlightFromMetadata(var5))
        {
            if (!var1.isRemote)
            {
                byte var8 = 8;

                for (int var9 = 0; var9 < var8; ++var9)
                {
                    this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcItemPileDirt));
                }
            }
        }
        else
        {
            super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (this.IsBlightFromMetadata(var6))
        {
            this.BlightRandomUpdateTick(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        int var2 = var1;

        if (var1 != 1 && var1 != 2 && var1 != 3)
        {
            if (var1 == 5)
            {
                var2 = 4;
            }
        }
        else
        {
            var2 = 0;
        }

        return new ItemStack(this.blockID, 1, var2);
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 == 6)
        {
            var5 = 1.2F;
        }
        else if (var6 == 7)
        {
            var5 = 0.8F;
        }

        return var5;
    }

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 7 ? FCBetterThanWolves.fcStepSoundSquish : this.stepSound;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.DropAsPiles(var1, var3, var4, var5, var6, 1.0F);

        if (var6 != 7 && var6 != 6)
        {
            this.OnDirtDugWithImproperTool(var1, var3, var4, var5);
        }
    }

    private void DropAsPiles(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        Item var7 = FCBetterThanWolves.fcItemPileDirt;
        byte var8 = 6;

        if (var5 == 6)
        {
            var8 = 12;
        }
        else if (var5 == 7)
        {
            var7 = FCBetterThanWolves.fcItemDung;
            var8 = 8;
        }

        for (int var10 = 0; var10 < var8; ++var10)
        {
            if (var1.rand.nextFloat() <= var6)
            {
                ItemStack var11 = new ItemStack(var7);
                this.dropBlockAsItem_do(var1, var2, var3, var4, var11);
            }
        }
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion var1)
    {
        return false;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        float var7 = 1.0F;

        if (var5 != null)
        {
            var7 = 1.0F / var5.explosionSize;
        }

        this.DropAsPiles(var1, var2, var3, var4, var6, var7);

        if (var6 != 7 && var6 != 6)
        {
            this.OnDirtDugWithImproperTool(var1, var2, var3, var4);
        }
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return this.IsBlightFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return var1 != null && var1.getItem() instanceof FCItemHoe;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        if (this.IsBlightFromMetadata(var2.getBlockMetadata(var3, var4, var5)))
        {
            var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);

            if (!var2.isRemote)
            {
                var2.playAuxSFX(2001, var3, var4, var5, this.blockID);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean IsSurfaceBlightFromMetadata(int var1)
    {
        return var1 >= 0 && var1 <= 4 && var1 != 3;
    }

    public boolean IsBlightFromMetadata(int var1)
    {
        return var1 >= 0 && var1 <= 5;
    }

    private int GetRootsSubtypeForLevel(int var1)
    {
        return var1 >= 3 ? 5 : 3;
    }

    private void BlightRandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForBlightSurfaceConversions(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var6 == 1)
            {
                this.BlightKillLeaves(var1, var2, var3, var4, var5);
            }
            else if (var6 >= 2)
            {
                this.BlightKillVinesAndLeaves(var1, var2, var3, var4, var5);
            }

            if (var1.provider.dimensionId != 1)
            {
                this.CheckForBlightSpread(var1, var2, var3, var4, var5);
                this.CheckForBlightEvolution(var1, var2, var3, var4, var5);
            }
        }
    }

    private void CheckForSpreadToLocation(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);

        if (var6 > 0)
        {
            int var7 = m_iSubtypeToBlightLevel[var5];

            if (var6 == this.blockID)
            {
                int var8 = var1.getBlockMetadata(var2, var3, var4);
                int var9 = m_iSubtypeToBlightLevel[var8];

                if (var9 < var7)
                {
                    if (this.IsSurfaceBlightFromMetadata(var8))
                    {
                        if (var7 == 3)
                        {
                            var9 = 3;
                        }
                        else
                        {
                            ++var9;
                        }

                        var1.setBlockMetadataWithNotify(var2, var3, var4, m_iBlightLevelToSubtype[var9]);
                    }
                    else
                    {
                        var1.setBlockMetadataWithNotify(var2, var3, var4, 5);
                    }
                }
            }
            else
            {
                Block var10 = Block.blocksList[var6];

                if (var10.GetCanBlightSpreadToBlock(var1, var2, var3, var4, var7))
                {
                    if (var7 < 3)
                    {
                        if (Block.lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2)
                        {
                            var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 0);
                        }
                    }
                    else if (Block.lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2)
                    {
                        var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 4);
                    }
                    else
                    {
                        var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 5);
                    }
                }
            }
        }
    }

    private void CheckForBlightSpread(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7;
        int var8;
        int var9;

        if (var6 == 0)
        {
            var7 = var2 + var5.nextInt(3) - 1;
            var8 = var3 + var5.nextInt(3) - 1;
            var9 = var4 + var5.nextInt(3) - 1;
            this.CheckForSpreadToLocation(var1, var7, var8, var9, var6);
        }
        else
        {
            int var10;

            if (var6 == 1)
            {
                for (var7 = 0; var7 < 2; ++var7)
                {
                    var8 = var2 + var5.nextInt(3) - 1;
                    var9 = var3 + var5.nextInt(4) - 1;
                    var10 = var4 + var5.nextInt(3) - 1;
                    this.CheckForSpreadToLocation(var1, var8, var9, var10, var6);
                }
            }
            else
            {
                for (var7 = 0; var7 < 4; ++var7)
                {
                    var8 = var2 + var5.nextInt(3) - 1;
                    var9 = var3 + var5.nextInt(5) - 2;
                    var10 = var4 + var5.nextInt(3) - 1;
                    this.CheckForSpreadToLocation(var1, var8, var9, var10, var6);
                }

                var7 = this.GetRootsSubtypeForLevel(m_iSubtypeToBlightLevel[var6]);

                if (var1.getBlockId(var2, var3 - 1, var4) == Block.dirt.blockID)
                {
                    var1.setBlockAndMetadataWithNotify(var2, var3 - 1, var4, this.blockID, var7);
                }

                if (var1.getBlockId(var2, var3 + 1, var4) == Block.dirt.blockID)
                {
                    var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, this.blockID, var7);
                }
            }
        }
    }

    private void CheckForBlightEvolution(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7;
        FCUtilsBlockPos var8;

        if (var6 == 0)
        {
            var7 = var5.nextInt(6);
            var8 = new FCUtilsBlockPos(var2, var3, var4, var7);

            if (var1.getBlockMaterial(var8.i, var8.j, var8.k) == Material.water)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 1);
            }
        }
        else if (var6 == 1)
        {
            var7 = var5.nextInt(6);
            var8 = new FCUtilsBlockPos(var2, var3, var4, var7);

            if (var1.getBlockMaterial(var8.i, var8.j, var8.k) == Material.lava)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 2);
            }
        }
        else if (var6 == 2 || var6 == 3)
        {
            var7 = var2 + var5.nextInt(7) - 3;
            int var11 = var3 + var5.nextInt(7) - 3;
            int var9 = var4 + var5.nextInt(7) - 3;
            int var10 = var1.getBlockId(var7, var11, var9);

            if (var10 == Block.portal.blockID)
            {
                if (var6 == 2)
                {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, 4);
                }
                else
                {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, 5);
                }
            }
        }
    }

    private boolean CheckForBlightSurfaceConversions(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = var1.getBlockId(var2, var3 + 1, var4);

        if (Block.lightOpacity[var6] > 2)
        {
            if (var5 == 0)
            {
                var1.setBlockWithNotify(var2, var3, var4, Block.dirt.blockID);
                return true;
            }

            if (var5 == 2)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 3);
                return true;
            }

            if (var5 == 4)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 5);
                return true;
            }
        }
        else
        {
            if (var5 == 3)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 2);
                return true;
            }

            if (var5 == 5)
            {
                var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, 4);
                return true;
            }
        }

        return false;
    }

    private void BlightKillLeaves(World var1, int var2, int var3, int var4, Random var5)
    {
        for (int var6 = 0; var6 < 4; ++var6)
        {
            int var7 = var2 + var5.nextInt(3) - 1;
            int var8 = var3 + var5.nextInt(9);
            int var9 = var4 + var5.nextInt(3) - 1;
            int var10 = var1.getBlockId(var7, var8, var9);

            if (var10 == Block.leaves.blockID)
            {
                var1.setBlockWithNotify(var7, var8, var9, 0);
            }
        }
    }

    private void BlightKillVinesAndLeaves(World var1, int var2, int var3, int var4, Random var5)
    {
        for (int var6 = 0; var6 < 4; ++var6)
        {
            int var7 = var2 + var5.nextInt(3) - 1;
            int var8 = var3 + var5.nextInt(9);
            int var9 = var4 + var5.nextInt(3) - 1;
            int var10 = var1.getBlockId(var7, var8, var9);

            if (var10 == Block.leaves.blockID || var10 == Block.vine.blockID)
            {
                var1.setBlockWithNotify(var7, var8, var9, 0);
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("dirt");
        this.m_IconDung = var1.registerIcon("fcBlockDung");
        this.m_IconPackedEarth = var1.registerIcon("FCBlockPackedEarth");
        this.m_IconBlightLevel0SideArray[0] = var1.registerIcon("FCBlockBlightL0_bottom");
        this.m_IconBlightLevel0SideArray[1] = var1.registerIcon("FCBlockBlightL0_top");
        Icon var2 = var1.registerIcon("FCBlockBlightL0_side");
        this.m_IconBlightLevel0SideArray[2] = var2;
        this.m_IconBlightLevel0SideArray[3] = var2;
        this.m_IconBlightLevel0SideArray[4] = var2;
        this.m_IconBlightLevel0SideArray[5] = var2;
        this.m_IconBlightLevel1SideArray[0] = var1.registerIcon("FCBlockBlightL1_bottom");
        this.m_IconBlightLevel1SideArray[1] = var1.registerIcon("FCBlockBlightL1_top");
        var2 = var1.registerIcon("FCBlockBlightL1_side");
        this.m_IconBlightLevel1SideArray[2] = var2;
        this.m_IconBlightLevel1SideArray[3] = var2;
        this.m_IconBlightLevel1SideArray[4] = var2;
        this.m_IconBlightLevel1SideArray[5] = var2;
        this.m_IconBlightLevel2SideArray[0] = var1.registerIcon("FCBlockBlightL2_bottom");
        this.m_IconBlightLevel2SideArray[1] = var1.registerIcon("FCBlockBlightL2_top");
        var2 = var1.registerIcon("FCBlockBlightL2_side");
        this.m_IconBlightLevel2SideArray[2] = var2;
        this.m_IconBlightLevel2SideArray[3] = var2;
        this.m_IconBlightLevel2SideArray[4] = var2;
        this.m_IconBlightLevel2SideArray[5] = var2;
        this.m_IconBlightLevel3SideArray[0] = var1.registerIcon("FCBlockBlightL3_roots");
        this.m_IconBlightLevel3SideArray[1] = var1.registerIcon("FCBlockBlightL3_top");
        var2 = var1.registerIcon("FCBlockBlightL3_side");
        this.m_IconBlightLevel3SideArray[2] = var2;
        this.m_IconBlightLevel3SideArray[3] = var2;
        this.m_IconBlightLevel3SideArray[4] = var2;
        this.m_IconBlightLevel3SideArray[5] = var2;
        this.m_IconBlightRootsLevel2SideArray[0] = var1.registerIcon("FCBlockBlightL2_bottom");
        var2 = var1.registerIcon("FCBlockBlightL2_roots");
        this.m_IconBlightRootsLevel2SideArray[1] = var2;
        this.m_IconBlightRootsLevel2SideArray[2] = var2;
        this.m_IconBlightRootsLevel2SideArray[3] = var2;
        this.m_IconBlightRootsLevel2SideArray[4] = var2;
        this.m_IconBlightRootsLevel2SideArray[5] = var2;
        this.m_IconBlightRootsLevel3 = var1.registerIcon("FCBlockBlightL3_roots");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var2 == 0 ? this.m_IconBlightLevel0SideArray[var1] : (var2 == 1 ? this.m_IconBlightLevel1SideArray[var1] : (var2 == 2 ? this.m_IconBlightLevel2SideArray[var1] : (var2 == 3 ? this.m_IconBlightRootsLevel2SideArray[var1] : (var2 == 4 ? this.m_IconBlightLevel3SideArray[var1] : (var2 == 5 ? this.m_IconBlightRootsLevel3 : (var2 == 6 ? this.m_IconPackedEarth : (var2 == 7 ? this.m_IconDung : this.blockIcon)))))));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 6));
        var3.add(new ItemStack(var1, 1, 7));
    }
}
