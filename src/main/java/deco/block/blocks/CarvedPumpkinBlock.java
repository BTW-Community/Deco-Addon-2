package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class CarvedPumpkinBlock extends Block {
	public CarvedPumpkinBlock(int id) {
		super(id, Material.pumpkin);
		this.setHardness(1.0F);
		this.setAxesEffectiveOn();
		this.setBuoyant();
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("pumpkin");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return 0;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int var5, int var6) {
		super.breakBlock(world, x, y, z, var5, var6);
		
		if (!world.isRemote) {
			world.playAuxSFX(2251, x, y, z, 0);
		}
	}
	
	@Override
	public int rotateMetadataAroundJAxis(int metadata, boolean isReversed) {
		int damage = metadata % 4;
		int facing = metadata / 4;
		int newFacing;
		
		if (!isReversed) {
			newFacing = (facing - 1) % 4;
		}
		else {
			newFacing = (facing + 1) % 4;
		}
		
		return damage + newFacing * 4;
	}
	
	@Override
	public boolean canBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
		return animal.canGrazeOnRoughVegetation();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack) {
		int yaw = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		int damage = itemStack.getItemDamage();
		world.setBlockMetadataWithNotify(x, y, z, yaw * 4 + damage, 2);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	public Icon sideIcon;
	@Environment(EnvType.CLIENT)
	public Icon topIcon;
	@Environment(EnvType.CLIENT)
	public Icon[] faces;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		int damage = metadata % 4;
		int facing = metadata / 4;
		
		switch (side) {
			case 0:
			case 1:
				return topIcon;
			case 2:
				return facing == 2 ? faces[damage] : sideIcon;
			case 3:
				return facing == 0 ? faces[damage] : sideIcon;
			case 4:
				return facing == 1 ? faces[damage] : sideIcon;
			case 5:
				return facing == 3 ? faces[damage] : sideIcon;
			default:
				return sideIcon;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		faces = new Icon[3];
		faces[0] = register.registerIcon("decoBlockPumpkin_0");
		faces[1] = register.registerIcon("decoBlockPumpkin_1");
		faces[2] = register.registerIcon("decoBlockPumpkin_2");
		this.topIcon = register.registerIcon("pumpkin_top");
		this.sideIcon = register.registerIcon("pumpkin_side");
	}
}
