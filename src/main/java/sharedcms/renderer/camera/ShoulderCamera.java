/*
 * Decompiled with CFR 0_122.
 */
package sharedcms.renderer.camera;

import sharedcms.renderer.camera.ShoulderSettings;

public final class ShoulderCamera
{
	public static float SHOULDER_ROTATION = -22.0f;
	public static float SHOULDER_ZOOM_MOD = 0.7f;

	private ShoulderCamera()
	{
	}

	public static void adjustCameraLeft()
	{
		if(ShoulderSettings.IS_ROTATION_UNLIMITED || SHOULDER_ZOOM_MOD < ShoulderSettings.ROTATION_MAXIMUM)
		{
			SHOULDER_ROTATION += 0.5f;
		}
	}

	public static void adjustCameraRight()
	{
		if(ShoulderSettings.IS_ROTATION_UNLIMITED || SHOULDER_ZOOM_MOD > ShoulderSettings.ROTATION_MINIMUM)
		{
			SHOULDER_ROTATION -= 0.5f;
		}
	}

	public static void adjustCameraIn()
	{
		if(ShoulderSettings.IS_ZOOM_UNLIMITED || SHOULDER_ZOOM_MOD < ShoulderSettings.ZOOM_MAXIMUM)
		{
			SHOULDER_ZOOM_MOD += 0.01f;
		}
	}

	public static void adjustCameraOut()
	{
		if(ShoulderSettings.IS_ZOOM_UNLIMITED || SHOULDER_ZOOM_MOD > ShoulderSettings.ZOOM_MINIMUM)
		{
			SHOULDER_ZOOM_MOD -= 0.01f;
		}
	}
}
