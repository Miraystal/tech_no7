package github.m1raystal.tech_no7.block.blocks;

import github.m1raystal.tech_no7.api.forBlock.StressMachine;
import github.m1raystal.tech_no7.api.forBlock.StressMachineBlocksFather;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStress;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStressBlockEntitiesFather;
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
public class GearSmallBlock extends StressMachineBlocksFather implements StressMachine {
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
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

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        BlockEntity theBlockEntity = world.getBlockEntity(pos);
        MachineWithStressBlockEntitiesFather blockEntity;

        if (theBlockEntity instanceof MachineWithStressBlockEntitiesFather)
            blockEntity = (MachineWithStressBlockEntitiesFather) theBlockEntity;
        else return;

        //TODO 编写union 刻不容缓
        BlockEntity[] facingMachines = this.getFacingMachines(world, pos);
        // connection
        if (facingMachines[0] instanceof MachineWithStress theMachine && theMachine.getStress() == 0)
            theMachine.setStress(blockEntity.getStress());
        if (facingMachines[1] instanceof MachineWithStress theMachine && theMachine.getStress() == 0)
            theMachine.setStress(blockEntity.getStress());

        // breaking connection
        boolean isFacingConnected, isBackConnected;
        if (facingMachines[0] instanceof MachineWithStress theMachine) {
            isFacingConnected = true;
            blockEntity.setStress(theMachine.getStress());
        } else isFacingConnected = false;
        if (facingMachines[1] instanceof MachineWithStress theMachine) {
            isBackConnected = true;
            blockEntity.setStress(theMachine.getStress());
        } else isBackConnected = false;
        if (!isFacingConnected && !isBackConnected) blockEntity.setStress(0);

        // call for update
        world.updateListeners(pos, state, state, 0);
    }

}
