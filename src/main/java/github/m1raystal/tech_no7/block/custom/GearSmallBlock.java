package github.m1raystal.tech_no7.block.custom;

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
import org.jetbrains.annotations.Nullable;


public class GearSmallBlock extends BlockWithEntity {
    public static final DirectionProperty FACING;

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
        return switch (dir) {
            case NORTH, SOUTH -> VoxelShapes.cuboid(0.0f, 0.0f, 0.3f, 1.0f, 1.0f, 0.7f);
            case EAST, WEST -> VoxelShapes.cuboid(0.3f, 0.0f, 0.0f, 0.7f, 1.0f, 1.0f);
            case UP, DOWN -> VoxelShapes.cuboid(0.0f, 0.3f, 0.0f, 1.0f, 0.7f, 1.0f);
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
//        BlockState state = super.getPlacementState(ctx);
//        if (state != null) {
//            Direction facing = ctx.getHorizontalPlayerFacing().getOpposite();
//            if (ctx.getPlayerLookDirection().getAxis() == Direction.Axis.Y) {
//                facing = facing.getOpposite();
//            }
//            return state.with(FACING, facing);
//        }
//        return this.getDefaultState();
        BlockState state = super.getPlacementState(ctx);
        if (state != null) {
            Direction facing = ctx.getPlayerLookDirection().getOpposite();
            return state.with(FACING, facing);
        }
        // TODO 向上模型向北偏移 向下模型向南偏移
        return this.getDefaultState();
    }
}
