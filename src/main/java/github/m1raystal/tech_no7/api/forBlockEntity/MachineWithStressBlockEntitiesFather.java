package github.m1raystal.tech_no7.api.forBlockEntity;

import github.m1raystal.tech_no7.block.animation_limit.RotateDirection;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;

public abstract class MachineWithStressBlockEntitiesFather extends BlockEntity implements GeoBlockEntity, MachineWithStress {
    private int stress;
    private RotateDirection rotateDirection;

    public MachineWithStressBlockEntitiesFather(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("stress", this.stress);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.stress = nbt.getInt("stress");
    }

    public RotateDirection getRotateDirection() {
        return rotateDirection;
    }

    public void setRotateDirection(RotateDirection rotateDirection) {
        this.rotateDirection = rotateDirection;
    }
}