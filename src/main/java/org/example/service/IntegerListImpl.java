package org.example.service;

import org.example.exception.InvalidIndexException;
import org.example.exception.NullItemException;
import org.example.exception.StorageFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private final Integer[] testArrayList;
    private int size;

    public IntegerListImpl() {
        testArrayList = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        testArrayList = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        testArrayList[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        testArrayList[index] = item;
        return item;
    }


    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = testArrayList[index];

        if (index != size) {
            System.arraycopy(testArrayList, index + 1, testArrayList, index, size - (index + 1));
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (testArrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (testArrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return testArrayList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        return Arrays.copyOf(testArrayList, size);
    }

    private void validateItem(Integer item) {
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

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
