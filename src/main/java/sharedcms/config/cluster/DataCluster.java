package sharedcms.config.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.imageio.stream.FileImageInputStream;

import sharedcms.config.clusters.ClusterBoolean;
import sharedcms.config.clusters.ClusterDouble;
import sharedcms.config.clusters.ClusterInteger;
import sharedcms.config.clusters.ClusterString;
import sharedcms.json.JSONObject;
import sharedcms.util.GList;
import sharedcms.util.GMap;

public class DataCluster
{
	private JSONObject data;

	public DataCluster()
	{
		data = new JSONObject();
	}

	public void put(String key, ICluster cluster, Object object)
	{
		cluster.write(data, key, object);
	}

	public Object get(String key, ICluster cluster)
	{
		return cluster.read(data, key);
	}

	public boolean contains(String key)
	{
		return data.has(key);
	}
	
	public int getInt(String key)
	{
		return (Integer) get(key, new ClusterInteger());
	}

	public void put(String key, int i)
	{
		put(key, new ClusterInteger(), i);
	}

	public double getDouble(String key)
	{
		return (Double) get(key, new ClusterDouble());
	}
	
	public void put(String key, double i)
	{
		put(key, new ClusterDouble(), i);
	}
	
	public boolean getBoolean(String key)
	{
		return (Boolean) get(key, new ClusterBoolean());
	}

	public void put(String key, boolean i)
	{
		put(key, new ClusterBoolean(), i);
	}
	
	public String getString(String key)
	{
		return (String) get(key, new ClusterString());
	}
	
	public void put(String key, String i)
	{
		put(key, new ClusterString(), i);
	}
	
	public JSONObject toJSON()
	{
		return data;
	}
	
	public void read(File f)
	{
		try
		{
			System.out.println("Read cms config");
			BufferedReader bu = new BufferedReader(new FileReader(f));
			String content = "";
			String line = "";
			
			while((line = bu.readLine()) != null)
			{
				content += line;
			}
			
			data = new JSONObject(content);
			bu.close();
		}
		
		catch(Exception e)
		{
			
		}
	}
	
	public void write(File f)
	{
		try
		{
			System.out.println("Saving cms config");
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter pw = new PrintWriter(fos);
			pw.println(toJSON().toString(4));
			pw.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
