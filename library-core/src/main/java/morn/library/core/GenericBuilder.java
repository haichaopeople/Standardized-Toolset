package morn.library.core;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 任意对象建造器
 * 通用消费者构造实现
 * Consumer -> 消费型接口，有参无返回值
 * Supplier -> 供给型接口，无参有返回值
 * 合成[GenericBuilder]有参数有返回值
 **/
public class GenericBuilder<T> {

    /**
     * 消费者实例
     */
    private final Supplier<T> instantiate;

    /**
     * 存储消费者每一个行为
     */
    private final List<Consumer<T>> consumers = new CopyOnWriteArrayList<>();

    /**
     * 构造函数实例化 消费者实例
     *
     * @param instantiate 消费者实例
     */
    public GenericBuilder(Supplier<T> instantiate) {
        this.instantiate = instantiate;
    }

    /**
     * @param instantiate 消费者实例
     * @param <T>         消费者类型
     * @return 实例对象
     */
    public synchronized static <T> GenericBuilder<T> of(T instantiate) {
        return new GenericBuilder<>(() -> instantiate);
    }

    /**
     * @param instantiate 消费者实例
     * @param <T>         消费者类型
     * @return 实例对象
     */
    public synchronized static <T> GenericBuilder<T> of(Supplier<T> instantiate) {
        return new GenericBuilder<>(instantiate);
    }

    /**
     * 操作给定给定消费者的参数执行此操作
     *
     * @param consumerBehavior 消费者的行为
     * @param consumerChange   消费者的改变
     * @param <U>              给定消费者的改变
     * @return 消费者
     */
    public synchronized <U> GenericBuilder<T> put(U consumerChange, BiConsumer<T, U> consumerBehavior) {
        Consumer<T> setOperationFunction = instance -> consumerBehavior.accept(instance, consumerChange);
        consumers.add(setOperationFunction);
        return this;
    }

    /**
     * 建立改变行为后的消费者
     *
     * @return 变更的消费者
     */
    public synchronized T build() {
        T instance = instantiate.get();
        consumers.forEach(data -> data.accept(instance));
        consumers.clear();
        return instance;
    }

    /**
     * 建立改变行为后的消费者
     *
     * @return 变更的消费者
     */
    public synchronized Optional<T> optionalBuild() {
        T instance = instantiate.get();
        consumers.forEach(data -> data.accept(instance));
        consumers.clear();
        return Optional.ofNullable(instance);
    }
}
