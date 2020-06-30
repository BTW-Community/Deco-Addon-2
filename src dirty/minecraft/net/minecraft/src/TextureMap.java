package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class TextureMap implements IconRegister
{
    /** 0 = terrain.png, 1 = items.png */
    private final int textureType;
    private final String textureName;
    private final String basePath;
    private final String textureExt;
    private final HashMap mapTexturesStiched = new HashMap();
    private BufferedImage missingImage = new BufferedImage(64, 64, 2);
    private TextureStitched missingTextureStiched;
    private Texture atlasTexture;
    private final List listTextureStiched = new ArrayList();
    private final Map textureStichedMap = new HashMap();

    public TextureMap(int par1, String par2, String par3Str, BufferedImage par4BufferedImage)
    {
        this.textureType = par1;
        this.textureName = par2;
        this.basePath = par3Str;
        this.textureExt = ".png";
        this.missingImage = par4BufferedImage;
    }

    public void refreshTextures()
    {
        this.textureStichedMap.clear();
        int var1;
        int var2;

        if (this.textureType == 0)
        {
            Block[] var3 = Block.blocksList;
            var1 = var3.length;

            for (var2 = 0; var2 < var1; ++var2)
            {
                Block var4 = var3[var2];

                if (var4 != null)
                {
                    var4.registerIcons(this);
                }
            }

            Minecraft.getMinecraft().renderGlobal.registerDestroyBlockIcons(this);
            RenderManager.instance.updateIcons(this);
        }

        Item[] var22 = Item.itemsList;
        var1 = var22.length;

        for (var2 = 0; var2 < var1; ++var2)
        {
            Item var23 = var22[var2];

            if (var23 != null && var23.getSpriteNumber() == this.textureType)
            {
                var23.registerIcons(this);
            }
        }

        HashMap var24 = new HashMap();
        Stitcher var5 = TextureManager.instance().createStitcher(this.textureName);
        this.mapTexturesStiched.clear();
        this.listTextureStiched.clear();
        Texture var6 = TextureManager.instance().makeTexture("missingno", 2, this.missingImage.getWidth(), this.missingImage.getHeight(), 10496, 6408, 9728, 9728, false, this.missingImage);
        StitchHolder var7 = new StitchHolder(var6);
        var5.addStitchHolder(var7);
        var24.put(var7, Arrays.asList(new Texture[] {var6}));
        Iterator var8 = this.textureStichedMap.keySet().iterator();

        while (var8.hasNext())
        {
            String var9 = (String)var8.next();
            String var10 = this.basePath + var9 + this.textureExt;
            List var11 = TextureManager.instance().createTexture(var10);

            if (!var11.isEmpty())
            {
                StitchHolder var12 = new StitchHolder((Texture)var11.get(0));
                var5.addStitchHolder(var12);
                var24.put(var12, var11);
            }
        }

        try
        {
            var5.doStitch();
        }
        catch (StitcherException var21)
        {
            throw var21;
        }

        this.atlasTexture = var5.getTexture();
        var8 = var5.getStichSlots().iterator();

        while (var8.hasNext())
        {
            StitchSlot var25 = (StitchSlot)var8.next();
            StitchHolder var27 = var25.getStitchHolder();
            Texture var28 = var27.func_98150_a();
            String var29 = var28.getTextureName();
            List var13 = (List)var24.get(var27);
            TextureStitched var14 = (TextureStitched)this.textureStichedMap.get(var29);
            boolean var15 = false;

            if (var14 == null)
            {
                var15 = true;
                var14 = TextureStitched.makeTextureStitched(var29);

                if (!var29.equals("missingno"))
                {
                    Minecraft.getMinecraft().getLogAgent().logWarning("Couldn\'t find premade icon for " + var29 + " doing " + this.textureName);
                }
            }

            var14.init(this.atlasTexture, var13, var25.getOriginX(), var25.getOriginY(), var27.func_98150_a().getWidth(), var27.func_98150_a().getHeight(), var27.isRotated());
            this.mapTexturesStiched.put(var29, var14);

            if (!var15)
            {
                this.textureStichedMap.remove(var29);
            }

            if (var13.size() > 1)
            {
                this.listTextureStiched.add(var14);
                String var16 = this.basePath + var29 + ".txt";
                ITexturePack var17 = Minecraft.getMinecraft().texturePackList.getSelectedTexturePack();
                boolean var18 = !var17.func_98138_b("/" + this.basePath + var29 + ".png", false);

                try
                {
                    InputStream var19 = var17.func_98137_a("/" + var16, var18);
                    var14.readAnimationInfo(new BufferedReader(new InputStreamReader(var19)));
                }
                catch (IOException var20)
                {
                    ;
                }
            }
            else if (var14.IsProcedurallyAnimated())
            {
                this.listTextureStiched.add(var14);
            }
        }

        this.missingTextureStiched = (TextureStitched)this.mapTexturesStiched.get("missingno");
        var8 = this.textureStichedMap.values().iterator();

        while (var8.hasNext())
        {
            TextureStitched var26 = (TextureStitched)var8.next();
            var26.copyFrom(this.missingTextureStiched);
        }

        this.atlasTexture.writeImage("debug.stitched_" + this.textureName + ".png");
        this.atlasTexture.uploadTexture();
    }

    public void updateAnimations()
    {
        FCClientAnimationFire.UpdateInstances();
        Iterator var1 = this.listTextureStiched.iterator();

        while (var1.hasNext())
        {
            TextureStitched var2 = (TextureStitched)var1.next();
            var2.updateAnimation();
        }
    }

    public Texture getTexture()
    {
        return this.atlasTexture;
    }

    public Icon registerIcon(String par1Str)
    {
        if (par1Str == null)
        {
            (new RuntimeException("Don\'t register null!")).printStackTrace();
        }

        TextureStitched var2 = (TextureStitched)this.textureStichedMap.get(par1Str);

        if (var2 == null)
        {
            var2 = TextureStitched.makeTextureStitched(par1Str);
            this.textureStichedMap.put(par1Str, var2);
        }

        return var2;
    }

    public Icon getMissingIcon()
    {
        return this.missingTextureStiched;
    }

    public Icon registerIcon(String var1, TextureStitched var2)
    {
        if (var1 == null)
        {
            (new RuntimeException("Don\'t register null!")).printStackTrace();
        }

        TextureStitched var3 = (TextureStitched)this.textureStichedMap.get(var1);

        if (var3 == null)
        {
            var3 = var2;
            this.textureStichedMap.put(var1, var2);
        }

        return var3;
    }
}
