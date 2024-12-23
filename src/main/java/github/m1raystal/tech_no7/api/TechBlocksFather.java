package github.m1raystal.tech_no7.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TechBlocksFather extends BlockWithEntity {
    public static final DirectionProperty SIX_FACING;
    public static final DirectionProperty FOUR_FACING;

    static {
        SIX_FACING = Properties.FACING;
        FOUR_FACING = Properties.HORIZONTAL_FACING;
    }

    protected TechBlocksFather(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
