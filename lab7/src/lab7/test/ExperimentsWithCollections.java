package lab7.test;

import java.util.*;

public class ExperimentsWithCollections {
    public static void main(String[] args) {
        experiment0();
        experiment1();
        experiment2();
        experiment3();
        experiment4();
    }

    public static void experiment0() {
        Random random = new Random();
        Collection<Integer> c1 = new Vector<Integer>();
        for (int i = 0; i < 15; i++) {
            c1.add(random.nextInt(10));
        }
        System.out.println("Collection vector");
        for (Integer x : c1) {
            System.out.printf("%d ", x);
        }
        Collection<Integer> c2 = new TreeSet<Integer>();
        c2.addAll(c1);
        System.out.println("\nCollection TreeSet");
        c2.forEach((x) -> System.out.printf("%d ", x));
    }

    public static void experiment1() {
        Random random = new Random();
        Collection<Integer> c1 = new ArrayList<>();
        Collection<Integer> c2 = new LinkedHashSet<>();
        Collection<Integer> c3 = new Vector<Integer>();
        for (int i = 0; i < 10; i++) {
            c1.add(random.nextInt(10));
            c2.add(random.nextInt(10));

        }
        c3.addAll(c1);
        c3.removeAll(c2);
        System.out.println(c1 + " removeAll " + c2 + " = " + c3);
        c3.clear();
        c3.addAll(c2);
        c3.removeAll(c1);
        System.out.println(c2 + " removeAll " + c1 + " = " + c3);
        c3.clear();
        c3.addAll(c1);
        c3.retainAll(c2);
        System.out.println(c1 + " retainAll " + c2 + " = " + c3);
        c3.clear();
        c3.addAll(c2);
        c3.retainAll(c1);
        System.out.println(c2 + " retainAll " + c1 + " = " + c3);
    }

    public static void experiment2() {
        Random random = new Random();
        Collection<Integer> c1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c1.add(random.nextInt(10));
        }
        Collection<Integer> c2 = new LinkedHashSet<>();
        c2.addAll(c1);
        boolean b1 = c1.containsAll(c2);
        System.out.println(c1 + " containsAll " + c2 + " = " + b1);
        Collection<Integer> c3 = new TreeSet<>();
        c3.addAll(c1);
        boolean b2 = c1.containsAll(c3);
        System.out.println(c1 + " containsAll " + c3 + " = " + b2);
    }

    public static void experiment3() {
        Random random = new Random();
        List<Integer> c1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c1.add(random.nextInt(10));
        }
        System.out.println("Before sort:\n" + c1);
        c1.sort((x, y) -> x - y);
        System.out.println("After sort:\n" + c1);
    }

    public static void experiment4() {
        Random random = new Random();
        Collection<Integer> coll = new ArrayDeque<Integer>();
        Collections.addAll(coll, 1, 3, 5, 3, 4, 2, 14);
        Collections.addAll(coll, new Integer[]{3, 7, 12});
        System.out.println(coll);
        System.out.println(Collections.frequency(coll, 3));
        System.out.println(Collections.frequency(coll, 3));
        System.out.println(Collections.max(coll));
        System.out.println(Collections.min(coll));

    }
}
