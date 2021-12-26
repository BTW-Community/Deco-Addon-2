package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSaplingAutumn extends DecoBlockSapling
{
    public static final String[] SAPLING_TYPES = new String[] {
			"red", "orange", "yellow", "",
			"red", "orange", "yellow", "",
			"red", "orange", "yellow", "",
			"redMature", "orangeMature", "yellowMature", ""
	};

    protected DecoBlockSaplingAutumn(int id) {
        super(id);
        this.setUnlocalizedName("decoBlockSaplingAutumn");
        this.baseTextureNames = new String[] {"decoBlockSaplingAutumnRed_0", "decoBlockSaplingAutumnOrange_0", "decoBlockSaplingAutumnYellow_0"};
    }
}