package github.m1raystal.tech_no7.block.blocks.powersource;

import github.m1raystal.tech_no7.TechItems;
import github.m1raystal.tech_no7.api.forBlock.PowerSource;
import github.m1raystal.tech_no7.api.forBlock.StressMachineBlocksFather;
import github.m1raystal.tech_no7.block.animation_limit.PowerSourceType;
import github.m1raystal.tech_no7.block.entity.powersource.WaterWheelBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
public class WaterWheelBlock extends StressMachineBlocksFather implements PowerSource {
    private static final PowerSourceType type = PowerSourceType.WATER_POWER;

    public WaterWheelBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WaterWheelBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
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
            if (Direction.UP.equals(facing) || Direction.DOWN.equals(facing)) return this.getDefaultState();
            return state.with(FACING, facing);
        }
        return this.getDefaultState();
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return List.of(new ItemStack(TechItems.WATER_WHEEL));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        BlockEntity theBlockEntity = world.getBlockEntity(pos);
        WaterWheelBlockEntity blockEntity;

        if (theBlockEntity instanceof WaterWheelBlockEntity)
            blockEntity = (WaterWheelBlockEntity) theBlockEntity;
        else return;

        if (shouldPowering(world, pos)) blockEntity.setStress(200);
        else blockEntity.setStress(0);

        sendPower(this.getFacingMachines(world, pos), world, pos, blockEntity.getStress());

        // call for update
        world.updateListeners(pos, state, state, 0);
    }

    @Override
    public boolean shouldPowering(World world, BlockPos pos) {
        return isNearbyWater(world, pos);
    }
}
