import exception.ArrayIsFullException;
import exception.ElementNotFoundException;
import exception.IndexNotFoundException;
import exception.ItemIsNullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{
    private final Integer[] strings;
    private int size ;

    public IntegerListImpl() {
        strings = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        strings = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        strings[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            strings[size++] = item;
            return item;
        }
        System.arraycopy(strings, index, strings, index + 1, size - index);
        strings[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        strings[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        if (index == -1) {
            throw new ElementNotFoundException("Не найден!");
        }

        if (index != size) {
            System.arraycopy(strings, index + 1, strings, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = strings[index];
        if (index != size) {
            System.arraycopy(strings, index + 1, strings, index, size - index);
        }
        return null;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] stringsCopy = toArray();
        sort(stringsCopy);
        return binarySearch(stringsCopy, item);
    }


    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return strings[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
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
        return Arrays.copyOf(strings, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new ItemIsNullException("Некорректный ввод!");
        }
    }

    private void validateSize() {
        if (size == strings.length) {
            throw new ArrayIsFullException("Список переполнен!");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexNotFoundException("Некорректный номер элемента!");
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
