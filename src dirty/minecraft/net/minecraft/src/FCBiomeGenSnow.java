package net.minecraft.src;

public class FCBiomeGenSnow extends BiomeGenSnow
{
    public FCBiomeGenSnow(int var1)
    {
        super(var1);
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityCow.class, 8, 4, 4));
    }
}
