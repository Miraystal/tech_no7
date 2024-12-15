package github.m1raystal.tech_no7.client.item.client;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.item.custom.GearSmallBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GearSmallBlockItemModel extends GeoModel<GearSmallBlockItem> {
    @Override
    public Identifier getModelResource(GearSmallBlockItem gearBlockItem) {
        return new Identifier(Tech_no7.MOD_ID, "geo/gear_small.geo.json");
    }

    @Override
    public Identifier getTextureResource(GearSmallBlockItem gearBlockItem) {
        return new Identifier(Tech_no7.MOD_ID, "textures/block/gear_small.png");
    }

    @Override
    public Identifier getAnimationResource(GearSmallBlockItem gearBlockItem) {
        return new Identifier(Tech_no7.MOD_ID, "animations/gear_small.animation.json");
    }
}
