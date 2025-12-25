package fr.sushi.sushis_bazaar.entity;

import fr.sushi.sushis_bazaar.registries.ModEntities;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownBrickEntity extends ThrowableProjectile
{
	public ThrownBrickEntity(EntityType<? extends ThrownBrickEntity> entityType,
							 Level level)
	{
		super(entityType, level);
	}

	public ThrownBrickEntity(Level level, LivingEntity pEntity)
	{
		super(ModEntities.THROWN_BRICK_ENTITY.get(),
			  pEntity.getX(),
			  pEntity.getEyeY(),
			  pEntity.getZ(),
			  level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
	}

	private float calculateImpactDamage()
	{
		double velocity = this.getDeltaMovement().length();
		return (float) velocity;
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		if (!this.level().isClientSide())
		{
			double force  = this.calculateImpactDamage();
			Entity entity = result.getEntity();
			entity.hurt(this.damageSources().thrown(this, this.getOwner()),
						(float) force);
		}
		super.onHitEntity(result);
	}

	@Override
	protected void onHit(HitResult result)
	{
		super.onHit(result);
		if (!this.level().isClientSide())
		{
			this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
			this.discard();
		}
		this
				.level()
				.playSound(null,
						   this.getX(),
						   this.getY(),
						   this.getZ(),
						   SoundEvents.NETHER_BRICKS_BREAK,
						   SoundSource.AMBIENT,
						   1.0f,
						   0.0f);
	}
}
