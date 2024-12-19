package github.m1raystal.tech_no7.client.item.client.gear;

import github.m1raystal.tech_no7.item.blockitems.GearSmallBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GearSmallBlockItemRenderer extends GeoItemRenderer<GearSmallBlockItem> {
    public GearSmallBlockItemRenderer() {
        super(new GearSmallBlockItemModel());
    }
}
