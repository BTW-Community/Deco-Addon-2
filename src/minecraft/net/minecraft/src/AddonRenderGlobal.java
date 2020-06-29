package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class AddonRenderGlobal extends RenderGlobal {
	public AddonRenderGlobal(Minecraft par1Minecraft, RenderEngine par2RenderEngine) {
		super(par1Minecraft, par2RenderEngine);
	}

    /**
     * set null to clear
     */
    public void setWorldAndLoadRenderers(WorldClient worldClient)
    {
        this.prevSortX = -9999.0D;
        this.prevSortY = -9999.0D;
        this.prevSortZ = -9999.0D;
        RenderManager.instance.set(worldClient);
        setWorldAndLoadRenderers_reflect(worldClient);

        if (worldClient != null)
        {
            worldClient.addWorldAccess(this);
            this.loadRenderers();
        }
    }
    
    private void setWorldAndLoadRenderers_reflect(WorldClient worldClient) {
    	try {
			Field worldField;
			Field renderBlocksField;

			if (AddonManager.isObfuscated()) {
				worldField = this.getClass().getSuperclass().getDeclaredField("r");
				renderBlocksField = this.getClass().getSuperclass().getDeclaredField("h");
			}
			else {
				worldField = this.getClass().getSuperclass().getDeclaredField("theWorld");
				renderBlocksField = this.getClass().getSuperclass().getDeclaredField("globalRenderBlocks");
			}

			worldField.setAccessible(true);
			renderBlocksField.setAccessible(true);
			
			WorldClient world = (WorldClient) worldField.get(this);
			
			if (world != null)
	        {
				world.removeWorldAccess(this);
	        }
			
			worldField.set(this, worldClient);
		} catch (NoSuchFieldException e) {
			if (AddonManager.isObfuscated()) {
				e.printStackTrace();
			}
			else {
				AddonManager.setObfuscated(true);
				setWorldAndLoadRenderers_reflect(worldClient);
			}
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
}