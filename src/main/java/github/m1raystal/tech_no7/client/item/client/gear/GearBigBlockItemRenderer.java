package github.m1raystal.tech_no7.client.item.client.gear;

import github.m1raystal.tech_no7.item.blockitems.GearBigBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GearBigBlockItemRenderer extends GeoItemRenderer<GearBigBlockItem> {
    public GearBigBlockItemRenderer() {
        super(new GearBigBlockItemModel());
    }
}
