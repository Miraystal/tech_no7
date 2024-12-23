package github.m1raystal.tech_no7.client;

import github.m1raystal.tech_no7.block.ModBlockEntities;
import github.m1raystal.tech_no7.client.block.entity.client.gear.GearBigBlockRenderer;
import github.m1raystal.tech_no7.client.block.entity.client.gear.GearSmallBlockRenderer;
import github.m1raystal.tech_no7.client.block.entity.client.powersource.WaterWheelBlockRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class Tech_no7Client implements ClientModInitializer {
    public static final String MOD_ID = "tech_no7";

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.GEAR_SMALL, GearSmallBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.GEAR_BIG, GearBigBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WATER_WHEEL, WaterWheelBlockRenderer::new);
    }
}
