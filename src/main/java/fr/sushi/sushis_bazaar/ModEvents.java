package fr.sushi.sushis_bazaar;

import fr.sushi.sushis_bazaar.client.ThrownBrickModel;
import fr.sushi.sushis_bazaar.client.ThrownBrickRenderer;
import fr.sushi.sushis_bazaar.registries.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SushisBazaar.MODID)
public class ModEvents
{
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers(
			EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(ModEntities.THROWN_BRICK_ENTITY.get(),
				ThrownBrickRenderer::new);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void registerLayerDefinitions(
			EntityRenderersEvent.RegisterLayerDefinitions event)
	{
		event.registerLayerDefinition(ThrownBrickModel.LAYER_LOCATION,
				ThrownBrickModel::createBodyLayer);
	}
}
