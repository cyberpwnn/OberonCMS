package sharedcms;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class L
{
	protected static Logger k = null;
	
	private static void log(Object o, Level l, String message)
	{
		if(k == null)
		{
			if(o == null)
			{
				k.log(l, message);
				return;
			}
			
			System.out.println(l.toString() + " [" + o.getClass().getSimpleName() + "]: " + message);
		}
		
		else
		{
			if(o == null)
			{
				k.log(l, message);
				return;
			}
			
			k.log(l, "[" + o.getClass().getSimpleName() + "]: " + message);
		}
	}
	
	public static void l(Object o, String message)
	{
		log(o, Level.INFO, message);
	}
	
	public static void l(String message)
	{
		l(null, message);
	}
	
	public static void w(Object o, String message)
	{
		log(o, Level.WARN, message);
	}
	
	public static void w(String message)
	{
		w(null, message);
	}
	
	public static void f(Object o, String message)
	{
		log(o, Level.FATAL, message);
	}
	
	public static void f(String message)
	{
		f(null, message);
	}
	
	public static void d(Object o, String message)
	{
		log(o, Level.DEBUG, message);
	}
	
	public static void d(String message)
	{
		d(null, message);
	}
}
