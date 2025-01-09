package github.m1raystal.tech_no7.legacy.animation;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class MainRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (isInvalid(entity)) return;

        mainRender(entity, tickDelta, matrices, vertexConsumers, light, overlay);
    }

    //这个渲染器用来渲染模型
    protected abstract void mainRender(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay);

    public boolean isInvalid(T be) {
        return !be.hasWorld() || be.getCachedState().isAir();
    }
}
