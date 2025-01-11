package github.m1raystal.tech_no7.client.block.entity.client.gear;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import github.m1raystal.tech_no7.legacy.gecko_animation.MainModel;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GearSmallBlockModel extends MainModel<GearSmallBlockEntity> {
    @Override
    public Identifier getModelResource(GearSmallBlockEntity gearSmallBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID, "geo/gear_small.geo.json");
    }

    @Override
    public Identifier getTextureResource(GearSmallBlockEntity gearSmallBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID, "textures/block/gear_small.png");
    }

    @Override
    public Identifier getAnimationResource(GearSmallBlockEntity gearSmallBlockEntity) {
        return new Identifier(Tech_no7.MOD_ID, "animations/gear_small.animation.json");
    }
}
