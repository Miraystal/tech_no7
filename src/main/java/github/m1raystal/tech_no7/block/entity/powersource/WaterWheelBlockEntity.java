package github.m1raystal.tech_no7.block.entity.powersource;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.inter.MachineWithStress;
import github.m1raystal.tech_no7.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WaterWheelBlockEntity extends BlockEntity implements GeoBlockEntity, MachineWithStress {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int stress;

    {
        stress = 0;
    }

    public WaterWheelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATER_WHEEL, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "work", 1, state -> {
            if (this.stress > 0) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("work"));
            } else {
                return PlayState.STOP;
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        //AbstractBlock.AbstractBlockState state = this.getCachedState();
        return this.cache;
    }

    @Override
    public void setStress(int stress) {
        this.stress = stress;
    }

    @Override
    public int getStress() {
        return this.stress;
    }
}
