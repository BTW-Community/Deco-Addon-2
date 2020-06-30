package net.minecraft.src;

public class FCBiomeGenEnd extends BiomeGenEnd
{
    public FCBiomeGenEnd(int var1)
    {
        super(var1);
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityEnderman.class, 10, 4, 4));
    }
}
