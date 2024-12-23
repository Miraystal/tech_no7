package github.m1raystal.tech_no7.api.inter;

import github.m1raystal.tech_no7.api.TechBlocksFather;
import github.m1raystal.tech_no7.block.animation_limit.RotateDirection;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface PowerSource {
    boolean shouldPowering(World world, BlockPos pos);

    default void sendPower(MachineWithStress machine, int power) {
        machine.setStress(power);
    }

    default RotateDirection getRotateDirection(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof MachineWithStress) {
            BlockState cachedState = blockEntity.getCachedState();
        }
        return RotateDirection.NONE;
    }

    default BlockState[] getFacingMachines(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);

        if (blockState.getBlock() instanceof TechBlocksFather) {
            Direction facing = blockState.get(TechBlocksFather.SIX_FACING);
            BlockState frontBlockState = world.getBlockState(pos.offset(facing));
            BlockState backBlockState = world.getBlockState(pos.offset(facing.getOpposite()));

            return new BlockState[]{frontBlockState, backBlockState};
        }
        return null;
    }

    default boolean isNearbyWater(World world, BlockPos pos) {
        return BlockPos.stream(pos.add(-1, -1, -1), pos.add(1, 1, 1))
                .anyMatch(neighborPos -> {
                    FluidState fluidState = world.getFluidState(neighborPos);
                    return fluidState.isIn(FluidTags.WATER);
                });
    }
}
