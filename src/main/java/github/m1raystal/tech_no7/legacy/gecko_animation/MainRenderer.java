package github.m1raystal.tech_no7.legacy.gecko_animation;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import java.util.List;

public abstract class MainRenderer<T extends BlockEntity & GeoAnimatable> extends GeoBlockRenderer<T> {
    public MainRenderer(GeoModel<T> model) {
        super(model);
    }

    @Override
    public void actuallyRender(MatrixStack poseStack, T animatable, BakedGeoModel model, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (!isReRender) {
            AnimationState<T> animationState = new AnimationState<>(animatable, 0.0F, 0.0F, partialTick, false);
            long instanceId = this.getInstanceId(animatable);
            // 新版本geckolib 以下步骤被分离
            animationState.setData(DataTickets.TICK, animatable.getTick(animatable));
            animationState.setData(DataTickets.BLOCK_ENTITY, animatable);
            //
            this.model.addAdditionalStateData(animatable, instanceId, animationState::setData);
            this.rotateBlock(this.getFacing(animatable), poseStack);
            ((MainModel<T>) this.model).handleAnimations(animatable, instanceId, animationState, false);
        }

        this.modelRenderTranslations = new Matrix4f(poseStack.peek().getPositionMatrix());
        // 当前版本仍然不是密封类
        //GeoRenderer.super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        this.updateAnimatedTextureFrame(animatable);

        for (GeoBone group : model.topLevelBones()) {
            this.renderRecursively(poseStack, animatable, group, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        }

    }

    //TODO
    public static void test(BlockState blockState) {
        //ModelUtil.interpolateAngle()
//        ModelOverrideList overrides = bakedModel.getOverrides();
//        overrides.apply()

//        List<BakedQuad> quads = bakedModel.getQuads(null, null, null);
//        for (Direction direction : Direction.values()) {
//            List<BakedQuad> directionQuads = bakedModel.getQuads(null, direction, null);
//            // 在此处理渲染面数据
//            System.out.println("Quads for direction " + direction + ": " + directionQuads.size());
//        }

        BakedModelManager bakedModelManager = MinecraftClient.getInstance().getBakedModelManager();
        BakedModel model = bakedModelManager.getBlockModels().getModel(blockState);

        //ModelOverrideList overrides = model.getOverrides();
        //overrides.apply(model, TechItems.COG_WHEEL_BLOCK_ITEM.getDefaultStack(), null, null, 0);

        List<BakedQuad> quads = model.getQuads(null, null, Random.create());
        for (BakedQuad quad : quads) {
            int[] vertexData = quad.getVertexData();
            for (int i = 0; i < vertexData.length; i += 8) {
                // 假设顶点数据格式为 [x, y, z, color, u, v, nx, ny, nz]
                float x = Float.intBitsToFloat(vertexData[i]);
                float y = Float.intBitsToFloat(vertexData[i + 1]);
                float z = Float.intBitsToFloat(vertexData[i + 2]);

                float angle = 45;
                // 应用旋转变换
                float newX = (float) (x * Math.cos(angle) - z * Math.sin(angle));
                float newZ = (float) (x * Math.sin(angle) + z * Math.cos(angle));

                // 更新顶点数据
                vertexData[i] = Float.floatToIntBits(newX);
                vertexData[i + 2] = Float.floatToIntBits(newZ);
            }
        }
    }
}
