package main;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArrayList<E> implements Iterable<E> {
    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[10];
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) data[index];
    }

    /** remove по индексу: вернём удалённый элемент */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        rangeCheck(index);
        E old = (E) data[index];
        int moved = size - index - 1;
        if (moved > 0) System.arraycopy(data, index + 1, data, index, moved);
        data[--size] = null;
        return old;
    }

    public void addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        ensureCapacity(size + c.size());
        for (E e : c) data[size++] = e;
    }

    public int size() { return size; }

    private void ensureCapacity(int cap) {
        if (cap <= data.length) return;
        int newCap = Math.max(data.length + (data.length >> 1), cap);
        data = Arrays.copyOf(data, newCap);
    }

    private void rangeCheck(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException("index=" + idx + ", size=" + size);
    }

    @Override public Iterator<E> iterator() {
        return new Iterator<>() {
            int i = 0;
            @Override public boolean hasNext() { return i < size; }
            @SuppressWarnings("unchecked")
            @Override public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (E) data[i++];
            }
        };
    }
}
