package github.m1raystal.tech_no7.client.item.client.gear;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.item.blockitems.GearBigBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GearBigBlockItemModel extends GeoModel<GearBigBlockItem> {
    @Override
    public Identifier getModelResource(GearBigBlockItem gearBigBlockItem) {
        return new Identifier(Tech_no7.MOD_ID,"geo/gear_big.geo.json");
    }

    @Override
    public Identifier getTextureResource(GearBigBlockItem gearBigBlockItem) {
        return new Identifier(Tech_no7.MOD_ID,"textures/block/gear_big.png");
    }

    @Override
    public Identifier getAnimationResource(GearBigBlockItem gearBigBlockItem) {
        return new Identifier(Tech_no7.MOD_ID,"animations/gear_big.animation.json");
    }
}
