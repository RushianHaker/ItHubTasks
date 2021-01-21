package ru.enterprise.patterns;

/*
 * @created 19/01/2021 - 0:10
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */

/*
    Создать единственную версию метода в родительском классе,
    которая вызывает метод фабрики для создания обьектов.
 */
public class OutputBuilder {

    public void AddBelow(String v){


    }

    public void AddBul(String v) throws RuntimeException {

        throw new RuntimeException("Some Exception");
    }
}
