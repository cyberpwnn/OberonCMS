package sharedcms.content.world.decorator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.content.structure.artifact.Artifact;
import sharedcms.content.structure.artifact.IArtifact;
import sharedcms.content.structure.model.IModel;
import sharedcms.util.Location;
import sharedcms.util.M;

public class DecorateArtifact extends SpecializedDecorator
{
	private IArtifact artifact;
	private Block[] on;
	private double chance;

	public DecorateArtifact(int itr, double chance, IModel model, Block... on)
	{
		this(itr, chance, new Artifact(model), on);
	}
	
	public DecorateArtifact(int itr, double chance, IArtifact artifact, Block... on)
	{
		super(itr);

		this.on = on;
		this.artifact = artifact;
		this.chance = chance;
	}

	@Override
	public boolean generate(World w, Random r, int x, int y, int z)
	{
		if(M.r(chance))
		{
			int h = heightOF(w, x, z, on);

			if(h > 0)
			{
				Location at = new Location(x, h, z);
				artifact.build(w, at);
			}
		}

		return true;
	}
}
