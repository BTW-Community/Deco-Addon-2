package net.minecraft.src;

public class FCBlockStub extends Block
{
    private final IBehaviorDispenseItem dropperDefaultBehaviour = new BehaviorDefaultDispenseItem();

    protected FCBlockStub(int var1)
    {
        super(var1, Material.rock);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockStub");
    }
}
