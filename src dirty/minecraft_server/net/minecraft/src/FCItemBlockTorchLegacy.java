package net.minecraft.src;

public class FCItemBlockTorchLegacy extends FCItemBlockTorchBurning
{
    public FCItemBlockTorchLegacy(int var1)
    {
        super(var1);
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        return FCBetterThanWolves.fcBlockTorchNetherBurning.blockID;
    }
}
