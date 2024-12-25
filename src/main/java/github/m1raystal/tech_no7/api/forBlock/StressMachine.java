package github.m1raystal.tech_no7.api.forBlock;

import github.m1raystal.tech_no7.Tech_no7;
import github.m1raystal.tech_no7.api.Machine;
import github.m1raystal.tech_no7.api.forBlockEntity.MachineWithStress;
import net.minecraft.block.entity.BlockEntity;

import java.util.Arrays;

public interface StressMachine extends Machine {
    default void transferStress(BlockEntity[] machines, int stress, double currentTick) {
        Arrays.stream(machines)
                .filter(machine -> machine instanceof MachineWithStress)
                .map(machine -> (MachineWithStress) machine)
                .filter(machine -> machine.getStress() == 0)
                .forEach(machine -> {
                    //machine.setCurrentTick(currentTick);
                    machine.setStress(stress);
                });
    }

    // has anyone instanceof , return true
    default boolean isBrokenConnection(BlockEntity facing, BlockEntity backing) {
        return facing instanceof MachineWithStress || backing instanceof MachineWithStress;
    }

    // TODO add Direction Setter
//    default void setCurrentTick(MachineWithStress machine, MachineWithStress sourceMachine) {
//        machine.setCurrentTick(sourceMachine.getCurrentTick());
//    }
}
