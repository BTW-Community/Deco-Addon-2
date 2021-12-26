package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSaplingAcacia extends DecoBlockSapling
{
    public static final String[] SAPLING_TYPES = new String[] {
			"acacia", "", "", "",
			"acacia", "", "", "",
			"acacia", "", "", "",
			"acaciaMature", "", "", ""
	};

    protected DecoBlockSaplingAcacia(int id) {
        super(id);
        this.setUnlocalizedName("decoBlockSaplingAcacia");
        this.baseTextureNames = new String[] {"decoBlockSaplingAcacia_0"};
    }
}