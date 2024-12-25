package github.m1raystal.tech_no7.block.entity;


import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStressBlockEntitiesFather;
import github.m1raystal.tech_no7.block.ModBlockEntities;
import github.m1raystal.tech_no7.block.animation_limit.BlockAnimationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class GearSmallBlockEntity extends MachineWithStressBlockEntitiesFather {
    public GearSmallBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public GearSmallBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.GEAR_SMALL, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "work", 4, state -> {
            if (this.getStress() > 0) {
                BlockAnimationHandler.setAnimationLimit(state, this).setAndContinue(RawAnimation.begin().thenLoop("left_work"));
                return PlayState.CONTINUE;
            } else return PlayState.STOP;
        }));
    }
}
