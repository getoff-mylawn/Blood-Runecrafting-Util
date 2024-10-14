package com.BloodRCUtil;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BloodRCUtilPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BloodRCUtilPlugin.class);
		RuneLite.main(args);
	}
}
