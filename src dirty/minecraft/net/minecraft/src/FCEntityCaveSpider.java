package net.minecraft.src;

public class FCEntityCaveSpider extends FCEntitySpider
{
    public FCEntityCaveSpider(World var1)
    {
        super(var1);
        this.texture = "/mob/cavespider.png";
        this.setSize(0.7F, 0.5F);
    }

    public int getMaxHealth()
    {
        return 12;
    }

    public boolean attackEntityAsMob(Entity var1)
    {
        if (super.attackEntityAsMob(var1))
        {
            if (var1 instanceof EntityLiving)
            {
                ((EntityLiving)var1).addPotionEffect(new PotionEffect(Potion.poison.id, 140, 0));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Initialize this creature.
     */
    public void initCreature() {}

    public boolean DoesLightAffectAggessiveness()
    {
        return false;
    }

    protected boolean DropsSpiderEyes()
    {
        return false;
    }

    protected void CheckForSpiderSkeletonMounting() {}

    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount()
    {
        return 0.7F;
    }
}
