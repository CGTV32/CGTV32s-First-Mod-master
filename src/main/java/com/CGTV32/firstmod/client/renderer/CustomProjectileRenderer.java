package com.CGTV32.firstmod.client.renderer;

import com.CGTV32.firstmod.FirstMod;
import com.CGTV32.firstmod.entity.CustomProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import net.minecraft.util.Mth;

public class CustomProjectileRenderer extends EntityRenderer<CustomProjectile> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/entity/custom_projectile.png");

    public CustomProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CustomProjectile entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();
        matrixStack.mulPose(rotation(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F, 0, 0));
        matrixStack.mulPose(rotation(0, 0, Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));

        matrixStack.scale(0.5F, 0.5F, 0.5F);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity)));
        PoseStack.Pose pose = matrixStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();

        vertex(vertexConsumer, matrix4f, matrix3f, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexConsumer, matrix4f, matrix3f, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexConsumer, matrix4f, matrix3f, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexConsumer, matrix4f, matrix3f, packedLight, 0.0F, 1, 0, 0);

        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    private void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, int packedLight, float x, float y, int u, int v) {
        vertexConsumer.vertex(matrix4f, x - 0.5F, y - 0.5F, 0.0F)
                .color(255, 255, 255, 255)
                .uv((float)u, (float)v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(matrix3f, 0.0F, 1.0F, 0.0F)
                .endVertex();
    }

    private static org.joml.Quaternionf rotation(float x, float y, float z) {
        return new org.joml.Quaternionf().rotationXYZ(x * ((float)Math.PI / 180F), y * ((float)Math.PI / 180F), z * ((float)Math.PI / 180F));
    }

    @Override
    public ResourceLocation getTextureLocation(CustomProjectile entity) {
        return TEXTURE;
    }
}