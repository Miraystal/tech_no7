package github.m1raystal.tech_no7.api;

import github.m1raystal.tech_no7.api.forBlock.StressMachineBlocksFather;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface Machine {
    /*
    The return value should be checked.
     */
    default BlockEntity[] getFacingMachines(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);

        if (blockState.getBlock() instanceof StressMachineBlocksFather) {
            Direction facing = blockState.get(StressMachineBlocksFather.FACING);
            BlockEntity frontBlockEntity = world.getBlockEntity(pos.offset(facing));
            BlockEntity backBlockEntity = world.getBlockEntity(pos.offset(facing.getOpposite()));

            return new BlockEntity[]{frontBlockEntity, backBlockEntity};
        }
        return null;
    }
}
