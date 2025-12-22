package fr.sushi.sushis_bazar.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PortugueseDiarrhea extends MobEffect
{
	public static final int BRICK_SPAWN_INTERVAL = 80;

	public PortugueseDiarrhea(MobEffectCategory category, int color)
	{
		super(category, color);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level,
								   LivingEntity entity,
								   int amplifier)
	{
		ItemEntity brickEntity = new ItemEntity(EntityType.ITEM, level);
		brickEntity.setItem(new ItemStack(Items.BRICK));
		brickEntity.setPos(entity.position());
		level.addFreshEntity(brickEntity);
		level.playSound(entity,
						entity.blockPosition(),
						SoundEvents.MUD_BRICKS_PLACE,
						SoundSource.PLAYERS);
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
	{
		int i = BRICK_SPAWN_INTERVAL >> amplifier;
		return i > 0 ? duration % i == 0 : true;
	}
}
