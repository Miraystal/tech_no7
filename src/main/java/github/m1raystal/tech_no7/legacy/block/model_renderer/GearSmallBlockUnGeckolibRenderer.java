package github.m1raystal.tech_no7.legacy.block.model_renderer;

import github.m1raystal.tech_no7.legacy.block.entity.GearSmallBlockEntityUnGeckolib;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Environment(EnvType.CLIENT)
public class GearSmallBlockUnGeckolibRenderer implements BlockEntityRenderer<GearSmallBlockEntityUnGeckolib> {
    public GearSmallBlockUnGeckolibRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(GearSmallBlockEntityUnGeckolib entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

////        // 动态计算旋转角度和偏移
////        float rotation = (entity.getWorld().getTime() + tickDelta) * 4 % 360;
////        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
////
////        // 移动和旋转
////        matrices.translate(0.5, 1.25 + offset, 0.5);
////        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));
////
////        MinecraftClient.getInstance().getBlockRenderManager().renderBlock(entity.getCachedState(), entity.getPos(), entity.getWorld(), matrices,
////                vertexConsumers.getBuffer(RenderLayer.getSolid()), false, Random.create(0L));
//
//        //new BlockModels()
//        ModelIdentifier blockId = BlockModels.getModelId(TechBlocks.GEAR_SMALL_UN_GECKOLIB.getDefaultState());
//
//        MinecraftClient client = MinecraftClient.getInstance();
//        BlockRenderManager blockRenderManager = client.getBlockRenderManager();
//        // 获取方块的模型
//        BakedModel model = blockRenderManager.getModel(entity.getCachedState());
//        // 获取渲染层，这里使用 solid 渲染层来进行渲染
//        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getSolid());
//
//
////        // 获取方块的纹理
////        Sprite sprite = client.getBlockRenderManager().getModel(state).getSprite();
        float rotation = (entity.getWorld().getTime() + tickDelta) * 4 % 360;
        matrices.translate(0.5, 0.5, 0.5); // 移动模型到正确位置
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation)); // 旋转
        //matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getTilt())); // 额外旋转（例如倾斜）
        //matrices.translate(-0.5, -0.5, -0.5); // 还原模型的位置

        BakedModelManager bakedModelManager = MinecraftClient.getInstance().getBakedModelManager();
        BakedModel model = bakedModelManager.getBlockModels().getModel(entity.getCachedState());
        List<BakedQuad> quads = model.getQuads(null, null, Random.create());
        while(quads.stream().iterator().hasNext()){
            BakedQuad next = quads.stream().iterator().next();
            int[] vertexData = next.getVertexData();
            while(Arrays.stream(vertexData).iterator().hasNext()){
                Integer vertex = Arrays.stream(vertexData).iterator().next();

            }
        }

        BlockModels blockModelRenderer = new BlockModels(bakedModelManager);
        HashMap<BlockState, BakedModel> blockStateBakedModelHashMap = new HashMap<>();
        blockStateBakedModelHashMap.put(entity.getCachedState(), model);
        blockModelRenderer.setModels(blockStateBakedModelHashMap);

        Random random = Random.create();

        // 渲染每个 quad
        for (BakedQuad quad : model.getQuads(null, null, random)) {
            renderQuad(quad, matrices, vertexConsumers, light, overlay);
        }

        matrices.pop();
    }

    private void renderQuad(BakedQuad quad, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getSolid());
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Matrix3f normalMatrix = matrices.peek().getNormalMatrix();

        // 获取顶点数据
        int[] vertexData = quad.getVertexData();

        // 每个顶点有8个元素，分别是位置(x, y, z)，纹理坐标(u, v)，颜色(r, g, b)
        for (int i = 0; i < 4; i++) {
            int baseIndex = i * 8;  // 每个顶点的起始位置
            float x = vertexData[baseIndex];      // 顶点x坐标
            float y = vertexData[baseIndex + 1];  // 顶点y坐标
            float z = vertexData[baseIndex + 2];  // 顶点z坐标
            float u = vertexData[baseIndex + 3];  // 纹理坐标u
            float v = vertexData[baseIndex + 4];  // 纹理坐标v
            int color = (int) vertexData[baseIndex + 5];  // 顶点颜色

            // 渲染该顶点
            vertexConsumer.vertex(matrix, x, y, z)
                    .color((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, 255)
                    .texture(u, v)
                    .overlay(overlay)
                    .light(light)
                    .normal(normalMatrix, 0.0f, 0.0f, -1.0f)  // 固定法线方向，应该根据实际情况计算
                    .next();
        }
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
