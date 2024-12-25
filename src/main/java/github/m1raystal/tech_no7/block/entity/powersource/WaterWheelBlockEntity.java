package github.m1raystal.tech_no7.block.entity.powersource;

import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStress;
import github.m1raystal.tech_no7.block.ModBlockEntities;
import github.m1raystal.tech_no7.block.animation_limit.BlockAnimationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WaterWheelBlockEntity extends BlockEntity implements GeoBlockEntity, MachineWithStress {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int stress = 0;

    public WaterWheelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATER_WHEEL, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "work", 1, state -> {
            if (this.stress > 0) {
                return BlockAnimationHandler.setAnimationLimit(state, this).setAndContinue(RawAnimation.begin().thenLoop("work"));
            } else {
                return PlayState.STOP;
            }
        }));
    }

    @Override
    public void setStress(int stress) {
        this.stress = stress;
    }

    @Override
    public int getStress() {
        return this.stress;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("stress", this.stress);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.stress = nbt.getInt("stress");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}
