package net.minecraft.src;

public class FCBiomeGenHell extends BiomeGenHell
{
    public FCBiomeGenHell(int var1)
    {
        super(var1);
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityGhast.class, 50, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityPigZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityMagmaCube.class, 1, 4, 4));
    }
}
