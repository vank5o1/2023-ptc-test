package core.api.dto.request;

import com.google.api.client.util.ArrayMap;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class RequestHelper {


    private RequestHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertDTOObjectToJSONString(Object instance) {
        try {
            Map<String, Object> jsonMap = ReflectionUtils.serializeObjectToMap(instance);
            return ObjectMapperUtils.dtoWriteValueAsString(jsonMap);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static List<Field> getAllFieldsHaveValue(Request instance)
            throws IllegalArgumentException, IllegalAccessException {
        List<Class<?>> classes = ReflectionUtils.getAllClasses(instance);
        List<Field> allFields = ReflectionUtils.getAllFields(classes);
        List<Field> allFieldsWithAccessible = new ArrayList<>();
        for (Field field : allFields) {
            field.setAccessible(true);
            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            if (field.get(instance) != null && serializedName != null)
                allFieldsWithAccessible.add(field);
        }
        return allFieldsWithAccessible;
    }

    public static List<Field> getAllFields(Request instance) {
        List<Class<?>> classes = ReflectionUtils.getAllClasses(instance);
        List<Field> allFields = ReflectionUtils.getAllFields(classes);
        List<Field> allFieldsWithAccessible = new ArrayList<>();
        for (Field field : allFields) {
            field.setAccessible(true);
            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            if (serializedName != null) allFieldsWithAccessible.add(field);
        }
        return allFieldsWithAccessible;
    }

    public static Map<String, Object> getDefaultRequestParams(Request instance) {
        try {
            Map<String, Object> requestParams = new ArrayMap<>();
            for (Field field : getAllFieldsHaveValue(instance)) {
                SerializedName serializedName = field.getAnnotation(SerializedName.class);
                requestParams.put(serializedName.value(), field.get(instance));
            }
            return requestParams;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return null;
        }
    }
}
