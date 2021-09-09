package morn.library.util;

import morn.library.core.ObjectData;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 主要解决操作 ObjectData 空指针问题
 * 不安全用法：data.get("key"); data为null时会报空指针异常
 * 安全用法：ObjectDataUtils.get(data,"key");
 * 其他类型，比如 Map<String, Object>，可自行进行封装
 **/
public class ObjectDataUtils {

    public static void set(ObjectData data, String key, Object value) {
        set(data, key, value, false);
    }

    /**
     * 安全的set方法
     * @param ifNew 当对象为null时，是否实例化一个对象（需要接受返回值）
     */
    public static ObjectData set(ObjectData data, String key, Object value, Boolean ifNew) {
        if (ifNew) {
            if (null == data) {
                data = new ObjectData();
            }
            data.set(key, value);
        } else {
            if (null != data) {
                data.set(key, value);
            }
        }
        return data;
    }

    /**
     * 安全的get方法
     * Object res = ObjectDataUtils.get(data,"key");
     */
    public static Object get(ObjectData data, String key) {
        return get(data, key, null);
    }

    /**
     * 可以 链式 获取
     * 不需要判断data是否为null，parent是否为null
     * Object res = ObjectDataUtils.getKeys(data, "parent", "children");
     */
    public static Object getKeys(ObjectData data, String... keys) {
        Optional<ObjectData> obj = Optional.ofNullable(data);
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (null != key) {
                if (i < (keys.length - 1)) {
                    obj = obj.map(o -> o.get(key, ObjectData.class));
                } else {
                    return obj.map(o -> o.get(key)).orElse(null);
                }
            }
        }
        return obj.orElse(null);
    }

    /**
     * 可以指定默认返回值
     * Object res = ObjectDataUtils.get(data,"key","我是默认返回对象");
     * Object res = ObjectDataUtils.get(data,"key",new ObjectData());
     */
    public static Object get(ObjectData data, String key, Object defaultObj) {
        return resolve(() -> data.get(key)).orElse(defaultObj);
    }

    /**
     * 可以指定默认返回类型
     * String res = ObjectDataUtils.getT(data,"key",String.class);
     */
    public static <T> T getT(ObjectData data, String key, Class<T> clazz) {
        return getT(data, key, clazz, null);
    }

    /**
     * 可以指定默认返回类型和返回值
     * String res = ObjectDataUtils.getT(data,"key",String.class,"我是默认返回字符串");
     */
    public static <T> T getT(ObjectData data, String key, Class<T> clazz, T defaultT) {
        return resolve(() -> data.get(key, clazz)).orElse(defaultT);
    }

    /**
     * 通过 Supplier 函数自定义增强 API
     */
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            // 可能会抛出空指针异常，直接返回一个空的 Optional 对象
            return Optional.empty();
        }
    }
}
