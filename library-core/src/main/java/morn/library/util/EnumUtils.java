package morn.library.util;

import lombok.extern.slf4j.Slf4j;
import morn.library.core.KeyValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class EnumUtils {

    /**
     * 将枚举类的所有值转化为 KeyValue 列表
     */
    public static <T extends Enum<T>> List<KeyValue<String, T>> toKeyValueList(Class<T> enumType) {
        return toKeyValueList(enumType, Enum::name);
    }

    /**
     * 将枚举类的所有值转化为 KeyValue 列表
     */
    public static <T extends Enum<T>> List<KeyValue<String, T>> toKeyValueList(Class<T> enumType, Function<T, String> toString) {
        List<KeyValue<String, T>> list = new ArrayList<>();
        EnumSet.allOf(enumType).forEach(value -> list.add(new KeyValue<>(toString.apply(value), value)));
        return list;
    }

    /**
     * 将枚举类的指定值转化为 KeyValue 列表
     */
    @SafeVarargs
    public static <T extends Enum<T>> List<KeyValue<String, T>> toKeyValueList(T... values) {
        List<KeyValue<String, T>> list = new ArrayList<>();
        Stream.of(values).forEach(value -> list.add(new KeyValue<>(value.name(), value)));
        return list;
    }

//    public static void main(String[] args) {
//        List<KeyValue<String, ConvertMoneyEnum>> keyValues = toKeyValueList(ConvertMoneyEnum.WAN_YUAN,ConvertMoneyEnum.YI_YUAN);
//        List<KeyValue<String, ConvertMoneyEnum>> keyValues = toKeyValueList(ConvertMoneyEnum.class);
//        keyValues.forEach(keyValue -> {
//            System.out.println("key:"+ keyValue.getKey() + " val:" + keyValue.getValue().getDecimal());
//        });
//    }
}