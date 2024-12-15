package github.m1raystal.tech_no7.client.item.client;

import github.m1raystal.tech_no7.item.custom.GearSmallBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GearSmallBlockItemRenderer extends GeoItemRenderer<GearSmallBlockItem> {
    public GearSmallBlockItemRenderer() {
        super(new GearSmallBlockItemModel());
    }
}
