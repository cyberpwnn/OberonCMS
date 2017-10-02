package sharedcms.config.cluster;

import sharedcms.json.JSONObject;

public interface ICluster<T>
{
	public Class<? extends T> getType();
	
	public T read(JSONObject json, String key);
	
	public void write(JSONObject json, String key, T value);
}
