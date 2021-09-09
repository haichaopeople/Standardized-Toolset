package morn.library.boot.json.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import morn.library.bean.annotation.Tag;
import morn.library.boot.json.JsonParser;
import morn.library.boot.json.constant.JsonParserConstants;
import morn.library.constant.ApplicationConstants;
import morn.library.core.CriteriaMap;
import morn.library.exception.ApplicationMessages;
import morn.library.type.TypeBuilder;
import org.springframework.core.ParameterizedTypeReference;

/**
 * FastJson解析器
 */
@Slf4j
@Tag(JsonParserConstants.JACKSON)
public class JacksonParser implements JsonParser {

  private final ObjectMapper objectMapper;

  public JacksonParser(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public CriteriaMap convertMap(Object object) {
    String string = convertStr(object);
    try {
      return objectMapper.readValue(string, CriteriaMap.class);
    } catch (IOException e) {
      throw ApplicationMessages
          .translateException(ApplicationConstants.Errors.JSON_DESERIALIZE_FAILURE, object.toString());
    }
  }

  @Override
  public <T> T convert(Object object, Class<T> cls) {
    String string = convertStr(object);
    try {
      return objectMapper.readValue(string, cls);
    } catch (IOException e) {
      throw ApplicationMessages
          .translateException(ApplicationConstants.Errors.JSON_DESERIALIZE_FAILURE, object.toString());
    }
  }

  @Override
  public <T> T convert(Object object, ParameterizedTypeReference<T> reference) {
    Type type = reference.getType();
    return convert(object, type);
  }

  @Override
  public <T> T convert(Object object, Type type) {
    try {
      String string = convertStr(object);
      JavaType javaType = objectMapper.getTypeFactory().constructType(type);
      return objectMapper.readValue(string, javaType);
    } catch (IOException e) {
      throw ApplicationMessages
          .translateException(ApplicationConstants.Errors.JSON_DESERIALIZE_FAILURE, object.toString());
    }
  }

  @Override
  public <T> T convert(Object object, TypeBuilder typeBuilder) {
    Type type = typeBuilder.build();
    return convert(object,type);
  }

  @Override
  public String convertStr(Object object) {
    if (object instanceof String) {
      return object.toString();
    }
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw ApplicationMessages
          .translateException(ApplicationConstants.Errors.JSON_SERIALIZE_FAILURE, object.toString());
    }
  }
}
