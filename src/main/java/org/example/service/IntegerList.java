package org.example.service;

public interface IntegerList {
    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    Integer add(Integer item);

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    Integer add(int index, Integer item);

    Integer set(int index, Integer item);

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    Integer remove(Integer item);

    Integer remove(int index);

    boolean contains(Integer item);

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    int indexOf(Integer item);

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    int lastIndexOf(Integer item);

    Integer get(int index);

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    boolean equals(IntegerList otherList);

    // Вернуть фактическое количество элементов.
    int size();

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    boolean isEmpty();

    // Удалить все элементы из списка.
    void clear();

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    Integer[] toArray();
}
