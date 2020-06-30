package net.minecraft.src;

import java.util.Random;

public class FCBiomeGenJungle extends BiomeGenJungle
{
    private static final int m_iExtraReedsPerChunk = 100;

    public FCBiomeGenJungle(int var1)
    {
        super(var1);
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(FCEntityChicken.class, 10, 4, 4));
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityJungleSpider.class, 2, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySpider.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityZombie.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySkeleton.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityCreeper.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntitySlime.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityEnderman.class, 1, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(FCEntityOcelot.class, 2, 1, 1));
    }

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);

        for (int var5 = 0; var5 < 100; ++var5)
        {
            int var6 = var3 + var2.nextInt(16) + 8;
            int var7 = var4 + var2.nextInt(16) + 8;
            int var8 = var2.nextInt(128);
            this.theBiomeDecorator.reedGen.generate(var1, var2, var6, var8, var7);
        }
    }
}
