package com.example.turing_thread._3;

public class HashMap<K, V> implements Map<K, V> {
    private Entry<K, V>[] table = null;
    int size = 0;

    public HashMap() {
        table = new Entry[16];
    }

    /***
     * 通过key 进行hash index下标
     * 数组所对应 值查找到该对象
     * 如果是该对象为空 说明没有冲突
     * 直接用数组存储就可以
     * 如果不为空 说明出现了冲突用链表来存储
     * 然后返回
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry entry = table[index];
        if (null == entry) {
            //可以用数组来存储 王五、陈二、李四、刘一
            table[index] = new Entry<>(k, v, index, null);
            size++;
        } else {
            //有冲突就要用链表来存储
            table[index] = new Entry<>(k, v, index, entry);
        }
        return table[index].getValue();
    }

    private int hash(K k) {
        int i = k.hashCode() % 16;
        return i >= 0 ? i : -i;
    }

    /***
     *   通过key 进行hash 下标
     *   对象是否为空
     *   如果为空 直接返回
     *   如果不为空的 找到了
     *   比较他们的对象的值和他们的下标地址是否相等
     *   如果相等就找到了 然后直接返回
     *   如果不相等在来继续找 知道next为空 或者当前对象为空
     *   返回
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        if (size == 0) {
            return null;
        }
        int index = hash(k);
        Entry<K, V> entry = findValue(k, table[index]);
        return entry == null ? null : entry.getValue();
    }

    private Entry<K, V> findValue(K k, Entry<K, V> entry) {
        if (k.equals(entry.getKey()) || k == entry.getKey()) {
            return entry;
        } else {
            if (entry.next != null) {
                return findValue(k, entry.next);
            }
        }
        return null;
    }


    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V> {
        K k;
        V v;
        int hash;
        Entry<K, V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }

}
