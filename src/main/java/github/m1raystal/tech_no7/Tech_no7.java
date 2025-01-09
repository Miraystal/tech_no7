package github.m1raystal.tech_no7;

import github.m1raystal.tech_no7.group.MainItemGroup;
import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;

public class Tech_no7 implements ModInitializer {
    public static final String MOD_ID = "tech_no7";
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //ctrl_shift_f1 & 游戏内 f3+t
    @Override
    public void onInitialize() {
        boolean isWorking = init();
        if (isWorking) {
            LOGGER.info("Seventh Tech initialized successfully!");
        }
        ReflectGetMaterials.init();
    }

    private boolean init() {
        TechItems.init();
        MainItemGroup.init();
        TechBlocks.init();

        //注册geo方块实体
        TechBlockEntities.register();
        return true;
    }
}
