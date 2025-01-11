package github.m1raystal.tech_no7.block.entity;


import github.m1raystal.tech_no7.TechBlockEntities;
import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStressBlockEntitiesFather;
import github.m1raystal.tech_no7.block.animation_limit.BlockAnimationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class GearSmallBlockEntity extends MachineWithStressBlockEntitiesFather {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GearSmallBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public GearSmallBlockEntity(BlockPos pos, BlockState state) {
        this(TechBlockEntities.GEAR_SMALL, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "work", 0, state -> {
//            {
//                if ((int) state.getAnimationTick() == 100) {
//                    AnimatableManager<GeoAnimatable> managerForId = this.getAnimatableInstanceCache().getManagerForId(this.getPos().hashCode());
//                    double fixedAnimTime = 100.0;
//                    //
//                    //state.animationTick = fixedAnimTime;
//                    double currentTick = this.getTick(this);
//
//                    // 使 animTime 恒定为 fixedAnimTime
//                    double fakeStartTime = currentTick - fixedAnimTime;
//
//                    //
//                    //managerForId.startedAt(RenderUtils.getCurrentTick());
//                    //managerForId.startedAt(fakeStartTime);
//                    managerForId.updatedAt(fakeStartTime);
//
//                    Tech_no7.LOGGER.info(String.valueOf(state.getAnimationTick()));
//                }
//            }
            if (this.getStress() > 0) {
                BlockAnimationHandler.setAnimationLimit(state, this).setAndContinue(RawAnimation.begin().thenLoop("left_work"));
                return PlayState.CONTINUE;
            } else return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
