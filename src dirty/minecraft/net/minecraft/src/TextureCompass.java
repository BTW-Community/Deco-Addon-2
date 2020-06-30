package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class TextureCompass extends TextureStitched
{
    public static TextureCompass compassTexture;

    /** Current compass heading in radians */
    public double currentAngle;

    /** Speed and direction of compass rotation */
    public double angleDelta;
    private boolean m_bDirectionUpdated = false;

    public TextureCompass()
    {
        super("compass");
        compassTexture = this;
    }

    public void updateAnimation()
    {
        this.UpdateInert();
        this.m_bDirectionUpdated = false;
    }

    private void updateCompass(World var1, double var2, double var4, double var6, boolean var8, boolean var9, EntityPlayer var10)
    {
        double var11 = 0.0D;

        if (var1 != null && !var8)
        {
            var11 = this.ComputeCompassAngle(var2, var4, var6, var9, var10);
        }

        if (var9)
        {
            this.currentAngle = var11;
        }
        else if (!this.m_bDirectionUpdated)
        {
            double var13;

            for (var13 = var11 - this.currentAngle; var13 < -Math.PI; var13 += (Math.PI * 2D))
            {
                ;
            }

            while (var13 >= Math.PI)
            {
                var13 -= (Math.PI * 2D);
            }

            if (var13 < -1.0D)
            {
                var13 = -1.0D;
            }

            if (var13 > 1.0D)
            {
                var13 = 1.0D;
            }

            this.angleDelta += var13 * 0.1D;
            this.angleDelta *= 0.8D;
            this.currentAngle += this.angleDelta;
            this.m_bDirectionUpdated = true;
        }

        int var15;

        for (var15 = (int)((this.currentAngle / (Math.PI * 2D) + 1.0D) * (double)this.textureList.size()) % this.textureList.size(); var15 < 0; var15 = (var15 + this.textureList.size()) % this.textureList.size())
        {
            ;
        }

        if (var15 != this.frameCounter)
        {
            this.frameCounter = var15;
            this.textureSheet.copyFrom(this.originX, this.originY, (Texture)this.textureList.get(this.frameCounter), this.rotated);
        }
    }

    public void UpdateActive()
    {
        Minecraft var1 = Minecraft.getMinecraft();

        if (var1.theWorld != null && var1.thePlayer != null)
        {
            this.updateCompass(var1.theWorld, var1.thePlayer.posX, var1.thePlayer.posZ, (double)var1.thePlayer.rotationYaw, false, false, var1.thePlayer);
        }
        else
        {
            this.updateCompass((World)null, 0.0D, 0.0D, 0.0D, true, false, (EntityPlayer)null);
        }
    }

    public void UpdateInert()
    {
        this.frameCounter = this.textureList.size() / 2;
        this.textureSheet.copyFrom(this.originX, this.originY, (Texture)this.textureList.get(this.frameCounter), this.rotated);
    }

    private double ComputeCompassAngle(double var1, double var3, double var5, boolean var7, EntityPlayer var8)
    {
        double var9 = Math.PI;

        if (!var7 && var8 != null)
        {
            if (!var8.HasValidMagneticPointForLocation())
            {
                var9 = Math.random() * Math.PI * 2.0D;
            }
            else
            {
                int var11 = var8.GetStongestMagneticPointForLocationI();
                int var12 = var8.GetStongestMagneticPointForLocationK();
                double var13 = (double)var11 + 0.5D - var1;
                double var15 = (double)var12 + 0.5D - var3;
                var5 %= 360.0D;
                var9 = -((var5 - 90.0D) * Math.PI / 180.0D - Math.atan2(var15, var13));
            }
        }

        return var9;
    }
}
