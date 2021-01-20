package ru.enterprise.patterns.descriptors;

import java.lang.reflect.Type;

public class AttributeDescriptor {
    private final String descriptorName;
    private final Type mapperType;
    private final Type forType;

    protected AttributeDescriptor(String descriptorName, Type mapperType, Type forType) {
        this.descriptorName = descriptorName;
        this.mapperType = mapperType;
        this.forType = forType;
    }

    public static String getDescriptorName(String descriptorName, Type mapperType, Type forType) {
        return descriptorName;
    }

}
