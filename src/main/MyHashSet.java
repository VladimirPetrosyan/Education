package main;

import java.util.Objects;

public class MyHashSet<E> {
    private static class Node<E> {
        final E key;
        Node<E> next;
        Node(E key, Node<E> next) { this.key = key; this.next = next; }
    }

    private Node<E>[] table;
    private int size;
    private int threshold;
    private final float loadFactor = 0.75f;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        table = (Node<E>[]) new Node[16];
        threshold = (int) (table.length * loadFactor);
    }

    private int index(Object key, int len) {
        int h = (key == null) ? 0 : key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7fffffff) % len;
    }

    /** Вставить: true — если добавили новый элемент, false — если уже был */
    public boolean insert(E key) {
        if (size + 1 > threshold) resize();

        int idx = index(key, table.length);
        for (Node<E> n = table[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) return false;
        }
        table[idx] = new Node<>(key, table[idx]);
        size++;
        return true;
    }

    /** Удалить: true — если существовал и удалён, false — если не найден */
    public boolean delete(E key) {
        int idx = index(key, table.length);
        Node<E> prev = null, cur = table[idx];
        while (cur != null) {
            if (Objects.equals(cur.key, key)) {
                if (prev == null) table[idx] = cur.next; else prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int size() { return size; }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] old = table;
        Node<E>[] nw = (Node<E>[]) new Node[old.length << 1];
        for (Node<E> head : old) {
            for (Node<E> n = head; n != null; n = n.next) {
                int idx = index(n.key, nw.length);
                nw[idx] = new Node<>(n.key, nw[idx]);
            }
        }
        table = nw;
        threshold = (int) (table.length * loadFactor);
    }
}

