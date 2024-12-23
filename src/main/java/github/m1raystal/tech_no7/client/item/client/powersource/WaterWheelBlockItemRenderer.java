package github.m1raystal.tech_no7.client.item.client.powersource;

import github.m1raystal.tech_no7.item.blockitems.powersource.WaterWheelBlockItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WaterWheelBlockItemRenderer extends GeoItemRenderer<WaterWheelBlockItem> {
    public WaterWheelBlockItemRenderer() {
        super(new WaterWheelBlockItemModel());
    }
}
