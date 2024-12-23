package github.m1raystal.tech_no7.client.item.client.powersource;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.item.blockitems.powersource.WaterWheelBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WaterWheelBlockItemModel extends GeoModel<WaterWheelBlockItem> {
    @Override
    public Identifier getModelResource(WaterWheelBlockItem waterWheelBlockItem) {
        return new Identifier(Tech_no7.MOD_ID, "geo/water_wheel.geo.json");
    }

    @Override
    public Identifier getTextureResource(WaterWheelBlockItem waterWheelBlockItem) {
        return new Identifier(Tech_no7.MOD_ID, "textures/block/water_wheel.png");
    }

    @Override
    public Identifier getAnimationResource(WaterWheelBlockItem waterWheelBlockItem) {
        return new Identifier(Tech_no7.MOD_ID, "animations/water_wheel.animation.json");
    }
}
