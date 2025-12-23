package fr.sushi.sushis_bazaar.registries;

import fr.sushi.sushis_bazaar.SushisBazaar;
import fr.sushi.sushis_bazaar.entity.ThrownBrickEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities
{
	public static final DeferredRegister.Entities ENTITY_REGISTRY =
			DeferredRegister.createEntities(SushisBazaar.MODID);

	public static final Supplier<EntityType<ThrownBrickEntity>>
			THROWN_BRICK_ENTITY = ENTITY_REGISTRY.registerEntityType(
			"thrown_brick_entity",
			ThrownBrickEntity::new,
			MobCategory.MISC);
}
