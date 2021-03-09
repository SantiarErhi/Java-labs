package lab2.model;

public class Cylinder extends AbstractForm {
    private float length;
    private float diameter;

    public Cylinder(Wood wood, float length, float diameter) {
        super(wood);
        this.length = length;
        this.diameter = diameter;
    }

    @Override
    public float volume() {
        return (float) (Math.PI * length * diameter);
    }

}
