package github.m1raystal.tech_no7;

import github.m1raystal.tech_no7.item.MaterialItem;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class ReflectGetMaterials {
    public static Item[] ALL_ITEMS;

    public static void init() {
        Class<MaterialItem> ItemsClass = MaterialItem.class;
        Field[] ItemsField = ItemsClass.getDeclaredFields();
        ALL_ITEMS = new Item[ItemsField.length];
        for (Field field : ItemsField) {
            try {
                field.setAccessible(true);
                for (int i = 0; i < ItemsField.length; i++) {
                    ALL_ITEMS[i] = (Item) ItemsField[i].get(ItemsClass);
                }
            } catch (IllegalAccessException | IllegalStateException e) {
                Logger.getGlobal().severe("非法访问异常或非法状态异常出现在存储类中的域时");
                Logger.getGlobal().info(e.getMessage());
            }
        }
    }
}
