package ru.enterprise.patterns.descriptors;

import java.lang.reflect.Type;


public class DefaultDescriptor extends AttributeDescriptor {
    private DefaultDescriptor(String descriptorName, Type mapperType, Type forType) {
        super(descriptorName, mapperType, forType);
    }
}
