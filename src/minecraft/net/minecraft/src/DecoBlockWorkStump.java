package net.minecraft.src;

public class DecoBlockWorkStump extends FCBlockWorkStump {
	protected DecoBlockWorkStump(int id) {
		super(id);
	}

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
    	if (meta == 4)
    		return DecoDefs.cherryLog.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4);
    	else
    		return Block.wood.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4);
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int meta = var2.getBlockMetadata(var3, var4, var5);
        int var8 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(0);
        var2.setBlockAndMetadataWithNotify(var3, var4, var5, DecoUtilsBlock.getDamagedLogFromMetadata(meta & 3), var8);

        if (!var2.isRemote)
        {
        	if (meta == 4) meta++;
            FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, meta), var6);
        }

        return true;
    }

    //CLIENT ONLY
    public static final String[] m_sideTextureNames = new String[] {"fcBlockWorkStumpOak", "fcBlockWorkStumpSpruce", "fcBlockWorkStumpBirch", "fcBlockWorkStumpJungle", "ginger_workStumpCherry"};
    private Icon[] m_iconSideArray;
    private Icon m_iconTop;
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 > 1 ? this.m_iconSideArray[var2] : (var1 == 1 ? this.m_iconTop : super.getIcon(var1, var2));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockTrunkTop");
        this.m_iconTop = var1.registerIcon("workbench_top");
        this.m_iconSideArray = new Icon[m_sideTextureNames.length];

        for (int var2 = 0; var2 < this.m_iconSideArray.length; ++var2)
        {
            this.m_iconSideArray[var2] = var1.registerIcon(m_sideTextureNames[var2]);
        }
    }
}