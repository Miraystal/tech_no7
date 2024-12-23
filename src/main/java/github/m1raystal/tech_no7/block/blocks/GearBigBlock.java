package github.m1raystal.tech_no7.block.blocks;

import github.m1raystal.tech_no7.block.entity.GearBigBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GearBigBlock extends GearSmallBlock {
    private BlockEntity blockEntity;

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public GearBigBlock(Settings settings) {
        super(settings);
        super.setDefaultState(getDefaultState().with(SIX_FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        blockEntity = new GearBigBlockEntity(pos, state);
        return blockEntity;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(SIX_FACING);
        VoxelShape shape = VoxelShapes.empty();
        switch (dir) {
            case NORTH, SOUTH -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(-0.3f, -0.3f, 0.4f, 1.3f, 1.3f, 0.6f),
                        VoxelShapes.cuboid(0.4f, 0.4f, 0.0f, 0.6f, 0.6f, 1.0f)
                );

            }
            case EAST, WEST -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(0.4f, -0.3f, -0.3f, 0.6f, 1.3f, 1.3f),
                        VoxelShapes.cuboid(0.0f, 0.4f, 0.4f, 1.0f, 0.6f, 0.6f)
                );
            }
            case UP, DOWN -> {
                shape = VoxelShapes.union(
                        VoxelShapes.cuboid(-0.3f, 0.4f, -0.3f, 1.3f, 0.6f, 1.3f),
                        VoxelShapes.cuboid(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f)
                );
            }
            default -> shape = VoxelShapes.fullCube();
        }
        return shape;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.syncWorldEvent(player, 2001, pos, getRawIdFromState(Blocks.IRON_BLOCK.getDefaultState()));
        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }
        world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, state));
    }

}
