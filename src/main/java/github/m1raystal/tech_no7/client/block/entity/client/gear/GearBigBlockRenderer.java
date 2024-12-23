package github.m1raystal.tech_no7.client.block.entity.client.gear;

import github.m1raystal.tech_no7.block.entity.GearBigBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GearBigBlockRenderer extends GeoBlockRenderer<GearBigBlockEntity> {
    public GearBigBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new GearBigBlockModel());
    }

    @Override
    protected void rotateBlock(Direction facing, MatrixStack poseStack) {
        GearSmallBlockRenderer.fixGearOffset(facing, poseStack);
    }
}
