package ru.enterprise.patterns.descriptors;

import java.lang.reflect.Type;


public class ReferenceDescriptor extends AttributeDescriptor {
    private ReferenceDescriptor(String descriptorName, Type mapperType, Type forType) {
        super(descriptorName, mapperType, forType);
    }
}
