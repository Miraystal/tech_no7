package github.m1raystal.tech_no7.legacy.gecko_animation;

import github.m1raystal.tech_no7.Tech_no7;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationProcessor;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.util.RenderUtils;

public abstract class MainModel<T extends GeoAnimatable> extends GeoModel<T> {
    private final AnimationProcessor<T> processor = new AnimationProcessor(this);
    private BakedGeoModel currentModel = null;
    private double animTime;
    private double lastGameTickTime;

    public MainModel() {
    }

    public void handleAnimations(T animatable, long instanceId, AnimationState<T> animationState, boolean whyFinal) {
        //_ = whyFinal;
        MinecraftClient mc = MinecraftClient.getInstance();
        AnimatableManager<T> animatableManager = animatable.getAnimatableInstanceCache().getManagerForId(instanceId);
        Double currentTick = (Double) animationState.getData(DataTickets.TICK);
        if (currentTick == null) {
            double var10000;
            if (animatable instanceof Entity) {
                Entity entity = (Entity) animatable;
                var10000 = (double) entity.age;
            } else {
                var10000 = RenderUtils.getCurrentTick();
            }
            currentTick = var10000;
        }

        if (animatableManager.getFirstTickTime() == -1.0) {
            animatableManager.startedAt(currentTick + (double) mc.getTickDelta());
        }

        if (!mc.isPaused() || animatable.shouldPlayAnimsWhileGamePaused()) {
            animatableManager.updatedAt(animatable instanceof Entity ? currentTick + (double) mc.getTickDelta() : currentTick - animatableManager.getFirstTickTime());
            double lastUpdateTime = animatableManager.getLastUpdateTime();
            this.animTime += lastUpdateTime - this.lastGameTickTime;
            this.lastGameTickTime = lastUpdateTime;
        }
        //TODO
        //this.animTime = 10;
        animationState.animationTick = this.animTime;
        AnimationProcessor<T> processor = this.getAnimationProcessor();
        processor.preAnimationSetup(animationState.getAnimatable(), this.animTime);
        if (!processor.getRegisteredBones().isEmpty()) {
            processor.tickAnimation(animatable, this, animatableManager, this.animTime, animationState, this.crashIfBoneMissing());
        }

        this.setCustomAnimations(animatable, instanceId, animationState);
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        AnimatableManager<T> animatableManager = animatable.getAnimatableInstanceCache().getManagerForId(instanceId);
    }
}
