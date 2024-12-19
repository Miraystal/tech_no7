package github.m1raystal.tech_no7.block.animation_limit;

import github.m1raystal.tech_no7.api.MachineWithStress;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;

public class GearAnimationLimit {
    public static <T extends GeoAnimatable> AnimationState<T> setAnimationLimit(AnimationState<T> tAnimationState, MachineWithStress machine) {
        return setAnimationSpeed(tAnimationState, machine);
    }


    private static <T extends GeoAnimatable> AnimationState<T> setAnimationSpeed(AnimationState<T> tAnimationState, MachineWithStress stress) {
        int theStress = stress.getStress();

        float speed = theStress > 1250 && theStress <= 1500 ? 3.5f :
                theStress > 1000 && theStress <= 1250 ? 3.0f :
                        theStress > 750 && theStress <= 1000 ? 2.5f :
                                theStress > 500 && theStress <= 750 ? 2.0f :
                                        theStress > 250 && theStress <= 500 ? 1.5f : 1.0f;

        tAnimationState.getController().setAnimationSpeed(speed);
        return tAnimationState;
    }
}
