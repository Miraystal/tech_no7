package github.m1raystal.tech_no7.block.animation_limit;

public enum RotateDirection {
    LEFT(1), RIGHT(2), NONE(0);
    private int value;

    RotateDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
