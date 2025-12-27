package fr.sushi.sushis_bazaar.entity;

import fr.sushi.sushis_bazaar.registries.ModEntities;
import net.minecraft.Util;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownBrickEntity extends ThrowableProjectile
{
	private static final int   BASE_DMG = 6;
	private              float zRot;
	private              float zRot0;

	public ThrownBrickEntity(EntityType<? extends ThrownBrickEntity> entityType,
							 Level level)
	{
		super(entityType, level);
		this.zRot0 = 0;
		this.zRot = 0;
	}

	public ThrownBrickEntity(Level level, LivingEntity pEntity)
	{
		super(ModEntities.THROWN_BRICK_ENTITY.get(),
			  pEntity.getX(),
			  pEntity.getEyeY(),
			  pEntity.getZ(),
			  level);
		this.zRot0 = 0;
		this.zRot = (this.getRandom().nextFloat() - 0.5f) * 10.0f;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		if (!this.level().isClientSide())
		{
			int    dmg    = this.calculateImpactDamage();
			Entity entity = result.getEntity();
			DamageSource source =
					this.damageSources().thrown(this, this.getOwner());
			entity.hurtOrSimulate(source, dmg);
		}
		super.onHitEntity(result);
	}

	@Override
	protected void onHit(HitResult result)
	{
		super.onHit(result);
		if (!this.level().isClientSide())
		{
			ServerLevel level = (ServerLevel) this.level();
			level.broadcastEntityEvent(this, EntityEvent.DEATH);
			ParticleOptions particle =
					new BlockParticleOption(ParticleTypes.BLOCK,
											Blocks.BRICKS.defaultBlockState());
			level.sendParticles(particle,
								this.getX(),
								this.getY(),
								this.getZ(),
								20,
								0.2D,
								0.2D,
								0.2D,
								0.15f);
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

	@Override
	public void tick()
	{
		super.tick();
		if (this.isAlive())
		{
			this.zRot += 10f;
		}
	}

	@Override
	protected void updateRotation()
	{
		super.updateRotation();
		//		this.setXRot(this.xRotO);
		//		this.setZRot(lerpRotation(this.zRot0, Mth.atan2()));
	}

	private int calculateImpactDamage()
	{
		double velocity = this.getDeltaMovement().length();
		return Mth.ceil(velocity * BASE_DMG);
	}

	public float getZRot()
	{
		return this.zRot;
	}

	public void setZRot(float zRot)
	{
		if (!Float.isFinite(zRot))
		{
			Util.logAndPauseIfInIde(
					"Invalid entity rotation: " + zRot + ", discarding.");
		}
		else
		{
			this.zRot = Math.clamp(zRot % 360.0F, -90.0F, 90.0F);
		}
	}
}
