package sharedcms.config.clusters;

import sharedcms.json.JSONObject;

public class ClusterString extends ClusterBase<String>
{
	public ClusterString()
	{
		super(String.class);
	}

	@Override
	public String read(JSONObject json, String key)
	{
		return json.getString(key);
	}

	@Override
	public void write(JSONObject json, String key, String value)
	{
		json.put(key, value);
	}
}
