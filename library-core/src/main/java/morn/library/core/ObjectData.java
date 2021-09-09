package morn.library.core;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import morn.library.type.TypeBuilder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义对象对应的数据
 *
 * @author admin
 */
@Data
public class ObjectData implements Serializable {

    private Map<String, Object> map;

    public ObjectData() {
        map = new ConcurrentHashMap<>();
    }

    public ObjectData(Map<String, Object> map) {
        if (MapUtil.isEmpty(map)) {
            this.map = new ConcurrentHashMap<>();
        }
        this.map = map;
    }

    public Object get(String key) {
        return map.get(key);
    }

    public void set(String key, Object value) {
        map.put(key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        return Convert.convert(TypeBuilder.newInstance(clazz).build(), map.get(key), null);
    }

    public String toJsonStr() {
        return JSONUtil.toJsonStr(map);
    }

    public CriteriaMap toCriteriaMap() {
        return new CriteriaMap(map);
    }


    public void fromJsonStr(String jsonStr) {
        if (StrUtil.isEmpty(jsonStr)) {
            map = new ConcurrentHashMap<>();
        }
        map = JSONUtil.parseObj(jsonStr).toBean(
                TypeBuilder
                        .newInstance(Map.class)
                        .addTypeParam(String.class).addTypeParam(Object.class
                ).build()
        );
    }

    public void fromJsonStr(String jsonStr, Type type) {
        if (StrUtil.isEmpty(jsonStr)) {
            map = new ConcurrentHashMap<>();
        }
        map = JSONUtil.parseObj(jsonStr).toBean(type);
    }

    @Override
    public String toString() {
        return toJsonStr();
    }
}