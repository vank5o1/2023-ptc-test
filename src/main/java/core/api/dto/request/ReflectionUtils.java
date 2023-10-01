package core.api.dto.request;

import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class ReflectionUtils {

    private ReflectionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Class<?>> getAllClasses(Object clazz) {
        List<Class<?>> classList = new ArrayList();
        Class<?> currentClazz = clazz.getClass();
        Class<?> superclass = currentClazz.getSuperclass();
        classList.add(currentClazz);
        if (isObjectClass(superclass)) {
            return classList;
        } else {
            classList.add(superclass);
            addParentClass(superclass, classList);
            return classList;
        }
    }

    public static List<Field> getAllFields(List<Class<?>> classes) {
        List<Field> fields = new ArrayList();
        Iterator var2 = classes.iterator();

        while(var2.hasNext()) {
            Class<?> classType = (Class)var2.next();
            Field[] allFields = classType.getDeclaredFields();
            Field[] var5 = allFields;
            int var6 = allFields.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                SerializedName serializedName = (SerializedName)field.getAnnotation(SerializedName.class);
                if (serializedName != null) {
                    field.setAccessible(true);
                    fields.add(field);
                    fields.addAll(Arrays.asList(allFields));
                }
            }
        }

        return fields;
    }

    private static void addParentClass(Class<?> superclass, List<Class<?>> classList) {
        while(true) {
            if (Boolean.FALSE.equals(isObjectClass(superclass))) {
                superclass = superclass.getSuperclass();
                if (!isObjectClass(superclass)) {
                    classList.add(superclass);
                    continue;
                }
            }

            return;
        }
    }

    private static boolean isObjectClass(Class<?> classType) {
        return classType.getSimpleName().equalsIgnoreCase("Object");
    }

    public static Map<String, Object> serializeObjectToMap(Object instance) {
        try {
            Map<String, Object> fieldMap = new HashMap();
            List<Field> listFields = findAllSerializeFieldInClass(instance);
            Set<Field> setFields = new HashSet(listFields);
            Iterator var4 = setFields.iterator();

            while(var4.hasNext()) {
                Field field = (Field)var4.next();
                field.setAccessible(true);
                Object fieldValue = field.get(instance);
                if (fieldValue != null) {
                    convertObjectFieldToJsonMap(fieldValue, field, fieldMap);
                }
            }

            return fieldMap;
        } catch (NullPointerException | IllegalAccessException var7) {
            return null;
        }
    }

    private static void convertObjectFieldToJsonMap(Object fieldValue, Field field, Map<String, Object> fieldMap) {
        List<Object> fieldList = new ArrayList();
        SerializedName serializedName = (SerializedName)field.getAnnotation(SerializedName.class);
        boolean isListType = fieldValue instanceof List;
        boolean ignoreField = serializedName == null;
        if (!ignoreField) {
            validateDuplicateKeyName(fieldMap, serializedName.value());
            if (isListType) {
                addListObjectToJsonMap(fieldValue, serializedName, fieldList, fieldMap);
            } else if (isPrimitiveObject(fieldValue)) {
                fieldMap.put(serializedName.value(), fieldValue);
            } else if (fieldValue.getClass().isEnum()) {
                fieldMap.put(serializedName.value(), fieldValue);
            } else {
                fieldMap.put(serializedName.value(), serializeObjectToMap(fieldValue));
            }

        }
    }

    private static void addListObjectToJsonMap(Object value, SerializedName serializedName, List<Object> fieldList, Map<String, Object> fieldMap) {
        List<Object> values = (List)value;
        Iterator var5 = values.iterator();

        while(var5.hasNext()) {
            Object v = var5.next();
            if (isPrimitiveObject(v)) {
                fieldList.add(v);
            } else {
                fieldList.add(serializeObjectToMap(v));
            }
        }

        fieldMap.put(serializedName.value(), fieldList);
    }

    private static List<Field> findAllSerializeFieldInClass(Object fieldValue) {
        List<Class<?>> classes = getAllClasses(fieldValue);
        return getAllFields(classes);
    }

    private static boolean isPrimitiveObject(Object value) {
        Class<?> clazz = value.getClass();
        return clazz.equals(Boolean.class) || clazz.equals(Integer.class) || clazz.equals(Character.class) || clazz.equals(Byte.class) || clazz.equals(Short.class) || clazz.equals(Double.class) || clazz.equals(Long.class) || clazz.equals(Float.class) || clazz.equals(String.class);
    }

    private static void validateDuplicateKeyName(Map<String, Object> fieldMap, String keyName) {
        if (fieldMap.containsKey(keyName)) {

        }
    }


}
