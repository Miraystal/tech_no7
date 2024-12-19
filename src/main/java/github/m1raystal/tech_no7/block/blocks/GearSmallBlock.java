package github.m1raystal.tech_no7.block.blocks;

import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;


public class GearSmallBlock extends BlockWithEntity {
    private static final DirectionProperty FACING;

    static {
        FACING = Properties.FACING;
    }

    public GearSmallBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GearSmallBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        VoxelShape shape = VoxelShapes.empty();
        switch (dir) {
            case NORTH, SOUTH -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.0f, 0.4f, 1.0f, 1.0f, 0.6f),
                        VoxelShapes.cuboid(0.4f, 0.4f, 0.0f, 0.6f, 0.6f, 1.0f)
                );

            }
            case EAST, WEST -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(0.4f, 0.0f, 0.0f, 0.6f, 1.0f, 1.0f),
                        VoxelShapes.cuboid(0.0f, 0.4f, 0.4f, 1.0f, 0.6f, 0.6f)
                );
            }
            case UP, DOWN -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(0.0f, 0.4f, 0.0f, 1.0f, 0.6f, 1.0f),
                        VoxelShapes.cuboid(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f)
                );
            }
            default -> shape = VoxelShapes.fullCube();
        }
        return shape;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state != null) {
            Direction facing = ctx.getPlayerLookDirection().getOpposite();
            return state.with(FACING, facing);
        }
        return this.getDefaultState();
    }
}
