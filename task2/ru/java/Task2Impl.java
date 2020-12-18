package ru.java;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>Задание №2</h1>
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по времени работы
 * с учетом скорости выполнения операции присвоения номера:
 * большим плюсом (хотя это и не обязательно) будет оценка числа операций, доказательство оптимальности
 * или указание области, в которой алгоритм будет оптимальным.</p>
 */
public class Task2Impl implements IElementNumberAssigner {


    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.


    public static final IElementNumberAssigner INSTANCE = new Task2Impl();

    @Override
    public void assignNumbers2(final List<IElement> elements) {
        IElement[] iElements = elements.toArray(new IElement[]{});
        int max = Integer.MIN_VALUE;
        for (IElement element : iElements) {
            if (element.getNumber() > max) {
                max = element.getNumber();
            }
        }

        for (int index = 0; index < iElements.length; index++) {
            int min = iElements[index].getNumber();
            int indexOfMin = index;

            for (int j = index + 1; j < iElements.length; j++) {
                if (iElements[j].getNumber() < min) {
                    min = iElements[j].getNumber();
                    indexOfMin = j;
                }
            }

            if (index != indexOfMin) {
                int num1 = iElements[index].getNumber();
                iElements[index].setupNumber(max + 1);
                iElements[indexOfMin].setupNumber(num1);
                iElements[index].setupNumber(index);
            } else if (iElements[index].getNumber() != index) {
                iElements[index].setupNumber(index);
            }
        }
    }
}


    /*
    @Override
    public void assignNumbers1(final List<IElement> elements) {

        int i = 0;

        if (elements.isEmpty()) {
            System.out.println("Пустой список");
            return;
        }

        for (IElement elem : elements) {
            IElement cerrentId = elements.get(i++);

            if (i != elem) {
                if (Arrays.asList(iElements).contains(i)) {
                    int number = Arrays.asList(iElements).indexOf(i);
                    elem = iElements[number].getNumber();
                }
                iElements[i].setupNumber(elem);
            }
        }
    }

}
*/













