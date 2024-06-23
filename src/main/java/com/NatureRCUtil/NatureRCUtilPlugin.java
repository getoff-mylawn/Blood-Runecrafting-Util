package com.NatureRCUtil;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.ClientTick;
import net.runelite.api.*;
import net.runelite.client.util.Text;
import org.apache.commons.lang3.ArrayUtils;
import net.runelite.api.coords.WorldPoint;

@Slf4j
@PluginDescriptor(
	name = "Nature Runecraft Utilities"
)
public class NatureRCUtilPlugin extends Plugin
{
	int[] capes = {
			19476, //diary cape
			13069 // diary cape (t)
	};
	@Inject
	private Client client;

	@Inject
	private NatureRCUtilConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("NatureRCutil started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("NatureRCUtil stopped!");
	}

	@Subscribe
	public void onClientTick(ClientTick event) {
		WorldPoint playerLoc = client.getLocalPlayer().getWorldLocation();
		if (9547 == playerLoc.getRegionID()) {
			MenuEntry[] menuEntries = client.getMenuEntries();
			if (ArrayUtils.contains(capes, menuEntries[menuEntries.length - 1].getItemId())) {
				int emptyIdx = -1;
				int topIdx = menuEntries.length - 1;
				for (int i = 0; i < topIdx; i++) {

					if (Text.removeTags(menuEntries[i].getOption()).equals("Jarr")) {
						emptyIdx = i;
						break;
					}
					//if (Text.removeTags(menuEntries[i].getOption()).equals("Kaleb Paramaya")) {
					//	log.info("Kaleb");
					//	checkidx = i;
					//}
				}
				if (emptyIdx == -1) {
					return;
				}

				MenuEntry entry1 = menuEntries[emptyIdx];
				MenuEntry entry2 = menuEntries[topIdx];
				menuEntries[emptyIdx] = entry2;
				menuEntries[topIdx] = entry1;

				client.setMenuEntries(menuEntries);
			}
		}
	}
	@Provides
	NatureRCUtilConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NatureRCUtilConfig.class);
	}
}
