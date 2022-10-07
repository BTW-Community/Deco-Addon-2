package deco.entity;

import net.minecraft.src.EntityList;

public class DecoEntityManager {
	public static final int FALLING_CONCRETE_ID = 3000;
	
	public static void initEntities() {
		EntityList.addMapping(FallingConcreteEntity.class, "FallingConcrete", FALLING_CONCRETE_ID);
	}
}
