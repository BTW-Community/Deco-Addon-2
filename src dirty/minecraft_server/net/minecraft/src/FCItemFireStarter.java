package net.minecraft.src;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class FCItemFireStarter extends Item
{
    private final float m_fExhaustionPerUse;

    public FCItemFireStarter(int var1, int var2, float var3)
    {
        super(var1);
        this.maxStackSize = 1;
        this.setMaxDamage(var2);
        this.m_fExhaustionPerUse = var3;
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var2.canPlayerEdit(var4, var5, var6, var7, var1))
        {
            this.PerformUseEffects(var2);

            if (!var3.isRemote)
            {
                this.NotifyNearbyAnimalsOfAttempt(var2);

                if (this.CheckChanceOfStart(var1, var3.rand))
                {
                    this.AttemptToLightBlock(var1, var3, var4, var5, var6, var7);
                }
            }

            var2.addExhaustion(this.m_fExhaustionPerUse);
            var1.damageItem(1, var2);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean GetCanItemStartFireOnUse(int var1)
    {
        return true;
    }

    protected abstract boolean CheckChanceOfStart(ItemStack var1, Random var2);

    protected void PerformUseEffects(EntityPlayer var1) {}

    protected boolean AttemptToLightBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockId(var3, var4, var5);
        Block var8 = Block.blocksList[var7];
        return var8 != null && var8.GetCanBeSetOnFireDirectlyByItem(var2, var3, var4, var5) && var8.SetOnFireDirectly(var2, var3, var4, var5);
    }

    protected boolean AttemptToLightBlockRegardlessOfFlamability(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockId(var3, var4, var5);
        Block var8 = Block.blocksList[var7];
        FCUtilsBlockPos var9 = new FCUtilsBlockPos(var3, var4, var5);

        if (var8 == null || !var8.GetCanBeSetOnFireDirectlyByItem(var2, var9.i, var9.j, var9.k))
        {
            var9.AddFacingAsOffset(var6);
            var8 = Block.blocksList[var2.getBlockId(var9.i, var9.j, var9.k)];
            var2.getBlockId(var9.i, var9.j, var9.k);
        }

        if (var8 != null && var8.GetCanBeSetOnFireDirectlyByItem(var2, var9.i, var9.j, var9.k))
        {
            if (var8.SetOnFireDirectly(var2, var9.i, var9.j, var9.k))
            {
                return true;
            }
        }
        else if (var2.isAirBlock(var9.i, var9.j, var9.k))
        {
            var2.setBlockWithNotify(var9.i, var9.j, var9.k, Block.fire.blockID);
            var2.playSoundEffect((double)var3 + 0.5D, (double)var4 + 0.5D, (double)var5 + 0.5D, "mob.ghast.fireball", 1.0F, var2.rand.nextFloat() * 0.4F + 0.8F);
            return true;
        }

        return false;
    }

    public void NotifyNearbyAnimalsOfAttempt(EntityPlayer var1)
    {
        List var2 = var1.worldObj.getEntitiesWithinAABB(EntityAnimal.class, var1.boundingBox.expand(6.0D, 6.0D, 6.0D));
        Iterator var3 = var2.iterator();

        while (var3.hasNext())
        {
            EntityAnimal var4 = (EntityAnimal)var3.next();

            if (!var4.isDead)
            {
                var4.OnNearbyFireStartAttempt(var1);
            }
        }
    }
}
