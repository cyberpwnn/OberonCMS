package sharedcms.base;

import net.minecraft.block.Block;

public class BSM
{
	private String jump;
	private String land;
	private String walk;
	private String wander;
	private String run;

	public BSM(String jump, String land, String walk, String wander, String run)
	{
		super();
		this.jump = jump;
		this.land = land;
		this.walk = walk;
		this.wander = wander;
		this.run = run;
	}

	public String getJump()
	{
		return jump;
	}

	public String getLand()
	{
		return land;
	}

	public String getWalk()
	{
		return walk;
	}

	public String getWander()
	{
		return wander;
	}

	public String getRun()
	{
		return run;
	}
}
