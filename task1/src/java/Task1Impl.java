package src.java;

import java.util.*;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        rows.sort(Comparator.comparing(line -> line[columnIndex], this::comparingColumns));
    }

    private int comparingColumns(String clmn1, String clmn2) {
        if (clmn1 == null || clmn2 == null)
            return clmn1 == null ? (clmn2 == null ? 0 : -1) : 1;

        if (clmn1.isEmpty() || clmn2.isEmpty())
            return clmn1.isEmpty() ? (clmn2.isEmpty() ? 0 : -1) : 1;


        Object[] typeKey1, typeKey2;

        typeKey1 = comparingTypeKey(clmn1);
        typeKey2 = comparingTypeKey(clmn2);

        int clmnLength1, clmnLength2;

        clmnLength1 = typeKey1.length;
        clmnLength2 = typeKey2.length;

        int limitLength = Math.min(clmnLength1, clmnLength2);

        int num = 0;
        while (num < limitLength) {
            int result = lineOrNumber(typeKey1[num], typeKey2[num]);

            if (result != 0)
                return result;
            num++;
        }
        return clmnLength1 - clmnLength2;
    }


    private int lineOrNumber(Object a, Object b) {
        if (a instanceof Long && b instanceof Long)
            return Long.compare((long) a, (long) b);
        return a.toString().compareTo(b.toString());
    }


    private Object[] comparingTypeKey(String value) {
        ArrayList<Object> keys = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        int keyType = -1;

        /**
         * @param keyType - переменная, которая хранит в себе данные о типе ключа = 0 - int, а  1 - other.
         */

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            int contentType = c >= '0' && c <= '9' ? 0 : 1;

            if (contentType != keyType) {
                if (stringBuilder.length() > 0) {
                    keys.add(parsingMethod(stringBuilder.toString(), keyType));
                    stringBuilder.delete(0, stringBuilder.length());
                }
                keyType = contentType;
            }
            stringBuilder.append(c);
        }
        if (stringBuilder.length() > 0) {
            keys.add(parsingMethod(stringBuilder.toString(), keyType));
        }

        return keys.toArray();
    }

    private Object parsingMethod(String line, int keyType) {
        return keyType == 0 ? Long.parseLong(line) : line;
    }
}
