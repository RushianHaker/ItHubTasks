package ru.java;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

        Task2Impl.INSTANCE.assignNumbers2(elements);

        elements.stream().forEach(e -> {
            System.out.println(e.getId() + " - " + e.getNumber());
        });
    }
}
