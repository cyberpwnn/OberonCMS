package sharedcms.config.clusters;

import sharedcms.json.JSONObject;

public class ClusterBoolean extends ClusterBase<Boolean>
{
	public ClusterBoolean()
	{
		super(Boolean.class);
	}

	@Override
	public Boolean read(JSONObject json, String key)
	{
		return json.getBoolean(key);
	}

	@Override
	public void write(JSONObject json, String key, Boolean value)
	{
		json.put(key, value);
	}
}
