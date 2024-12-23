package github.m1raystal.tech_no7.block.blocks.powersource;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.TechBlocksFather;
import github.m1raystal.tech_no7.api.inter.MachineWithStress;
import github.m1raystal.tech_no7.api.inter.PowerSource;
import github.m1raystal.tech_no7.block.animation_limit.PowerSourceType;
import github.m1raystal.tech_no7.block.blocks.GearSmallBlock;
import github.m1raystal.tech_no7.block.entity.powersource.WaterWheelBlockEntity;
import github.m1raystal.tech_no7.item.MaterialItem;
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
public class WaterWheelBlock extends TechBlocksFather implements PowerSource {
    private static final PowerSourceType type = PowerSourceType.WATER_POWER;
    private BlockEntity blockEntity;
    private BlockEntity dataBlockEntity;
    private int runCount = 0;

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public BlockEntity getDataBlockEntity() {
        return dataBlockEntity;
    }

    public WaterWheelBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SIX_FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        runCount++;
        BlockEntity currentBlockEntity = new WaterWheelBlockEntity(pos, state);
        if (runCount % 4 == 1 || runCount % 4 == 3) {
            blockEntity = currentBlockEntity;
            //Tech_no7.LOGGER.info("createddddddddddddddddddddddddddddd createBlockEntity :" + blockEntity.toString());
        } else {
            dataBlockEntity = currentBlockEntity;
        }
        if (runCount >= 4) {
            runCount = 0;
        }
        return currentBlockEntity;
        //blockEntity = new WaterWheelBlockEntity(pos, state);
        //Tech_no7.LOGGER.info("createddddddddddddddddddddddddddddd WaterWheelBlockEntity :" + blockEntity.toString());
        //return blockEntity;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
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
            if (Direction.UP.equals(facing) || Direction.DOWN.equals(facing)) return this.getDefaultState();
            return state.with(SIX_FACING, facing);
        }
        return this.getDefaultState();
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return List.of(new ItemStack(MaterialItem.WATER_WHEEL));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        // 两个BlockEntity被创建 第一个控制动画 第二个用来控制数据
        BlockEntity blockEntity = this.getBlockEntity();
        //BlockEntity blockEntity = this.getDataBlockEntity();
        WaterWheelBlockEntity trueBlockEntity;
        if (blockEntity instanceof WaterWheelBlockEntity) trueBlockEntity = (WaterWheelBlockEntity) blockEntity;
        else return;
        if (shouldPowering(world, pos)) trueBlockEntity.setStress(200);
        else trueBlockEntity.setStress(0);

        if (this.getFacingMachines(world, pos)[0].getBlock() instanceof GearSmallBlock) {
            //sendPower(((MachineWithStress) ((GearSmallBlock) this.getFacingMachines(world, pos)[0].getBlock()).getBlockEntity()), 200);
            ((MachineWithStress) ((GearSmallBlock) this.getFacingMachines(world, pos)[0].getBlock()).getBlockEntity()).setStress(200);
            Tech_no7.LOGGER.info("setttttttttttting GearSmallBlock stress to 200" + (((GearSmallBlock) this.getFacingMachines(world, pos)[0].getBlock()).getBlockEntity()).toString());
            Tech_no7.LOGGER.info("Poweredddddddddddddddd GearSmallBlock");
        }
    }

//    @Override
//    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
//        this.createBlockEntity(pos, state);
//    }

    @Override
    public boolean shouldPowering(World world, BlockPos pos) {
        return isNearbyWater(world, pos);
    }
}
