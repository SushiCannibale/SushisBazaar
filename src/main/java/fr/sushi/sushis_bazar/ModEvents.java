package fr.sushi.sushis_bazar;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = SushisBazaar.MODID)
public class ModEvents
{
	@SubscribeEvent
	public static void registerCreativeTabs(BuildCreativeModeTabContentsEvent event) {

	}
}
