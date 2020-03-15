package net.minecraft.src;

public class AddonBlockTrapDoor extends FCBlockTrapDoor {

	protected AddonBlockTrapDoor(int ID) {
		super(ID);
		this.setUnlocalizedName("trapdoor");
	}

	//CLIENT ONLY
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        //this.m_filterIcon = var1.registerIcon("fcBlockHopper_trap");
    }
    
    @Override public boolean RenderBlock(RenderBlocks r, int X, int Y, int Z)
	{
		super.setBlockBoundsForItemRender();
		super.setBlockBoundsBasedOnState(r.blockAccess, X, Y, Z);
        r.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(r.blockAccess, X, Y, Z));
		
        //0 east
        //1 west
        //2 north
        //3 south
        
		switch(r.blockAccess.getBlockMetadata(X, Y, Z)) {
		case 0:
		case 8:
			r.SetUvRotateTop(3);
			r.SetUvRotateBottom(3);
			break;
		case 1:
		case 9:
			r.SetUvRotateTop(0);
			r.SetUvRotateBottom(0);
			break;
		case 2:
		case 10:
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		case 3:
		case 11:
			r.SetUvRotateTop(2);
			r.SetUvRotateBottom(2);
			break;
		case 4:
		case 5:
			r.SetUvRotateEast(3);
			r.SetUvRotateWest(3);
			r.SetUvRotateNorth(1);
			r.SetUvRotateSouth(1);
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		case 6:
		case 7:
			r.SetUvRotateEast(1);
			r.SetUvRotateWest(1);
			r.SetUvRotateNorth(3);
			r.SetUvRotateSouth(3);
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		case 14:
		case 15:
			r.SetUvRotateTop(1);
			r.SetUvRotateBottom(1);
			break;
		default:
			break;
		}
		r.renderStandardBlock(this, X, Y, Z);
		r.ClearUvRotation();
		return true;
	}
}
