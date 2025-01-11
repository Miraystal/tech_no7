package github.m1raystal.tech_no7.client.block.entity.client.gear;

import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import github.m1raystal.tech_no7.legacy.gecko_animation.MainRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GearSmallBlockRenderer extends MainRenderer<GearSmallBlockEntity> {

    public GearSmallBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new GearSmallBlockModel());
    }

    @Override
    protected void rotateBlock(Direction facing, MatrixStack poseStack) {
        fixGearOffset(facing, poseStack);
    }


    public static void fixGearOffset(Direction facing, MatrixStack poseStack) {
        switch (facing) {
            case SOUTH -> {
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
                poseStack.translate(-0.5f,0.0f,-0.5f);
            }
            case WEST -> {
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
                poseStack.translate(-0.5f,0.0f,0.5f);
            }
            case NORTH -> {
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0.0F));
                poseStack.translate(0.5f,0.0f,0.5f);
            }
            case EAST -> {
                poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(270.0F));
                poseStack.translate(0.5f,0.0f,-0.5f);
            }
            case UP -> {
                poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));  // 旋转
                poseStack.translate(0.5F, 0.0F, -0.5F);
            }
            case DOWN -> {
                poseStack.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(90.0F));  // 旋转
                poseStack.translate(0.5f, -1.0f, 0.5F);
            }
        }
    }
}
