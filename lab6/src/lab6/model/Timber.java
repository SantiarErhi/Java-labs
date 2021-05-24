package lab6.model;

public class Timber extends AbstractForm {
    private float length;
    private float height;
    private float width;

    @Override
    public float volume(){
        return length * height * width;
    }

    public Wood getWood() {
        return wood;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "Timber " +
                "wood = " + wood +
                ", length = " + length +
                ", height = " + height +
                ", width = " + width;
    }

    public Timber(Wood wood, float length, float height, float width) throws Exception {
        super(wood);
        this.length = length;
        this.height = height;
        this.width = width;
        if(weight() < 0.1 || weight() > 10) {
            throw new Exception(weight() + " is not correct \n"
                    + "Value must be from 0.1 to 10");
        }
    }
}
