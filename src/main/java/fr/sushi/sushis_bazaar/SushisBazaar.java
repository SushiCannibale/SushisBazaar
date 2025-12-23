package fr.sushi.sushis_bazaar;

import com.mojang.logging.LogUtils;
import fr.sushi.sushis_bazaar.registries.ModEffects;
import fr.sushi.sushis_bazaar.registries.ModEntities;
import fr.sushi.sushis_bazaar.registries.ModItems;
import fr.sushi.sushis_bazaar.registries.ModTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
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
		ModEffects.MOB_EFFECTS.register(modEventBus);
		ModEntities.ENTITY_REGISTRY.register(modEventBus);

		modEventBus.register(ModEvents.class);
		//		NeoForge.EVENT_BUS.register(this);

		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}
}
