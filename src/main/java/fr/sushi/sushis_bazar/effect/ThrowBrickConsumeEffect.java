package fr.sushi.sushis_bazar.effect;

import fr.sushi.sushis_bazar.entity.ThrownBrickEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

public record ThrowBrickConsumeEffect() implements ConsumeEffect
{
	@Override
	public Type<? extends ConsumeEffect> getType()
	{
		return Type.APPLY_EFFECTS;
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity entity)
	{
		ThrownBrickEntity brickEntity = new ThrownBrickEntity(level);
		/* maybe brick velocity could depend on diarrhea strength */
		brickEntity.shootFromRotation(entity, entity.getXRot(), entity.getYRot(), 0.0f, 6.0f, 1.0f);
		level.addFreshEntity(brickEntity);
		return true;
	}
}
