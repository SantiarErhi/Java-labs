package lab7.model;

import java.io.Serializable;
import java.util.Objects;

public class Wood implements Serializable {
    private int id;
    private String name;
    private float density;

    public Wood(int id, String name, float density) {
        this.id = id;
        this.name = name;
        this.density = density;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDensity() {
        return density;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wood)) return false;
        Wood wood = (Wood) o;
        return Float.compare(wood.getDensity(), getDensity()) == 0 && getName().equals(wood.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDensity());
    }

    @Override
    public String toString() {
        return "Wood " +
                "id = " + id +
                ", name = '" + name +
                ", density = " + density;
    }
}
