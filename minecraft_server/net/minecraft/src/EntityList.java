package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntityList
{
	/** Provides a mapping between entity classes and a string */
	private static Map stringToClassMapping = new HashMap();

	/** Provides a mapping between a string and an entity classes */
	private static Map classToStringMapping = new HashMap();

	/** provides a mapping between an entityID and an Entity Class */
	private static Map IDtoClassMapping = new HashMap();

	/** provides a mapping between an Entity Class and an entity ID */
	private static Map classToIDMapping = new HashMap();

	/** Maps entity names to their numeric identifiers */
	private static Map stringToIDMapping = new HashMap();

	/** This is a HashMap of the Creative Entity Eggs/Spawners. */
	public static HashMap entityEggs = new LinkedHashMap();

	/**
	 * adds a mapping between Entity classes and both a string representation and an ID
	 */
	// FCMOD: Code change
	/*
    private static void addMapping(Class par0Class, String par1Str, int par2)
	 */
	public static void addMapping(Class par0Class, String par1Str, int par2)
	// END FCMOD
	{
		stringToClassMapping.put(par1Str, par0Class);
		classToStringMapping.put(par0Class, par1Str);
		IDtoClassMapping.put(Integer.valueOf(par2), par0Class);
		classToIDMapping.put(par0Class, Integer.valueOf(par2));
		stringToIDMapping.put(par1Str, Integer.valueOf(par2));
	}

	/**
	 * Adds a entity mapping with egg info.
	 */
	public static void addMapping(Class par0Class, String par1Str, int par2, int par3, int par4)
	{
		addMapping(par0Class, par1Str, par2);
		entityEggs.put(Integer.valueOf(par2), new EntityEggInfo(par2, par3, par4));
	}

	/**
	 * Create a new instance of an entity in the world by using the entity name.
	 */
	public static Entity createEntityByName(String par0Str, World par1World)
	{
		Entity var2 = null;

		try
		{
			Class var3 = (Class)stringToClassMapping.get(par0Str);

			if (var3 != null)
			{
				if (FCEntityVillager.class.equals(var3)) {
					var2 = FCEntityVillager.createVillager(par1World);
				}
				else {
					var2 = (Entity)var3.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
				}
			}
		}
		catch (Exception var4)
		{
			var4.printStackTrace();
		}

		return var2;
	}

	/**
	 * create a new instance of an entity from NBT store
	 */
	public static Entity createEntityFromNBT(NBTTagCompound par0NBTTagCompound, World par1World)
	{
		Entity var2 = null;

		if ("Minecart".equals(par0NBTTagCompound.getString("id")))
		{
			switch (par0NBTTagCompound.getInteger("Type"))
			{
			case 0:
				par0NBTTagCompound.setString("id", "MinecartRideable");
				break;

			case 1:
				par0NBTTagCompound.setString("id", "MinecartChest");
				break;

			case 2:
				par0NBTTagCompound.setString("id", "MinecartFurnace");
			}

			par0NBTTagCompound.removeTag("Type");
		}

		try
		{
			Class var3 = (Class)stringToClassMapping.get(par0NBTTagCompound.getString("id"));

			if (var3 != null)
			{
				if (FCEntityVillager.class.equals(var3)) {
					int profession = par0NBTTagCompound.getInteger("Profession");

					var2 = FCEntityVillager.createVillagerFromProfession(par1World, profession);
				}
				else if (EntityPlayerMP.class.equals(var3)) {
					return null;
				}
				else {
					var3 = getRegisteredReplacement(var3);
					
					var2 = (Entity)var3.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
				}
			}
		}
		catch (Exception var4)
		{
			var4.printStackTrace();
		}

		if (var2 != null)
		{
			var2.readFromNBT(par0NBTTagCompound);
		}
		else
		{
			//Client
			//par1World.getWorldLogAgent().logWarning("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
			//Server
			par1World.getWorldLogAgent().func_98236_b("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
		}

		return var2;
	}

	/**
	 * Create a new instance of an entity in the world by using an entity ID.
	 */
	public static Entity createEntityByID(int par0, World par1World)
	{
		Entity var2 = null;

		try
		{
			Class var3 = getClassFromID(par0);

			if (var3 != null)
			{
				if (FCEntityVillager.class.equals(var3)) {
					var2 = FCEntityVillager.createVillager(par1World);
				}
				else {
					var2 = (Entity)var3.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
				}
			}
		}
		catch (Exception var4)
		{
			var4.printStackTrace();
		}

		if (var2 == null)
		{
			//Client
			//par1World.getWorldLogAgent().logWarning("Skipping Entity with id " + par0);
			//Server
			par1World.getWorldLogAgent().func_98236_b("Skipping Entity with id " + par0);
		}

		return var2;
	}

	/**
	 * gets the entityID of a specific entity
	 */
	public static int getEntityID(Entity par0Entity)
	{
		Class var1 = par0Entity.getClass();
		return classToIDMapping.containsKey(var1) ? ((Integer)classToIDMapping.get(var1)).intValue() : 0;
	}

	/**
	 * Return the class assigned to this entity ID.
	 */
	public static Class getClassFromID(int par0)
	{
		return (Class)IDtoClassMapping.get(Integer.valueOf(par0));
	}

	/**
	 * Gets the string representation of a specific entity.
	 */
	public static String getEntityString(Entity par0Entity)
	{
		return (String)classToStringMapping.get(par0Entity.getClass());
	}

	/**
	 * Finds the class using IDtoClassMapping and classToStringMapping
	 */
	public static String getStringFromID(int par0)
	{
		Class var1 = getClassFromID(par0);
		return var1 != null ? (String)classToStringMapping.get(var1) : null;
	}

	static
	{
		addMapping(EntityItem.class, "Item", 1);
		addMapping(EntityXPOrb.class, "XPOrb", 2);
		addMapping(EntityPainting.class, "Painting", 9);
		addMapping(EntityArrow.class, "Arrow", 10);
		addMapping(EntitySnowball.class, "Snowball", 11);
		addMapping(EntityLargeFireball.class, "Fireball", 12);
		addMapping(EntitySmallFireball.class, "SmallFireball", 13);
		addMapping(EntityEnderPearl.class, "ThrownEnderpearl", 14);
		addMapping(EntityEnderEye.class, "EyeOfEnderSignal", 15);
		addMapping(EntityPotion.class, "ThrownPotion", 16);
		addMapping(EntityExpBottle.class, "ThrownExpBottle", 17);
		addMapping(EntityItemFrame.class, "ItemFrame", 18);
		addMapping(EntityWitherSkull.class, "WitherSkull", 19);
		addMapping(EntityTNTPrimed.class, "PrimedTnt", 20);
		addMapping(EntityFallingSand.class, "FallingSand", 21);
		addMapping(EntityFireworkRocket.class, "FireworksRocketEntity", 22);
		addMapping(EntityBoat.class, "Boat", 41);
		addMapping(EntityMinecartEmpty.class, "MinecartRideable", 42);
		addMapping(EntityMinecartChest.class, "MinecartChest", 43);
		addMapping(EntityMinecartFurnace.class, "MinecartFurnace", 44);
		addMapping(EntityMinecartTNT.class, "MinecartTNT", 45);
		addMapping(EntityMinecartHopper.class, "MinecartHopper", 46);
		addMapping(EntityMinecartMobSpawner.class, "MinecartSpawner", 47);
		addMapping(EntityLiving.class, "Mob", 48);
		addMapping(EntityMob.class, "Monster", 49);
		addMapping(EntityCreeper.class, "Creeper", 50, 894731, 0);
		addMapping(EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
		addMapping(EntitySpider.class, "Spider", 52, 3419431, 11013646);
		addMapping(EntityGiantZombie.class, "Giant", 53);
		addMapping(EntityZombie.class, "Zombie", 54, 44975, 7969893);
		addMapping(EntitySlime.class, "Slime", 55, 5349438, 8306542);
		addMapping(EntityGhast.class, "Ghast", 56, 16382457, 12369084);
		addMapping(EntityPigZombie.class, "PigZombie", 57, 15373203, 5009705);
		addMapping(EntityEnderman.class, "Enderman", 58, 1447446, 0);
		addMapping(EntityCaveSpider.class, "CaveSpider", 59, 803406, 11013646);
		addMapping(EntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
		addMapping(EntityBlaze.class, "Blaze", 61, 16167425, 16775294);
		addMapping(EntityMagmaCube.class, "LavaSlime", 62, 3407872, 16579584);
		addMapping(EntityDragon.class, "EnderDragon", 63);
		addMapping(EntityWither.class, "WitherBoss", 64);
		addMapping(EntityBat.class, "Bat", 65, 4996656, 986895);
		addMapping(EntityWitch.class, "Witch", 66, 3407872, 5349438);
		addMapping(EntityPig.class, "Pig", 90, 15771042, 14377823);
		addMapping(EntitySheep.class, "Sheep", 91, 15198183, 16758197);
		addMapping(EntityCow.class, "Cow", 92, 4470310, 10592673);
		addMapping(EntityChicken.class, "Chicken", 93, 10592673, 16711680);
		addMapping(EntitySquid.class, "Squid", 94, 2243405, 7375001);
		addMapping(EntityWolf.class, "Wolf", 95, 14144467, 13545366);
		addMapping(EntityMooshroom.class, "MushroomCow", 96, 10489616, 12040119);
		addMapping(EntitySnowman.class, "SnowMan", 97);
		addMapping(EntityOcelot.class, "Ozelot", 98, 15720061, 5653556);
		addMapping(EntityIronGolem.class, "VillagerGolem", 99);
		addMapping(EntityVillager.class, "Villager", 120, 5651507, 12422002);
		addMapping(EntityEnderCrystal.class, "EnderCrystal", 200);

		//Only used for entity factory
		//Dummy ids only for entity replacing
		int id = -1;

		addMapping(EntityPlayerMP.class, "PlayerMP", id--);
		addMapping(FCEntityLightningBolt.class, "LightningBolt", id--);
		addMapping(EntityEgg.class, "Egg", id--);
		addMapping(EntitySnowball.class, "Snowball", id--);
		addMapping(EntityFishHook.class, "FishHook", id--);
		addMapping(EntityDragonPart.class, "DragonPart",id--);
	}

	public static void AddMapping( Class entityClass, String sName, int iID )
	{
		addMapping( entityClass, sName, iID );
	}

	public static boolean RemoveMapping( String sName, boolean bRemoveEgg )
	{
		Integer iID = (Integer)stringToIDMapping.get( sName );

		if ( iID != null )
		{
			Class mappedClass = (Class)IDtoClassMapping.get( Integer.valueOf( iID ) );

			if ( mappedClass != null )
			{
				stringToClassMapping.remove( sName );
				classToStringMapping.remove( mappedClass );
				IDtoClassMapping.remove( iID );
				classToIDMapping.remove( mappedClass );
				stringToIDMapping.remove( sName );

				if ( bRemoveEgg )
				{
					// may or may not have an egg, but this is a safe operation

					entityEggs.remove( iID );
				}

				return true;
			}
		}

		return false;
	}

	public static boolean ReplaceExistingMapping( Class newClass, String sName )
	{    	
		Integer iID = (Integer)stringToIDMapping.get( sName );

		if ( iID != null )
		{
			if ( RemoveMapping( sName, false ) ) // egg mapping intentionally left in place
			{    		
				addMapping( newClass, sName, iID );
			}
		}

		return false;
	}

	/**
	 * Replaces entity mapping but leaves the mapping from class to id so the game won't crash if it tries to spawn something of a class which has been replaced
	 * Does not handle spawnable mobs
	 * @param newClass The new class to be added to the mapping
	 * @param entityName The internal name of the entity
	 * @return
	 */
	public static boolean replaceExistingMappingSafe(Class newClass, String entityName) {
		int id = (Integer) stringToIDMapping.get(entityName);

		if (removeMappingSafe(entityName, false)) {
			addMapping(newClass, entityName, id);
		}

		return false;
	}

	public static boolean removeMappingSafe(String var0, boolean var1)
	{
		Integer var2 = (Integer)stringToIDMapping.get(var0);

		if (var2 != null)
		{
			Class var3 = (Class)IDtoClassMapping.get(Integer.valueOf(var2.intValue()));

			if (var3 != null)
			{
				stringToClassMapping.remove(var0);
				IDtoClassMapping.remove(var2);

				if (var1)
				{
					entityEggs.remove(var2);
				}

				return true;
			}
		}

		return false;
	}

	public static Entity createEntityOfType(Class<? extends Entity> entityClass, Object ... parameters) {
		Entity entity = null; 

		Class[] parameterTypes = new Class[parameters.length];
		Object[] parameterValues = new Object[parameters.length];

		for (int i = 0; i < parameters.length; i++) {
			Class<?> type = parameters[i].getClass();

			Class<?> primitiveType = FCUtilsReflection.getPrimitiveFromBoxedClass(type);

			if (primitiveType != null) {
				type = primitiveType;
			}

			parameterTypes[i] = type;
			parameterValues[i] = parameters[i];
		}

		entityClass = getRegisteredReplacement(entityClass);

		if (EntityItem.class.isAssignableFrom(entityClass)) {
			for (Object o : parameters) {
				if (o instanceof ItemStack) {
					Item item = ((ItemStack) o).getItem();

					if (item.hasCustomItemEntity()) {
						entityClass = item.getCustomItemEntity(); 
					}

					break;
				}
			}
		}

		Constructor constructorToUse = FCUtilsReflection.findMatchingConstructor(entityClass, parameterTypes);

		if (constructorToUse == null) {
			String message = "No appropriate constructor found for " + classToStringMapping.get(entityClass) + ": ";

			for (Class<?> paramType : parameterTypes) {
				message += paramType.getSimpleName() + ", ";
			}

			throw new RuntimeException(message);
		}

		try {
			constructorToUse.setAccessible(true);
			entity = (Entity) constructorToUse.newInstance(parameterValues);
		} catch (InstantiationException e) {
			throw new RuntimeException("A problem has occured attempting to instantiate replacement for " + classToStringMapping.get(entityClass));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

			throw new RuntimeException("Incompatible types passed to specified constructor for " + classToStringMapping.get(entityClass));
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Caused by:");
			e.getCause().printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

	private static Class<? extends Entity> getRegisteredReplacement(Class<? extends Entity> entityClass) {
		int id = (Integer) classToIDMapping.get(entityClass);

		return (Class<? extends Entity>) IDtoClassMapping.get(id);
	}
}