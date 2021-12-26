package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSaplingCherry extends DecoBlockSapling
{
    public static final String[] SAPLING_TYPES = new String[] {
			"cherry", "", "", "",
			"cherry", "", "", "",
			"cherry", "", "", "",
			"cherryMature", "", "",  ""
	};

    protected DecoBlockSaplingCherry(int id) {
        super(id);
        this.setUnlocalizedName("decoBlockSaplingCherry");
        this.baseTextureNames = new String[] {"decoBlockSaplingCherry_0"};
    }
}