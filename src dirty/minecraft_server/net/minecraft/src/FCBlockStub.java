package net.minecraft.src;

public class FCBlockStub extends Block
{
    private final IBehaviorDispenseItem dropperDefaultBehaviour = new BehaviorDefaultDispenseItem();

    protected FCBlockStub(int var1)
    {
        super(var1, Material.rock);
    }
}
