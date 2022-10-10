package deco.block.blocks;

import btw.block.blocks.MortarReceiverSlabBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class LooseStoneSlabBlock extends MortarReceiverSlabBlock implements SlabInterface {
	public final Block[] blockTypes;
	private int[] mortaredIDs;
	private int[] mortaredMetas;
	
	public LooseStoneSlabBlock(int blockID, Block[] parents, int[] mortaredIDs, int[] mortaredMetas) {
		super(blockID, Material.rock);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setPicksEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("stoneLooseSlab");
		this.blockTypes = parents;
		this.mortaredIDs = mortaredIDs;
		this.mortaredMetas = mortaredMetas;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata & 7;
	}
	
	@Override
	public boolean onMortarApplied(World world, int x, int y, int z) {
		int looseMeta = world.getBlockMetadata(x, y, z);
		int mortaredMeta = mortaredMetas[looseMeta & 7];
		
		if (this.getIsUpsideDown(world, x, y, z)) {
			mortaredMeta |= 8;
			looseMeta |= 8;
		}
		
		world.setBlockAndMetadataWithNotify(x, y, z, mortaredIDs[looseMeta & 7], mortaredMeta);
		return true;
	}
	
	@Override
	public boolean getIsUpsideDown(int metadata) {
		return (metadata & 8) > 0;
	}
	
	@Override
	public int setIsUpsideDown(int metadata, boolean upsideDown) {
		if (upsideDown) {
			metadata |= 8;
		}
		else {
			metadata &= 7;
		}
		
		return metadata;
	}
	
	@Override
	public int getCombinedBlockID(int metadata) {
		return blockTypes[metadata & 7].blockID;
	}
	
	@Override
	public int getNumTypes() {
		return this.blockTypes.length;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		int baseMeta = meta & 7;
		return blockTypes[baseMeta].getIcon(side, baseMeta);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {}
}
