package github.m1raystal.tech_no7.block;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.block.blocks.GearBigBlock;
import github.m1raystal.tech_no7.block.blocks.GearSmallBlock;
import github.m1raystal.tech_no7.block.blocks.powersource.WaterWheelBlock;
import github.m1raystal.tech_no7.group.MainItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModBlocks {
    public static final Block GEAR_SMALL = Registry.register(Registries.BLOCK, new Identifier(Tech_no7.MOD_ID, "gear_small"),
            new GearSmallBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block GEAR_BIG = Registry.register(Registries.BLOCK, new Identifier(Tech_no7.MOD_ID, "gear_big"),
            new GearBigBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block WATER_WHEEL = Registry.register(Registries.BLOCK, new Identifier(Tech_no7.MOD_ID, "water_wheel"),
            new WaterWheelBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        Item returnedItem = registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Tech_no7.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        //注意此处内联的物品组
        Item item = Registry.register(Registries.ITEM, new Identifier(Tech_no7.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(MainItemGroup.CUSTOM_BLOCK_GROUP_KEY).register(entries -> {
            entries.add(item);
        });

        return Registry.register(Registries.ITEM, new Identifier(Tech_no7.MOD_ID, name), new BlockItem(block,
                new Item.Settings()));
    }

    public static void init() {
    }
}
