package morn.library.boot.json.support;

import static morn.library.boot.json.constant.JsonParserConstants.FAST_JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Type;

import morn.library.bean.annotation.Tag;
import morn.library.boot.json.JsonParser;
import morn.library.core.CriteriaMap;
import morn.library.type.TypeBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

/**
 * FastJson解析器
 */
@Tag(FAST_JSON)
public class FastJsonParser implements JsonParser {

  @Override
  public CriteriaMap convertMap(Object object) {
    String jsonStr = convertStr(object);
    JSONObject jsonObject = JSON.parseObject(jsonStr);
    return new CriteriaMap(jsonObject);
  }

  @Override
  public <T> T convert(Object object, Class<T> cls) {
    String jsonStr = convertStr(object);
    return JSON.parseObject(jsonStr, cls);
  }

  @Override
  public <T> T convert(Object object, ParameterizedTypeReference<T> reference) {
    ResolvableType resolvableType = ResolvableType.forType(reference);
    Type type = resolvableType.getType();
    return convert(object, type);
  }

  @Override
  public <T> T convert(Object object, Type type) {
    String jsonStr = convertStr(object);
    return JSON.parseObject(jsonStr, type);
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
    return JSON.toJSONString(object);
  }
}
