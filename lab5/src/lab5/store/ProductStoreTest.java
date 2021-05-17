package lab5.store;

import lab5.model.Cylinder;
import lab5.model.IWeight;
import lab5.model.Timber;
import lab5.model.Waste;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ProductStoreTest {
    private WoodDirectory wd = new WoodDirectory();


    @Test
    @DisplayName("Correct removing test")
    public void remove() {
        ProductStore ps1 = new ProductStore();
        try {
            ps1.add(new Timber(wd.get(0), 5f, 0.5f, 0.4f));
            ps1.add(new Timber(wd.get(1), 10f, 0.5f, 0.4f));
            ps1.add(new Cylinder(wd.get(1), 1f, 2f));
            ps1.add(new Waste(5));
            ps1.add(new Waste(4));
            ps1.add(new Waste(3));
            ps1.add(new Waste(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProductStore ps2 = new ProductStore();
        try {
            ps2.add(new Timber(wd.get(0), 5f, 0.5f, 0.4f));
            ps2.add(new Timber(wd.get(1), 10f, 0.5f, 0.4f));
            ps2.add(new Cylinder(wd.get(1), 1f, 2f));
            ps2.add(new Waste(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        float maxWeight = 2;
        ps1.remove((o) -> o instanceof Waste && ((IWeight) o).weight() > maxWeight);
        assertEquals(ps2.count, ps1.count);
        assertEquals(ps1.toString(), ps2.toString());
    }

    @Test
    @DisplayName("doForAll test")
    public void doForAll() {
        ProductStore ps1 = new ProductStore();
        StringBuilder res = new StringBuilder();
        try {
            ps1.add(new Timber(wd.get(0), 1, 1, 1));
            ps1.add(new Timber(wd.get(0), 1, 1, 1));
            ps1.add(new Timber(wd.get(0), 1, 1, 1));
            ps1.add(new Timber(wd.get(0), 1, 1, 1));
            ps1.doForAll((o) -> res.append(o.toString()).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(res.toString(), ps1.toString());
    }

    @Test
    public void doOnlyFor() {
        ProductStore ps1 = new ProductStore();
        ProductStore ps2 = new ProductStore();

        StringBuilder res = new StringBuilder();
        try {
            ps1.add(new Timber(wd.get(0), 1, 1, 1));
            ps1.add(new Timber(wd.get(0), 2, 1, 1));
            ps1.add(new Timber(wd.get(0), 1, 1, 1));
            ps1.add(new Timber(wd.get(0), 1, 1, 2));

            ps2.add(new Timber(wd.get(0), 2, 1, 1));
            ps2.add(new Timber(wd.get(0), 1, 1, 2));

            ps1.doOnlyFor(o -> ((IWeight)o).weight() > 2, o -> res.append(o.toString()).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(res.toString(), ps2.toString());

    }
}