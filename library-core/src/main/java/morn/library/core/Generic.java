package morn.library.core;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import morn.library.util.GenericUtils;

/**
 * 任意类型变量
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Generic {

    /**
     * 值
     */
    private Object value;

    private Generic() { }

    private Generic(Object value) {
        this.value = value;
    }

    /**
     * 获取集合
     *
     * @return 集合
     */
    @SuppressWarnings("unchecked")
    public <T> Collection<T> asCollection() {
        return value(Collection.class);
    }

    /**
     * 获取长整型值
     *
     * @return 长整型值
     */
    public Long asLong() {
        return value(Long.class);
    }

    /**
     * 获取字符值
     *
     * @return 字符值
     */
    public String asString() {
        return value(String.class);
    }

    /**
     * 获取值
     *
     * @param cls 目标类
     * @param <T> 目标类型
     * @return 值
     */
    public <T> T value(Class<T> cls) {
        return GenericUtils.castFrom(value(), cls);
    }

    /**
     * 构建“任意”类型对象
     */
    public static Generic from(Object o) {
        return new Generic(Optional.ofNullable(o).orElseGet(Generic::new));
    }
}
