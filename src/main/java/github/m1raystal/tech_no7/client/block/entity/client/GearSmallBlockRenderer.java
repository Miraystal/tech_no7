package github.m1raystal.tech_no7.client.block.entity.client;

import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.state.property.Properties;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GearSmallBlockRenderer extends GeoBlockRenderer<GearSmallBlockEntity> {

    public GearSmallBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new GearSmallBlockModel());
        //context.getRenderManager().renderBlock();
    }
}
