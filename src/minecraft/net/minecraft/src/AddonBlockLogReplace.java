package net.minecraft.src;

import java.util.List;

public class AddonBlockLogReplace extends FCBlockLog {
	private String[] topTextureTypes = {"tree_top", "ginger_logSpruceTop", "ginger_logBirchTop", "ginger_logJungleTop"};
	private String[] trunkTopTextureTypes = {"fcBlockTrunkTop", "ginger_trunkSpruceTop", "ginger_trunkBirchTop", "ginger_trunkJungleTop"};
	
	public AddonBlockLogReplace(int ID) {
		super(ID);
	}
	
	//CLIENT ONLY
	private Icon[] topIcons;
	private Icon[] trunkTopIcons;
    
    @Override public Icon getIcon(int var1, int var2)
    {
        int var3 = var2 & 12;
        int var4 = var2 & 3;
        
        return (var2 & 12) == 12 ? (var1 > 1 ? super.getIcon(var1, var2) : this.trunkTopIcons[var4]) : var3 == 0 && (var1 == 1 || var1 == 0) ? this.topIcons[var4] : (var3 == 4 && (var1 == 5 || var1 == 4) ? this.topIcons[var4] : (var3 == 8 && (var1 == 2 || var1 == 3) ? this.topIcons[var4] : super.getIcon(var1, var2)));
    }
    
    @Override 
    public void registerIcons(IconRegister Register)
    {
    	super.registerIcons(Register);
    	
    	topIcons = new Icon[topTextureTypes.length];
    	trunkTopIcons = new Icon[topTextureTypes.length];

    	for (int i = 0; i < topIcons.length; i++) {
    		topIcons[i] = Register.registerIcon(topTextureTypes[i]);
    		trunkTopIcons[i] = Register.registerIcon(trunkTopTextureTypes[i]);
    	}
    }
}
