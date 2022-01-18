package ua.com.alevel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class JsonConverter {
    private JsonConverter() {
    }

    public static List<? extends Object> listOfObjectsFromJson(String jsonString, Class className) {
        List<Object> listOfObject = new ArrayList<>();
        jsonString = jsonString.replace("[", "");
        jsonString = jsonString.replace("]", "");
        jsonString = jsonString.replace("},  {", "},{");
        String[] arrayOfObjectsLikeString = jsonString.split("},\\{");
        for (String s : arrayOfObjectsLikeString) {
            listOfObject.add(parseObject(s, className));
        }
        return listOfObject;
    }

    private static Object parseObject(String jsonString, Class<?> className) {
        Map<String, String> fieldsOfObject = new HashMap<>();
        int firstIndex;
        int lastIndex = 0;
        String key;
        String value;
        while (true) {
            firstIndex = jsonString.indexOf("\"", lastIndex);
            if (firstIndex == -1) {
                break;
            }
            lastIndex = jsonString.indexOf(":", firstIndex);
            key = jsonString.substring(firstIndex + 1, lastIndex - 1);
            if (jsonString.indexOf("{", lastIndex) == -1) {
                firstIndex = jsonString.indexOf("\"", lastIndex);
                lastIndex = jsonString.indexOf(",", firstIndex);
                if (lastIndex == -1) {
                    lastIndex = jsonString.indexOf("\"", firstIndex + 1) + 1;
                }
                value = jsonString.substring(firstIndex + 1, lastIndex - 1);
                fieldsOfObject.put(key, value);
            } else if (jsonString.indexOf("{", lastIndex) > jsonString.indexOf("\"", lastIndex)) {
                firstIndex = jsonString.indexOf("\"", lastIndex);
                lastIndex = jsonString.indexOf(",", firstIndex);
                if (lastIndex == -1) {
                    lastIndex = jsonString.indexOf("\"", firstIndex + 1) + 1;
                }
                value = jsonString.substring(firstIndex + 1, lastIndex - 1);
                fieldsOfObject.put(key, value);
            } else {
                firstIndex = jsonString.indexOf("{", lastIndex);
                lastIndex = jsonString.indexOf("}", firstIndex);
                value = jsonString.substring(firstIndex, lastIndex + 1);
                fieldsOfObject.put(key, value);
            }

            if (lastIndex == jsonString.length()) {
                break;
            }
        }
        Object newObject = null;
        try {
            newObject = className.getConstructor().newInstance();
            Field[] fieldsSuper = newObject.getClass().getSuperclass().getDeclaredFields();
            for (Field field : fieldsSuper) {
                if (!field.canAccess(newObject)) {
                    field.setAccessible(true);
                }
                if (field.getType().getSimpleName().equals("String")) {
                    field.set(newObject, fieldsOfObject.get(field.getName()).replace("}", "").trim());
                }
            }
            Field[] fieldsClass = newObject.getClass().getDeclaredFields();
            for (Field aClass : fieldsClass) {
                if (!aClass.canAccess(newObject)) {
                    aClass.setAccessible(true);
                }
                if (aClass.getType().getSimpleName().equals("String")) {
                    aClass.set(newObject, fieldsOfObject.get(aClass.getName()).replace("}", "").trim());
                } else {
                    Object object = parseObject(fieldsOfObject.get(aClass.getName()), aClass.getType());
                    aClass.set(newObject, object);
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    public static String toJson(List<?> listOfObjects) {
        return String.valueOf(convertListToJson(listOfObjects));
    }

    private static StringBuilder convertListToJson(List<?> listOfObjects) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n[\n");
        int size = listOfObjects.size();
        for (int i = 0; i < size; i++) {
            builder.append(convertObjectToJson(listOfObjects.get(i)));
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("\n]\n");
        return builder;
    }

    private static StringBuilder convertObjectToJson(Object object) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        try {
            if (!object.getClass().getSuperclass().equals(Object.class)) {
                Field[] superFields = object.getClass().getSuperclass().getDeclaredFields();
                for (Field superField : superFields) {
                    builder.append("\"")
                            .append(superField.getName())
                            .append("\"")
                            .append(":");
                    if (!superField.canAccess(object)) {
                        superField.setAccessible(true);
                    }
                    builder.append(getFieldType(superField.get(object)));
                    builder.append(",");
                }
            }
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                builder.append("\"")
                        .append(fields[i].getName())
                        .append("\"")
                        .append(":");
                if (!fields[i].canAccess(object)) {
                    fields[i].setAccessible(true);
                }
                builder.append(getFieldType(fields[i].get(object)));
                if (i != fields.length - 1) {
                    builder.append(",");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        builder.append("}");
        return builder;
    }

    private static StringBuilder getFieldType(Object obj) {
        StringBuilder result = new StringBuilder();
        if (obj instanceof String) {
            result.append("\"")
                    .append(obj)
                    .append("\"");
        } else {
            result.append(convertObjectToJson(obj));
        }
        return result;
    }
}