package sharedcms.config.clusters;

import sharedcms.json.JSONObject;

public class ClusterInteger extends ClusterBase<Integer>
{
	public ClusterInteger()
	{
		super(Integer.class);
	}

	@Override
	public Integer read(JSONObject json, String key)
	{
		return json.getInt(key);
	}

	@Override
	public void write(JSONObject json, String key, Integer value)
	{
		json.put(key, value);
	}
}
