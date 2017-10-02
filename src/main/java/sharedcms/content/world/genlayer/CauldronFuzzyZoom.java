package sharedcms.content.world.genlayer;

public class CauldronFuzzyZoom extends CauldronZoom
{
	private static final String __OBFID = "CL_00000556";

	public CauldronFuzzyZoom(long seed, Cauldron parent)
	{
		super(seed, parent);
	}

	protected int selectModeOrRandom(int par1, int par2, int par3, int par4)
	{
		return this.selectRandom(new int[] { par1, par2, par3, par4 });
	}
}