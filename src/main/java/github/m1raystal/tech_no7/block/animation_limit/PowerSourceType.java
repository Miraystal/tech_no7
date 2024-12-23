package github.m1raystal.tech_no7.block.animation_limit;

public enum PowerSourceType {
    WATER_POWER(0), ELEC_MOTOR(1), FUEL_ENGINE(2);
    private int value;

    PowerSourceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
