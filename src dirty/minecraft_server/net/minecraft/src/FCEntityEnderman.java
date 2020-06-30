package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

public class FCEntityEnderman extends EntityEnderman
{
    public static boolean[] m_bCarriableBlocks = new boolean[4096];
    protected int m_iTeleportDelay = 0;
    protected int m_iAggressionCounter = 0;
    protected boolean m_bShouldBeScreaming;
    private static int m_iMaxEnstonePlaceWeight;
    private static final int m_iEndstonePlaceWeightPower = 8;
    private static int[] m_iEndstonePlacementNeighborWeights;

    public FCEntityEnderman(World var1)
    {
        super(var1);
    }

    protected void entityInit()
    {
        this.EntityCreatureEntityInit();
        this.dataWatcher.addObject(16, new Integer(0));
        this.dataWatcher.addObject(17, new Byte((byte)0));
        this.dataWatcher.addObject(18, new Byte((byte)0));
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        super.dropFewItems(var1, var2);
        this.DropCarriedBlock();
    }

    /**
     * Set the id of the block an enderman carries
     */
    public void setCarried(int var1)
    {
        this.dataWatcher.updateObject(16, Integer.valueOf(var1));
    }

    /**
     * Get the id of the block an enderman carries
     */
    public int getCarried()
    {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

        if (var1 != null && this.IsPlayerStaringAtMe(var1))
        {
            this.m_bShouldBeScreaming = true;

            if (this.m_iAggressionCounter == 0)
            {
                this.worldObj.playSoundAtEntity(var1, "mob.endermen.stare", 1.0F, 1.0F);
            }

            ++this.m_iAggressionCounter;

            if (this.m_iAggressionCounter > 5)
            {
                this.m_iAggressionCounter = 0;
                this.setScreaming(true);
                this.AngerNearbyEndermen(var1);
                return var1;
            }
        }
        else
        {
            this.m_iAggressionCounter = 0;
        }

        return null;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.moveSpeed = 0.3F;

        if (this.entityToAttack != null)
        {
            this.moveSpeed = 6.5F;
        }

        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1);
        }

        if (!this.worldObj.isRemote)
        {
            if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") && this.entityToAttack == null)
            {
                if (this.getCarried() == 0)
                {
                    if (!this.UpdateWithoutCarriedBlock())
                    {
                        return;
                    }
                }
                else if (!this.UpdateWithCarriedBlock())
                {
                    return;
                }
            }

            if (this.worldObj.isDaytime())
            {
                float var1 = this.getBrightness(1.0F);

                if (var1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F)
                {
                    this.Panic();
                }
            }
        }
        else
        {
            this.EmitParticles();
        }

        if (this.isWet() || this.isBurning())
        {
            this.Panic();
        }

        if (this.isScreaming() && !this.m_bShouldBeScreaming && this.rand.nextInt(100) == 0)
        {
            this.setScreaming(false);
        }

        this.isJumping = false;

        if (this.entityToAttack != null)
        {
            this.faceEntity(this.entityToAttack, 100.0F, 100.0F);
        }

        if (!this.worldObj.isRemote && this.isEntityAlive())
        {
            if (this.entityToAttack != null)
            {
                if (this.entityToAttack instanceof EntityPlayer && this.IsPlayerStaringAtMe((EntityPlayer)this.entityToAttack))
                {
                    this.moveStrafing = this.moveForward = 0.0F;
                    this.moveSpeed = 0.0F;

                    if (this.entityToAttack.getDistanceSqToEntity(this) < 16.0D)
                    {
                        this.teleportRandomly();
                    }

                    this.m_iTeleportDelay = 0;
                }
                else if (this.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.m_iTeleportDelay++ >= 30 && this.teleportToEntity(this.entityToAttack))
                {
                    this.m_iTeleportDelay = 0;
                }
            }
            else
            {
                this.setScreaming(false);
                this.m_iTeleportDelay = 0;
            }
        }

        this.EntityMobOnLivingUpdate();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (!this.isEntityInvulnerable())
        {
            this.setScreaming(true);

            if (var1 instanceof EntityDamageSource && var1.getEntity() instanceof EntityPlayer)
            {
                this.m_bShouldBeScreaming = true;
            }

            if (var1 instanceof EntityDamageSourceIndirect)
            {
                this.m_bShouldBeScreaming = false;

                for (int var5 = 0; var5 < 64; ++var5)
                {
                    if (this.teleportRandomly())
                    {
                        return true;
                    }
                }

                return false;
            }
            else if (!(var1.getEntity() instanceof EntityPlayer))
            {
                return this.EntityMobAttackEntityFrom(var1, var2);
            }
            else
            {
                boolean var3 = this.EntityMobAttackEntityFrom(var1, var2);

                if (this.isEntityAlive())
                {
                    for (int var4 = 0; var4 < 64 && !this.teleportRandomly(); ++var4)
                    {
                        ;
                    }
                }

                this.AngerNearbyEndermen((EntityPlayer)var1.getEntity());
                return var3;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        if (this.worldObj.provider.dimensionId == 1 && this.worldObj.rand.nextInt(5) == 0)
        {
            this.setCarried(Block.whiteStone.blockID);
            this.setCarryingData(0);
        }
    }

    public void CheckForScrollDrop()
    {
        if (this.rand.nextInt(1000) == 0)
        {
            ItemStack var1 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, Enchantment.silkTouch.effectId);
            this.entityDropItem(var1, 0.0F);
        }
    }

    protected boolean IsPlayerStaringAtMe(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.armorInventory[3];

        if (var2 == null || var2.itemID != FCBetterThanWolves.fcItemEnderSpectacles.itemID)
        {
            Vec3 var3 = var1.getLook(1.0F).normalize();
            Vec3 var4 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX - var1.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (var1.posY + (double)var1.getEyeHeight()), this.posZ - var1.posZ);
            double var5 = var4.lengthVector();
            var4 = var4.normalize();
            double var7 = var3.dotProduct(var4);

            if (var7 > 1.0D - 0.025D / var5)
            {
                return var1.canEntityBeSeen(this);
            }
        }

        return false;
    }

    public void DropCarriedBlock()
    {
        int var1 = this.getCarried();

        if (var1 != 0)
        {
            Block var2 = Block.blocksList[var1];

            if (var2 != null)
            {
                int var3 = var2.damageDropped(this.getCarryingData());
                this.entityDropItem(new ItemStack(var1, 1, var3), 0.0F);
                this.setCarried(0);
                this.setCarryingData(0);
            }
        }
    }

    private boolean CanPickUpBlock(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (m_bCarriableBlocks[var4])
        {
            if (!this.worldObj.isBlockNormalCube(var1, var2, var3) && var4 != Block.cactus.blockID)
            {
                return true;
            }

            int var5 = 0;
            int var6;

            if (!this.DoesBlockBlockPickingUp(var1, var2 - 1, var3))
            {
                ++var5;
            }
            else
            {
                for (var6 = var1 - 1; var6 <= var1 + 1; ++var6)
                {
                    for (int var7 = var3 - 1; var7 <= var3 + 1; ++var7)
                    {
                        if (this.DoesBlockBlockPickingUp(var6, var2 + 1, var7))
                        {
                            return false;
                        }
                    }
                }
            }

            for (var6 = 1; var6 < 6; ++var6)
            {
                FCUtilsBlockPos var8 = new FCUtilsBlockPos(var1, var2, var3);
                var8.AddFacingAsOffset(var6);

                if (!this.DoesBlockBlockPickingUp(var8.i, var8.j, var8.k))
                {
                    ++var5;
                }
            }

            if (var5 >= 3)
            {
                return true;
            }
        }

        return false;
    }

    private boolean DoesBlockBlockPickingUp(int var1, int var2, int var3)
    {
        if (this.worldObj.isAirBlock(var1, var2, var3))
        {
            return false;
        }
        else if (this.worldObj.isBlockNormalCube(var1, var2, var3))
        {
            return true;
        }
        else
        {
            int var4 = this.worldObj.getBlockId(var1, var2, var3);
            Block var5 = Block.blocksList[var4];
            return var5 != null && var5 != Block.waterMoving && var5 != Block.waterStill && var5 != Block.lavaMoving && var5 != Block.lavaStill && var5 != Block.fire && var5 != FCBetterThanWolves.fcBlockFireStoked && !var5.blockMaterial.isReplaceable() && var5.blockMaterial != Material.plants && var5.blockMaterial != Material.leaves;
        }
    }

    private boolean UpdateWithCarriedBlock()
    {
        int var1 = this.getCarried();
        int var2;
        int var3;
        int var4;

        if (this.worldObj.provider.dimensionId == 1)
        {
            var2 = MathHelper.floor_double(this.posX) + this.rand.nextInt(5) - 2;
            var3 = MathHelper.floor_double(this.posY) + this.rand.nextInt(7) - 3;
            var4 = MathHelper.floor_double(this.posZ) + this.rand.nextInt(5) - 2;
            int var5 = this.GetPlaceEndstoneWeight(var2, var3, var4);

            if (this.rand.nextInt(m_iMaxEnstonePlaceWeight >> 9) < var5)
            {
                this.worldObj.playAuxSFX(2246, var2, var3, var4, var1 + (this.getCarryingData() << 12));
                this.worldObj.setBlockAndMetadataWithNotify(var2, var3, var4, this.getCarried(), this.getCarryingData());
                this.setCarried(0);
            }
        }
        else if (this.rand.nextInt(2400) == 0)
        {
            var2 = MathHelper.floor_double(this.posX);
            var3 = MathHelper.floor_double(this.posY) + 1;
            var4 = MathHelper.floor_double(this.posZ);
            this.worldObj.playAuxSFX(2247, var2, var3, var4, 0);
            this.setDead();
            return false;
        }

        return true;
    }

    private boolean UpdateWithoutCarriedBlock()
    {
        int var1;
        int var2;
        int var3;

        if (this.rand.nextInt(20) == 0)
        {
            var1 = MathHelper.floor_double(this.posX - 3.0D + this.rand.nextDouble() * 6.0D);
            var2 = MathHelper.floor_double(this.posY - 1.0D + this.rand.nextDouble() * 7.0D);
            var3 = MathHelper.floor_double(this.posZ - 3.0D + this.rand.nextDouble() * 6.0D);
            int var4 = this.worldObj.getBlockId(var1, var2, var3);

            if (this.CanPickUpBlock(var1, var2, var3))
            {
                this.worldObj.playAuxSFX(2244, var1, var2, var3, var4 + (this.worldObj.getBlockMetadata(var1, var2, var3) << 12));
                this.setCarried(this.worldObj.getBlockId(var1, var2, var3));
                this.setCarryingData(this.worldObj.getBlockMetadata(var1, var2, var3));
                this.worldObj.setBlockToAir(var1, var2, var3);
            }
        }
        else if (this.worldObj.provider.dimensionId == 1 && this.rand.nextInt(9600) == 0)
        {
            var1 = MathHelper.floor_double(this.posX);
            var2 = MathHelper.floor_double(this.posY) + 1;
            var3 = MathHelper.floor_double(this.posZ);
            this.worldObj.playAuxSFX(2247, var1, var2, var3, 0);
            this.setDead();
            return false;
        }

        return true;
    }

    private int GetPlaceEndstoneWeight(int var1, int var2, int var3)
    {
        int var4 = 0;

        if (this.worldObj.isAirBlock(var1, var2, var3))
        {
            if (this.worldObj.doesBlockHaveSolidTopSurface(var1, var2 - 1, var3))
            {
                ++var4;
            }

            for (int var5 = 1; var5 < 6; ++var5)
            {
                FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1, var2, var3);
                var6.AddFacingAsOffset(var5);
                int var7 = this.worldObj.getBlockId(var6.i, var6.j, var6.k);

                if (var7 == Block.whiteStone.blockID)
                {
                    ++var4;
                }
            }
        }

        return m_iEndstonePlacementNeighborWeights[var4];
    }

    protected void AngerNearbyEndermen(EntityPlayer var1)
    {
        List var2 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
        Iterator var3 = var2.iterator();

        while (var3.hasNext())
        {
            Entity var4 = (Entity)var3.next();

            if (var4 instanceof FCEntityEnderman)
            {
                FCEntityEnderman var5 = (FCEntityEnderman)var4;

                if (var5.entityToAttack == null)
                {
                    var5.entityToAttack = var1;
                    var5.setScreaming(true);
                }
            }
        }
    }

    protected void EmitParticles()
    {
        for (int var1 = 0; var1 < 2; ++var1)
        {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }
    }

    protected void Panic()
    {
        this.entityToAttack = null;
        this.setScreaming(false);
        this.m_bShouldBeScreaming = false;
        this.teleportRandomly();
    }

    static
    {
        m_bCarriableBlocks[Block.grass.blockID] = true;
        m_bCarriableBlocks[Block.dirt.blockID] = true;
        m_bCarriableBlocks[Block.sand.blockID] = true;
        m_bCarriableBlocks[Block.gravel.blockID] = true;
        m_bCarriableBlocks[Block.tnt.blockID] = true;
        m_bCarriableBlocks[Block.cactus.blockID] = true;
        m_bCarriableBlocks[Block.pumpkin.blockID] = true;
        m_bCarriableBlocks[Block.melon.blockID] = true;
        m_bCarriableBlocks[Block.mycelium.blockID] = true;
        m_bCarriableBlocks[Block.wood.blockID] = true;
        m_bCarriableBlocks[Block.netherrack.blockID] = true;
        m_iEndstonePlacementNeighborWeights = new int[7];

        for (int var0 = 0; var0 < 7; ++var0)
        {
            m_iEndstonePlacementNeighborWeights[var0] = var0;

            for (int var1 = 1; var1 < 8; ++var1)
            {
                m_iEndstonePlacementNeighborWeights[var0] *= var0;
            }
        }

        m_iMaxEnstonePlaceWeight = m_iEndstonePlacementNeighborWeights[6];
    }
}
