package org.example.service;

import org.example.exception.InvalidIndexException;
import org.example.exception.NullItemException;
import org.example.exception.StorageFullException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] testArrayList;
    private int size;

    public StringListImpl() {
        testArrayList = new String[10];
    }

    public StringListImpl(int initSize) {
        testArrayList = new String[initSize];
    }

    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        testArrayList[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);
        validateSize();
        validateIndex(index);
        if (index == size) {
            return item;
        }
        System.arraycopy(testArrayList, index, testArrayList, index - 1, size - index);
        testArrayList[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        testArrayList[index] = item;
        return item;
    }


    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = testArrayList[index];

        if (index != size) {
            System.arraycopy(testArrayList, index + 1, testArrayList, index, size - (index + 1));
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (testArrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (testArrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return testArrayList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;

    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(testArrayList, size);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == testArrayList.length) {
            throw new StorageFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException();
        }
    }
}
