// FCMOD

package net.minecraft.src;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class FCEntityVillager extends EntityVillager
{
	public static int m_iNumProfessionTypes = 5;

	protected static final int m_iInLoveDataWatcherID = 22;	
	protected static final int m_iTradeLevelDataWatcherID = 23;

	// data watcher 24 used by EntityCreature parent to indicate possession

	protected static final int m_iTradeExperienceDataWatcherID = 25; 
	protected static final int m_iDirtyPeasantDataWatcherID = 26;

	protected int m_iAIFullTickCountdown;
	protected int m_iUpdateTradesCountdown;

	public static final int professionIDFarmer = 0;
	public static final int professionIDLibrarian = 1;
	public static final int professionIDPriest = 2;
	public static final int professionIDBlacksmith = 3;
	public static final int professionIDButcher = 4;

	public static Map<Integer, Class> professionMap = new HashMap<Integer, Class>();

	protected NBTTagCompound tagForFormatConversion;

	static {
		professionMap.put(professionIDFarmer, AddonEntityVillagerFarmer.class);
		professionMap.put(professionIDLibrarian, AddonEntityVillagerLibrarian.class);
		professionMap.put(professionIDPriest, AddonEntityVillagerPriest.class);
		professionMap.put(professionIDBlacksmith, AddonEntityVillagerBlacksmith.class);
		professionMap.put(professionIDButcher, AddonEntityVillagerButcher.class);
	}

	public static FCEntityVillager createVillager(World world) {
		return createVillagerFromProfession(world, 0);
	}

	public static FCEntityVillager createVillagerFromProfession(World world, int profession) {
		Class villagerClass = professionMap.get(profession);

		try {
			FCEntityVillager villager = (FCEntityVillager) villagerClass.getConstructor(World.class).newInstance(world);

			return villager;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;
	}

	public FCEntityVillager( World world )
	{
		this( world, 0 );
	}

	public FCEntityVillager( World world, int iProfession )
	{
		super( world, iProfession );

		tasks.RemoveAllTasksOfClass( EntityAIAvoidEntity.class );

		tasks.addTask( 1, new EntityAIAvoidEntity( this, FCEntityZombie.class, 8.0F, 0.3F, 0.35F ) );
		tasks.addTask( 1, new EntityAIAvoidEntity( this, FCEntityWolf.class, 8.0F, 0.3F, 0.35F ) );

		tasks.RemoveAllTasksOfClass( EntityAIVillagerMate.class );

		tasks.addTask( 1, new FCEntityAIVillagerMate( this ) );
		tasks.addTask( 2, new EntityAITempt( this, 0.3F, Item.diamond.itemID, false ) );

		experienceValue = 50; // set experience when slain

		m_iUpdateTradesCountdown = 0;        
		m_iAIFullTickCountdown = 0;
	}

	@Override
	protected void updateAITick()
	{
		m_iAIFullTickCountdown--;

		if ( m_iAIFullTickCountdown <= 0 )
		{
			m_iAIFullTickCountdown = 70 + rand.nextInt(50); // schedule the next village position update

			worldObj.villageCollectionObj.addVillagerPosition( MathHelper.floor_double( posX ), MathHelper.floor_double( posY ), MathHelper.floor_double( posZ ) );

			villageObj = worldObj.villageCollectionObj.findNearestVillage(
					MathHelper.floor_double( posX ), MathHelper.floor_double( posY ), MathHelper.floor_double( posZ ), 32 );

			if ( villageObj == null )
			{
				detachHome();
			}
			else
			{
				ChunkCoordinates var1 = villageObj.getCenter();

				setHomeArea( var1.posX, var1.posY, var1.posZ, (int)( (float)villageObj.getVillageRadius() * 0.6F ) );
			}
		}

		if ( !isTrading() )
		{
			if ( GetCurrentTradeLevel() == 0 )
			{
				// this indicates a newly created villager or an old one that was created before I revamped the trading system

				SetTradeLevel( 1 );

				buyingList = null;
				m_iUpdateTradesCountdown = 0;

				CheckForNewTrades( 1 );
			}
			else if ( m_iUpdateTradesCountdown > 0 )
			{
				m_iUpdateTradesCountdown--;

				if ( m_iUpdateTradesCountdown <= 0 )
				{
					// remove all trades which have exceeded their maximum uses

					Iterator tradeListIterator = this.buyingList.iterator();

					while ( tradeListIterator.hasNext() )
					{
						MerchantRecipe tempRecipe = (MerchantRecipe)tradeListIterator.next();

						if (tempRecipe.func_82784_g()) // check for toolUses >= this.maxTradeUses;
						{
							tradeListIterator.remove();
						}
					}

					int iDesiredNumTrades = GetCurrentMaxNumTrades();

					if ( buyingList.size() < iDesiredNumTrades )
					{
						CheckForNewTrades( iDesiredNumTrades - buyingList.size() );

						worldObj.setEntityState( this, (byte)14 ); // triggers "happy villager" particles

						addPotionEffect( new PotionEffect( Potion.regeneration.id, 200, 0 ) );
					}
				}
			}
			else
			{
				// schedule periodic checks of the trade list so it'll never jam up

				m_iUpdateTradesCountdown = 600 + rand.nextInt( 600 ); // 30 seconds to a minute
			}
		}
	}

	@Override
	public boolean interact( EntityPlayer player )
	{
		if ( CustomInteract( player ) )
		{
			return true;
		}

		if ( GetInLove() > 0 )
		{
			return EntityAgeableInteract( player );
		}

		return super.interact( player );    		
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		//Dirty hacks to maintain compatibility with older saves
		/*if (this.getClass().equals(FCEntityVillager.class)) {
			FCEntityVillager villager = createVillagerFromProfession(this.worldObj, this.getProfession());
			villager.readEntityFromNBT(tagForFormatConversion);
			this.worldObj.spawnEntityInWorld(villager);

			this.setDead();
			return;
		}*/

		dataWatcher.addObject( m_iInLoveDataWatcherID, new Integer( 0 ) );
		dataWatcher.addObject( m_iTradeLevelDataWatcherID, new Integer( 0 ) );
		dataWatcher.addObject( m_iTradeExperienceDataWatcherID, new Integer( 0 ) );
		dataWatcher.addObject( m_iDirtyPeasantDataWatcherID, new Integer( 0 ) );
	}

	@Override
	public void writeEntityToNBT( NBTTagCompound tag )
	{
		super.writeEntityToNBT( tag );

		tag.setInteger( "FCInLove", GetInLove() );

		tag.setInteger( "FCTradeLevel", GetCurrentTradeLevel() );
		tag.setInteger( "FCTradeXP", GetCurrentTradeXP() );

		tag.setInteger( "FCDirty", GetDirtyPeasant() );
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);

		if (tag.hasKey("FCInLove")) {
			this.SetInLove(tag.getInteger("FCInLove"));
		}

		if (tag.hasKey("FCTradeLevel")) {
			this.SetTradeLevel(tag.getInteger("FCTradeLevel"));
		}

		if (tag.hasKey("FCTradeXP")) {
			this.SetTradeExperience(tag.getInteger("FCTradeXP"));
		}

		if (tag.hasKey("FCDirty")) {
			this.SetDirtyPeasant(tag.getInteger("FCDirty"));
		}

		this.CheckForInvalidTrades();

		this.tagForFormatConversion = tag;
	}

	@Override
	public void setRevengeTarget( EntityLiving attackingEntity )
	{
		entityLivingToAttack = attackingEntity;

		if ( attackingEntity != null )
		{
			revengeTimer = 100;

			if ( villageObj != null )
			{
				villageObj.addOrRenewAgressor( attackingEntity );
			}

			if ( isEntityAlive() )
			{
				worldObj.setEntityState( this, (byte)13 );
			}
		}
		else
		{
			revengeTimer = 0;
		}
	}

	@Override
	public void useRecipe( MerchantRecipe recipe )
	{
		recipe.incrementToolUses();

		m_iUpdateTradesCountdown = 10;

		// special trade reactions

		if ( recipe.getItemToBuy().itemID == FCBetterThanWolves.fcCompanionCube.blockID )
		{
			worldObj.playSoundEffect( posX, posY, posZ, 
					"mob.wolf.hurt", 5.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);            
		}
		else if ( recipe.getItemToBuy().itemID == FCBetterThanWolves.fcBlockLightningRod.blockID ) 
		{
			worldObj.playSoundEffect( posX, posY, posZ, 
					"random.classic_hurt", 1F, 
					getSoundPitch() * 2.0F);
		}
		else if ( recipe.getItemToBuy().itemID == FCBetterThanWolves.fcItemSoap.itemID )
		{
			worldObj.playSoundEffect( posX, posY, posZ, "mob.slime.attack", 1.0F, ( rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F );

			SetDirtyPeasant( 0 );
		}
		else if ( recipe.getItemToSell().itemID == FCBetterThanWolves.fcAnvil.blockID )
		{
			worldObj.playSoundEffect( posX, posY, posZ, "random.anvil_land", 0.3F, rand.nextFloat() * 0.1F + 0.9F );
			worldObj.playSoundEffect( posX, posY, posZ, "ambient.cave.cave4", 0.5F, rand.nextFloat() * 0.05F + 0.5F );
		}

		if ( recipe.m_iTradeLevel < 0 ) // negative trade levels represent a level up trade
		{
			int iVillagerTradeLevel = GetCurrentTradeLevel();

			if ( iVillagerTradeLevel < 5 && 
					GetCurrentTradeXP() == GetCurrentTradeMaxXP() && 
					GetCurrentTradeLevel() == -( recipe.m_iTradeLevel ) )
			{
				iVillagerTradeLevel++;

				SetTradeLevel( iVillagerTradeLevel );
				SetTradeExperience( 0 );

				if ( this.getProfession() == 2 && GetCurrentTradeLevel() >= 5 )
				{
					worldObj.playSoundAtEntity( this, "mob.enderdragon.growl", 1.0F, 0.5F );

					worldObj.playSoundAtEntity( this, "ambient.weather.thunder", 1.0F, rand.nextFloat() * 0.4F + 0.8F );

					worldObj.playSoundAtEntity( this, "random.levelup", 0.75F + ( rand.nextFloat() * 0.25F ), 0.5F );
				}
				else
				{
					worldObj.playSoundAtEntity( this, "random.levelup", 0.5F + ( rand.nextFloat() * 0.25F ), 1.5F );
				}
			}
		}
		else if ( recipe.m_iTradeLevel >= GetCurrentTradeLevel() )
		{    		
			int iCurrentXP = GetCurrentTradeXP() + 1;
			int iMaxXP = GetCurrentTradeMaxXP();

			if ( iCurrentXP > iMaxXP )
			{
				iCurrentXP = iMaxXP;
			}

			SetTradeExperience( iCurrentXP );
		}
	}

	@Override
	public MerchantRecipeList getRecipes( EntityPlayer player )
	{
		if ( buyingList == null )
		{
			CheckForNewTrades( 1 );
		}

		return buyingList;
	}

	@Override
	public void initCreature()
	{
		setProfession( worldObj.rand.nextInt( m_iNumProfessionTypes ) );
	}


	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if ( !worldObj.isRemote )
		{
			if ( isEntityAlive() )
			{
				CheckForLooseMilk();
			}
		}
		else
		{
			UpdateStatusParticles();
		}
	}

	@Override
	protected void dropFewItems( boolean bKilledByPlayer, int iLootingModifier )
	{
		if ( !HasHeadCrabbedSquid() )
		{
			int iDropItemID = FCBetterThanWolves.fcItemRawMysteryMeat.itemID;

			if ( isBurning() )
			{
				iDropItemID = FCBetterThanWolves.fcItemCookedMysteryMeat.itemID;
			}

			int iNumDropped = rand.nextInt( 2 ) + 1 + rand.nextInt( 1 + iLootingModifier );

			for ( int iTempCount = 0; iTempCount < iNumDropped; ++iTempCount )
			{        	
				dropItem( iDropItemID, 1 );
			}
		}
	}

	@Override
	protected float getSoundPitch()
	{
		float fPitch = super.getSoundPitch();

		if ( IsPossessed() || ( getProfession() == 2 && GetCurrentTradeLevel() == 5 ) )
		{
			fPitch *= 0.60F;
		}

		return fPitch;
	}

	@Override
	protected boolean GetCanCreatureTypeBePossessed()
	{
		return true;
	}

	@Override
	protected void OnFullPossession()
	{
		worldObj.playAuxSFX( FCBetterThanWolves.m_iPossessedVillagerTransformToWitchAuxFXID, 
				MathHelper.floor_double( posX ), MathHelper.floor_double( posY ), MathHelper.floor_double( posZ ), 
				0 );

		setDead();

		FCEntityWitch entityWitch = new FCEntityWitch( worldObj );

		entityWitch.setLocationAndAngles( posX, posY, posZ, rotationYaw, rotationPitch );
		entityWitch.renderYawOffset = renderYawOffset;

		entityWitch.SetPersistent( true );

		worldObj.spawnEntityInWorld( entityWitch );
	}

	@Override
	public boolean IsValidZombieSecondaryTarget( EntityZombie zombie )
	{
		return true;
	}

	@Override
	public boolean IsSecondaryTargetForSquid()
	{
		return true;
	}

	@Override
	public double getMountedYOffset()
	{
		return (double)height;
	}

	@Override
	public FCEntityVillager func_90012_b( EntityAgeable otherParent )
	{
		// creates new villager when breeding

		FCEntityVillager child = createVillager(this.worldObj);

		child.initCreature();

		return child;
	}

	//------------- Class Specific Methods ------------//

	protected void CheckForNewTrades(int availableTrades)
	{
		if (availableTrades > 0)
		{
			if (this.GetCurrentTradeMaxXP() == this.GetCurrentTradeXP() && this.CheckForLevelUpTrade())
			{
				--availableTrades;

				if (availableTrades <= 0)
				{
					return;
				}
			}

			availableTrades = this.checkForProfessionMandatoryTrades(availableTrades, this.GetCurrentTradeLevel());

			if (availableTrades > 0)
			{
				MerchantRecipeList recipeList = new MerchantRecipeList();

				this.checkForProfessionTrades(recipeList);

				if (recipeList.isEmpty())
				{
					recipeList.add(this.getProfessionDefaultTrade());
				}
				else
				{
					Collections.shuffle(recipeList);
				}

				if (this.buyingList == null)
				{
					this.buyingList = new MerchantRecipeList();
				}

				for (int i = 0; i < availableTrades && i < recipeList.size(); ++i)
				{
					this.buyingList.addToListWithCheck((MerchantRecipe)recipeList.get(i));
				}
			}
		}
	}

	private boolean CheckForLevelUpTrade()
	{
		MerchantRecipe recipe = this.getProfessionLevelUpTrade(this.GetCurrentTradeLevel());

		if (recipe != null && !this.DoesRecipeListAlreadyContainRecipe(recipe))
		{
			this.buyingList.add(recipe);
			return true;
		}
		else
		{
			return false;
		}
	}

	protected boolean AttemptToAddTradeToBuyingList( MerchantRecipe recipe )
	{
		if ( recipe != null )
		{
			if ( !DoesRecipeListAlreadyContainRecipe( recipe ) )
			{
				buyingList.add( recipe );

				return true;
			}        	
		}

		return false;
	}

	/**
	 * Check for all trades available to the profession
	 * @param recipeList
	 */
	protected abstract void checkForProfessionTrades(MerchantRecipeList recipeList);

	/**
	 * Return the level up trade based on current villager level
	 * @return The level up trade
	 */
	protected abstract MerchantRecipe getProfessionLevelUpTrade(int level);

	/**
	 * Return the default trade to use if no other trade is found
	 * @return The default trade
	 */
	protected abstract MerchantRecipe getProfessionDefaultTrade();

	/**
	 * Adds the mandatory trades for the profession at the current level
	 * Use AttemptToAddTradeToBuyingList() for each recipe and decrement remaining available recipes on success
	 * @param availableTrades
	 * @return
	 */
	protected int checkForProfessionMandatoryTrades(int availableTrades, int level) {
		return availableTrades;
	}

	/**
	 * Used to clear any invalid trades e.g. that may be left over from previous versions
	 * @param trade The trade to check
	 * @return Whether the trade was invalid and should be removed
	 */
	protected boolean isInvalidProfessionTrade(MerchantRecipe trade) {
		return false;
	}

	protected void AddDefaultTradeToList( MerchantRecipeList newTradeList )
	{		
		// default recipe add if no others found

		switch ( getProfession() )
		{
		case 0: // peasant

			newTradeList.add( new MerchantRecipe( 
					new ItemStack( FCBetterThanWolves.fcBlockDirtLoose.blockID, rand.nextInt( 17 ) + 48, 0 ), 
					new ItemStack( Item.emerald.itemID, 1, 0 ), 
					1 ) );        	    

			break;

		case 1: // librarian

			newTradeList.add( new MerchantRecipe( 
					new ItemStack( Item.paper.itemID, rand.nextInt( 12 ) + 27, 0 ), 
					new ItemStack( Item.emerald.itemID, 1, 0 ), 
					1 ) );

			break;

		case 2: // priest

			newTradeList.add( new MerchantRecipe( 
					new ItemStack( FCBetterThanWolves.fcItemHemp.itemID, rand.nextInt( 5 ) + 18, 0 ), 
					new ItemStack( Item.emerald.itemID, 1, 0 ), 
					1 ) );

			break;

		case 3: // blacksmith

			newTradeList.add( new MerchantRecipe( 
					new ItemStack( Item.coal.itemID, rand.nextInt( 9 ) + 16, 0 ), 
					new ItemStack( Item.emerald.itemID, 1, 0 ), 
					1 ) );        	    

			break;

		case 4: // butcher

			newTradeList.add( new MerchantRecipe( 
					new ItemStack( Item.emerald.itemID, 1, 0 ), 
					new ItemStack( Item.beefRaw.itemID, rand.nextInt( 3 ) + 7, 0 ), 
					1 ) );        	    

			break;
		}        
	}

	protected float ComputeAdjustedChanceOfTrade( float fBaseFrequency, int iTradeLevel )
	{
		float fLevelModifier = 1F;
		int iVillagerTradeLevel = GetCurrentTradeLevel();

		if ( iTradeLevel > 0 && iVillagerTradeLevel > 0 )
		{
			fLevelModifier = (float)iTradeLevel / (float)iVillagerTradeLevel;
		}

		return fBaseFrequency * fLevelModifier;
	}

	protected void CheckForWishToBuyMultipleItemsTrade( MerchantRecipeList tradeList, int iItemID, float fFrequency, int iMinItemCount, int iMaxItemCount, int iTradeLevel )
	{
		CheckForWishToBuyMultipleItemsTrade( tradeList, iItemID, 0, fFrequency, iMinItemCount, iMaxItemCount, iTradeLevel );
	}

	protected void CheckForWishToBuyMultipleItemsTrade( MerchantRecipeList tradeList, int iItemID, int iItemMetadata, float fFrequency, int iMinItemCount, int iMaxItemCount, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iItemCount = MathHelper.getRandomIntegerInRange( rand, iMinItemCount, iMaxItemCount );

			AddWishToBuyTradeToList( tradeList, iItemID, iItemCount, iItemMetadata, 1, iTradeLevel );
		}
	}

	protected void CheckForWishToBuySingleItemTrade( MerchantRecipeList tradeList, int iItemID, float fFrequency, int iMinEmeraldCount, int iMaxEmeraldCount, int iTradeLevel )
	{
		CheckForWishToBuySingleItemTrade( tradeList, iItemID, 0, fFrequency, iMinEmeraldCount, iMaxEmeraldCount, iTradeLevel );
	}

	protected void CheckForWishToBuySingleItemTrade( MerchantRecipeList tradeList, int iItemID, int iItemMetadata, float fFrequency, int iMinEmeraldCount, int iMaxEmeraldCount, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iEmeraldCount = MathHelper.getRandomIntegerInRange( rand, iMinEmeraldCount, iMaxEmeraldCount );        	

			AddWishToBuyTradeToList( tradeList, iItemID, 1, iItemMetadata, iEmeraldCount, iTradeLevel );        	
		}
	}

	protected void AddWishToBuyTradeToList( MerchantRecipeList tradeList, int iItemID, int iItemCount, int iItemMetadata, int iEmeraldCount, int iTradeLevel )
	{
		ItemStack emeraldStack = new ItemStack( Item.emerald.itemID, iEmeraldCount, 0 );
		ItemStack itemToSellStack = new ItemStack( iItemID, iItemCount, iItemMetadata );

		tradeList.add( new MerchantRecipe( itemToSellStack, emeraldStack, iTradeLevel ) );
	}

	protected void CheckForWishToSellSingleItemTrade( MerchantRecipeList tradeList, int iItemID, float fFrequency, int iMinEmeraldCount, int iMaxEmeraldCount, int iTradeLevel )
	{
		CheckForWishToSellSingleItemTrade( tradeList, iItemID, 0, fFrequency, iMinEmeraldCount, iMaxEmeraldCount, iTradeLevel );
	}

	protected void CheckForWishToSellSingleItemTrade( MerchantRecipeList tradeList, int iItemID, int iItemMetadata, float fFrequency, int iMinEmeraldCount, int iMaxEmeraldCount, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iEmeraldCount = MathHelper.getRandomIntegerInRange( rand, iMinEmeraldCount, iMaxEmeraldCount );        	

			AddWishToSellTradeToList( tradeList, iItemID, 1, iItemMetadata, iEmeraldCount, iTradeLevel );
		}
	}

	protected void CheckForWishToSellMultipleItemsTrade( MerchantRecipeList tradeList, int iItemID, float fFrequency, int iMinItemCount, int iMaxItemCount, int iTradeLevel )
	{
		CheckForWishToSellMultipleItemsTrade( tradeList, iItemID, 0, fFrequency, iMinItemCount, iMaxItemCount, iTradeLevel );
	}

	protected void CheckForWishToSellMultipleItemsTrade( MerchantRecipeList tradeList, int iItemID, int iItemMetadata, float fFrequency, int iMinItemCount, int iMaxItemCount, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iItemCount = MathHelper.getRandomIntegerInRange( rand, iMinItemCount, iMaxItemCount );

			AddWishToSellTradeToList( tradeList, iItemID, iItemCount, iItemMetadata, 1, iTradeLevel );
		}
	}

	protected void AddWishToSellTradeToList( MerchantRecipeList tradeList, int iItemID, int iItemCount, int iItemMetadata, int iEmeraldCount, int iTradeLevel )
	{
		ItemStack emeraldStack = new ItemStack( Item.emerald.itemID, iEmeraldCount, 0 );
		ItemStack itemToSellStack = new ItemStack( iItemID, iItemCount, iItemMetadata );

		tradeList.add( new MerchantRecipe( emeraldStack, itemToSellStack, iTradeLevel ) );
	}

	protected void CheckForArcaneScrollTrade( MerchantRecipeList tradeList, int iEnchantmentID, float fFrequency, int iMinPrice, int iMaxPrice, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iEmeraldQuantity = MathHelper.getRandomIntegerInRange( rand, iMinPrice, iMaxPrice );

			ItemStack outputStack = new ItemStack( FCBetterThanWolves.fcItemArcaneScroll, 1, iEnchantmentID );

			tradeList.add( new MerchantRecipe( new ItemStack( Item.paper ), new ItemStack( Item.emerald, iEmeraldQuantity ), outputStack, iTradeLevel ) );
		}
	}

	protected void CheckForItemEnchantmentForCostTrade( MerchantRecipeList tradeList, Item inputItem, float fFrequency, int iMinPrice, int iMaxPrice, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iEmeraldQuantity = MathHelper.getRandomIntegerInRange( rand, iMinPrice, iMaxPrice );

			tradeList.add( new MerchantRecipe( 
					new ItemStack( inputItem, 1, 0 ), 
					new ItemStack( Item.emerald, iEmeraldQuantity, 0 ), 
					EnchantmentHelper.addRandomEnchantment( rand, new ItemStack( inputItem, 1, 0 ), 5 + rand.nextInt( 15 ) ), 
					iTradeLevel ) );
		}
	}

	protected void CheckForItemConversionForCostTrade( MerchantRecipeList tradeList, Item inputItem, Item outputItem, float fFrequency, int iMinPrice, int iMaxPrice, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iEmeraldQuantity = MathHelper.getRandomIntegerInRange( rand, iMinPrice, iMaxPrice );

			ItemStack inputStack = new ItemStack( inputItem );
			ItemStack outputStack = new ItemStack( outputItem );

			tradeList.add( new MerchantRecipe( inputStack, new ItemStack( Item.emerald, iEmeraldQuantity ), outputStack, iTradeLevel ) );
		}
	}

	protected void CheckForSkullConversionForCostTrade( MerchantRecipeList tradeList, int iInputSkullType, int iOutputSkullType, float fFrequency, int iMinPrice, int iMaxPrice, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			int iEmeraldQuantity = MathHelper.getRandomIntegerInRange( rand, iMinPrice, iMaxPrice );

			ItemStack inputStack = new ItemStack( Item.skull, 1, iInputSkullType );
			ItemStack outputStack = new ItemStack( Item.skull, 1, iOutputSkullType );

			tradeList.add( new MerchantRecipe( inputStack, new ItemStack( Item.emerald, iEmeraldQuantity ), outputStack, iTradeLevel ) );
		}
	}

	protected void CheckForComplexTrade( MerchantRecipeList tradeList, ItemStack inputStack1, ItemStack inputStack2, ItemStack outputStack, float fFrequency, int iTradeLevel )
	{
		if ( GetCurrentTradeLevel() >= iTradeLevel && rand.nextFloat() < ComputeAdjustedChanceOfTrade( fFrequency, iTradeLevel ) )
		{
			tradeList.add( new MerchantRecipe( inputStack1, inputStack2, outputStack, iTradeLevel ) );
		}
	}

	protected boolean DoesRecipeListAlreadyContainRecipe( MerchantRecipe recipe )
	{
		for ( int iTempRecipeIndex = 0; iTempRecipeIndex < buyingList.size(); ++iTempRecipeIndex )
		{
			MerchantRecipe tempRecipe = (MerchantRecipe)buyingList.get(iTempRecipeIndex);

			if ( recipe.hasSameIDsAs( tempRecipe ) )
			{
				return true;
			}
		}

		return false;
	}    

	protected boolean CustomInteract( EntityPlayer player )
	{
		ItemStack heldStack = player.inventory.getCurrentItem();

		if ( heldStack != null && heldStack.getItem().itemID == Item.diamond.itemID && 
				getGrowingAge() == 0 && GetInLove() == 0 && !IsPossessed() )
		{
			if ( !player.capabilities.isCreativeMode )
			{
				heldStack.stackSize--;

				if ( heldStack.stackSize <= 0 )
				{
					player.inventory.setInventorySlotContents( player.inventory.currentItem, (ItemStack)null );
				}
			}

			worldObj.playSoundAtEntity( this, 
					"random.classic_hurt", 1.0F, 
					getSoundPitch() * 2.0F);

			SetInLove( 1 );

			entityToAttack = null;

			return true;
		}
		else if (heldStack.itemID == DecoDefs.nameTag.itemID) {
			DecoDefs.nameTag.itemInteractionForEntity(heldStack, this);
		}

		return false;
	}    

	private void CheckForInvalidTrades() {
		MerchantRecipe trade;

		if (this.buyingList != null) {
			Iterator iterator = this.buyingList.iterator();

			while (iterator.hasNext()) {
				trade = (MerchantRecipe)iterator.next();

				if (this.isInvalidProfessionTrade(trade)) {
					iterator.remove();
				}
			}
		}
	}

	protected void UpdateStatusParticles()
	{
		if ( getProfession() == 2 && GetCurrentTradeLevel() >= 5 ) // top level priest
		{
			// enderman particles

			worldObj.spawnParticle( "portal", 
					posX + ( rand.nextDouble() - 0.5D ) * width, 
					posY + rand.nextDouble() * height - 0.25D, 
					posZ + ( rand.nextDouble() - 0.5D ) * width, 
					( rand.nextDouble() - 0.5D ) * 2D, 
					-rand.nextDouble(), 
					( rand.nextDouble() - 0.5D ) * 2D );
		}

		if ( GetInLove() > 0 )
		{
			GenerateRandomParticles( "heart" );
		}
	}

	protected void GenerateRandomParticles( String sParticle )
	{
		for ( int iTempCount = 0; iTempCount < 5; ++iTempCount )
		{
			double dVelX = rand.nextGaussian() * 0.02D;
			double dVelY = rand.nextGaussian() * 0.02D;
			double dVelZ = rand.nextGaussian() * 0.02D;

			worldObj.spawnParticle( sParticle, 
					posX + ( rand.nextFloat() * width * 2F ) - width, 
					posY + 1D + ( rand.nextFloat() * height ), 
					posZ + ( rand.nextFloat() * width * 2F ) - width, 
					dVelX, dVelY, dVelZ);
		}
	}

	public void CheckForLooseMilk()
	{    
		List collisionList = worldObj.getEntitiesWithinAABB( EntityItem.class, 
				AxisAlignedBB.getAABBPool().getAABB( 
						posX - 1.0f, posY - 1.0f, posZ - 1.0f, 
						posX + 1.0f, posY + 1.0f, posZ + 1.0f) );

		if ( !collisionList.isEmpty() )
		{
			for(int listIndex = 0; listIndex < collisionList.size(); listIndex++)
			{
				EntityItem entityItem = (EntityItem)collisionList.get( listIndex );;

				if ( entityItem.delayBeforeCanPickup <= 0 && !(entityItem.isDead) )
				{
					int iTempItemID = entityItem.getEntityItem().itemID;

					Item tempItem = Item.itemsList[iTempItemID];

					if ( tempItem.itemID == Item.bucketMilk.itemID )
					{
						// toss the milk

						entityItem.setDead();

						entityItem = new EntityItem( worldObj, posX, posY - 0.30000001192092896D + (double)getEyeHeight(), posZ, 
								new ItemStack( Item.bucketMilk, 1, 0 ) );

						float f1 = 0.2F;

						entityItem.motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
						entityItem.motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;

						entityItem.motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f1 + 0.2F;

						f1 = 0.02F;
						float f3 = rand.nextFloat() * 3.141593F * 2.0F;
						f1 *= rand.nextFloat();
						entityItem.motionX += Math.cos(f3) * (double)f1;
						entityItem.motionY += 0.25F;
						entityItem.motionZ += Math.sin(f3) * (double)f1;

						entityItem.delayBeforeCanPickup = 10;

						worldObj.spawnEntityInWorld( entityItem );

						int iFXI = MathHelper.floor_double( entityItem.posX );
						int iFXJ = MathHelper.floor_double( entityItem.posY );
						int iFXK = MathHelper.floor_double( entityItem.posZ );

						int iExtraData = 0;

						if ( IsPossessed() || ( getProfession() == 2 && GetCurrentTradeLevel() == 5 ) )
						{
							iExtraData = 1;
						}

						worldObj.playAuxSFX( FCBetterThanWolves.m_iTossTheMilkAuxFXID, iFXI, iFXJ, iFXK, iExtraData );  	        


					}
				}
			}
		}
	}    

	public int GetInLove()
	{
		return dataWatcher.getWatchableObjectInt( m_iInLoveDataWatcherID );
	}

	public void SetInLove( int iInLove )
	{
		dataWatcher.updateObject( m_iInLoveDataWatcherID, Integer.valueOf( iInLove ) );
	}

	public int GetDirtyPeasant()
	{
		return dataWatcher.getWatchableObjectInt( m_iDirtyPeasantDataWatcherID );
	}

	public void SetDirtyPeasant( int iDirtyPeasant )
	{
		dataWatcher.updateObject( m_iDirtyPeasantDataWatcherID, Integer.valueOf( iDirtyPeasant ) );
	}

	public int GetCurrentTradeLevel()
	{
		return dataWatcher.getWatchableObjectInt( m_iTradeLevelDataWatcherID );
	}

	public void SetTradeLevel( int iTradeLevel )
	{
		dataWatcher.updateObject( m_iTradeLevelDataWatcherID, Integer.valueOf( iTradeLevel ) );
	}

	public int GetCurrentTradeXP()
	{
		return dataWatcher.getWatchableObjectInt( m_iTradeExperienceDataWatcherID );
	}

	public void SetTradeExperience( int iTradeExperience )
	{
		dataWatcher.updateObject( m_iTradeExperienceDataWatcherID, Integer.valueOf( iTradeExperience ) );
	}

	public int GetCurrentTradeMaxXP()
	{
		int iLevel = GetCurrentTradeLevel();

		switch ( iLevel )
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
		int iCurrentTradeLevel = GetCurrentTradeLevel();
		int iMaxTrades = iCurrentTradeLevel;

		switch ( getProfession() )
		{
		case 0: // peasant

			break;

		case 1: // librarian

			break;

		case 2: // priest

			if ( iCurrentTradeLevel >= 4 )
			{
				// extra trade to help compensate for the 2 mandatory ones at level 4

				iMaxTrades += 1;
			}

			break;

		case 3: // blacksmith

			break;

		case 4: // butcher

			break;

		default:

			break;
		}

		return iMaxTrades;
	}

	protected void ScheduleImmediateTradelistRefresh()
	{
		m_iUpdateTradesCountdown = 1;
	}

	//----------- Client Side Functionality -----------//

	@Override
	public String getTexture()
	{
		switch ( getProfession() )
		{
		case 0: // peasant

			if ( GetDirtyPeasant() > 0 )
			{
				return "/btwmodtex/fcDirtyPeasant.png";
			}

			break;

		case 1: // librarian

			if ( GetCurrentTradeLevel() >= 5 )
			{
				return "/btwmodtex/fcLibrarianSpecs.png";
			}

			break;

		case 2: // priest

			if ( GetCurrentTradeLevel() >= 5 )
			{
				return "/btwmodtex/fcPriestLvl.png";
			}

			break;

		case 3: // smith

			break;

		case 4: // butcher

			if ( GetCurrentTradeLevel() > 3 )
			{
				return "/btwmodtex/fcButcherLvl.png";
			}

			break;
		}

		return super.getTexture();
	}

	@Override
	public void handleHealthUpdate( byte bUpdateType )
	{
		super.handleHealthUpdate( bUpdateType );

		if ( bUpdateType == 14 )
		{
			// item collect sound on villager restock

			worldObj.playSound( posX, posY, posZ, "random.pop", 
					0.25F, ( ( rand.nextFloat() - rand.nextFloat() ) * 0.7F + 1F ) * 2F );
		}        
	}
}