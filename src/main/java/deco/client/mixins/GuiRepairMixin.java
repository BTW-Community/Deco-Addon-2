package deco.client.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiRepair.class)
@Environment(EnvType.CLIENT)
/**
 * Overwrites used here because lazy, and it's highly unlikely any other addon would edit this
 */
public abstract class GuiRepairMixin extends GuiContainer {
	public GuiRepairMixin(Container container) {
		super(container);
	}
	
	@Shadow
	private ContainerRepair repairContainer;
	@Shadow
	private InventoryPlayer field_82325_q;
	
	/**
	 * @author dawnraider00 - Deco Addon
	 * @reason Lazy, unlikely to conflict, difficult mixin otherwise
	 */
	@Overwrite
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glDisable(GL11.GL_LIGHTING);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.repair"), 60, 6, 4210752);
		
		if (this.repairContainer.maximumCost > 0) {
			int var3 = 8453920;
			boolean var4 = true;
			String var5 = StatCollector.translateToLocalFormatted("container.repair.cost", new Object[]{Integer.valueOf(this.repairContainer.maximumCost)});
			
			if (this.repairContainer.maximumCost >= 40 && !this.mc.thePlayer.capabilities.isCreativeMode) {
				var5 = StatCollector.translateToLocal("container.repair.expensive");
				var3 = 16736352;
			}
			else if (!this.repairContainer.getSlot(2).getHasStack()) {
				var4 = false;
			}
			else if (!this.repairContainer.getSlot(2).canTakeStack(this.field_82325_q.player)) {
				var3 = 16736352;
			}
			
			if (var4) {
				int var6 = -16777216 | (var3 & 16579836) >> 2 | var3 & -16777216;
				int var7 = this.xSize - 8 - this.fontRenderer.getStringWidth(var5);
				byte var8 = 67;
				
				if (this.fontRenderer.getUnicodeFlag()) {
					drawRect(var7 - 3, var8 - 2, this.xSize - 7, var8 + 10, -16777216);
					drawRect(var7 - 2, var8 - 1, this.xSize - 8, var8 + 9, -12895429);
				}
			}
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	
	/**
	 * @author dawnraider00 - Deco Addon
	 * @reason Lazy, unlikely to conflict, difficult mixin otherwise
	 */
	@Overwrite
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/deco/guiName.png");
		int var4 = (this.width - this.xSize) / 2;
		int var5 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
		this.drawTexturedModalRect(var4 + 59, var5 + 20, 0, this.ySize + (this.repairContainer.getSlot(0).getHasStack() ? 0 : 16), 110, 16);
		
		if ((this.repairContainer.getSlot(0).getHasStack() || this.repairContainer.getSlot(1).getHasStack()) &&
				!this.repairContainer.getSlot(2).getHasStack()) {
			this.drawTexturedModalRect(var4 + 99, var5 + 45, this.xSize, 0, 28, 21);
		}
	}
}
