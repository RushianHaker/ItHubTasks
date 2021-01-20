package ru.enterprise.patterns.descriptors;

import java.lang.reflect.Type;


public class BooleanDescriptor extends AttributeDescriptor {
    private BooleanDescriptor(String descriptorName, Type mapperType, Type forType) {
        super(descriptorName, mapperType, forType);
    }
}
