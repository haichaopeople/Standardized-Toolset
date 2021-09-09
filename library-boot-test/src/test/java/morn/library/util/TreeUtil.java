package morn.library.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtil {

    public static String ROOT_ID = "0";

    /**
     * 获取树map
     */
    public static <N extends NodeInfo> Map<String, List<N>> getTreeMap(List<N> nodeInfos) {
        if (hasEmptyCollection(nodeInfos)) {
            return new ConcurrentHashMap<>();
        }
        return nodeInfos.stream().collect(Collectors.groupingBy(N::getParentId));
    }

    /**
     * 获取多个节点的上级
     */
    public static <N extends NodeInfo> Map<String, List<N>> getParentMap(List<N> nodeInfos, Set<String> nodeIds, boolean conatianSelf) {
        if (hasEmptyCollection(nodeInfos) || hasEmptyCollection(nodeIds)) {
            return new ConcurrentHashMap<>();
        }

        Map<String, N> resultNodes = nodeInfos.stream().collect(Collectors.toMap(N::getId, Function.identity()));
        Map<String, List<N>> parentMap = new ConcurrentHashMap<>();
        nodeIds.forEach(nodeId -> {

            List<N> parents = new ArrayList<>();
            N self = resultNodes.get(nodeId);
            if (conatianSelf && self != null) {
                parents.add(self);
            }
            N node = self;
            while (node != null && hasNotEmptyStr(node.getParentId()) && !ROOT_ID.equals(node.getParentId())
                    && resultNodes.get(node.getParentId()) != null) {
                node = resultNodes.get(node.getParentId());
                parents.add(node);
            }
            parentMap.put(nodeId, parents);
        });
        return parentMap;
    }

    /**
     * 获取多个节点的上级Id
     */
    public static <N extends NodeInfo> Map<String, List<String>> getParentIdMap(List<N> nodeInfos, Set<String> nodeIds, boolean conatianSelf) {
        if (hasEmptyCollection(nodeInfos) || hasEmptyCollection(nodeIds)) {
            return new ConcurrentHashMap<>();
        }
        Map<String, N> resultNodes = nodeInfos.stream().collect(Collectors.toMap(N::getId, Function.identity()));
        Map<String, List<String>> parentMap = new ConcurrentHashMap<>();
        nodeIds.forEach(nodeId -> {

            List<String> parents = new ArrayList<>();
            N self = resultNodes.get(nodeId);
            if (conatianSelf && self != null) {
                parents.add(self.getId());
            }
            N node = self;
            while (node != null && hasNotEmptyStr(node.getParentId()) && !ROOT_ID.equals(node.getParentId())
                    && resultNodes.get(node.getParentId()) != null) {
                node = resultNodes.get(node.getParentId());
                parents.add(node.getId());
            }
            parentMap.put(nodeId, parents);
        });
        return parentMap;
    }

    /**
     * 获取多个节点的下级
     */
    public static <N extends NodeInfo> Map<String, List<N>> getChildrenMap(
            List<N> nodeInfos, Set<String> nodeIds, boolean conatianSelf, boolean isDirect) {
        if (hasEmptyCollection(nodeInfos) || hasEmptyCollection(nodeIds)) {
            return new ConcurrentHashMap<>();
        }
        Map<String, N> resultNodes = nodeInfos.stream().collect(Collectors.toMap(N::getId, Function.identity()));
        Map<String, List<N>> treeMap = nodeInfos.stream().collect(Collectors.groupingBy(N::getParentId));
        Map<String, List<N>> childrenMap = new ConcurrentHashMap<>();
        nodeIds.forEach(nodeId -> {
            List<N> children = new ArrayList<>();
            N self = resultNodes.get(nodeId);
            if (self != null) {
                if (conatianSelf) {
                    children.add(self);
                }
                if (isDirect) {
                    if (hasNotEmptyCollection(treeMap.get(nodeId))) {
                        children.addAll(treeMap.get(nodeId));
                    }
                } else {
                    Stack<N> stack = new Stack<>();
                    if (hasNotEmptyCollection(treeMap.get(nodeId))) {
                        treeMap.get(nodeId).forEach(stack::push);
                    }
                    while (stack.size() > 0) {
                        N node = stack.pop();
                        children.add(node);
                        List<N> childrenList = treeMap.get(node.getId());
                        if (hasNotEmptyCollection(childrenList)) {
                            childrenList.forEach(stack::push);
                        }
                    }
                }
            }
            childrenMap.put(nodeId, children);
        });
        return childrenMap;
    }

    /**
     * 获取多个节点的下级id
     */
    public static <N extends NodeInfo> Map<String, List<String>> getChildrenIdMap(
            List<N> nodeInfos, Set<String> nodeIds, boolean conatianSelf, boolean isDirect) {
        if (hasEmptyCollection(nodeInfos) || hasEmptyCollection(nodeIds)) {
            return new ConcurrentHashMap<>();
        }
        Map<String, N> resultNodes = nodeInfos.stream().collect(Collectors.toMap(N::getId, Function.identity()));
        Map<String, List<N>> treeMap = nodeInfos.stream().collect(Collectors.groupingBy(N::getParentId));
        Map<String, List<String>> childrenMap = new ConcurrentHashMap<>();
        nodeIds.forEach(nodeId -> {
            N self = resultNodes.get(nodeId);
            List<String> children = new ArrayList<>();
            if (self != null) {
                if (conatianSelf) {
                    children.add(self.getId());
                }
                if (isDirect) {
                    if (hasNotEmptyCollection(treeMap.get(nodeId))) {
                        List<N> directChildren = treeMap.get(nodeId);
                        List<String> directIds = directChildren.stream().map(N::getId).collect(Collectors.toList());
                        children.addAll(directIds);
                    }
                } else {
                    Stack<N> stack = new Stack<>();
                    if (hasNotEmptyCollection(treeMap.get(nodeId))) {
                        treeMap.get(nodeId).forEach(stack::push);
                    }
                    while (stack.size() > 0) {
                        N node = stack.pop();
                        children.add(node.getId());
                        List<N> childrenList = treeMap.get(node.getId());
                        if (hasNotEmptyCollection(childrenList)) {
                            childrenList.forEach(stack::push);
                        }
                    }
                }
            }
            childrenMap.put(nodeId, children);
        });
        return childrenMap;
    }

    private static boolean hasNotEmptyCollection(Collection<?> list){
        return null != list && 0 != list.size();
    }

    private static boolean hasEmptyCollection(Collection<?> list){
        return !hasNotEmptyCollection(list);
    }


    private static boolean hasEmptyStr(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    private static boolean hasNotEmptyStr(CharSequence cs) {
        return !hasEmptyStr(cs);
    }
}
