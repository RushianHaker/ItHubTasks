package ru.java;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main.version1();

        System.out.println("-----");

        Main.version2();
    }

    public static void version1() {
        ElementExampleImpl.Context context = new ElementExampleImpl.Context();

        List<IElement> elements = Arrays.asList(
                new ElementExampleImpl(context, 11),
                new ElementExampleImpl(context, 2),
                new ElementExampleImpl(context, 5),
                new ElementExampleImpl(context, 4),
                new ElementExampleImpl(context, 15),
                new ElementExampleImpl(context, 6),
                new ElementExampleImpl(context, 1),
                new ElementExampleImpl(context, 0)
        );

        Task2Impl.INSTANCE.assignNumbers1(elements);
        elements.stream().forEach(e -> {
            System.out.println(e.getId() + " - " + e.getNumber());
        });
    }

    public static void version2() {
        ElementExampleImpl.Context context = new ElementExampleImpl.Context();

        List<IElement> elements = Arrays.asList(
                new ElementExampleImpl(context, 0),
                new ElementExampleImpl(context, 71),
                new ElementExampleImpl(context, 62),
                new ElementExampleImpl(context, 5),
                new ElementExampleImpl(context, 14),
                new ElementExampleImpl(context, 13),
                new ElementExampleImpl(context, 2),
                new ElementExampleImpl(context, 11)
        );

        Task2Impl.INSTANCE.assignNumbers2(elements);

        elements.forEach(e->System.out.println(e.getId() + e.getNumber()));
    }

}
