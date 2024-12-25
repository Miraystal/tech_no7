package github.m1raystal.tech_no7.api.forBlock;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.Machine;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStress;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Arrays;

public interface PowerSource extends Machine {
    boolean shouldPowering(World world, BlockPos pos);

    default void sendPower(BlockEntity[] machines, World world, BlockPos pos,int stress) {
        Arrays.stream(machines)
                .filter(machine -> machine instanceof MachineWithStress)
                .map(machine -> (MachineWithStress) machine)
                .forEach(machine -> {
                    machine.setStress(stress);
                });
    }

    default boolean isNearbyWater(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof StressMachineBlocksFather) {
            Direction direction = blockState.get(StressMachineBlocksFather.FACING);
            if (direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH)) {
                return BlockPos.stream(pos.add(-1, -1, 0), pos.add(1, 1, 0))
                        .anyMatch(neighborPos -> {
                            FluidState fluidState = world.getFluidState(neighborPos);
                            return fluidState.isIn(FluidTags.WATER);
                        });
            } else if (direction.equals(Direction.EAST) || direction.equals(Direction.WEST)) {
                return BlockPos.stream(pos.add(0, -1, -1), pos.add(0, 1, 1))
                        .anyMatch(neighborPos -> {
                            FluidState fluidState = world.getFluidState(neighborPos);
                            return fluidState.isIn(FluidTags.WATER);
                        });
            }
        }
        return false;
    }
}
