package lab5.model;

public class Waste implements IWeight{
    private float weight;

    public Waste(float weight) throws Exception {
        if(weight < 0 || weight > 5){
            throw new Exception(weight + " is not correct \n"
                    + "Value must be from 0 to 5");
        }
        this.weight = weight;
    }

    @Override
    public float weight() {
        return weight;
    }
    @Override
    public String toString() {
        return "Waste " +
                "weight = " + weight;
    }
}
