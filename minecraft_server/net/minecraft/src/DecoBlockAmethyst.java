package net.minecraft.src;

import java.util.Random;

public class DecoBlockAmethyst extends Block {
    public DecoBlockAmethyst(int id) {
        super(id, Material.glass);
        this.setHardness(0.3F);
        this.setStepSound(Block.soundGlassFootstep);
        this.setUnlocalizedName("decoBlockAmethyst");
        this.SetPicksEffectiveOn();
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    @Override
    public boolean IsNormalCube(IBlockAccess var1, int var2, int var3, int var4) {
    	return true;
    }

    @Override
    public int quantityDroppedWithBonus(int fortuneLevel, Random rand) {
        return MathHelper.clamp_int(this.quantityDropped(rand) + rand.nextInt(fortuneLevel + 1), 1, 4);
    }

    @Override
    public int quantityDropped(Random rand) {
        return 2 + rand.nextInt(3);
    }

    @Override
    public int idDropped(int par1, Random rand, int par3) {
        return DecoDefs.amethystShard.itemID;
    }
}
