package net.minecraft.src;

public class FCItemCompositeBow extends FCItemBow
{
    private static int m_iMaxDamage = 576;
    private Icon[] drawIconArray = new Icon[3];

    public FCItemCompositeBow(int var1)
    {
        super(var1);
        this.setMaxDamage(m_iMaxDamage);
        this.setUnlocalizedName("fcItemBowComposite");
    }

    public boolean CanItemBeFiredAsArrow(int var1)
    {
        return var1 == Item.arrow.itemID || var1 == FCBetterThanWolves.fcItemRottenArrow.itemID || var1 == FCBetterThanWolves.fcItemBroadheadArrow.itemID;
    }

    public float GetPullStrengthToArrowVelocityMultiplier()
    {
        return 3.0F;
    }

    protected EntityArrow CreateArrowEntityForItem(World var1, EntityPlayer var2, int var3, float var4)
    {
        if (var3 == FCBetterThanWolves.fcItemBroadheadArrow.itemID)
        {
            return new FCEntityBroadheadArrow(var1, var2, var4 * this.GetPullStrengthToArrowVelocityMultiplier());
        }
        else if (var3 != FCBetterThanWolves.fcItemRottenArrow.itemID)
        {
            return super.CreateArrowEntityForItem(var1, var2, var3, var4);
        }
        else
        {
            var1.playSoundAtEntity(var2, "random.break", 0.8F, 0.8F + var1.rand.nextFloat() * 0.4F);

            if (var1.isRemote)
            {
                float var5 = -MathHelper.sin(var2.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(var2.rotationPitch / 180.0F * (float)Math.PI) * var4;
                float var6 = MathHelper.cos(var2.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(var2.rotationPitch / 180.0F * (float)Math.PI) * var4;
                float var7 = -MathHelper.sin(var2.rotationPitch / 180.0F * (float)Math.PI) * var4;

                for (int var8 = 0; var8 < 32; ++var8)
                {
                    var1.spawnParticle("iconcrack_333", var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, (double)var5 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F), (double)var7 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F), (double)var6 + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F));
                }
            }

            return null;
        }
    }

    protected void PlayerBowSound(World var1, EntityPlayer var2, float var3)
    {
        var1.playSoundAtEntity(var2, "random.bow", 1.0F, 1.5F / (itemRand.nextFloat() * 0.4F + 1.2F) + var3 * 0.5F);
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("fcItemBowComposite");
        this.drawIconArray[0] = var1.registerIcon("fcItemBowComposite_pull_0");
        this.drawIconArray[1] = var1.registerIcon("fcItemBowComposite_pull_1");
        this.drawIconArray[2] = var1.registerIcon("fcItemBowComposite_pull_2");
    }

    public Icon getDrawIcon(int var1)
    {
        return var1 >= 18 ? this.drawIconArray[2] : (var1 > 12 ? this.drawIconArray[1] : (var1 > 0 ? this.drawIconArray[0] : this.itemIcon));
    }
}
