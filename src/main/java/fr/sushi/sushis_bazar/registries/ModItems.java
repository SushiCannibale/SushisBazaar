package fr.sushi.sushis_bazar.registries;

import fr.sushi.sushis_bazar.SushisBazaar;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems
{
	public static final DeferredRegister.Items ITEM_REGISTRY =
			DeferredRegister.createItems(SushisBazaar.MODID);
	public static final Supplier<Item>         SHAKY_SHAKE   =
			ITEM_REGISTRY.registerItem("shaky_shake",
									   (props) -> new Item(props.food(new FoodProperties.Builder()
																			  .alwaysEdible()
																			  .nutrition(
																					  2)
																			  .saturationModifier(
																					  2.5f)
																			  .build())));
}
