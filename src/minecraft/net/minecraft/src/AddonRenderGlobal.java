package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class AddonRenderGlobal extends RenderGlobal {
	private Minecraft mc;
	private RenderEngine renderEngine;
	private WorldClient world;
	
	public AddonRenderGlobal(Minecraft minecraft, RenderEngine renderEngine) {
		super(minecraft, renderEngine);
		this.mc = minecraft;
		this.renderEngine = renderEngine;
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
    	this.world = worldClient;
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

    /**
     * Spawns a particle. Arg: particleType, x, y, z, velX, velY, velZ
     */
    public void spawnParticle(String par1Str, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        try
        {
            this.doSpawnParticle(par1Str, par2, par4, par6, par8, par10, par12);
        }
        catch (Throwable var17)
        {
            CrashReport var15 = CrashReport.makeCrashReport(var17, "Exception while adding particle");
            CrashReportCategory var16 = var15.makeCategory("Particle being added");
            var16.addCrashSection("Name", par1Str);
            var16.addCrashSectionCallable("Position", new CallableParticlePositionInfo(this, par2, par4, par6));
            throw new ReportedException(var15);
        }
    }

    /**
     * Spawns a particle. Arg: particleType, x, y, z, velX, velY, velZ
     */
    public EntityFX doSpawnParticle(String par1Str, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        if (this.mc != null && this.mc.renderViewEntity != null && this.mc.effectRenderer != null)
        {
            int var14 = this.mc.gameSettings.particleSetting;

            if (var14 == 1 && this.world.rand.nextInt(3) == 0)
            {
                var14 = 2;
            }

            double var15 = this.mc.renderViewEntity.posX - par2;
            double var17 = this.mc.renderViewEntity.posY - par4;
            double var19 = this.mc.renderViewEntity.posZ - par6;
            EntityFX var21 = null;
            double var22;

            if (par1Str.equals("hugeexplosion"))
            {
                this.mc.effectRenderer.addEffect(var21 = new EntityHugeExplodeFX(this.world, par2, par4, par6, par8, par10, par12));
            }
            else if (par1Str.equals("largeexplode"))
            {
                this.mc.effectRenderer.addEffect(var21 = new EntityLargeExplodeFX(this.renderEngine, this.world, par2, par4, par6, par8, par10, par12));
            }
            else if (par1Str.equals("fireworksSpark"))
            {
                this.mc.effectRenderer.addEffect(var21 = new EntityFireworkSparkFX(this.world, par2, par4, par6, par8, par10, par12, this.mc.effectRenderer));
            }
            else if (par1Str.equals("fccinders"))
            {
                var22 = var15 * var15 + var17 * var17 + var19 * var19;

                if (var22 < 1024.0D)
                {
                    this.mc.effectRenderer.addEffect(var21 = new FCClientEntityCindersFX(this.world, par2, par4, par6));
                }
            }

            if (var21 != null)
            {
                return (EntityFX)var21;
            }
            else
            {
                var22 = 16.0D;

                if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
                {
                    return null;
                }
                else if (var14 > 1)
                {
                    return null;
                }
                else
                {
                    if (par1Str.equals("bubble"))
                    {
                        var21 = new EntityBubbleFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("suspended"))
                    {
                        var21 = new EntitySuspendFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("depthsuspend"))
                    {
                        var21 = new EntityAuraFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("townaura"))
                    {
                        var21 = new EntityAuraFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("crit"))
                    {
                        var21 = new EntityCritFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("magicCrit"))
                    {
                        var21 = new EntityCritFX(this.world, par2, par4, par6, par8, par10, par12);
                        ((EntityFX)var21).setRBGColorF(((EntityFX)var21).getRedColorF() * 0.3F, ((EntityFX)var21).getGreenColorF() * 0.8F, ((EntityFX)var21).getBlueColorF());
                        ((EntityFX)var21).nextTextureIndexX();
                    }
                    else if (par1Str.equals("smoke"))
                    {
                        var21 = new EntitySmokeFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("mobSpell"))
                    {
                        var21 = new EntitySpellParticleFX(this.world, par2, par4, par6, 0.0D, 0.0D, 0.0D);
                        ((EntityFX)var21).setRBGColorF((float)par8, (float)par10, (float)par12);
                    }
                    else if (par1Str.equals("mobSpellAmbient"))
                    {
                        var21 = new EntitySpellParticleFX(this.world, par2, par4, par6, 0.0D, 0.0D, 0.0D);
                        ((EntityFX)var21).setAlphaF(0.15F);
                        ((EntityFX)var21).setRBGColorF((float)par8, (float)par10, (float)par12);
                    }
                    else if (par1Str.equals("spell"))
                    {
                        var21 = new EntitySpellParticleFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("instantSpell"))
                    {
                        var21 = new EntitySpellParticleFX(this.world, par2, par4, par6, par8, par10, par12);
                        ((EntitySpellParticleFX)var21).setBaseSpellTextureIndex(144);
                    }
                    else if (par1Str.equals("witchMagic"))
                    {
                        var21 = new EntitySpellParticleFX(this.world, par2, par4, par6, par8, par10, par12);
                        ((EntitySpellParticleFX)var21).setBaseSpellTextureIndex(144);
                        float var24 = this.world.rand.nextFloat() * 0.5F + 0.35F;
                        ((EntityFX)var21).setRBGColorF(1.0F * var24, 0.0F * var24, 1.0F * var24);
                    }
                    else if (par1Str.equals("note"))
                    {
                        var21 = new EntityNoteFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("portal"))
                    {
                        var21 = new EntityPortalFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("enchantmenttable"))
                    {
                        var21 = new EntityEnchantmentTableParticleFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("explode"))
                    {
                        var21 = new EntityExplodeFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("flame"))
                    {
                        var21 = new EntityFlameFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("lava"))
                    {
                        var21 = new EntityLavaFX(this.world, par2, par4, par6);
                    }
                    else if (par1Str.equals("footstep"))
                    {
                        var21 = new EntityFootStepFX(this.renderEngine, this.world, par2, par4, par6);
                    }
                    else if (par1Str.equals("splash"))
                    {
                        var21 = new EntitySplashFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("largesmoke"))
                    {
                        var21 = new EntitySmokeFX(this.world, par2, par4, par6, par8, par10, par12, 2.5F);
                    }
                    else if (par1Str.equals("cloud"))
                    {
                        var21 = new EntityCloudFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("reddust"))
                    {
                        var21 = new EntityReddustFX(this.world, par2, par4, par6, (float)par8, (float)par10, (float)par12);
                    }
                    else if (par1Str.equals("snowballpoof"))
                    {
                        var21 = new EntityBreakingFX(this.world, par2, par4, par6, Item.snowball, this.renderEngine);
                    }
                    else if (par1Str.equals("dripWater"))
                    {
                        var21 = new EntityDropParticleFX(this.world, par2, par4, par6, Material.water);
                    }
                    else if (par1Str.equals("dripLava"))
                    {
                        var21 = new EntityDropParticleFX(this.world, par2, par4, par6, Material.lava);
                    }
                    else if (par1Str.equals("snowshovel"))
                    {
                        var21 = new EntitySnowShovelFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("slime"))
                    {
                        var21 = new EntityBreakingFX(this.world, par2, par4, par6, Item.slimeBall, this.renderEngine);
                    }
                    else if (par1Str.equals("heart"))
                    {
                        var21 = new EntityHeartFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("angryVillager"))
                    {
                        var21 = new EntityHeartFX(this.world, par2, par4 + 0.5D, par6, par8, par10, par12);
                        ((EntityFX)var21).setParticleTextureIndex(81);
                        ((EntityFX)var21).setRBGColorF(1.0F, 1.0F, 1.0F);
                    }
                    else if (par1Str.equals("happyVillager"))
                    {
                        var21 = new EntityAuraFX(this.world, par2, par4, par6, par8, par10, par12);
                        ((EntityFX)var21).setParticleTextureIndex(82);
                        ((EntityFX)var21).setRBGColorF(1.0F, 1.0F, 1.0F);
                    }
                    else if (par1Str.startsWith("iconcrack_"))
                    {
                        int var27 = Integer.parseInt(par1Str.substring(par1Str.indexOf("_") + 1));
                        var21 = new EntityBreakingFX(this.world, par2, par4, par6, par8, par10, par12, Item.itemsList[var27], this.renderEngine);
                    }
                    else if (par1Str.startsWith("tilecrack_"))
                    {
                        String[] var28 = par1Str.split("_", 3);
                        int var25 = Integer.parseInt(var28[1]);
                        int var26 = Integer.parseInt(var28[2]);
                        var21 = (new EntityDiggingFX(this.world, par2, par4, par6, par8, par10, par12, Block.blocksList[var25], 0, var26, this.renderEngine)).applyRenderColor(var26);
                    }
                    else if (par1Str.equals("fcwhitesmoke"))
                    {
                        var21 = new FCClientEntityWhiteSmokeFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("fcwhitecloud"))
                    {
                        var21 = new FCClientEntityWhiteCloudFX(this.world, par2, par4, par6, par8, par10, par12);
                    }
                    else if (par1Str.equals("fcsmallflame"))
                    {
                        var21 = new FCClientEntitySmallFlameFX(this.world, par2, par4, par6, par8, par10, par12);
                    }

                    if (var21 != null)
                    {
                        this.mc.effectRenderer.addEffect((EntityFX)var21);
                    }

                    return (EntityFX)var21;
                }
            }
        }
        else
        {
            return null;
        }
    }
}