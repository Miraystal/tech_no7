package github.m1raystal.tech_no7.client;

import github.m1raystal.tech_no7.block.entity.ModBlockEntities;
import github.m1raystal.tech_no7.client.block.entity.client.GearSmallBlockRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class Tech_no7Client implements ClientModInitializer {
    public static final String MOD_ID = "tech_no7";

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.GEAR_SMALL, GearSmallBlockRenderer::new);
    }
}
