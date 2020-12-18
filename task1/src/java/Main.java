package src.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String[]> lines = Arrays.asList(
                new String[]{"144", "9920", "00", ""},
                new String[]{null, "22gd", "hello"},
                new String[]{"", "34g", "", null}
        );

        Task1Impl.INSTANCE.sort(lines,0);

        /*lines.stream().forEach(e -> {
            Stream.of(e).forEach(System.out::println);
            System.out.println();
        });*/

    }
}
