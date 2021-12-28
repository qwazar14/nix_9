package ua.com.alevel.util;

public class Navigation {
    public static void printNavigationConstruct(){
        System.out.println("\t\t\t\t\t\tCONSTRUCTORS\t\t\t\t\t\t");
        System.out.println();
        System.out.println("1. Создать динамический MathSet с начальным размером 0");
        System.out.println("2. Создать динамический MathSet с предопределенным размером");
        System.out.println("3. Создать динамический MathSet из массива");
        System.out.println("4. Создать динамический MathSet из нескольких массивов");
        System.out.println("5. Создать динамический MathSet из другого MathSet");
        System.out.println("6. Создать динамический MathSet из нескольких MathSet");
        System.out.println();
//        System.out.println("+. Следующая страница");
        System.out.println("x. Выход из программы");
    }

    public static void printNavigationAddJoinIntersection(){
        System.out.println("\t\t\t\t\t\tADD, JOIN, INTERSECTION\t\t\t\t\t\t");
        System.out.println();
        System.out.println("1. Добавить элемент (add(Number n))");
        System.out.println("2. Добавить несколько элементов (add(Number ... n))");
        System.out.println("3. Найти схождение с другим MathSet (join(MathSet ms))");
        System.out.println("4. Найти схождение с несколькими другими MathSet (join(MathSet ... ms))");
        System.out.println("5. Найти пересечение с другим MathSet (intersection(MathSet ms))");
        System.out.println("6. Найти пересечение с несколькими другими MathSet (intersection (MathSet ... ms))");
        System.out.println();
        System.out.println("+. Следующая страница");
        System.out.println("x. Выход из программы");
    }

    public static void printNavigationSort(){
        System.out.println("\t\t\t\t\t\tSORT\t\t\t\t\t\t");
        System.out.println();
        System.out.println("1. Сортировать MathSet по убыванию (sortDesc())");
        System.out.println("2. Сортировать часть MathSet по убыванию (sortDesc(int firstIndex, int lastIndex))");
        System.out.println("3. Сортировать MathSet с определенного элемента по убыванию (sortDesc(Number value))");
        System.out.println("4. Сортировать MathSet по возрастанию (sortAsc())");
        System.out.println("5. Сортировать часть MathSet по возрастанию (sortAsc(int firstIndex, int lastIndex))");
        System.out.println("6. Сортировать MathSet с определенного элемента по возрастанию (sortAsc(Number value))");
        System.out.println();
        System.out.println("-. Предыдущая страница");
        System.out.println("+. Следующая страница");
        System.out.println("x. Выход из программы");
    }

    public static void printNavigationGet(){
        System.out.println("\t\t\t\t\t\tGET\t\t\t\t\t\t");
        System.out.println();
        System.out.println("1. Найти элемент по индексу (get(int index))");
        System.out.println("2. Найти элемент с минимальным значением (getMax())");
        System.out.println("3. Найти элемент с максимальеым значением (getMin())");
        System.out.println("4. Найти среднее значение элементов (getAverage())");
        System.out.println("5. Найти медиану элементов (getMedian())");
        System.out.println();
        System.out.println("-. Предыдущая страница");
        System.out.println("+. Следующая страница");
        System.out.println("x. Выход из программы");
    }

    public static void printNavigationClearCut(){
        System.out.println("\t\t\t\t\t\tCLEAR, CUT\t\t\t\t\t\t");
        System.out.println();
        System.out.println("1. Вырезать часть из MathSet по индексам (cut(int firstIndex, int lastIndex))");
        System.out.println("2. Очистить весь MathSet (clear())");
        System.out.println("3. Очистить из MathSet определенные элементы (clear(Number[] numbers))");
        System.out.println();
        System.out.println("-. Предыдущая страница");
//        System.out.println("+. Следующая страница");
        System.out.println("x. Выход из программы");
    }

    public static void printMainNavigation(){
        System.out.println("============COLLECTIONS============");
        System.out.println();
        System.out.println("1. Меню CONSTRUCTORS");
        System.out.println("2. Меню ADD, JOIN, INTERSECTION");
        System.out.println("3. Меню SORT");
        System.out.println("4. Меню GET");
        System.out.println("5. Меню CLEAR, CUT");
        System.out.println();
//        System.out.println("+. Следующая страница");
        System.out.println("x. Выход из программы");
    }
}
