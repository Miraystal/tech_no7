package github.m1raystal.tech_no7.api.forBlock;

import github.m1raystal.tech_no7.api.Machine;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStress;
import net.minecraft.block.entity.BlockEntity;

import java.util.Arrays;

public interface StressMachine extends Machine {
    default void transferStress(BlockEntity[] machines, int stress) {
        Arrays.stream(machines)
                .filter(machine -> machine instanceof MachineWithStress)
                .map(machine -> (MachineWithStress) machine)
                .filter(machine -> machine.getStress() == 0)
                .forEach(machine -> {
                    machine.setStress(stress);
                });
    }

    // has anyone instanceof , return true
    default boolean isConnection(BlockEntity facing, BlockEntity backing) {
        return facing instanceof MachineWithStress || backing instanceof MachineWithStress;
    }
}
