package net.minecraft.src;

public class FCBlockTorchNetherUnlit extends FCBlockTorchBaseUnlit
{
    protected FCBlockTorchNetherUnlit(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockTorchIdle");
    }

    protected int GetLitBlockID()
    {
        return FCBetterThanWolves.fcBlockTorchNetherBurning.blockID;
    }
}
