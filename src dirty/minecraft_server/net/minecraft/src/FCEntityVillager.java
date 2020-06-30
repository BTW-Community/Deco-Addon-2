package net.minecraft.src;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FCEntityVillager extends EntityVillager
{
    public static int m_iNumProfessionTypes = 5;
    protected static final int m_iInLoveDataWatcherID = 22;
    protected static final int m_iTradeLevelDataWatcherID = 23;
    protected static final int m_iTradeExperienceDataWatcherID = 25;
    protected static final int m_iDirtyPeasantDataWatcherID = 26;
    protected int m_iAIFullTickCountdown;
    protected int m_iUpdateTradesCountdown;

    public FCEntityVillager(World var1)
    {
        this(var1, 0);
    }

    public FCEntityVillager(World var1, int var2)
    {
        super(var1, var2);
        this.tasks.RemoveAllTasksOfClass(EntityAIAvoidEntity.class);
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, FCEntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, FCEntityWolf.class, 8.0F, 0.3F, 0.35F));
        this.tasks.RemoveAllTasksOfClass(EntityAIVillagerMate.class);
        this.tasks.addTask(1, new FCEntityAIVillagerMate(this));
        this.tasks.addTask(2, new EntityAITempt(this, 0.3F, Item.diamond.itemID, false));
        this.experienceValue = 50;
        this.m_iUpdateTradesCountdown = 0;
        this.m_iAIFullTickCountdown = 0;
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick()
    {
        --this.m_iAIFullTickCountdown;

        if (this.m_iAIFullTickCountdown <= 0)
        {
            this.m_iAIFullTickCountdown = 70 + this.rand.nextInt(50);
            this.worldObj.villageCollectionObj.addVillagerPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);

            if (this.villageObj == null)
            {
                this.detachHome();
            }
            else
            {
                ChunkCoordinates var1 = this.villageObj.getCenter();
                this.setHomeArea(var1.posX, var1.posY, var1.posZ, (int)((float)this.villageObj.getVillageRadius() * 0.6F));
            }
        }

        if (!this.isTrading())
        {
            if (this.GetCurrentTradeLevel() == 0)
            {
                this.SetTradeLevel(1);
                this.buyingList = null;
                this.m_iUpdateTradesCountdown = 0;
                this.CheckForNewTrades(1);
            }
            else if (this.m_iUpdateTradesCountdown > 0)
            {
                --this.m_iUpdateTradesCountdown;

                if (this.m_iUpdateTradesCountdown <= 0)
                {
                    Iterator var3 = this.buyingList.iterator();

                    while (var3.hasNext())
                    {
                        MerchantRecipe var2 = (MerchantRecipe)var3.next();

                        if (var2.func_82784_g())
                        {
                            var3.remove();
                        }
                    }

                    int var4 = this.GetCurrentMaxNumTrades();

                    if (this.buyingList.size() < var4)
                    {
                        this.CheckForNewTrades(var4 - this.buyingList.size());
                        this.worldObj.setEntityState(this, (byte)14);
                        this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
                    }
                }
            }
            else
            {
                this.m_iUpdateTradesCountdown = 600 + this.rand.nextInt(600);
            }
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        return this.CustomInteract(var1) ? true : (this.GetInLove() > 0 ? this.EntityAgeableInteract(var1) : super.interact(var1));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(22, new Integer(0));
        this.dataWatcher.addObject(23, new Integer(0));
        this.dataWatcher.addObject(25, new Integer(0));
        this.dataWatcher.addObject(26, new Integer(0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("FCInLove", this.GetInLove());
        var1.setInteger("FCTradeLevel", this.GetCurrentTradeLevel());
        var1.setInteger("FCTradeXP", this.GetCurrentTradeXP());
        var1.setInteger("FCDirty", this.GetDirtyPeasant());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("FCInLove"))
        {
            this.SetInLove(var1.getInteger("FCInLove"));
        }

        if (var1.hasKey("FCTradeLevel"))
        {
            this.SetTradeLevel(var1.getInteger("FCTradeLevel"));
        }

        if (var1.hasKey("FCTradeXP"))
        {
            this.SetTradeExperience(var1.getInteger("FCTradeXP"));
        }

        if (var1.hasKey("FCDirty"))
        {
            this.SetDirtyPeasant(var1.getInteger("FCDirty"));
        }

        this.CheckForInvalidTrades();
    }

    public void setRevengeTarget(EntityLiving var1)
    {
        //this.randomTickDivider = var1;

        if (var1 != null)
        {
            this.isMating = true;

            if (this.villageObj != null)
            {
                this.villageObj.addOrRenewAgressor(var1);
            }

            if (this.isEntityAlive())
            {
                this.worldObj.setEntityState(this, (byte)13);
            }
        }
        else
        {
            this.isMating = false;
        }
    }

    public void useRecipe(MerchantRecipe var1)
    {
        var1.incrementToolUses();
        this.m_iUpdateTradesCountdown = 10;

        if (var1.getItemToBuy().itemID == FCBetterThanWolves.fcCompanionCube.blockID)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.wolf.hurt", 5.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        }
        else if (var1.getItemToBuy().itemID == FCBetterThanWolves.fcBlockLightningRod.blockID)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.classic_hurt", 1.0F, this.getSoundPitch() * 2.0F);
        }
        else if (var1.getItemToBuy().itemID == FCBetterThanWolves.fcItemSoap.itemID)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.slime.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.SetDirtyPeasant(0);
        }
        else if (var1.getItemToSell().itemID == FCBetterThanWolves.fcAnvil.blockID)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.anvil_land", 0.3F, this.rand.nextFloat() * 0.1F + 0.9F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.cave.cave4", 0.5F, this.rand.nextFloat() * 0.05F + 0.5F);
        }

        if (this.worldObj.getWorldInfo().getGameType() != EnumGameType.CREATIVE)
        {
            int var2;

            if (var1.m_iTradeLevel < 0)
            {
                var2 = this.GetCurrentTradeLevel();

                if (var2 < 5 && this.GetCurrentTradeXP() == this.GetCurrentTradeMaxXP() && this.GetCurrentTradeLevel() == -var1.m_iTradeLevel)
                {
                    ++var2;
                    this.SetTradeLevel(var2);
                    this.SetTradeExperience(0);

                    if (this.getProfession() == 2 && this.GetCurrentTradeLevel() >= 5)
                    {
                        this.worldObj.playSoundAtEntity(this, "mob.enderdragon.growl", 1.0F, 0.5F);
                        this.worldObj.playSoundAtEntity(this, "ambient.weather.thunder", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
                        this.worldObj.playSoundAtEntity(this, "random.levelup", 0.75F + this.rand.nextFloat() * 0.25F, 0.5F);
                    }
                    else
                    {
                        this.worldObj.playSoundAtEntity(this, "random.levelup", 0.5F + this.rand.nextFloat() * 0.25F, 1.5F);
                    }
                }
            }
            else if (var1.m_iTradeLevel >= this.GetCurrentTradeLevel())
            {
                var2 = this.GetCurrentTradeXP() + 1;
                int var3 = this.GetCurrentTradeMaxXP();

                if (var2 > var3)
                {
                    var2 = var3;
                }

                this.SetTradeExperience(var2);
            }
        }
    }

    public MerchantRecipeList getRecipes(EntityPlayer var1)
    {
        if (this.buyingList == null)
        {
            this.CheckForNewTrades(1);
        }

        return this.buyingList;
    }

    /**
     * Initialize this creature.
     */
    public void initCreature()
    {
        this.setProfession(this.worldObj.rand.nextInt(m_iNumProfessionTypes));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote)
        {
            if (this.isEntityAlive())
            {
                this.CheckForLooseMilk();
            }
        }
        else
        {
            this.UpdateStatusParticles();
        }
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        if (!this.HasHeadCrabbedSquid())
        {
            int var3 = FCBetterThanWolves.fcItemRawMysteryMeat.itemID;

            if (this.isBurning())
            {
                var3 = FCBetterThanWolves.fcItemCookedMysteryMeat.itemID;
            }

            int var4 = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + var2);

            for (int var5 = 0; var5 < var4; ++var5)
            {
                this.dropItem(var3, 1);
            }
        }
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        float var1 = super.getSoundPitch();

        if (this.IsPossessed() || this.getProfession() == 2 && this.GetCurrentTradeLevel() == 5)
        {
            var1 *= 0.6F;
        }

        return var1;
    }

    protected boolean GetCanCreatureTypeBePossessed()
    {
        return true;
    }

    protected void OnFullPossession()
    {
        this.worldObj.playAuxSFX(2260, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
        this.setDead();
        FCEntityWitch var1 = new FCEntityWitch(this.worldObj);
        var1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        var1.renderYawOffset = this.renderYawOffset;
        var1.SetPersistent(true);
        this.worldObj.spawnEntityInWorld(var1);
    }

    public boolean IsValidZombieSecondaryTarget(EntityZombie var1)
    {
        return true;
    }

    public boolean IsSecondaryTargetForSquid()
    {
        return true;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height;
    }

    public FCEntityVillager func_90012_b(EntityAgeable var1)
    {
        FCEntityVillager var2 = new FCEntityVillager(this.worldObj);
        var2.initCreature();
        return var2;
    }

    protected void CheckForNewTrades(int var1)
    {
        if (var1 > 0)
        {
            if (this.GetCurrentTradeMaxXP() == this.GetCurrentTradeXP() && this.CheckForLevelUpTrade())
            {
                --var1;

                if (var1 <= 0)
                {
                    return;
                }
            }

            var1 = this.CheckForMandatoryTrades(var1);

            if (var1 > 0)
            {
                MerchantRecipeList var2 = new MerchantRecipeList();

                switch (this.getProfession())
                {
                    case 0:
                        this.CheckForPeasantTrades(var2);
                        break;

                    case 1:
                        this.CheckForLibrarianTrades(var2);
                        break;

                    case 2:
                        this.CheckForPriestTrades(var2);
                        break;

                    case 3:
                        this.CheckForBlacksmithTrades(var2);
                        break;

                    case 4:
                        this.CheckForButcherTrades(var2);
                }

                if (var2.isEmpty())
                {
                    this.AddDefaultTradeToList(var2);
                }
                else
                {
                    Collections.shuffle(var2);
                }

                if (this.buyingList == null)
                {
                    this.buyingList = new MerchantRecipeList();
                }

                for (int var3 = 0; var3 < var1 && var3 < var2.size(); ++var3)
                {
                    this.buyingList.addToListWithCheck((MerchantRecipe)var2.get(var3));
                }
            }
        }
    }

    private boolean CheckForLevelUpTrade()
    {
        MerchantRecipe var1 = null;

        switch (this.getProfession())
        {
            case 0:
                var1 = this.GetPeasantLevelUpRecipe();
                break;

            case 1:
                var1 = this.GetLibrarianLevelUpRecipe();
                break;

            case 2:
                var1 = this.GetPriestLevelUpRecipe();
                break;

            case 3:
                var1 = this.GetBlacksmithLevelUpRecipe();
                break;

            case 4:
                var1 = this.GetButcherLevelUpRecipe();
        }

        if (var1 != null && !this.DoesRecipeListAlreadyContainRecipe(var1))
        {
            this.buyingList.add(var1);
            return true;
        }
        else
        {
            return false;
        }
    }

    private int CheckForMandatoryTrades(int var1)
    {
        Object var2 = null;
        int var3 = this.GetCurrentTradeLevel();

        switch (this.getProfession())
        {
            case 0:
                var1 = this.CheckForPeasantMandatoryTrades(var1);
                break;

            case 1:
                var1 = this.CheckForLibrarianMandatoryTrades(var1);
                break;

            case 2:
                var1 = this.CheckForPriestMandatoryTrades(var1);
                break;

            case 3:
                var1 = this.CheckForBlacksmithMandatoryTrades(var1);
                break;

            case 4:
                var1 = this.CheckForButcherMandatoryTrades(var1);
        }

        return var1;
    }

    private boolean AttemptToAddTradeToBuyingList(MerchantRecipe var1)
    {
        if (var1 != null && !this.DoesRecipeListAlreadyContainRecipe(var1))
        {
            this.buyingList.add(var1);
            return true;
        }
        else
        {
            return false;
        }
    }

    private void CheckForPeasantTrades(MerchantRecipeList var1)
    {
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBlockDirtLoose.blockID, 0.2F, 48, 64, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.wood.blockID, 0, 0.15F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.wood.blockID, 1, 0.15F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.wood.blockID, 2, 0.15F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.wood.blockID, 3, 0.15F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemWool.itemID, 3, 0.2F, 16, 24, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.dyePowder.itemID, 15, 0.2F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemFlour.itemID, 0.2F, 24, 32, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.sugar.itemID, 0.2F, 10, 20, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemCocoaBeans.itemID, 0.2F, 10, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemMushroomBrown.itemID, 0.2F, 10, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemHempSeeds.itemID, 0.2F, 24, 32, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.egg.itemID, 0.2F, 12, 12, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.thinGlass.blockID, 0.2F, 16, 32, 2);
        this.CheckForWishToBuySingleItemTrade(var1, Item.bucketMilk.itemID, 0.05F, 1, 2, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemWheat.itemID, 0.2F, 8, 16, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.appleRed.itemID, 0.1F, 2, 4, 2);

        if (this.GetDirtyPeasant() > 0)
        {
            this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemSoap.itemID, 0.2F, 1, 2, 3);
        }

        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.melon.blockID, 0.2F, 8, 10, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBlockPumpkinFresh.blockID, 0.2F, 10, 16, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemStumpRemover.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemChocolate.itemID, 0.2F, 1, 2, 3);
        this.CheckForWishToBuySingleItemTrade(var1, Item.shears.itemID, 0.1F, 1, 1, 3);
        this.CheckForWishToBuySingleItemTrade(var1, Item.flintAndSteel.itemID, 0.1F, 1, 1, 3);
        this.CheckForComplexTrade(var1, new ItemStack(FCBetterThanWolves.fcItemStake, 2), new ItemStack(Item.silk, this.rand.nextInt(17) + 16), new ItemStack(Item.emerald, 1), 0.1F, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.bread.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemCookedMushroomOmelet.itemID, 0.1F, 8, 12, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemCookedScrambledEggs.itemID, 0.1F, 8, 12, 3);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemBucketCement.itemID, 0.2F, 2, 4, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcLightBulbOff.blockID, 0.2F, 2, 4, 4);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.cookie.itemID, 0.2F, 8, 16, 4);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.pumpkinPie.itemID, 0.2F, 1, 2, 4);
        this.CheckForWishToSellSingleItemTrade(var1, Item.cake.itemID, 0.2F, 2, 4, 4);
        this.CheckForWishToSellSingleItemTrade(var1, Block.mycelium.blockID, 0.2F, 10, 20, 5);
        this.CheckForArcaneScrollTrade(var1, Enchantment.looting.effectId, 0.2F, 16, 32, 5);
    }

    private MerchantRecipe GetPeasantLevelUpRecipe()
    {
        int var1 = this.GetCurrentTradeLevel();

        switch (var1)
        {
            case 1:
                return new MerchantRecipe(new ItemStack(Item.hoeIron, 1), new ItemStack(Item.emerald.itemID, 1, 0), -var1);

            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcMillStone, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemWaterWheel, 1), new ItemStack(Item.emerald.itemID, 3, 0), -var1);

            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockPlanterSoil, this.rand.nextInt(5) + 8), new ItemStack(Item.emerald.itemID, 4, 0), -var1);

            default:
                return null;
        }
    }

    private int CheckForPeasantMandatoryTrades(int var1)
    {
        return var1;
    }

    private void CheckForLibrarianTrades(MerchantRecipeList var1)
    {
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.paper.itemID, 0.2F, 27, 38, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.dyePowder.itemID, 0, 0.2F, 27, 38, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.feather.itemID, 0.2F, 27, 38, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.book.itemID, 0.2F, 1, 3, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.writableBook.itemID, 0.2F, 1, 1, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.bookShelf.blockID, 0.2F, 1, 1, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.netherStalkSeeds.itemID, 0.2F, 16, 24, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.lightStoneDust.itemID, 0.2F, 24, 32, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemNitre.itemID, 0.2F, 32, 48, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemBatWing.itemID, 0.2F, 14, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.spiderEye.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.redstone.itemID, 0.2F, 32, 48, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemWitchWart.itemID, 0.2F, 6, 10, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemMysteriousGland.itemID, 0.2F, 14, 16, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.fermentedSpiderEye.itemID, 0.2F, 4, 8, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.ghastTear.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.magmaCream.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.blazePowder.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBlockDetector.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBuddyBlock.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBlockDispenser.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcLens.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemBrimstone.itemID, 0.2F, 16, 32, 5);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcAestheticVegetation.blockID, 2, 0.2F, 8, 16, 5);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemBloodMossSpores.itemID, 0.2F, 2, 3, 5);
        this.CheckForArcaneScrollTrade(var1, Enchantment.power.effectId, 0.2F, 32, 48, 5);
    }

    private MerchantRecipe GetLibrarianLevelUpRecipe()
    {
        int var1 = this.GetCurrentTradeLevel();

        switch (var1)
        {
            case 1:
                return new MerchantRecipe(new ItemStack(Item.enchantedBook, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 2:
                return new MerchantRecipe(new ItemStack(Item.brewingStand, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockDispenser, 1), new ItemStack(Item.emerald.itemID, 4, 0), -var1);

            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemEnderSpectacles, 1), new ItemStack(Item.emerald.itemID, 3, 0), -var1);

            default:
                return null;
        }
    }

    private int CheckForLibrarianMandatoryTrades(int var1)
    {
        int var2 = this.GetCurrentTradeLevel();

        if (var2 >= 5 && this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.enderPearl, 1), new ItemStack(Item.emerald, this.rand.nextInt(3) + 6), new ItemStack(Item.eyeOfEnder, 1), 4)))
        {
            --var1;
        }

        return var1;
    }

    private void CheckForPriestTrades(MerchantRecipeList var1)
    {
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemHemp.itemID, 0.2F, 18, 22, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemMushroomRed.itemID, 0.2F, 10, 16, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.cactus.blockID, 0.2F, 32, 64, 1);
        this.CheckForWishToBuySingleItemTrade(var1, Item.painting.itemID, 0.1F, 2, 3, 1);
        this.CheckForWishToBuySingleItemTrade(var1, Item.flintAndSteel.itemID, 0.1F, 1, 1, 1);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.swordIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.axeIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.pickaxeIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.helmetIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.plateIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.legsIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.bootsIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.swordDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.axeDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.pickaxeDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.helmetDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.plateDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.legsDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(var1, Item.bootsDiamond, 0.05F, 2, 4, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemCandle.itemID, this.rand.nextInt(16), 0.2F, 4, 8, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.skull.itemID, 0, 0.2F, 3, 5, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.skull.itemID, 2, 0.2F, 2, 4, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.skull.itemID, 4, 0.2F, 2, 4, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcAestheticOpaque.blockID, 15, 0.2F, 4, 6, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemSoulUrn.itemID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemCanvas.itemID, 0.2F, 2, 3, 5);
        this.CheckForArcaneScrollTrade(var1, Enchantment.fortune.effectId, 0.2F, 48, 64, 5);
    }

    private MerchantRecipe GetPriestLevelUpRecipe()
    {
        int var1 = this.GetCurrentTradeLevel();

        switch (var1)
        {
            case 1:
                return new MerchantRecipe(new ItemStack(Block.enchantmentTable, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockArcaneVessel, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 3:
                return new MerchantRecipe(new ItemStack(Item.skull.itemID, 1, 1), new ItemStack(Item.emerald.itemID, 3, 0), -var1);

            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcInfernalEnchanter, 1), new ItemStack(Item.emerald.itemID, 4, 0), -var1);

            default:
                return null;
        }
    }

    private int CheckForPriestMandatoryTrades(int var1)
    {
        int var2 = this.GetCurrentTradeLevel();

        if (var2 >= 4)
        {
            if (this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.skull, 1, 1), new ItemStack(Item.emerald, this.rand.nextInt(3) + 6), new ItemStack(Item.skull, 1, 5), 3)))
            {
                --var1;
            }

            if (var1 >= 0 && this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.netherStar), new ItemStack(FCBetterThanWolves.fcBlockSoulforgeDormant), new ItemStack(FCBetterThanWolves.fcAnvil), 3)))
            {
                --var1;
            }
        }

        return var1;
    }

    private void CheckForBlacksmithTrades(MerchantRecipeList var1)
    {
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.coal.itemID, 0.2F, 16, 24, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Block.wood.blockID, 2, 0.2F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemChunkIronOre.itemID, 0.2F, 18, 27, 1);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID, 0.2F, 1, 1, 1);
        this.CheckForWishToSellSingleItemTrade(var1, Item.swordIron.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToSellSingleItemTrade(var1, Item.axeIron.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToSellSingleItemTrade(var1, Item.pickaxeIron.itemID, 0.2F, 6, 9, 1);
        this.CheckForWishToSellSingleItemTrade(var1, Item.shovelIron.itemID, 0.2F, 2, 3, 1);
        this.CheckForWishToSellSingleItemTrade(var1, Item.hoeIron.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemNethercoal.itemID, 0.2F, 12, 20, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBBQ.blockID, 0.2F, 2, 3, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemCreeperOysters.itemID, 0.2F, 14, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemChunkGoldOre.itemID, 0.2F, 18, 27, 2);
        this.CheckForWishToBuySingleItemTrade(var1, Item.diamond.itemID, 0.2F, 2, 3, 2);
        this.CheckForWishToSellSingleItemTrade(var1, Item.bootsIron.itemID, 0.2F, 4, 6, 2);
        this.CheckForWishToSellSingleItemTrade(var1, Item.helmetIron.itemID, 0.2F, 10, 15, 2);
        this.CheckForWishToSellSingleItemTrade(var1, Item.plateIron.itemID, 0.2F, 16, 24, 2);
        this.CheckForWishToSellSingleItemTrade(var1, Item.legsIron.itemID, 0.2F, 14, 21, 2);
        this.CheckForWishToSellSingleItemTrade(var1, Item.swordDiamond.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToSellSingleItemTrade(var1, Item.axeDiamond.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToSellSingleItemTrade(var1, Item.pickaxeDiamond.itemID, 0.2F, 12, 18, 3);
        this.CheckForWishToSellSingleItemTrade(var1, Item.shovelDiamond.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellSingleItemTrade(var1, Item.hoeDiamond.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemSoulUrn.itemID, 0, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemHaft.itemID, 0, 0.2F, 6, 8, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemMould.itemID, 0.2F, 12, 16, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcBlockMiningCharge.blockID, 0, 0.2F, 4, 6, 4);
        this.CheckForWishToSellSingleItemTrade(var1, Item.bootsDiamond.itemID, 0.2F, 8, 12, 4);
        this.CheckForWishToSellSingleItemTrade(var1, Item.helmetDiamond.itemID, 0.2F, 20, 30, 4);
        this.CheckForWishToSellSingleItemTrade(var1, Item.plateDiamond.itemID, 0.2F, 32, 48, 4);
        this.CheckForWishToSellSingleItemTrade(var1, Item.legsDiamond.itemID, 0.2F, 28, 42, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemSoulFlux.itemID, 0, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(var1, Item.bootsChain.itemID, 0.2F, 4, 6, 5);
        this.CheckForWishToSellSingleItemTrade(var1, Item.helmetChain.itemID, 0.2F, 10, 15, 5);
        this.CheckForWishToSellSingleItemTrade(var1, Item.plateChain.itemID, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(var1, Item.legsChain.itemID, 0.2F, 14, 21, 5);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemRefinedAxe.itemID, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemRefinedHoe.itemID, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemRefinedPickAxe.itemID, 0.2F, 24, 36, 5);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemRefinedShovel.itemID, 0.2F, 8, 12, 5);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemRefinedSword.itemID, 0.2F, 16, 24, 5);
        this.CheckForArcaneScrollTrade(var1, Enchantment.unbreaking.effectId, 0.2F, 32, 48, 5);
    }

    private MerchantRecipe GetBlacksmithLevelUpRecipe()
    {
        int var1 = this.GetCurrentTradeLevel();

        switch (var1)
        {
            case 1:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBBQ, 1), new ItemStack(Item.emerald.itemID, 1, 0), -var1);

            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBellows, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcCrucible, 1), new ItemStack(Item.emerald.itemID, 3, 0), -var1);

            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 8), new ItemStack(Item.emerald.itemID, 4, 0), -var1);

            default:
                return null;
        }
    }

    private int CheckForBlacksmithMandatoryTrades(int var1)
    {
        return var1;
    }

    private void CheckForButcherTrades(MerchantRecipeList var1)
    {
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.arrow.itemID, 0.2F, 24, 32, 1);
        this.CheckForWishToBuySingleItemTrade(var1, Item.shears.itemID, 0.1F, 1, 1, 1);
        this.CheckForWishToBuySingleItemTrade(var1, Item.fishingRod.itemID, 0.1F, 1, 1, 1);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.beefRaw.itemID, 0.2F, 7, 9, 1);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.porkRaw.itemID, 0.2F, 8, 11, 1);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.chickenRaw.itemID, 0.2F, 9, 12, 1);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.fishRaw.itemID, 0.2F, 10, 13, 1);
        this.CheckForWishToSellMultipleItemsTrade(var1, Item.leather.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemMuttonRaw.itemID, 0.2F, 10, 13, 1);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemDung.itemID, 0.2F, 10, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemWolfRaw.itemID, 0.2F, 6, 8, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemBark.itemID, 1, 0.2F, 48, 64, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemSteakAndPotatoes.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemHamAndEggs.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemTastySandwich.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemFishSoup.itemID, 0.2F, 10, 12, 2);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemCookedKebab.itemID, 0.2F, 4, 6, 2);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.carrot.itemID, 0.2F, 10, 16, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, Item.potato.itemID, 0.2F, 10, 16, 3);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemBeastLiverRaw.itemID, 0.2F, 1, 2, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemTannedLeatherCut.itemID, 0.2F, 4, 8, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemSteakDinner.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemPorkDinner.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemChickenSoup.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemWolfDinner.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellSingleItemTrade(var1, Item.saddle.itemID, 0.2F, 6, 8, 3);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemRawMysteryMeat.itemID, 0.2F, 2, 4, 4);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemDynamite.itemID, 0.2F, 4, 6, 5);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemScrew.itemID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemCompositeBow.itemID, 0.2F, 2, 3, 4);
        this.CheckForWishToSellMultipleItemsTrade(var1, FCBetterThanWolves.fcItemHeartyStew.itemID, 0.2F, 3, 4, 4);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemArmorTannedBoots.itemID, 0.1F, 2, 3, 4);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemArmorTannedChest.itemID, 0.1F, 6, 8, 4);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemArmorTannedHelm.itemID, 0.1F, 3, 4, 4);
        this.CheckForWishToSellSingleItemTrade(var1, FCBetterThanWolves.fcItemArmorTannedLeggings.itemID, 0.1F, 4, 6, 4);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcItemBattleAxe.itemID, 0.2F, 4, 5, 5);
        this.CheckForWishToBuySingleItemTrade(var1, FCBetterThanWolves.fcCompanionCube.blockID, 0.2F, 1, 2, 5);
        this.CheckForWishToBuyMultipleItemsTrade(var1, FCBetterThanWolves.fcItemBroadheadArrow.itemID, 0.2F, 6, 12, 5);
        this.CheckForComplexTrade(var1, new ItemStack(FCBetterThanWolves.fcBlockLightningRod, 1), new ItemStack(FCBetterThanWolves.fcItemSoap, 1), new ItemStack(Item.emerald, this.rand.nextInt(3) + 3), 0.1F, 5);
        this.CheckForArcaneScrollTrade(var1, Enchantment.sharpness.effectId, 0.2F, 32, 48, 5);
    }

    private MerchantRecipe GetButcherLevelUpRecipe()
    {
        int var1 = this.GetCurrentTradeLevel();

        switch (var1)
        {
            case 1:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcCauldron, 1), new ItemStack(Item.emerald.itemID, 1, 0), -var1);

            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcSaw, 1), new ItemStack(Item.emerald.itemID, 2, 0), -var1);

            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemBreedingHarness, 1), new ItemStack(Item.emerald.itemID, 3, 0), -var1);

            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 12), new ItemStack(Item.emerald.itemID, 4, 0), -var1);

            default:
                return null;
        }
    }

    private int CheckForButcherMandatoryTrades(int var1)
    {
        int var2 = this.GetCurrentTradeLevel();

        if (var2 >= 4 && this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.skull, 1, 0), new ItemStack(Item.emerald, this.rand.nextInt(3) + 6), new ItemStack(Item.skull, 1, 1), 3)))
        {
            --var1;
        }

        return var1;
    }

    private void AddDefaultTradeToList(MerchantRecipeList var1)
    {
        switch (this.getProfession())
        {
            case 0:
                var1.add(new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLoose.blockID, this.rand.nextInt(17) + 48, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1));
                break;

            case 1:
                var1.add(new MerchantRecipe(new ItemStack(Item.paper.itemID, this.rand.nextInt(12) + 27, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1));
                break;

            case 2:
                var1.add(new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemHemp.itemID, this.rand.nextInt(5) + 18, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1));
                break;

            case 3:
                var1.add(new MerchantRecipe(new ItemStack(Item.coal.itemID, this.rand.nextInt(9) + 16, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1));
                break;

            case 4:
                var1.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 1, 0), new ItemStack(Item.beefRaw.itemID, this.rand.nextInt(3) + 7, 0), 1));
        }
    }

    private float ComputeAdjustedChanceOfTrade(float var1, int var2)
    {
        float var3 = 1.0F;
        int var4 = this.GetCurrentTradeLevel();

        if (var2 > 0 && var4 > 0)
        {
            var3 = (float)var2 / (float)var4;
        }

        return var1 * var3;
    }

    private void CheckForWishToBuyMultipleItemsTrade(MerchantRecipeList var1, int var2, float var3, int var4, int var5, int var6)
    {
        this.CheckForWishToBuyMultipleItemsTrade(var1, var2, 0, var3, var4, var5, var6);
    }

    private void CheckForWishToBuyMultipleItemsTrade(MerchantRecipeList var1, int var2, int var3, float var4, int var5, int var6, int var7)
    {
        if (this.GetCurrentTradeLevel() >= var7 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var4, var7))
        {
            int var8 = MathHelper.getRandomIntegerInRange(this.rand, var5, var6);
            this.AddWishToBuyTradeToList(var1, var2, var8, var3, 1, var7);
        }
    }

    private void CheckForWishToBuySingleItemTrade(MerchantRecipeList var1, int var2, float var3, int var4, int var5, int var6)
    {
        this.CheckForWishToBuySingleItemTrade(var1, var2, 0, var3, var4, var5, var6);
    }

    private void CheckForWishToBuySingleItemTrade(MerchantRecipeList var1, int var2, int var3, float var4, int var5, int var6, int var7)
    {
        if (this.GetCurrentTradeLevel() >= var7 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var4, var7))
        {
            int var8 = MathHelper.getRandomIntegerInRange(this.rand, var5, var6);
            this.AddWishToBuyTradeToList(var1, var2, 1, var3, var8, var7);
        }
    }

    private void AddWishToBuyTradeToList(MerchantRecipeList var1, int var2, int var3, int var4, int var5, int var6)
    {
        ItemStack var7 = new ItemStack(Item.emerald.itemID, var5, 0);
        ItemStack var8 = new ItemStack(var2, var3, var4);
        var1.add(new MerchantRecipe(var8, var7, var6));
    }

    private void CheckForWishToSellSingleItemTrade(MerchantRecipeList var1, int var2, float var3, int var4, int var5, int var6)
    {
        this.CheckForWishToSellSingleItemTrade(var1, var2, 0, var3, var4, var5, var6);
    }

    private void CheckForWishToSellSingleItemTrade(MerchantRecipeList var1, int var2, int var3, float var4, int var5, int var6, int var7)
    {
        if (this.GetCurrentTradeLevel() >= var7 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var4, var7))
        {
            int var8 = MathHelper.getRandomIntegerInRange(this.rand, var5, var6);
            this.AddWishToSellTradeToList(var1, var2, 1, var3, var8, var7);
        }
    }

    private void CheckForWishToSellMultipleItemsTrade(MerchantRecipeList var1, int var2, float var3, int var4, int var5, int var6)
    {
        this.CheckForWishToSellMultipleItemsTrade(var1, var2, 0, var3, var4, var5, var6);
    }

    private void CheckForWishToSellMultipleItemsTrade(MerchantRecipeList var1, int var2, int var3, float var4, int var5, int var6, int var7)
    {
        if (this.GetCurrentTradeLevel() >= var7 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var4, var7))
        {
            int var8 = MathHelper.getRandomIntegerInRange(this.rand, var5, var6);
            this.AddWishToSellTradeToList(var1, var2, var8, var3, 1, var7);
        }
    }

    private void AddWishToSellTradeToList(MerchantRecipeList var1, int var2, int var3, int var4, int var5, int var6)
    {
        ItemStack var7 = new ItemStack(Item.emerald.itemID, var5, 0);
        ItemStack var8 = new ItemStack(var2, var3, var4);
        var1.add(new MerchantRecipe(var7, var8, var6));
    }

    private void CheckForArcaneScrollTrade(MerchantRecipeList var1, int var2, float var3, int var4, int var5, int var6)
    {
        if (this.GetCurrentTradeLevel() >= var6 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var3, var6))
        {
            int var7 = MathHelper.getRandomIntegerInRange(this.rand, var4, var5);
            ItemStack var8 = new ItemStack(FCBetterThanWolves.fcItemArcaneScroll, 1, var2);
            var1.add(new MerchantRecipe(new ItemStack(Item.paper), new ItemStack(Item.emerald, var7), var8, var6));
        }
    }

    private void CheckForItemEnchantmentForCostTrade(MerchantRecipeList var1, Item var2, float var3, int var4, int var5, int var6)
    {
        if (this.GetCurrentTradeLevel() >= var6 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var3, var6))
        {
            int var7 = MathHelper.getRandomIntegerInRange(this.rand, var4, var5);
            var1.add(new MerchantRecipe(new ItemStack(var2, 1, 0), new ItemStack(Item.emerald, var7, 0), EnchantmentHelper.addRandomEnchantment(this.rand, new ItemStack(var2, 1, 0), 5 + this.rand.nextInt(15)), var6));
        }
    }

    private void CheckForItemConversionForCostTrade(MerchantRecipeList var1, Item var2, Item var3, float var4, int var5, int var6, int var7)
    {
        if (this.GetCurrentTradeLevel() >= var7 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var4, var7))
        {
            int var8 = MathHelper.getRandomIntegerInRange(this.rand, var5, var6);
            ItemStack var9 = new ItemStack(var2);
            ItemStack var10 = new ItemStack(var3);
            var1.add(new MerchantRecipe(var9, new ItemStack(Item.emerald, var8), var10, var7));
        }
    }

    private void CheckForSkullConversionForCostTrade(MerchantRecipeList var1, int var2, int var3, float var4, int var5, int var6, int var7)
    {
        if (this.GetCurrentTradeLevel() >= var7 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var4, var7))
        {
            int var8 = MathHelper.getRandomIntegerInRange(this.rand, var5, var6);
            ItemStack var9 = new ItemStack(Item.skull, 1, var2);
            ItemStack var10 = new ItemStack(Item.skull, 1, var3);
            var1.add(new MerchantRecipe(var9, new ItemStack(Item.emerald, var8), var10, var7));
        }
    }

    private void CheckForComplexTrade(MerchantRecipeList var1, ItemStack var2, ItemStack var3, ItemStack var4, float var5, int var6)
    {
        if (this.GetCurrentTradeLevel() >= var6 && this.rand.nextFloat() < this.ComputeAdjustedChanceOfTrade(var5, var6))
        {
            var1.add(new MerchantRecipe(var2, var3, var4, var6));
        }
    }

    private boolean DoesRecipeListAlreadyContainRecipe(MerchantRecipe var1)
    {
        for (int var2 = 0; var2 < this.buyingList.size(); ++var2)
        {
            MerchantRecipe var3 = (MerchantRecipe)this.buyingList.get(var2);

            if (var1.hasSameIDsAs(var3))
            {
                return true;
            }
        }

        return false;
    }

    private boolean CustomInteract(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && var2.getItem().itemID == Item.diamond.itemID && this.getGrowingAge() == 0 && this.GetInLove() == 0 && !this.IsPossessed())
        {
            if (!var1.capabilities.isCreativeMode)
            {
                --var2.stackSize;

                if (var2.stackSize <= 0)
                {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                }
            }

            this.worldObj.playSoundAtEntity(this, "random.classic_hurt", 1.0F, this.getSoundPitch() * 2.0F);
            this.SetInLove(1);
            this.entityToAttack = null;
            return true;
        }
        else
        {
            return false;
        }
    }

    private void CheckForInvalidTrades()
    {
        int var1 = this.getProfession();
        int var2 = this.GetCurrentTradeLevel();
        Iterator var3;
        MerchantRecipe var4;

        if (var1 == 0)
        {
            if (var2 >= 4)
            {
                var3 = this.buyingList.iterator();

                while (var3.hasNext())
                {
                    var4 = (MerchantRecipe)var3.next();

                    if (this.IsInvalidPeasantTrade(var4))
                    {
                        var3.remove();
                    }
                }
            }
        }
        else if (var1 == 2)
        {
            if (var2 >= 3)
            {
                var3 = this.buyingList.iterator();

                while (var3.hasNext())
                {
                    var4 = (MerchantRecipe)var3.next();

                    if (this.IsInvalidPriestTrade(var4))
                    {
                        var3.remove();
                    }
                }
            }
        }
        else if (var1 == 3)
        {
            if (var2 >= 4)
            {
                var3 = this.buyingList.iterator();

                while (var3.hasNext())
                {
                    var4 = (MerchantRecipe)var3.next();

                    if (this.IsInvalidBlacksmithTrade(var4))
                    {
                        var3.remove();
                    }
                }
            }
        }
        else if (var1 == 4 && var2 >= 5)
        {
            var3 = this.buyingList.iterator();

            while (var3.hasNext())
            {
                var4 = (MerchantRecipe)var3.next();

                if (this.IsInvalidButcherTrade(var4))
                {
                    var3.remove();
                }
            }
        }
    }

    private boolean IsInvalidPeasantTrade(MerchantRecipe var1)
    {
        return var1.getItemToBuy().itemID == FCBetterThanWolves.fcPlanter.blockID;
    }

    private boolean IsInvalidPriestTrade(MerchantRecipe var1)
    {
        if (var1.getItemToBuy().itemID == Item.netherStar.itemID)
        {
            if (var1.getSecondItemToBuy() == null && var1.getItemToSell().itemID == Item.emerald.itemID || var1.getSecondItemToBuy() != null && var1.getSecondItemToBuy().itemID == Item.emerald.itemID && var1.getItemToSell().itemID == FCBetterThanWolves.fcAnvil.blockID)
            {
                return true;
            }
        }
        else if (var1.getItemToBuy().itemID == Item.bone.itemID && var1.getItemToBuy().stackSize > 16)
        {
            return true;
        }

        return false;
    }

    private boolean IsInvalidBlacksmithTrade(MerchantRecipe var1)
    {
        return var1.getItemToBuy().itemID == FCBetterThanWolves.fcAnvil.blockID && var1.getItemToSell().itemID == Item.emerald.itemID;
    }

    private boolean IsInvalidButcherTrade(MerchantRecipe var1)
    {
        return var1.getItemToBuy().itemID == FCBetterThanWolves.fcAestheticNonOpaque.blockID && var1.getItemToBuy().getItemDamage() == 12;
    }

    private void UpdateStatusParticles()
    {
        if (this.getProfession() == 2 && this.GetCurrentTradeLevel() >= 5)
        {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.GetInLove() > 0)
        {
            this.GenerateRandomParticles("heart");
        }
    }

    protected void GenerateRandomParticles(String var1)
    {
        for (int var2 = 0; var2 < 5; ++var2)
        {
            double var3 = this.rand.nextGaussian() * 0.02D;
            double var5 = this.rand.nextGaussian() * 0.02D;
            double var7 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(var1, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var3, var5, var7);
        }
    }

    public void CheckForLooseMilk()
    {
        List var1 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().getAABB(this.posX - 1.0D, this.posY - 1.0D, this.posZ - 1.0D, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D));

        if (!var1.isEmpty())
        {
            for (int var2 = 0; var2 < var1.size(); ++var2)
            {
                EntityItem var3 = (EntityItem)var1.get(var2);

                if (var3.delayBeforeCanPickup <= 0 && !var3.isDead)
                {
                    int var4 = var3.getEntityItem().itemID;
                    Item var5 = Item.itemsList[var4];

                    if (var5.itemID == Item.bucketMilk.itemID)
                    {
                        var3.setDead();
                        var3 = new EntityItem(this.worldObj, this.posX, this.posY - 0.30000001192092896D + (double)this.getEyeHeight(), this.posZ, new ItemStack(Item.bucketMilk, 1, 0));
                        float var6 = 0.2F;
                        var3.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var6);
                        var3.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var6);
                        var3.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * var6 + 0.2F);
                        var6 = 0.02F;
                        float var7 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                        var6 *= this.rand.nextFloat();
                        var3.motionX += Math.cos((double)var7) * (double)var6;
                        var3.motionY += 0.25D;
                        var3.motionZ += Math.sin((double)var7) * (double)var6;
                        var3.delayBeforeCanPickup = 10;
                        this.worldObj.spawnEntityInWorld(var3);
                        int var8 = MathHelper.floor_double(var3.posX);
                        int var9 = MathHelper.floor_double(var3.posY);
                        int var10 = MathHelper.floor_double(var3.posZ);
                        byte var11 = 0;

                        if (this.IsPossessed() || this.getProfession() == 2 && this.GetCurrentTradeLevel() == 5)
                        {
                            var11 = 1;
                        }

                        this.worldObj.playAuxSFX(2265, var8, var9, var10, var11);
                    }
                }
            }
        }
    }

    public int GetInLove()
    {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void SetInLove(int var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf(var1));
    }

    public int GetDirtyPeasant()
    {
        return this.dataWatcher.getWatchableObjectInt(26);
    }

    public void SetDirtyPeasant(int var1)
    {
        this.dataWatcher.updateObject(26, Integer.valueOf(var1));
    }

    public int GetCurrentTradeLevel()
    {
        return this.dataWatcher.getWatchableObjectInt(23);
    }

    public void SetTradeLevel(int var1)
    {
        this.dataWatcher.updateObject(23, Integer.valueOf(var1));
    }

    public int GetCurrentTradeXP()
    {
        return this.dataWatcher.getWatchableObjectInt(25);
    }

    public void SetTradeExperience(int var1)
    {
        this.dataWatcher.updateObject(25, Integer.valueOf(var1));
    }

    public int GetCurrentTradeMaxXP()
    {
        int var1 = this.GetCurrentTradeLevel();

        switch (var1)
        {
            case 1:
                return 5;

            case 2:
                return 7;

            case 3:
                return 10;

            case 4:
                return 15;

            default:
                return 20;
        }
    }

    public int GetCurrentMaxNumTrades()
    {
        int var1 = this.GetCurrentTradeLevel();
        int var2 = var1;

        switch (this.getProfession())
        {
            case 2:
                if (var1 >= 4)
                {
                    var2 = var1 + 1;
                }

            case 0:
            case 1:
            case 3:
            case 4:
            default:
                return var2;
        }
    }
}
