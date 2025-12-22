package fr.sushi.sushis_bazar.registries;

import fr.sushi.sushis_bazar.SushisBazaar;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs
{
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
									SushisBazaar.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab>
			SUSHIS_BAZAAR_TAB = CREATIVE_MODE_TABS.register("sushis_bazaar",
													  () -> CreativeModeTab
															  .builder()
															  .title(Component.translatable(
																	  "itemGroup.sushis_bazaar"))
															  .withTabsBefore(
																	  CreativeModeTabs.COMBAT)
															  .icon(() -> ModItems.SHAKY_SHAKE
																	  .get()
																	  .getDefaultInstance())
															  .displayItems((parameters, output) ->
																			{
																				output.accept(
																						ModItems.SHAKY_SHAKE.get());
																			})
															  .build());
}
