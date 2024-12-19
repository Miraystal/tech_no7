package github.m1raystal.tech_no7.block;


import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.block.entity.GearBigBlockEntity;
import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<GearSmallBlockEntity> GEAR_SMALL;
    public static BlockEntityType<GearBigBlockEntity> GEAR_BIG;

    public static void register() {
        GEAR_SMALL= Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Tech_no7.MOD_ID,"gear_small"), FabricBlockEntityTypeBuilder.create(
                        GearSmallBlockEntity::new, ModBlocks.GEAR_SMALL).build());

        GEAR_BIG= Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Tech_no7.MOD_ID,"gear_big"), FabricBlockEntityTypeBuilder.create(
                        GearBigBlockEntity::new, ModBlocks.GEAR_BIG).build());
    }
}
