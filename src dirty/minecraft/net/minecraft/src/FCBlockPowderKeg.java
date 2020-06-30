package net.minecraft.src;

public class FCBlockPowderKeg extends BlockTNT
{
    private Icon[] m_IconBySideArray = new Icon[6];

    public FCBlockPowderKeg(int var1)
    {
        super(var1);
        this.setHardness(0.0F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.EXPLOSIVES);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("tnt");
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        if (!var1.isRemote)
        {
            EntityLiving var6 = null;

            if (var5 != null)
            {
                var6 = var5.func_94613_c();
            }

            EntityTNTPrimed var7 = new EntityTNTPrimed(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), var6);
            var7.fuse = var1.rand.nextInt(var7.fuse / 4) + var7.fuse / 8;
            var1.spawnEntityInWorld(var7);
        }
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
        this.onBlockDestroyedByPlayer(var1, var2, var3, var4, 1);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockPowderKeg_bottom");
        this.blockIcon = var2;
        this.m_IconBySideArray[0] = var2;
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockPowderKeg_top");
        Icon var3 = var1.registerIcon("fcBlockPowderKeg_side");
        this.m_IconBySideArray[2] = var3;
        this.m_IconBySideArray[3] = var3;
        this.m_IconBySideArray[4] = var3;
        this.m_IconBySideArray[5] = var3;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconBySideArray[var1];
    }
}
