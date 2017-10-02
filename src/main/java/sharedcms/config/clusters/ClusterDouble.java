package sharedcms.config.clusters;

import sharedcms.json.JSONObject;

public class ClusterDouble extends ClusterBase<Double>
{
	public ClusterDouble()
	{
		super(Double.class);
	}

	@Override
	public Double read(JSONObject json, String key)
	{
		return json.getDouble(key);
	}

	@Override
	public void write(JSONObject json, String key, Double value)
	{
		json.put(key, value);
	}
}
