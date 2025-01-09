package github.m1raystal.tech_no7.legacy.block.model_renderer;

import github.m1raystal.tech_no7.TechBlocks;
import github.m1raystal.tech_no7.legacy.block.entity.GearSmallBlockEntityUnGeckolib;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class GearSmallBlockUnGeckolibRenderer implements BlockEntityRenderer<GearSmallBlockEntityUnGeckolib> {
    public GearSmallBlockUnGeckolibRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(GearSmallBlockEntityUnGeckolib entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

//        // 动态计算旋转角度和偏移
//        float rotation = (entity.getWorld().getTime() + tickDelta) * 4 % 360;
//        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
//
//        // 移动和旋转
//        matrices.translate(0.5, 1.25 + offset, 0.5);
//        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));
//
//        MinecraftClient.getInstance().getBlockRenderManager().renderBlock(entity.getCachedState(), entity.getPos(), entity.getWorld(), matrices,
//                vertexConsumers.getBuffer(RenderLayer.getSolid()), false, Random.create(0L));

        //new BlockModels()
        ModelIdentifier blockId = BlockModels.getModelId(TechBlocks.GEAR_SMALL_UN_GECKOLIB.getDefaultState());

        MinecraftClient client = MinecraftClient.getInstance();
        BlockRenderManager blockRenderManager = client.getBlockRenderManager();
        // 获取方块的模型
        BakedModel model = blockRenderManager.getModel(entity.getCachedState());
        // 获取渲染层，这里使用 solid 渲染层来进行渲染
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getSolid());


//        // 获取方块的纹理
//        Sprite sprite = client.getBlockRenderManager().getModel(state).getSprite();
        matrices.pop();
    }

    protected void rotateBlock(Direction facing, MatrixStack matrices) {
        switch (facing) {
            case SOUTH -> matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
            case WEST -> matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
            case NORTH -> matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0.0F));
            case EAST -> matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(270.0F));
            case UP -> matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            case DOWN -> matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(90.0F));
        }

    }
}
