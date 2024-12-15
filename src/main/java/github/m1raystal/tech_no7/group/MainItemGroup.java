package github.m1raystal.tech_no7.group;

import github.m1raystal.tech_no7.ReflectGetMaterials;
import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.item.MaterialItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class MainItemGroup {
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Tech_no7.MOD_ID, "item_group"));
    public static final RegistryKey<ItemGroup> CUSTOM_BLOCK_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Tech_no7.MOD_ID, "block_group"));

    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MaterialItem.STEEL_INGOT))
            .displayName(Text.translatable("itemGroup.tech_7th-item-group"))
            .build();

    private static final ItemGroup BLOCK_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MaterialItem.STEEL_PLATE))
            .displayName(Text.translatable("itemGroup.tech_7th-block-group"))
            .build();

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, CUSTOM_BLOCK_GROUP_KEY, BLOCK_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            for (Item item : ReflectGetMaterials.ALL_ITEMS) {
                itemGroup.add(item);
            }
        });
    }
}
