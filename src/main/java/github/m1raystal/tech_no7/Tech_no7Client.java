package github.m1raystal.tech_no7;

import github.m1raystal.tech_no7.client.block.entity.client.gear.GearBigBlockRenderer;
import github.m1raystal.tech_no7.client.block.entity.client.gear.GearSmallBlockRenderer;
import github.m1raystal.tech_no7.client.block.entity.client.powersource.WaterWheelBlockRenderer;
import github.m1raystal.tech_no7.legacy.block.model_renderer.GearSmallBlockUnGeckolibRenderer;
import github.m1raystal.tech_no7.legacy.gecko_animation.study.TutorialModelLoadingPlugin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class Tech_no7Client implements ClientModInitializer {
    public static final String MOD_ID = "tech_no7";

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(TechBlockEntities.GEAR_SMALL, GearSmallBlockRenderer::new);
        BlockEntityRendererFactories.register(TechBlockEntities.GEAR_BIG, GearBigBlockRenderer::new);
        BlockEntityRendererFactories.register(TechBlockEntities.WATER_WHEEL, WaterWheelBlockRenderer::new);
        //TODO
        {
            BlockEntityRendererFactories.register(TechBlockEntities.GEAR_SMALL_UN_GECKOLIB, GearSmallBlockUnGeckolibRenderer::new);
        }

        //ModelLoadingPlugin.register(new TutorialModelLoadingPlugin());
    }
}
