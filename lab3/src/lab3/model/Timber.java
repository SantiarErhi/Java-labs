package lab3.model;

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
        return "Timber{" +
                "wood=" + wood +
                ", length=" + length +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public Timber(Wood wood, float length, float height, float width) {
        super(wood);
        this.length = length;
        this.height = height;
        this.width = width;
    }
}
