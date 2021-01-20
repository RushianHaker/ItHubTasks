package ru.enterprise.patterns.mappers;

import ru.enterprise.patterns.descriptors.AttributeDescriptor;
import ru.enterprise.patterns.descriptors.BooleanDescriptor;
import ru.enterprise.patterns.descriptors.DefaultDescriptor;
import ru.enterprise.patterns.descriptors.ReferenceDescriptor;
import ru.enterprise.patterns.domain.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DescriptorMapper {
    protected List<AttributeDescriptor> CreateAttributeDescriptors() {
        List result = new ArrayList<AttributeDescriptor>();

        result.add( DefaultDescriptor.getDescriptorName("remoteId", GetClass(), Integer.TYPE));
        result.add( DefaultDescriptor.getDescriptorName("createdDate", GetClass(), Date.class));
        result.add( DefaultDescriptor.getDescriptorName("lastChangedDate", GetClass(), Date.class));
        result.add( ReferenceDescriptor.getDescriptorName("createdBy", GetClass(), User.class));
        result.add( ReferenceDescriptor.getDescriptorName("lastChangedBy", GetClass(), User.class));
        result.add( DefaultDescriptor.getDescriptorName("optimisticLockVersion", GetClass(), Integer.TYPE));
        return result;
    }

    private Type GetClass() {
        return DescriptorMapper.class;
    }
}
