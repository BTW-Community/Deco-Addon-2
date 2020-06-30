package net.minecraft.src;

public class FCBiomeGenForest extends BiomeGenForest
{
    public FCBiomeGenForest(int var1)
    {
        super(var1);
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityCow.class, 8, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityWolf.class, 5, 4, 4));
    }
}
