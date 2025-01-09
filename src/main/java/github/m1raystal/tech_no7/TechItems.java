package github.m1raystal.tech_no7;

import github.m1raystal.tech_no7.item.blockitems.GearBigBlockItem;
import github.m1raystal.tech_no7.item.blockitems.GearSmallBlockItem;
import github.m1raystal.tech_no7.item.blockitems.powersource.WaterWheelBlockItem;
import github.m1raystal.tech_no7.legacy.block.blockitems.CogWheelBigBlockItem;
import github.m1raystal.tech_no7.legacy.block.blockitems.GearSmallBlockItemUnGeckolib;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TechItems {
    public static final Item STEEL_INGOT = register(new SteelLore(new Item.Settings()), "steel_ingot");
    public static final Item STEEL_PLATE = register(new Item(new Item.Settings()), "steel_plate");
    public static final Item SILVER_INGOT = register(new Item(new Item.Settings()), "silver_ingot");
    public static final Item SILVER_PLATE = register(new Item(new Item.Settings()), "silver_plate");
    public static final Item SILVER_STRING = register(new Item(new Item.Settings()), "silver_string");
    public static final Item COPPER_PLATE = register(new Item(new Item.Settings()), "copper_plate");
    public static final Item COPPER_STRING = register(new Item(new Item.Settings()), "copper_string");
    public static final Item GOLD_PLATE = register(new Item(new Item.Settings()), "gold_plate");
    public static final Item GOLD_STRING = register(new Item(new Item.Settings()), "gold_string");
    public static final Item LODESTONE = register(new Item(new Item.Settings()), "lodestone");
    public static final Item LODESTONE_PLATE = register(new Item(new Item.Settings()), "lodestone_plate");

    public static final Item GEAR_SMALL_BLOCK_ITEM = register(new GearSmallBlockItem(TechBlocks.GEAR_SMALL, new Item.Settings()), "gear_small");
    public static final Item GEAR_BIG_BLOCK_ITEM = register(new GearBigBlockItem(TechBlocks.GEAR_BIG, new Item.Settings()), "gear_big");
    public static final Item WATER_WHEEL = register(new WaterWheelBlockItem(TechBlocks.WATER_WHEEL, new Item.Settings()), "water_wheel");

    //TODO
    public static final Item GEAR_SMALL_UN_GECKOLIB = register(new GearSmallBlockItemUnGeckolib(TechBlocks.GEAR_SMALL_UN_GECKOLIB,
            new Item.Settings()), "gear_small_un_geckolib");
    public static final Item COG_WHEEL_BLOCK_ITEM = register(new CogWheelBigBlockItem(TechBlocks.COG_WHEEL_BLOCK, new Item.Settings()), "cogwheel_big");

    public static void init() {
    }

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Tech_no7.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static class SteelLore extends Item {
        public SteelLore(Settings settings) {
            super(settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(Text.translatable("item.tech_7th.steel_ingot.lore").formatted(Formatting.GOLD));
        }
    }
}
