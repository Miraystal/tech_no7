package github.m1raystal.tech_no7.datagen;

import github.m1raystal.tech_no7.ReflectGetMaterials;
import github.m1raystal.tech_no7.TechItems;
import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.group.MainItemGroup;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DataGen implements DataGeneratorEntrypoint {
    private static final TagKey<Item> STEEL_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(Tech_no7.MOD_ID, "steel_ingot"));

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModelGen::new);
        pack.addProvider(TagGen::new);
        pack.addProvider(LangGen::new);
    }

    private static class TagGen extends FabricTagProvider.ItemTagProvider {
        public TagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {

        }
    }

    private static class ModelGen extends FabricModelProvider {
        private ModelGen(FabricDataOutput generator) {
            super(generator);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            for (Item item : ReflectGetMaterials.ALL_ITEMS) {
                itemModelGenerator.register(item, Models.GENERATED);
            }
        }
    }

    private static class LangGen extends FabricLanguageProvider {
        private LangGen(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataGenerator, "zh_cn");
        }
        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add("item.tech_7th.steel_ingot.lore","工业的基础");
            translationBuilder.add(MainItemGroup.CUSTOM_ITEM_GROUP_KEY, "第七号科技原材料");
            translationBuilder.add(TechItems.STEEL_INGOT, "钢锭");
            translationBuilder.add(TechItems.STEEL_PLATE, "钢板");
            translationBuilder.add(TechItems.SILVER_INGOT, "银锭");
            translationBuilder.add(TechItems.SILVER_PLATE, "银板");
            translationBuilder.add(TechItems.SILVER_STRING, "银线");
            translationBuilder.add(TechItems.COPPER_PLATE, "铜板");
            translationBuilder.add(TechItems.COPPER_STRING, "铜线");
            translationBuilder.add(TechItems.GOLD_PLATE, "金板");
            translationBuilder.add(TechItems.GOLD_STRING, "金线");
            translationBuilder.add(TechItems.LODESTONE, "磁铁锭");
            translationBuilder.add(TechItems.LODESTONE_PLATE, "磁铁板");
        }
    }
}
