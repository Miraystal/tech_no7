package github.m1raystal.tech_no7.block.blocks;

import github.m1raystal.tech_no7.block.entity.GearBigBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class GearBigBlock extends GearSmallBlock {
    private static final DirectionProperty FACING;

    static {
        FACING = Properties.FACING;
    }

    public GearBigBlock(Settings settings) {
        super(settings);
        super.setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GearBigBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        VoxelShape shape = VoxelShapes.empty();
        switch (dir) {
            case NORTH, SOUTH -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(-0.5f, -0.5f, 0.4f, 1.5f, 1.5f, 0.6f),
                        VoxelShapes.cuboid(0.4f, 0.4f, 0.0f, 0.6f, 0.6f, 1.0f)
                );

            }
            case EAST, WEST -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(0.4f, -0.5f, -0.5f, 0.6f, 1.5f, 1.5f),
                        VoxelShapes.cuboid(0.0f, 0.4f, 0.4f, 1.0f, 0.6f, 0.6f)
                );
            }
            case UP, DOWN -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(-0.5f, 0.4f, -0.5f, 1.5f, 0.6f, 1.5f),
                        VoxelShapes.cuboid(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f)
                );
            }
            default -> shape = VoxelShapes.fullCube();
        }
        return shape;
    }
}
