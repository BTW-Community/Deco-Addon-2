package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class DawnRenderGlobal extends RenderGlobal {
	private Minecraft mc;
	private RenderEngine renderEngine;
	private WorldClient theWorld;
	
	public DawnRenderGlobal(Minecraft mc, RenderEngine renderEngine) {
		super(mc, renderEngine);
		this.mc = mc;
		this.renderEngine = renderEngine;
	}

    /**
     * set null to clear
     */
    public void setWorldAndLoadRenderers(WorldClient worldClient)
    {
    	super.setWorldAndLoadRenderers(worldClient);
        this.theWorld = worldClient;
    }

    /**
     * Spawns a particle. Arg: particleType, x, y, z, velX, velY, velZ
     */
    public EntityFX doSpawnParticle(String particleType, double x, double y, double z, double velX, double velY, double velZ)
    {
    	EntityFX fx = super.doSpawnParticle(particleType, x, y, z, velX, velY, velZ);
    	
    	if (fx == null)
    		return DawnAddonHandler.spawnCustomParticle(this.mc, this.theWorld, particleType, x, y, z, velX, velY, velZ);
    	else
    		return fx;
    }
}