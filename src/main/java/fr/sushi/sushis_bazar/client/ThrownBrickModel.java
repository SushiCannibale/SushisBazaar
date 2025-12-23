package fr.sushi.sushis_bazar.client;

import fr.sushi.sushis_bazar.SushisBazaar;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrownBrickModel extends EntityModel<ThrownBrickRenderState>
{
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(
					SushisBazaar.MODID,
					"thrown_brick_model"), "main");
	private final       ModelPart          bb_main;

	public ThrownBrickModel(ModelPart root)
	{
		super(root);
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("bb_main",
										 CubeListBuilder
												 .create()
												 .texOffs(0, 0)
												 .addBox(-3.0F,
														 -4.0F,
														 -5.0F,
														 6.0F,
														 4.0F,
														 10.0F,
														 new CubeDeformation(
																 0.0F)),
										 PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void setupAnim(ThrownBrickRenderState renderState)
	{
		super.setupAnim(renderState);
		this.bb_main.xRot = renderState.xRot;
		this.bb_main.yRot = renderState.yRot;
		this.bb_main.zRot = renderState.zRot;
	}
}
