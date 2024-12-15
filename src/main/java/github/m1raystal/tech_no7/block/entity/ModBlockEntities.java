package github.m1raystal.tech_no7.block.entity;


import github.m1raystal.tech_no7.Tech_no7;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import github.m1raystal.tech_no7.block.MainBlocks;

public class ModBlockEntities {
    public static BlockEntityType<GearSmallBlockEntity> GEAR_SMALL;

    public static void register() {
        GEAR_SMALL= Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Tech_no7.MOD_ID,"gear_small"), FabricBlockEntityTypeBuilder.create(
                        GearSmallBlockEntity::new, MainBlocks.GEAR_SMALL).build());
    }
}
