package github.m1raystal.tech_no7.client.block.entity.client.powersource;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.block.entity.powersource.WaterWheelBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WaterWheelBlockModel extends GeoModel<WaterWheelBlockEntity> {

    @Override
    public Identifier getModelResource(WaterWheelBlockEntity waterWheelBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID, "geo/water_wheel.geo.json");
    }

    @Override
    public Identifier getTextureResource(WaterWheelBlockEntity waterWheelBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID, "textures/block/water_wheel.png");
    }

    @Override
    public Identifier getAnimationResource(WaterWheelBlockEntity waterWheelBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID, "animations/water_wheel.animation.json");
    }
}
