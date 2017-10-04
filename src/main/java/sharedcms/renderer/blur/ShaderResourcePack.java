/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.data.IMetadataSection
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.client.resources.data.PackMetadataSection
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 */
package sharedcms.renderer.blur;

import com.google.common.collect.ImmutableSet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import sharedcms.controller.client.BackgroundBlurController;

public class ShaderResourcePack implements IResourcePack, IResourceManagerReloadListener
{
	private final Map<ResourceLocation, String> loadedData = new HashMap<ResourceLocation, String>();

	protected boolean validPath(ResourceLocation location)
	{
		return location.getResourceDomain().equals("minecraft") && location.getResourcePath().startsWith("shaders/");
	}

	public InputStream getInputStream(final ResourceLocation location) throws IOException
	{
		if(this.validPath(location))
		{
			String s = this.loadedData.computeIfAbsent(location, new Function<ResourceLocation, String>()
			{
				@Override
				public String apply(ResourceLocation loc)
				{
					StringBuilder data;
					InputStream in = BackgroundBlurController.class.getResourceAsStream("/" + location.getResourcePath());
					data = new StringBuilder();
					Scanner scan = new Scanner(in);
					try
					{
						while(scan.hasNextLine())
						{
							data.append(scan.nextLine().replaceAll("@radius@", Integer.toString(BackgroundBlurController.instance.radius))).append('\n');
						}
					}
					finally
					{
						scan.close();
					}
					return data.toString();
				}
			});
			return new ByteArrayInputStream(s.getBytes());
		}
		throw new FileNotFoundException(location.toString());
	}

	public boolean resourceExists(ResourceLocation location)
	{
		return this.validPath(location) && BackgroundBlurController.class.getResource("/" + location.getResourcePath()) != null;
	}

	public Set<String> getResourceDomains()
	{
		return ImmutableSet.of("minecraft");
	}

	public IMetadataSection getPackMetadata(IMetadataSerializer metadataSerializer, String metadataSectionName) throws IOException
	{
		return new PackMetadataSection((IChatComponent) new ChatComponentText("Blur's default shaders"), 3);
	}

	public BufferedImage getPackImage() throws IOException
	{
		throw new FileNotFoundException("pack.png");
	}

	public String getPackName()
	{
		return "Blur dummy resource pack";
	}

	public void onResourceManagerReload(IResourceManager resourceManager)
	{
		this.loadedData.clear();
	}
}
