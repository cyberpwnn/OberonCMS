package sharedcms.asm.util;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sharedcms.util.GList;
import sharedcms.util.M;

public class Clicker extends Thread
{
	private static GList<Runnable> r = new GList<Runnable>();
	public static Clicker i;

	public Clicker()
	{
		super("SNDClicker");
		i = this;
		i.start();
	}

	public static void finish()
	{
		i.interrupt();
	}

	public void run()
	{
		while(!interrupted())
		{
			while(!r.isEmpty())
			{
				try
				{
					Thread.sleep(1);
				}
				
				catch(InterruptedException e)
				{

				}
				
				r.pop().run();

				if(interrupted())
				{
					r.clear();
					System.gc();
					break;
				}
			}
		}
	}

	public static void clip()
	{
		r.add(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					URL url = Clicker.class.getClassLoader().getResource("click.wav");
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float range = gainControl.getMaximum() - gainControl.getMinimum();
					float gain = (range * 0.3f) + gainControl.getMinimum();
					gainControl.setValue(gain);
				}

				catch(UnsupportedAudioFileException e)
				{

				}

				catch(IOException e)
				{

				}

				catch(LineUnavailableException e)
				{

				}
			}
		});
	}
}
