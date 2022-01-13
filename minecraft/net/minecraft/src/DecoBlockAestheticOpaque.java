package net.minecraft.src;

import java.util.List;

public class DecoBlockAestheticOpaque extends FCBlockAestheticOpaque {
	public DecoBlockAestheticOpaque(int id) {
		super(id);
	}

    public StepSound GetStepSound(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        case 1:
        	return FCBetterThanWolves.fcStepSoundSquish;
        case 15:
        	return DecoManager.getNewSoundsInstalled() ? DecoDefs.stepSoundBone : soundGravelFootstep;
        default:
        	return this.stepSound;
        }
    }
    
    //------ Client Only ------//
    
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
        list.add(new ItemStack(blockID, 1, m_iSubtypeHellfire));
        list.add(new ItemStack(blockID, 1, m_iSubtypePadding));
        list.add(new ItemStack(blockID, 1, m_iSubtypeSoap));
        list.add(new ItemStack(blockID, 1, m_iSubtypeFlint));
        list.add(new ItemStack(blockID, 1, m_iSubtypeWhiteStone));
        list.add(new ItemStack(blockID, 1, m_iSubtypeChoppingBlockDirty));
        list.add(new ItemStack(blockID, 1, m_iSubtypeChoppingBlockClean));
        list.add(new ItemStack(blockID, 1, m_iSubtypeEnderBlock));
        list.add(new ItemStack(blockID, 1, m_iSubtypeBone));
    }
}