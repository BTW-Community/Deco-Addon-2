package net.minecraft.src;

public class FCClientModelSquid extends ModelSquid
{
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
        this.squidBody.render(var7);
        byte var8 = -1;

        if (((FCEntitySquid)var1).m_iTentacleAttackInProgressCounter > 0)
        {
            var8 = 6;
        }

        for (int var9 = 0; var9 < this.squidTentacles.length; ++var9)
        {
            if (var9 != var8)
            {
                this.squidTentacles[var9].render(var7);
            }
        }
    }
}
