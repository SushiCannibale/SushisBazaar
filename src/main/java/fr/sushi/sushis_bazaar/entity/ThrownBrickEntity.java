package fr.sushi.sushis_bazaar.entity;

import fr.sushi.sushis_bazaar.registries.ModEntities;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
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
			  pEntity.getEyeY() - 1.0f,
			  pEntity.getZ(),
			  level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
	}

	@Override
	protected void onHitBlock(BlockHitResult result)
	{
		super.onHitBlock(result);
		/* Spawn mud */
	}

	@Override
	protected void onHit(HitResult result)
	{
		super.onHit(result);
		/* Maybe it can cause portuguese diarrhea too idk... */
	}
}
