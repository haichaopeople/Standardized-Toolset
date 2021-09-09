package morn.library.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import morn.library.util.SpareCollectionUtils;

/**
 * 任务组异步操作对象
 *
 * <p>可对任务组中的所有任务元素及总体进行异步操作</p>
 *
 * @param <T> 结果类型
 */
public class TaskGroupFuture<T> {

    /**
     * 所有回调对象的集合
     */
    private final List<CompletableFuture<T>> elementFutures;

    /**
     * 结果集
     */
    private final List<T> results;

    /**
     * 异常结果集
     */
    private final List<Throwable> throwableList;

    /**
     * 成功索引集
     */
    private final List<Integer> successIndexes;

    /**
     * 失败索引集
     */
    private final List<Integer> failureIndexes;

    /**
     * 总回调对象
     */
    private CompletableFuture<Void> allOfFuture;

    public TaskGroupFuture(int size) {
        this.elementFutures = Collections.synchronizedList(new ArrayList<>(size));
        this.results = Collections.synchronizedList(new ArrayList<>(size));
        this.throwableList = Collections.synchronizedList(new ArrayList<>());
        this.successIndexes = Collections.synchronizedList(new ArrayList<>(size));
        this.failureIndexes = Collections.synchronizedList(new ArrayList<>());

        SpareCollectionUtils.initCollection(this.elementFutures, size);
        SpareCollectionUtils.initCollection(this.results, size);
    }

    /**
     * 保存任务元素的异步操作对象
     *
     * @param element 任务元素
     * @param future  异步操作对象
     */
    public void setElementFuture(TaskGroupElement<T> element, CompletableFuture<T> future) {
        int i = element.getIndex();
        /* 成功回调 */
        future.thenAccept(v -> whenSuccess(i, v));
        /* 异常回调 */
        future.exceptionally(throwable -> whenFailure(i, throwable));
        elementFutures.set(i, future);
    }

    /**
     * 执行成功回调
     *
     * @param index 索引
     * @param value 返回值
     */
    public void whenSuccess(int index, T value) {
        /* 记录成功索引 */
        successIndexes.add(index);
        /* 记录成功结果 */
        results.set(index, value);
    }

    /**
     * 执行失败回调
     *
     * @param index     索引
     * @param throwable 异常
     * @return null
     */
    public T whenFailure(int index, Throwable throwable) {
        /* 记录失败索引 */
        failureIndexes.add(index);
        /* 记录异常信息 */
        throwableList.add(throwable);
        return null;
    }

    public List<CompletableFuture<T>> elements() {
        return elementFutures;
    }

    public List<T> results() {
        return results;
    }

    public List<Throwable> throwableList() {
        return throwableList;
    }

    public List<Integer> successIndexes() {
        return successIndexes;
    }

    public List<Integer> failureIndexes() {
        return failureIndexes;
    }

    public CompletableFuture<Void> allOf() {
        if (Objects.isNull(allOfFuture)) {
            allOfFuture = CompletableFuture.allOf(elementFutures.toArray(new CompletableFuture[0]));
        }
        return allOfFuture;
    }
}
