package fr.sushi.sushis_bazaar.registries;

import fr.sushi.sushis_bazaar.SushisBazaar;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags
{
	public static final TagKey<Block> BREAKS_ON_BRICK_IMPACT =
			TagKey.create(Registries.BLOCK,
					ResourceLocation.fromNamespaceAndPath(SushisBazaar.MODID,
							"breaks_on_brick_impact"));
}
