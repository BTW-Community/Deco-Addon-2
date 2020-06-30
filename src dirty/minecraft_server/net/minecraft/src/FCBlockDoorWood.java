package net.minecraft.src;

public class FCBlockDoorWood extends FCBlockDoor
{
    protected FCBlockDoorWood(int var1)
    {
        super(var1, Material.wood);
        this.setHardness(1.5F);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("doorWood");
        this.disableStats();
    }

    public boolean CanPathThroughBlock(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return var6.CanPathThroughClosedWoodDoor() || var6.CanPathThroughOpenWoodDoor() && this.getBlocksMovement(var1, var2, var3, var4);
    }

    public boolean IsBreakableBarricade(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsBreakableBarricadeOpen(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.isDoorOpen(var1, var2, var3, var4);
    }

    /**
     * A function to open a door.
     */
    public void onPoweredBlockChange(World var1, int var2, int var3, int var4, boolean var5) {}

    public void OnAIOpenDoor(World var1, int var2, int var3, int var4, boolean var5)
    {
        super.onPoweredBlockChange(var1, var2, var3, var4, var5);
    }
}
