package org.springframwork.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    List<PropertyValue> propertyValues = new ArrayList<>();

    public PropertyValue[] getPropertyValues() {
        return propertyValues.toArray(new PropertyValue[0]);
    }

    public void addProperty(PropertyValue newPv) {
        for (int i = 0; i < propertyValues.size(); i++) {
            if (propertyValues.get(i).getName().equals(newPv.getName())) {
                propertyValues.set(i, newPv);
                return;
            }
        }
        propertyValues.add(newPv);
    }
}
