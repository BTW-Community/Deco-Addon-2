package deco.block.model;

import btw.block.model.BlockModel;

public class PergolaBlockModel extends BlockModel {
	private static final float BEAM_WIDTH = .25F;
	private static final float BEAM_HEIGHT = .25F;
	private static final float BEAM_LEVEL_HEIGHT = .1875F;
	private static final float BEAM_SPACING = .375F;
	private static final float BEAM_SPACING_FROM_EDGE = (1.0F - 2 * BEAM_WIDTH - BEAM_SPACING)/2;
	public static final float BLOCK_HEIGHT = .4375F;
	
	@Override
	public void initModel() {
		this.addBox(0, 0, (float) BEAM_SPACING_FROM_EDGE, 1.0F, (float) BEAM_HEIGHT, (float) BEAM_SPACING_FROM_EDGE + BEAM_WIDTH);
		this.addBox(0, 0, 1 - (BEAM_SPACING_FROM_EDGE + BEAM_WIDTH), 1.0, BEAM_HEIGHT, 1 - BEAM_SPACING_FROM_EDGE);
		this.addBox(BEAM_SPACING_FROM_EDGE, BEAM_LEVEL_HEIGHT, 0, BEAM_SPACING_FROM_EDGE + BEAM_WIDTH, BEAM_HEIGHT + BEAM_LEVEL_HEIGHT, 1.0);
		this.addBox(1 - (BEAM_SPACING_FROM_EDGE + BEAM_WIDTH), BEAM_LEVEL_HEIGHT, 0, 1 - BEAM_SPACING_FROM_EDGE, BEAM_HEIGHT + BEAM_LEVEL_HEIGHT, 1.0);
	}
}
