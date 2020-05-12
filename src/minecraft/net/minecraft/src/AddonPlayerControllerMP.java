package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class AddonPlayerControllerMP extends PlayerControllerMP {
    /** The Minecraft instance. */
    private final Minecraft mc;
    private final NetClientHandler netClientHandler;

	public AddonPlayerControllerMP(Minecraft par1Minecraft, NetClientHandler par2NetClientHandler) {
		super(par1Minecraft, par2NetClientHandler);
        this.mc = par1Minecraft;
        this.netClientHandler = par2NetClientHandler;
	}

    public EntityClientPlayerMP func_78754_a(World par1World)
    {
    	System.out.println("Addon player controller new entity");
        return new EntityClientPlayerMP(this.mc, par1World, this.mc.session, this.netClientHandler);
    }

}
