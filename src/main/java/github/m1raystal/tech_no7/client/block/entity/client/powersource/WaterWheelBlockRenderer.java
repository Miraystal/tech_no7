package github.m1raystal.tech_no7.client.block.entity.client.powersource;

import github.m1raystal.tech_no7.block.entity.powersource.WaterWheelBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class WaterWheelBlockRenderer extends GeoBlockRenderer<WaterWheelBlockEntity> {
    public WaterWheelBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new WaterWheelBlockModel());
    }
}
