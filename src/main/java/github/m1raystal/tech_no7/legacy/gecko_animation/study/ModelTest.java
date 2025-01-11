package github.m1raystal.tech_no7.legacy.gecko_animation.study;

import github.m1raystal.tech_no7.Tech_no7;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModelTest implements UnbakedModel, BakedModel, FabricBakedModel {
    private static final SpriteIdentifier[] SPRITE_IDS = new SpriteIdentifier[]{
            new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, new Identifier(Tech_no7.MOD_ID, "block/gear_small/gear_big")),
            new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, new Identifier(Tech_no7.MOD_ID, "block/gear_small/gear_small"))
    };
    private Sprite[] SPRITES = new Sprite[SPRITE_IDS.length];

    // 一些常量，以避免魔法数据，需要匹配 SPRITE_IDS
    private static final int SPRITE_SIDE = 0;
    private static final int SPRITE_TOP = 1;
    private Mesh mesh;

    @Override
    public Collection<Identifier> getModelDependencies() {
        return Collections.emptyList(); // 模型不依赖于其他模型。
    }

    @Override
    public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
        // 与模型继承有关，我们这里还不需要使用到
    }

    @Nullable
    @Override
    public BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
        // 获得 sprites
        for (int i = 0; i < SPRITE_IDS.length; ++i) {
            SPRITES[i] = textureGetter.apply(SPRITE_IDS[i]);
        }
        // 用 Renderer API 构建 mesh
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        MeshBuilder builder = renderer.meshBuilder();
        QuadEmitter emitter = builder.getEmitter();

        for (Direction direction : Direction.values()) {
            int spriteIdx = direction == Direction.UP || direction == Direction.DOWN ? SPRITE_TOP : SPRITE_SIDE;
            // 将新的面（face）添加到 mesh
            emitter.square(direction, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
            // 设置面的 sprite，必须在 .square() 之后调用
            // 我们还没有指定任何 uv 坐标，所以我们使用整个纹理，BAKE_LOCK_UV 恰好就这么做。
            // 设置每个顶点的 UV 坐标
            Sprite sprite = SPRITES[spriteIdx]; // 获取当前使用的纹理
            float uMin = sprite.getMinU(); // 获取纹理的最小 U
            float uMax = sprite.getMaxU(); // 获取纹理的最大 U
            float vMin = sprite.getMinV(); // 获取纹理的最小 V
            float vMax = sprite.getMaxV(); // 获取纹理的最大 V

// 设置每个顶点的 UV 坐标
//            emitter.sprite(0, 0, uMin, vMin);
//            emitter.sprite(1, 0, uMax, vMin);
//            emitter.sprite(2, 0, uMin, vMax);
//            emitter.sprite(3, 0, uMax, vMax);

// 设置每个顶点的颜色 (-1 表示使用默认颜色)
            emitter.color(0, -1); // 顶点 1
            emitter.color(1, -1); // 顶点 2
            emitter.color(2, -1); // 顶点 3
            emitter.color(3, -1); // 顶点 4
            // 将 quad 添加到 mesh
            emitter.emit();
        }
        mesh = builder.build();

        return this;
    }

    //baked model
    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction face, Random random) {
        return Collections.emptyList(); // 不需要，因为我们使用的是 FabricBakedModel，但是最好不要返回 null，因为有些模组会要调用这个函数
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true; // 环境光遮蔽：我们希望方块在有临近方块时显示阴影
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public boolean hasDepth() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        return SPRITES[1]; // 方块被破坏时产生的颗粒，使用 furnace_top
    }

    @Override
    public ModelTransformation getTransformation() {
        return ModelTransformation.NONE;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }

    //fabric baked model
    @Override
    public boolean isVanillaAdapter() {
        return false; // false 以触发 FabricBakedModel 渲染
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, Supplier<Random> supplier, RenderContext renderContext) {
        // 渲染函数

        // 我们仅渲染 mesh
        renderContext.meshConsumer().accept(mesh);
    }

    @Override
    public void emitItemQuads(ItemStack itemStack, Supplier<Random> supplier, RenderContext renderContext) {

    }
}