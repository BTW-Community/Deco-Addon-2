package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;

public class FCBlockDirtSlab extends FCBlockSlabAttached
{
    public static final int m_iSubtypeDirt = 0;
    public static final int m_iSubtypeGrass = 1;
    public static final int m_iSubtypeMycelium = 2;
    public static final int m_iSubtypePackedEarth = 3;
    public static final int m_iNumSubtypes = 4;
    private Icon m_IconGrassSide;
    private Icon m_IconGrassSideOverlay;
    private Icon m_IconGrassTop;
    private Icon iconGrassTopRough;
    private Icon m_IconGrassTopItem;
    private Icon m_IconGrassSideHalf;
    private Icon m_IconGrassSideOverlayHalf;
    private Icon m_IconPackedEarth;
    private Icon m_IconGrassWithSnowSide;
    private Icon m_IconGrassWithSnowSideHalf;

    public FCBlockDirtSlab(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockSlabDirt");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
        	Block blockAbove = Block.blocksList[world.getBlockId(x, y + 1, z)];
        	
            if ((world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2) || (blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
            	this.SetSubtype(world, x, y, z, 0);
            }
            else if ((world.getBlockLightValue(x, y + 1, z) >= 9 || world.GetBlockNaturalLightValue(x, y + 1, z) >= 9) && (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
                FCBlockGrass.CheckForGrassSpreadFromLocation(world, x, y, z);
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        int var2 = this.GetSubtype(var1);
        return var2 == 3 ? var2 : 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        int var4 = this.GetSubtype(var1);
        return var4 == 3 ? super.idDropped(var1, var2, var3) : FCBetterThanWolves.fcBlockDirtLooseSlab.blockID;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 == 3)
        {
            var5 = 1.2F;
        }

        return var5;
    }

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 != 0 && var5 != 3 ? this.stepSound : soundGravelFootstep;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        byte var7 = 3;

        if (this.GetSubtype(var5) == 3)
        {
            var7 = 6;
        }

        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, var7, 0, var6);
        return true;
    }

    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);

        if (var5 == 0)
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
            boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

            if (var6 == null || var6.GetCanGrassGrowUnderBlock(var1, var2, var3 + 1, var4, !var7))
            {
                return true;
            }
        }

        return false;
    }

    public boolean SpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        this.SetSubtype(var1, var2, var3, var4, 1);
        return true;
    }

    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 != 0 ? false : !this.GetIsUpsideDown(var1, var2, var3, var4) || !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0);
    }

    public boolean SpreadMyceliumToBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = ((FCBlockMyceliumSlab)FCBetterThanWolves.fcBlockMyceliumSlab).SetIsUpsideDown(0, this.GetIsUpsideDown(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockMyceliumSlab.blockID, var5);
        return true;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (var5.blockID == FCBetterThanWolves.fcBlockDirtLooseSlab.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (this.GetSubtype(var6) != 3 && !this.GetIsUpsideDown(var6))
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
                return true;
            }
        }

        return super.AttemptToCombineWithFallingEntity(var1, var2, var3, var4, var5);
    }

    protected void OnAnchorBlockLost(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
    }

    public int GetCombinedBlockID(int var1)
    {
        int var2 = this.GetSubtype(var1);
        return var2 == 3 ? FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID : Block.dirt.blockID;
    }

    public int GetCombinedMetadata(int var1)
    {
        int var2 = this.GetSubtype(var1);
        return var2 == 3 ? 6 : 0;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        return new ItemStack(this.blockID, 1, this.GetSubtype(var1));
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return this.GetSubtype(var1, var2, var3, var4) == 1;
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (!var5.GetDisruptsEarthOnGraze())
        {
            this.SetSubtype(var1, var2, var3, var4, 0);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        super.OnBlockDestroyedWithImproperTool(var1, var2, var3, var4, var5, var6);

        if (this.GetSubtype(var6) != 3)
        {
            this.OnDirtSlabDugWithImproperTool(var1, var3, var4, var5, this.GetIsUpsideDown(var6));
        }
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        super.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);

        if (this.GetSubtype(var1, var2, var3, var4) != 3)
        {
            this.OnDirtSlabDugWithImproperTool(var1, var2, var3, var4, this.GetIsUpsideDown(var1, var2, var3, var4));
        }
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 != 3 && (var6 != 1 || var5 == 0))
        {
            boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

            if ((!var7 || var5 != 0) && (var7 || var5 != 1))
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            }
        }
    }

    public int GetSubtype(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetSubtype(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetSubtype(int var1)
    {
        return (var1 & -2) >> 1;
    }

    public void SetSubtype(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 1;
        var6 |= var5 << 1;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("dirt");
        this.m_IconGrassSide = var1.registerIcon("grass_side");
        this.m_IconGrassSideOverlay = var1.registerIcon("grass_side_overlay");
        this.m_IconGrassTop = var1.registerIcon("grass_top");
        this.iconGrassTopRough = var1.registerIcon("decoBlockGrassRough");
        this.m_IconGrassTopItem = var1.registerIcon("fcBlockSlabDirt_grass_top_item");
        this.m_IconGrassSideHalf = var1.registerIcon("FCBlockSlabDirt_grass_side");
        this.m_IconGrassSideOverlayHalf = var1.registerIcon("FCBlockSlabDirt_grass_side_overlay");
        this.m_IconPackedEarth = var1.registerIcon("FCBlockPackedEarth");
        this.m_IconGrassWithSnowSide = var1.registerIcon("snow_side");
        this.m_IconGrassWithSnowSideHalf = var1.registerIcon("FCBlockSlabDirt_grass_snow_side");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 1 && this.GetSubtype(var2) == 1 ? this.m_IconGrassTopItem : this.GetIconFromMetadata(var1, var2);
    }

    private Icon GetIconFromMetadata(int var1, int var2)
    {
        int var3 = this.GetSubtype(var2);

        if (var3 == 1 && var1 != 0)
        {
            if (var1 != 1)
            {
                boolean var4 = (var2 & 1) > 0;
                return var4 ? this.m_IconGrassSide : this.m_IconGrassSideHalf;
            }
            else
            {
                return this.m_IconGrassTop;
            }
        }
        else
        {
            return var3 == 3 ? this.m_IconPackedEarth : this.blockIcon;
        }
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	World world = null;
    	
    	if (blockAccess instanceof ChunkCache) {
    		ChunkCache chunkCache = (ChunkCache) blockAccess;
    		world = chunkCache.worldObj;
    	}
    	else if (blockAccess instanceof World) {
    		world = (World) blockAccess;
    	}
    	
    	int skylight;
    	
    	if (world != null)
    		skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	else
    		skylight = 9;

        int meta = world.getBlockMetadata(x, y, z);
        int type = this.GetSubtype(meta);
    	
        if (side == 1 && type == this.m_iSubtypeGrass) {
        	return (skylight < 9 && this.GetSubtype(blockAccess.getBlockMetadata(x, y, z)) == this.m_iSubtypeGrass ? this.iconGrassTopRough : this.m_IconGrassTop);
        }
        else {
            return type == 1 && side > 1 && this.IsSnowCoveringTopSurface(world, x, y, z) ? (this.GetIsUpsideDown(meta) ? this.m_IconGrassWithSnowSide : this.m_IconGrassWithSnowSideHalf) : this.GetIconFromMetadata(side, meta);
        }
    }
    
    /*
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.GetSubtype(var6);
        return var7 == 1 && var5 > 1 && this.IsSnowCoveringTopSurface(var1, var2, var3, var4) ? (this.GetIsUpsideDown(var6) ? this.m_IconGrassWithSnowSide : this.m_IconGrassWithSnowSideHalf) : this.GetIconFromMetadata(var5, var6);
    }
    */

    public Icon GetSideOverlayTexture(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsUpsideDown(var1, var2, var3, var4) ? this.m_IconGrassSideOverlayHalf : this.m_IconGrassSideOverlay;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
        var3.add(new ItemStack(var1, 1, 3));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        int var6 = FCBetterThanWolves.fcBlockDirtSlab.GetSubtype(var5, var2, var3, var4);

        if (var6 == 1 && var5.getBlockId(var2, var3 + 1, var4) != Block.snow.blockID)
        {
            int var7 = this.colorMultiplier(var5, var2, var3, var4);
            float var8 = (float)(var7 >> 16 & 255) / 255.0F;
            float var9 = (float)(var7 >> 8 & 255) / 255.0F;
            float var10 = (float)(var7 & 255) / 255.0F;
            return Minecraft.isAmbientOcclusionEnabled() ? var1.renderGrassBlockWithAmbientOcclusion(this, var2, var3, var4, var8, var9, var10, this.GetSideOverlayTexture(var5, var2, var3, var4)) : var1.renderGrassBlockWithColorMultiplier(this, var2, var3, var4, var8, var9, var10, this.GetSideOverlayTexture(var5, var2, var3, var4));
        }
        else
        {
            return var1.renderStandardBlock(this, var2, var3, var4);
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, var2 << 1);
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);

        if (var5 == 1 && var1.getBlockId(var2, var3 + 1, var4) != Block.snow.blockID)
        {
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;

            for (int var9 = -1; var9 <= 1; ++var9)
            {
                for (int var10 = -1; var10 <= 1; ++var10)
                {
                    int var11 = var1.getBiomeGenForCoords(var2 + var10, var4 + var9).getBiomeGrassColor();
                    var6 += (var11 & 16711680) >> 16;
                    var7 += (var11 & 65280) >> 8;
                    var8 += var11 & 255;
                }
            }

            return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
        }
        else
        {
            return super.colorMultiplier(var1, var2, var3, var4);
        }
    }
}
