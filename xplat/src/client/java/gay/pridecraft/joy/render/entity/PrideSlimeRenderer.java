package gay.pridecraft.joy.render.entity;

import gay.pridecraft.joy.JoyUtil;
import gay.pridecraft.joy.entity.PrideSlimeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class PrideSlimeRenderer extends MobEntityRenderer<PrideSlimeEntity, SlimeEntityModel<PrideSlimeEntity>> {

    private static final Identifier TEXTURE = JoyUtil.id("textures/entity/slime/slime_gay.png");

    public PrideSlimeRenderer(EntityRendererFactory.Context context) {
        super(context, new SlimeEntityModel<>(context.getPart(EntityModelLayers.SLIME)), 0.25F);
        this.addFeature(new SlimeOverlayFeatureRenderer<>(this, context.getModelLoader()));

    }

    @Override
    public void render(PrideSlimeEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25F * (float) entity.getSize();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    protected void scale(PrideSlimeEntity entity, MatrixStack matrixStack, float f) {
        float g = 0.999F;
        matrixStack.scale(0.999F, 0.999F, 0.999F);
        matrixStack.translate(0.0F, 0.001F, 0.0F);
        float h = (float) entity.getSize();
        float i = MathHelper.lerp(f, entity.lastStretch, entity.stretch) / (h * 0.5F + 1.0F);
        float j = 1.0F / (i + 1.0F);
        matrixStack.scale(j * h, 1.0F / j * h, j * h);
    }

    @Override
    public Identifier getTexture(PrideSlimeEntity entity) {
        return entity.getVariant().getTexture();
    }

}
