package fr.sushi.sushis_bazaar;

import fr.sushi.sushis_bazaar.entity.ThrownBrickEntity;
import fr.sushi.sushis_bazaar.registries.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class ThrowableBrickItem extends ProjectileWeaponItem
{
	public ThrowableBrickItem(Properties properties)
	{
		super(properties);
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles()
	{
		return (itemstack) -> itemstack.is(ModItems.THROWABLE_BRICK.get());
	}

	@Override
	public int getDefaultProjectileRange()
	{
		return 15;
	}

	/**
	 * As of 1.21.5, this is only used locally by super.shoot() which we
	 * short-circuit because mojang stated that projectiles == arrows... Wtf
	 * mojang
	 */
	@Override
	protected void shootProjectile(LivingEntity shooter,
								   Projectile projectile,
								   int index,
								   float velocity,
								   float inaccuracy,
								   float angle,
								   @Nullable LivingEntity target)
	{
	}

	@Override
	public int getUseDuration(ItemStack itemstack, LivingEntity shooter)
	{
		return 72000;
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack)
	{
		return ItemUseAnimation.SPEAR;
	}

	@Override
	public InteractionResult use(Level level,
								 Player player,
								 InteractionHand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);
		boolean   flag      = !player.getProjectile(itemstack).isEmpty();

		/* Replace this with its own event */
		InteractionResult ret =
				net.neoforged.neoforge.event.EventHooks.onArrowNock(itemstack,
																	level,
																	player,
																	hand,
																	flag);
		if (ret != null)
		{
			return ret;
		}

		if (!player.hasInfiniteMaterials() && !flag)
		{
			return InteractionResult.FAIL;
		}
		else
		{
			player.startUsingItem(hand);
			return InteractionResult.CONSUME;
		}
	}

	public static float getPowerForTime(int charge)
	{
		float f = charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F)
		{
			f = 1.0F;
		}

		return f;
	}

	@Override
	public boolean releaseUsing(ItemStack pStack,
								Level pLevel,
								LivingEntity pEntity,
								int pTime)
	{
		int i = this.getUseDuration(pStack, pEntity) - pTime;
		if (i < 0)
		{
			return false;
		}
		float f = getPowerForTime(i);
		if (f < 0.1)
		{
			return false;
		}
		else
		{
			if (pLevel instanceof ServerLevel serverlevel && !pStack.isEmpty())
			{
				ThrownBrickEntity projectile =
						new ThrownBrickEntity(serverlevel, pEntity);
				serverlevel.addFreshEntity(projectile);
				projectile.shootFromRotation(pEntity,
											 pEntity.getXRot(),
											 pEntity.getYRot(),
											 0.0f,
											 f,
											 0.1f);
				pStack.consume(1, pEntity);
			}

			pLevel.playSound(null,
							 pEntity.getX(),
							 pEntity.getY(),
							 pEntity.getZ(),
							 SoundEvents.MUD_BRICKS_PLACE,
							 SoundSource.PLAYERS,
							 1.0f,
							 0.0f);
			return true;
		}
	}
}
