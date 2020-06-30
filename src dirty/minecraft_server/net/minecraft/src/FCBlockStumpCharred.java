package net.minecraft.src;

import java.util.Random;

public class FCBlockStumpCharred extends Block
{
    public static final float m_fHardness = 3.0F;
    private FCModelBlock[] m_blockModelsNarrowOneSide;
    private static final float m_fRimWidth = 0.0625F;
    private static final float m_fLayerHeight = 0.125F;
    private static final float m_fFirstLayerHeight = 0.1875F;
    private static final float m_fLayerWidthGap = 0.0625F;
    private FCModelBlock m_tempCurrentModel;

    public FCBlockStumpCharred(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(3.0F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.InitModels();
        Block.useNeighborBrightness[var1] = true;
        this.setLightOpacity(8);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("fcBlockStumpCharred");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        return this.GetCurrentModelForBlock(var1, var2, var3, var4).CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 <= 1;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        int var8 = this.GetDamageLevel(var7);

        if (var8 < 3)
        {
            ++var8;
            this.SetDamageLevel(var2, var3, var4, var5, var8);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1000;
    }

    protected void InitModels()
    {
        this.m_blockModelsNarrowOneSide = new FCModelBlock[4];
        int var1;

        for (var1 = 0; var1 < 4; ++var1)
        {
            FCModelBlock var2 = this.m_blockModelsNarrowOneSide[var1] = new FCModelBlock();
            float var3 = 0.0625F + 0.0625F * (float)var1;
            float var4 = 0.0F;

            if (var1 > 0)
            {
                var4 = 0.1875F + 0.125F * (float)(var1 - 1);
            }

            var2.AddBox((double)var3, (double)var4, (double)var3, (double)(1.0F - var3), 1.0D, (double)(1.0F - var3));
        }

        for (var1 = 1; var1 < 4; ++var1)
        {
            this.m_blockModelsNarrowOneSide[var1].AddBox(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.1875D, 0.9375D);
        }

        float var5 = 0.125F;
        float var6 = 0.1875F;

        for (int var7 = 2; var7 < 4; ++var7)
        {
            this.m_blockModelsNarrowOneSide[var7].AddBox((double)var5, (double)var6, (double)var5, (double)(1.0F - var5), (double)(var6 + 0.125F), (double)(1.0F - var5));
        }

        var5 = 0.1875F;
        var6 = 0.3125F;
        this.m_blockModelsNarrowOneSide[3].AddBox((double)var5, (double)var6, (double)var5, (double)(1.0F - var5), (double)(var6 + 0.125F), (double)(1.0F - var5));
    }

    public void SetDamageLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetDamageLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetDamageLevel(int var1, int var2)
    {
        var1 &= -4;
        return var1 | var2;
    }

    public int GetDamageLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetDamageLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetDamageLevel(int var1)
    {
        return var1 & 3;
    }

    public FCModelBlock GetCurrentModelForBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetDamageLevel(var1, var2, var3, var4);
        return this.m_blockModelsNarrowOneSide[var5];
    }
}
