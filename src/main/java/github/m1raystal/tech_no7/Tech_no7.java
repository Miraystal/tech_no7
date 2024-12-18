package github.m1raystal.tech_no7;

import github.m1raystal.tech_no7.block.ModBlocks;
import github.m1raystal.tech_no7.block.entity.ModBlockEntities;
import github.m1raystal.tech_no7.group.MainItemGroup;
import github.m1raystal.tech_no7.item.MaterialItem;
import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;

public class Tech_no7 implements ModInitializer {
    public static final String MOD_ID = "tech_no7";
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        boolean isWorking = init();
        if (isWorking) {
            LOGGER.info("Seventh Tech initialized successfully!");
        }
    }

    private boolean init() {
        ReflectGetMaterials.init();//必须最先

        MaterialItem.init();
        MainItemGroup.init();
        ModBlocks.init();

        //注册geo方块实体
        ModBlockEntities.register();
        return true;
    }
}
