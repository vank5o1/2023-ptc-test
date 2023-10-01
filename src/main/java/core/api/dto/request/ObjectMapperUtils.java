package core.api.dto.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParser;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

public class ObjectMapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ObjectMapperUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Gson getGsonInstance() {
        return (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").disableHtmlEscaping().create();
    }

    public static <T, K, V> T convertMapToDTOObject(Map<K, V> mapValue, Class<T> clazz) {
        ObjectMapper mapper = getMapperInstance();
        return mapper.convertValue(mapValue, clazz);
    }

    public static <T> T convertResponseToGenericDTOObject(String responseData, TypeReference<T> listTypes) {
        try {
            ObjectMapper mapper = getMapperInstance();
            return mapper.readValue(responseData, listTypes);
        } catch (IOException var4) {
            String className = listTypes.getType().toString();
            return null;
        }
    }

    public static ObjectMapper getMapperInstance() {
        return new ObjectMapper();
    }

    public static <T> T convertJSONStringToDTOClassByObjectMapper(String jsonString, Class<T> clazz) {
        try {
            ObjectMapper mapper = getMapperInstance();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            return mapper.readValue(jsonString, clazz);
        } catch (IOException var4) {
            return (T) var4.getMessage();

        }
    }

    public static <T> T convertJSONStringToDTOClassByGson(String jsonString, Class<T> clazz) {
        Gson builder = getGsonInstance();
        return builder.fromJson(jsonString, clazz);
    }

    public static String convertDTOClassToJSONString(Object dtoObject) {
        try {
            Gson builder = getGsonInstance();
            return builder.toJson(dtoObject);
        } catch (JsonIOException | UnsupportedOperationException | IllegalArgumentException var4) {
            try {
                return getMapperInstance().writerWithDefaultPrettyPrinter().writeValueAsString(dtoObject);
            } catch (JsonProcessingException var3) {
                return var3.getMessage();
            }
        }
    }

    public static String dtoWriteValueAsString(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Throwable var2) {
            return var2.getMessage();
        }
    }

}
