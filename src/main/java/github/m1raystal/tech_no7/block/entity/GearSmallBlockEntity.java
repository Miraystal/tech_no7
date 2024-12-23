package github.m1raystal.tech_no7.block.entity;


import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.inter.MachineWithStress;
import github.m1raystal.tech_no7.block.ModBlockEntities;
import github.m1raystal.tech_no7.block.animation_limit.BlockAnimationHandler;
import github.m1raystal.tech_no7.block.animation_limit.RotateDirection;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class GearSmallBlockEntity extends BlockEntity implements GeoBlockEntity, MachineWithStress {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int stress;
    private RotateDirection rotateDirection;
    private boolean stopOrIdle = false;

    public GearSmallBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.stress = 0;
        this.rotateDirection = RotateDirection.NONE;
    }

    public GearSmallBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.GEAR_SMALL, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "work", 1, state -> {
            //Tech_no7.LOGGER.info("stress is:" + this.stress);
            //Tech_no7.LOGGER.info(this.toString());
            if (stress > 0) {
                BlockAnimationHandler.setAnimationLimit(state, this).setAndContinue(RawAnimation.begin().thenLoop("left_work"));
            } else if (!stopOrIdle) {
                state.getController().setAnimation(RawAnimation.begin().thenPlay("idle"));
                this.stopOrIdle = true;
                return PlayState.STOP;

            } else {
                Tech_no7.LOGGER.info("here is stop");
                state.getController().stop();
                return PlayState.STOP;
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public void setStress(int stress) {
        Tech_no7.LOGGER.info("setting stress " + stress);
        this.stress = stress;
    }

    @Override
    public int getStress() {
        Tech_no7.LOGGER.info("getting stress " + this.stress);
        return this.stress;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }


    public RotateDirection getRotateDirection() {
        return rotateDirection;
    }

    public void setRotateDirection(RotateDirection rotateDirection) {
        this.rotateDirection = rotateDirection;
    }
}
