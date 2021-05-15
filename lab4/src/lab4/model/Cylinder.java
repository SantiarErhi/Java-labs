package lab4.model;

public class Cylinder extends AbstractForm {
    private float length;
    private float diameter;

    public Cylinder(Wood wood, float length, float diameter) throws Exception {
        super(wood);
        this.length = length;
        this.diameter = diameter;
        if(weight() < 0.1 || weight() > 10) {
            throw new Exception(weight() + " is not correct \n"
                    + "Value must be from 0.1 to 10");
        }

    }

    @Override
    public String toString() {
        return "Cylinder " +
                "wood = " + wood +
                ", length = " + length +
                ", diameter = " + diameter;
    }

    @Override
    public float volume() {
        return (float) (Math.PI * Math.pow(diameter/2, 2) * length);
    }

}
