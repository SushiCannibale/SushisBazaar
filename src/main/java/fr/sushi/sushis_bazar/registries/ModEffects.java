package fr.sushi.sushis_bazar.registries;

import fr.sushi.sushis_bazar.SushisBazaar;
import fr.sushi.sushis_bazar.effect.PortugueseDiarrhea;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects
{
	public static final DeferredRegister<MobEffect> MOB_EFFECTS =
			DeferredRegister.create(Registries.MOB_EFFECT, SushisBazaar.MODID);

	public static final Holder<MobEffect> PORTUGUESE_DIARRHEA =
			MOB_EFFECTS.register("portuguese_diarrhea",
								 () -> new PortugueseDiarrhea(MobEffectCategory.NEUTRAL,
															  16551892).addAttributeModifier(
										 Attributes.MOVEMENT_SPEED,
										 ResourceLocation.fromNamespaceAndPath(
												 SushisBazaar.MODID,
												 "effect.portuguese_diarrhea"),
										 0.15D,
										 AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
}
