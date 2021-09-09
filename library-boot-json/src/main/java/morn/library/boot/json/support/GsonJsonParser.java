package morn.library.boot.json.support;

import morn.library.boot.json.JsonParser;
import morn.library.core.CriteriaMap;
import morn.library.type.TypeBuilder;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;

public class GsonJsonParser implements JsonParser {


    @Override
    public CriteriaMap convertMap(Object object) {
        return null;
    }

    @Override
    public <T> T convert(Object object, Class<T> cls) {
        return null;
    }

    @Override
    public <T> T convert(Object object, ParameterizedTypeReference<T> reference) {
        return null;
    }

    @Override
    public <T> T convert(Object object, Type type) {
        return null;
    }

    @Override
    public <T> T convert(Object object, TypeBuilder typeBuilder) {
        return null;
    }

    @Override
    public String convertStr(Object object) {
        return null;
    }
}
