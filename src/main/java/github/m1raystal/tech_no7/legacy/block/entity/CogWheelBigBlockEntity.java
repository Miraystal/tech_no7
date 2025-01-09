package github.m1raystal.tech_no7.legacy.block.entity;

import github.m1raystal.tech_no7.TechBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CogWheelBigBlockEntity extends BlockEntity {
    public CogWheelBigBlockEntity(BlockPos pos, BlockState state) {
        super(TechBlockEntities.COG_WHEEL_BIG, pos, state);
    }
}
