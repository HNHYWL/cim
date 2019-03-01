package com.crossoverjie.cim.common.data.construct;


import java.util.Arrays;


public class SortArrayMapV2 {

    /**
     * 核心数组
     */
    private Node[] buckets;

    private static final int DEFAULT_SIZE = 10;

    /**
     * 数组大小
     */
    private int size = 0;

    public SortArrayMapV2() {
        buckets = new Node[DEFAULT_SIZE];
    }

    /**
     * 写入数据
     *
     * @param key
     * @param value
     */
    public void add(Long key, String value) {
        checkSize(size + 1);
        Node node = new Node(key, value);
        buckets[size++] = node;
    }

    /**
     * 校验是否需要扩容
     *
     * @param size
     */
    private void checkSize(int size) {
        if (size >= buckets.length) {
            //扩容自身的 3/2
            int oldLen = buckets.length;
            int newLen = oldLen + (oldLen >> 1);
            buckets = Arrays.copyOf(buckets, newLen);
        }
    }

    /**
     * 二分查找
     * 顺时针取出数据
     *
     * @param key
     * @return
     */

    public String firstNodeValue(long key) {
        if (size == 0) {
            return null;
        }

        int index = Arrays.binarySearch(buckets, 0, size, new Node(key, ""));
        int insertIndex = -index - 1;
        if (insertIndex < size) {
            return buckets[insertIndex].value;
        } else {
            return buckets[0].value;
        }

    }

    /**
     * 排序
     */
    public void sort() {
        Arrays.sort(buckets, 0, size);
    }

    public void print() {
        for (Node bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            System.out.println(bucket.toString());
        }
    }

    public int size() {
        return size;
    }

    /**
     * 数据节点
     */
    private class Node implements Comparable<Node> {
        public Long key;
        public String value;

        public Node(Long key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Node otherNode) {
            if (key > otherNode.key) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}

