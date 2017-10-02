package sharedcms.config.clusters;

import sharedcms.config.cluster.ICluster;

public abstract class ClusterBase<T> implements ICluster<T>
{
	public Class<? extends T> type;
	
	public ClusterBase(Class<? extends T> type)
	{
		this.type = type;
	}
	
	@Override
	public Class<? extends T> getType()
	{
		return type;
	}
}
