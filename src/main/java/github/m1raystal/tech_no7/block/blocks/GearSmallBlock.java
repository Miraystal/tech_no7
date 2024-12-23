package github.m1raystal.tech_no7.block.blocks;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.TechBlocksFather;
import github.m1raystal.tech_no7.block.entity.GearSmallBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class GearSmallBlock extends TechBlocksFather {
    private BlockEntity blockEntity;
    private int runCount = 0;

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public GearSmallBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SIX_FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        runCount++;
        BlockEntity currentBlockEntity = new GearSmallBlockEntity(pos, state);
        if (runCount % 4 == 1 || runCount % 4 == 3) {
            blockEntity = currentBlockEntity;
            Tech_no7.LOGGER.info("createddddddddddddddddddddddddddddd createBlockEntity :" + blockEntity.toString());
        }
        if (runCount >= 4) {
            runCount = 0;
        }
        return currentBlockEntity;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(SIX_FACING);
        VoxelShape shape;
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
        builder.add(SIX_FACING);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state != null) {
            Direction facing = ctx.getPlayerLookDirection().getOpposite();
            return state.with(SIX_FACING, facing);
        }
        return this.getDefaultState();
    }

    //@Override
    //public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    //this.createBlockEntity(pos, state);
    //}

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.syncWorldEvent(player, 2001, pos, getRawIdFromState(Blocks.STONE.getDefaultState()));
        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }
        world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, state));
    }
}
