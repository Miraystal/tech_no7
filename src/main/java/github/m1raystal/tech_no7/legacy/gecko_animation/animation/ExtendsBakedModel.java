package github.m1raystal.tech_no7.legacy.gecko_animation.animation;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ExtendsBakedModel implements BakedModel, FabricBakedModel {
    //private final BakedModel bakedModel;
    public ExtendsBakedModel(){
        //this.bakedModel = bakedModel;
    }

    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
        return Collections.emptyList();
    }

    public boolean useAmbientOcclusion() {
        return true;
    }

    public boolean hasDepth() {
        return false;
    }

    public boolean isSideLit() {
        return false;
    }

    public boolean isBuiltin() {
        return false;
    }

    public Sprite getParticleSprite() {
        //return this.sprite;
        return null;
    }

    public ModelTransformation getTransformation() {
        //return this.transformation;
        return null;
    }

    public ModelOverrideList getOverrides() {
//        return this.itemPropertyOverrides;
        return null;
    }


    @Override
    public boolean isVanillaAdapter() {
        return false; // false 以触发 FabricBakedModel 渲染
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockRenderView, BlockState blockState, BlockPos blockPos, Supplier<Random> supplier, RenderContext renderContext) {
        renderContext.pushTransform(mutableQuadView -> {
            mutableQuadView.pos(0, new Vector3f(1.0f, 1.0f, 1.0f));
            return false;
        });

        QuadEmitter emitter = renderContext.getEmitter();
        //emitter.
    }

    @Override
    public void emitItemQuads(ItemStack itemStack, Supplier<Random> supplier, RenderContext renderContext) {

    }
}
