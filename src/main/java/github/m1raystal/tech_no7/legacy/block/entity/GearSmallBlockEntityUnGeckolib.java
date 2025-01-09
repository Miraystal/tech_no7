package github.m1raystal.tech_no7.legacy.block.entity;

import github.m1raystal.tech_no7.TechBlockEntities;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStress;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class GearSmallBlockEntityUnGeckolib extends BlockEntity implements MachineWithStress {
    private int stress = 0;

    public GearSmallBlockEntityUnGeckolib(BlockPos pos, BlockState state) {
        super(TechBlockEntities.GEAR_SMALL_UN_GECKOLIB, pos, state);
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
