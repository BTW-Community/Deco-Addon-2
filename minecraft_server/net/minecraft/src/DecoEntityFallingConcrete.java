package net.minecraft.src;

public class DecoEntityFallingConcrete extends FCEntityFallingBlock {
	public DecoEntityFallingConcrete(World var1, double var2, double var4, double var6, int var8, int var9) {
		super(var1, var2, var4, var6, var8, var9);
	}
	
	@Override
	public void onUpdate() {
        this.handleWaterMovement();
		
		if (!this.worldObj.isRemote && this.inWater) {
            int x = MathHelper.floor_double(this.posX);
            int y = MathHelper.floor_double(this.posY);
            int z = MathHelper.floor_double(this.posZ);

            if (this.AttemptToReplaceBlockAtPosition(x, y, z))
            {
                Block.blocksList[this.blockID].onFinishFalling(this.worldObj, x, y, z, this.metadata);
                this.setDead();
                return;
            }
		}
		
		super.onUpdate();
	}

    private boolean AttemptToReplaceBlockAtPosition(int x, int y, int z)
    {
        if (!this.m_bHasBlockBrokenOnLand && this.CanReplaceBlockAtPosition(x, y, z) && (!Block.blocksList[this.blockID].CanFallIntoBlockAtPos(this.worldObj, x, y - 1, z) || 
        		(Block.blocksList[this.worldObj.getBlockId(x, y, z)] != null && Block.blocksList[this.worldObj.getBlockId(x, y, z)].blockMaterial == Material.water)))
        {
            Block var4 = Block.blocksList[this.worldObj.getBlockId(x, y, z)];

            if (var4 != null)
            {
                var4.OnCrushedByFallingEntity(this.worldObj, x, y, z, this);
            }

            this.worldObj.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.3F, 2.6F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.8F);
            
            return this.worldObj.setBlock(x, y, z, this.blockID, this.metadata, 3);
        }
        else
        {
            return false;
        }
    }

    private boolean CanReplaceBlockAtPosition(int x, int y, int z)
    {
        if (this.worldObj.canPlaceEntityOnSide(this.blockID, x, y, z, true, 1, (Entity)null, (ItemStack)null) || Block.blocksList[this.worldObj.getBlockId(x, y, z)].blockMaterial == Material.water)
        {
            return true;
        }
        else
        {
            Block var4 = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
            return var4 != null && var4.CanBeCrushedByFallingEntity(this.worldObj, x, y, z, this);
        }
    }
}