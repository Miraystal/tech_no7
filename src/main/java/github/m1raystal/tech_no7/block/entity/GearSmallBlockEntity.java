package github.m1raystal.tech_no7.block.entity;


import github.m1raystal.tech_no7.api.MachineWithStress;
import github.m1raystal.tech_no7.block.ModBlockEntities;
import github.m1raystal.tech_no7.block.animation_limit.GearAnimationLimit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class GearSmallBlockEntity extends BlockEntity implements GeoBlockEntity, MachineWithStress {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int stress;
    private boolean stopOrIdle = false;

    public GearSmallBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEAR_SMALL, pos, state);
        this.stress = 0;
        this.createNbt().putInt("stress", stress);
        this.markDirty();
    }

    public GearSmallBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.stress = 0;
        this.createNbt().putInt("stress", stress);
        this.markDirty();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @SuppressWarnings("deprecation")
    protected <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        GearSmallBlockEntity blockEntity = this;
        Direction direction = blockEntity.getCachedState().get(Properties.FACING);

        if (stress != 0) {
            GearAnimationLimit.setAnimationLimit(tAnimationState, this).getController().setAnimation(RawAnimation.begin().thenLoop("left_work"));
        } else if (!stopOrIdle) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().thenPlay("idle"));
            this.stopOrIdle = true;
            return PlayState.STOP;

        } else {
            tAnimationState.getController().stop();
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void setStress(int stress) {
        this.stress = stress;
        if (this.createNbt().contains("stress")) this.createNbt().remove("stress");
        this.createNbt().putInt("stress", stress);
        this.markDirty();
    }

    @Override
    public int getStress() {
        return this.stress;
    }
}
