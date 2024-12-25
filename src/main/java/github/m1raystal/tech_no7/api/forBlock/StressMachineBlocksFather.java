package github.m1raystal.tech_no7.api.forBlock;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;

public abstract class StressMachineBlocksFather extends BlockWithEntity {
    public static final DirectionProperty FACING;

    static {
        FACING = Properties.FACING;
    }

    protected StressMachineBlocksFather(Settings settings) {
        super(settings);
    }
}
