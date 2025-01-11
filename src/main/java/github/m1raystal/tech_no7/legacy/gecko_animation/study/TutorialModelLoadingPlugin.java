package github.m1raystal.tech_no7.legacy.gecko_animation.study;

import github.m1raystal.tech_no7.Tech_no7;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TutorialModelLoadingPlugin implements ModelLoadingPlugin {
    public static final Identifier FOUR_SIDED_FURNACE_MODEL = new Identifier(Tech_no7.MOD_ID, "four_sided_furnace");

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        // 我们需要在模型被加载时添加模型
        pluginContext.modifyModelOnLoad().register((original, context) -> {
            // 这个每次加载模型时都会调用，所以确保我们只针对我们的
            final Identifier id = context.id();
            if (id != null && id.equals(FOUR_SIDED_FURNACE_MODEL)) {
                return new ModelTest();
            } else {
                // 如果不修改模型，就照样返回原来的
                return new ModelTest();
            }
        });
    }
}