package fr.sushi.sushis_bazar;

import com.mojang.logging.LogUtils;
import fr.sushi.sushis_bazar.registries.ModItems;
import fr.sushi.sushis_bazar.registries.ModTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(SushisBazaar.MODID)
public class SushisBazaar
{
	public static final  String MODID  = "sushis_bazaar";
	private static final Logger LOGGER = LogUtils.getLogger();

	public SushisBazaar(IEventBus modEventBus, ModContainer modContainer)
	{
		ModItems.ITEM_REGISTRY.register(modEventBus);
		ModTabs.CREATIVE_MODE_TABS.register(modEventBus);

		modEventBus.register(ModEvents.class);
//		NeoForge.EVENT_BUS.register(this);

		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}
}
