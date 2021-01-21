package ru.enterprise.patterns;

public abstract class AbsBuiderTest extends TestCase{
    protected OutputBuilder builder;

    public abstract OutputBuilder createBuilder(String root);

}
