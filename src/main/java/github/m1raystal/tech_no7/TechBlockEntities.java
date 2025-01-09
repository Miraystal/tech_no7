package github.m1raystal.tech_no7;


import github.m1raystal.tech_no7.block.entity.GearBigBlockEntity;
import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import github.m1raystal.tech_no7.block.entity.powersource.WaterWheelBlockEntity;
import github.m1raystal.tech_no7.legacy.block.entity.CogWheelBigBlockEntity;
import github.m1raystal.tech_no7.legacy.block.entity.GearSmallBlockEntityUnGeckolib;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TechBlockEntities {
    public static BlockEntityType<GearSmallBlockEntity> GEAR_SMALL;
    public static BlockEntityType<GearBigBlockEntity> GEAR_BIG;
    public static BlockEntityType<WaterWheelBlockEntity> WATER_WHEEL;
    //TODO
    public static BlockEntityType<GearSmallBlockEntityUnGeckolib> GEAR_SMALL_UN_GECKOLIB;
    public static BlockEntityType<CogWheelBigBlockEntity> COG_WHEEL_BIG;

    public static void register() {
        GEAR_SMALL = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Tech_no7.MOD_ID, "gear_small"), FabricBlockEntityTypeBuilder.create(
                        GearSmallBlockEntity::new, TechBlocks.GEAR_SMALL).build());

        GEAR_BIG = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Tech_no7.MOD_ID, "gear_big"), FabricBlockEntityTypeBuilder.create(
                        GearBigBlockEntity::new, TechBlocks.GEAR_BIG).build());

        WATER_WHEEL = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Tech_no7.MOD_ID, "water_wheel"), FabricBlockEntityTypeBuilder.create(
                        WaterWheelBlockEntity::new, TechBlocks.WATER_WHEEL).build());

        {
            //TODO
            GEAR_SMALL_UN_GECKOLIB = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                    new Identifier(Tech_no7.MOD_ID, "gear_small_un_geckolib"),
                    FabricBlockEntityTypeBuilder.create(GearSmallBlockEntityUnGeckolib::new, TechBlocks.GEAR_SMALL_UN_GECKOLIB).build());
        }

        {
            COG_WHEEL_BIG = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                    new Identifier(Tech_no7.MOD_ID, "cogwheel_big"), FabricBlockEntityTypeBuilder.create(
                            CogWheelBigBlockEntity::new, TechBlocks.COG_WHEEL_BLOCK
                    ).build());
        }
    }
}
