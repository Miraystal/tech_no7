package github.m1raystal.tech_no7.block.entity;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class GearSmallBlockEntity extends BlockEntity implements GeoBlockEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public GearSmallBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEAR_SMALL, pos, state);
        //this.createNbt();
        //this.writeNbt();
        //this.markDirty();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        GearSmallBlockEntity blockEntity = this;
        Direction direction = blockEntity.getCachedState().get(Properties.FACING);
        if (direction.equals(Direction.DOWN)) blockEntity.setCachedState(blockEntity.getCachedState().with(Properties.FACING, Direction.UP));
        if (direction.equals(Direction.UP)) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().thenLoop("fix_left_work"));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("left_work", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
