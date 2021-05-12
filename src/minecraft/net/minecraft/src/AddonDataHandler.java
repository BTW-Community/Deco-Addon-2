package net.minecraft.src;

public class AddonDataHandler extends AddonExt {

	private static AddonDataHandler instance;
	
	static {
		EntityList.addMapping(AddonEntityVillagerFarmer.class, "addonVillagerFarmer", 600, 5651507, 12422002);
		EntityList.addMapping(AddonEntityVillagerLibrarian.class, "addonVillagerLibrarian", 601, 14342874, 16179719);
		EntityList.addMapping(AddonEntityVillagerPriest.class, "addonVillagerPriest", 602, 8470879, 12422002);
		EntityList.addMapping(AddonEntityVillagerBlacksmith.class, "addonVillagerBlacksmith", 603, 4802889, 12422002);
		EntityList.addMapping(AddonEntityVillagerButcher.class, "addonVillagerButcher", 604, 11447982, 12422002);
		
		EntityList.entityEggs.remove(120);
	}

	public AddonDataHandler() {
		super("Extended Addon API", "1.1.1", "API");
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage("Initializing Addon Ext Handler...");
		initFarmer();
		initLibrarian();
		initPriest();
		initBlacksmith();
		initButcher();
		FCAddOnHandler.LogMessage("Addon Ext Handler Initialized");
	}
	
	public void initFarmer() {
		
	}
	
	public void initLibrarian() {
		
	}
	
	public void initPriest() {
		
	}
	
	public void initBlacksmith() {
		
	}
	
	public void initButcher() {
		
	}
	
	public static AddonDataHandler getInstance() {
		if (instance == null) {
			instance = new AddonDataHandler();
		}
		
		return instance;
	}

	public String GetLanguageFilePrefix()
	{
		return "AddonExt";
	}
}