package fr.sushi.sushis_bazaar.client;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrownBrickRenderState extends EntityRenderState
{
	public float yRot;
	public float xRot;
	public float zRot;
}
