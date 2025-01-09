package github.m1raystal.tech_no7.block.entity.powersource;

import github.m1raystal.tech_no7.TechBlockEntities;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStressBlockEntitiesFather;
import github.m1raystal.tech_no7.block.animation_limit.BlockAnimationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WaterWheelBlockEntity extends MachineWithStressBlockEntitiesFather {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public WaterWheelBlockEntity(BlockPos pos, BlockState state) {
        super(TechBlockEntities.WATER_WHEEL, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "work", 4, state -> {
            if (this.getStress() > 0) {
                return BlockAnimationHandler.setAnimationLimit(state, this).setAndContinue(RawAnimation.begin().thenLoop("work"));
            } else {
                return PlayState.STOP;
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
