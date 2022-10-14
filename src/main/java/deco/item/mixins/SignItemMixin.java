package deco.item.mixins;

import btw.item.items.SignItem;
import btw.world.util.BlockPos;
import deco.block.DecoBlockIDs;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SignItem.class)
public class SignItemMixin extends ItemSign {
	private int[] floorSigns;
	private int[] wallSigns;
	
	private String[] textures;
	
	public SignItemMixin(int itemID) {
		super(itemID);
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	public void setupArrays(CallbackInfo info) {
		floorSigns = new int[] {
				Block.signPost.blockID,
				DecoBlockIDs.SPRUCE_SIGN_ID,
				DecoBlockIDs.BIRCH_SIGN_ID,
				DecoBlockIDs.JUNGLE_SIGN_ID,
				DecoBlockIDs.BLOOD_SIGN_ID,
				DecoBlockIDs.CHERRY_SIGN_ID,
				DecoBlockIDs.ACACIA_SIGN_ID,
				DecoBlockIDs.MAHOGANY_SIGN_ID,
				DecoBlockIDs.MANGROVE_SIGN_ID,
				DecoBlockIDs.HAZEL_SIGN_ID,
				DecoBlockIDs.FIR_SIGN_ID
		};
		
		wallSigns = new int[] {
				Block.signWall.blockID,
				DecoBlockIDs.SPRUCE_WALL_SIGN_ID,
				DecoBlockIDs.BIRCH_WALL_SIGN_ID,
				DecoBlockIDs.JUNGLE_WALL_SIGN_ID,
				DecoBlockIDs.BLOOD_WALL_SIGN_ID,
				DecoBlockIDs.CHERRY_WALL_SIGN_ID,
				DecoBlockIDs.ACACIA_WALL_SIGN_ID,
				DecoBlockIDs.MAHOGANY_WALL_SIGN_ID,
				DecoBlockIDs.MANGROVE_WALL_SIGN_ID,
				DecoBlockIDs.HAZEL_WALL_SIGN_ID,
				DecoBlockIDs.FIR_WALL_SIGN_ID
		};
		
		textures = new String[] {
				"sign",
				"decoItemSignSpruce",
				"decoItemSignBirch",
				"decoItemSignJungle",
				"decoItemSignBlood",
				"decoItemSignCherry",
				"decoItemSignAcacia",
				"decoItemSignMahogany",
				"decoItemSignMangrove",
				"decoItemSignHazel",
				"decoItemSignFir"
		};
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int itemType = MathHelper.clamp_int(itemStack.getItemDamage(), 0, textures.length - 1);
		return this.getUnlocalizedName() + "." + WoodTypeHelper.woodNames[itemType];
	}
	
	@Inject(method = "onItemUse(Lnet/minecraft/src/ItemStack;Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;IIIIFFF)Z",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/src/World;" +
							"setBlock(IIIIII)Z",
					shift = At.Shift.AFTER,
					ordinal = 0
			))
	public void placeFloorSign(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> info) {
		BlockPos pos = new BlockPos(x, y, z, facing);
		int yaw = MathHelper.floor_double( ( ( player.rotationYaw + 180F ) * 16F / 360F ) + 0.5D ) & 15;
		world.setBlock(pos.x, pos.y, pos.z, floorSigns[stack.getItemDamage()], yaw, 2);
	}
	
	@Inject(method = "onItemUse(Lnet/minecraft/src/ItemStack;Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;IIIIFFF)Z",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/src/World;" +
							"setBlock(IIIIII)Z",
					shift = At.Shift.AFTER,
					ordinal = 1
			))
	public void placeWallSign(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> info) {
		BlockPos pos = new BlockPos(x, y, z, facing);
		world.setBlock(pos.x, pos.y, pos.z, wallSigns[stack.getItemDamage()], facing, 2);
	}
	
	@Inject(method = "onItemUse(Lnet/minecraft/src/ItemStack;Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;IIIIFFF)Z",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/src/EntityPlayer;" +
							"displayGUIEditSign(Lnet/minecraft/src/TileEntity;)V"
			))
	public void playSoundOnUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> info) {
		world.playSound(x, y, z, "dig.wood", 1.0F, .75F);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[textures.length];
		
		for (int i = 0; i < iconArray.length; i++) {
			iconArray[i] = register.registerIcon(textures[i]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIconFromDamage(int itemDamage) {
		int itemType = MathHelper.clamp_int(itemDamage, 0, textures.length - 1);
		return this.iconArray[itemType];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < textures.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}
