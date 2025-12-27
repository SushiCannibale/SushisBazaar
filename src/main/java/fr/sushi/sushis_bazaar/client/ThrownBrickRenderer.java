package fr.sushi.sushis_bazaar.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.sushi.sushis_bazaar.SushisBazaar;
import fr.sushi.sushis_bazaar.entity.ThrownBrickEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrownBrickRenderer
		extends EntityRenderer<ThrownBrickEntity, ThrownBrickRenderState>
{
	public final         ThrownBrickModel model;
	private static final ResourceLocation TEXTURE =
			ResourceLocation.fromNamespaceAndPath(SushisBazaar.MODID,
												  "textures/entity/thrown_brick.png");

	public ThrownBrickRenderer(EntityRendererProvider.Context context)
	{
		super(context);
		this.model =
				new ThrownBrickModel(context.bakeLayer(ThrownBrickModel.LAYER_LOCATION));
	}

	@Override
	public void render(ThrownBrickRenderState renderState,
					   PoseStack poseStack,
					   MultiBufferSource bufferSource,
					   int packedLight)
	{
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(renderState.yRot));
		poseStack.mulPose(Axis.ZP.rotationDegrees(renderState.xRot));
		poseStack.mulPose(Axis.XP.rotationDegrees(renderState.zRot));
		this.model.setupAnim(renderState);
		VertexConsumer buffer =
				bufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
		this.model.renderToBuffer(poseStack, buffer, packedLight, 0, -1);

		poseStack.popPose();
		super.render(renderState, poseStack, bufferSource, packedLight);
	}

	@Override
	public void extractRenderState(ThrownBrickEntity pEntity,
								   ThrownBrickRenderState pState,
								   float pPartialTick)
	{
		super.extractRenderState(pEntity, pState, pPartialTick);
		pState.xRot = pEntity.getXRot();
		pState.yRot = pEntity.getYRot();
		pState.zRot = pEntity.getZRot();
	}

	@Override
	public ThrownBrickRenderState createRenderState()
	{
		return new ThrownBrickRenderState();
	}
}
