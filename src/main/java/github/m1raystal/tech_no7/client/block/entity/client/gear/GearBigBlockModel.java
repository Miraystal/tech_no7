package github.m1raystal.tech_no7.client.block.entity.client.gear;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.block.entity.GearBigBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GearBigBlockModel extends GeoModel<GearBigBlockEntity> {
    @Override
    public Identifier getModelResource(GearBigBlockEntity gearBigBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID,"geo/gear_big.geo.json");
    }

    @Override
    public Identifier getTextureResource(GearBigBlockEntity gearBigBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID,"textures/block/gear_big.png");
    }

    @Override
    public Identifier getAnimationResource(GearBigBlockEntity gearBigBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID,"animations/gear_big.animation.json");
    }
}
