package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class AddonEntityPlayerSP extends EntityPlayerSP {

	public AddonEntityPlayerSP(Minecraft par1Minecraft, World par2World, Session par3Session, int par4) {
		super(par1Minecraft, par2World, par3Session, par4);
	}

    public void updateEntityActionState()
    {
        super.updateEntityActionState();
        this.moveStrafing = this.movementInput.moveStrafe;
        this.moveForward = this.movementInput.moveForward;
        this.isJumping = this.movementInput.jump && !this.isClimbingFreestandingLadder();
        this.prevRenderArmYaw = this.renderArmYaw;
        this.prevRenderArmPitch = this.renderArmPitch;
        this.renderArmPitch = (float)((double)this.renderArmPitch + (double)(this.rotationPitch - this.renderArmPitch) * 0.5D);
        this.renderArmYaw = (float)((double)this.renderArmYaw + (double)(this.rotationYaw - this.renderArmYaw) * 0.5D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {

        if (this.mc.playerController.enableEverythingIsScrewedUpMode())
        {
            this.posX = this.posZ = 0.5D;
            this.posX = 0.0D;
            this.posZ = 0.0D;
            this.rotationYaw = (float)this.ticksExisted / 12.0F;
            this.rotationPitch = 10.0F;
            this.posY = 68.5D;
        }
        else
        {
            if (this.isClimbingFreestandingLadder()) {
            	this.motionY = 0.15D * this.GetLadderVerticalMovementModifier();
            }

            super.onLivingUpdate();
        }
    }
    
    private boolean isClimbingFreestandingLadder() {
    	return this.isOnLadder() && this.movementInput.jump;
    }
}