package ua.com.alevel;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class MathSet {
    private final int SIZE = 10;
    private Number[] mathSet;
    private int capacity;

    /* CONSTRUCTOR START **/
    public MathSet() {
        this.mathSet = new Number[SIZE];
    }

    public MathSet(int capacity) {
        this.capacity = capacity;
        this.mathSet = new Number[capacity];
    }

    MathSet(Number[] numbers) {
        this.mathSet = fromArrayToSet(numbers);
    }

    public MathSet(Number[]... numbers) {
        this.mathSet = fromArrayToSet(mergeArrays(numbers));
    }

    MathSet(MathSet numbers) {
        this.mathSet = numbers.getMathSet();
    }

    MathSet(MathSet... numbers) {
        this.mathSet = fromArrayToSet(mergeMathSets(numbers));
    }



    /* CONSTRUCTOR END **/

    /* GETTERS AND SETTERS START **/
    public Number[] getMathSet() {
        return mathSet;
    }

    public void setMathSet(Number[] mathSet) {
        this.mathSet = fromArrayToSet(mathSet);
    }
    /* GETTERS AND SETTERS END **/

    /* METHODS START **/
    void add(Number n) {
        if (!isPresent(n) && !isFull(mathSet)) {
            for (int i = 0; i < mathSet.length; i++) {
                if (mathSet[i] == null) {
                    mathSet[i] = n;
                    break;
                }
            }
        } else if (!isPresent(n) && !isFull(mathSet) && capacity == mathSet.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (!isPresent(n) && !isFull(mathSet) && capacity != mathSet.length) {
            changeSize();
            add(n);
        }
    }

    void add(Number... n) {
        for (Number number : n) add(number);
    }

    void join(MathSet ms) {
        mathSet = fromArrayToSet(mergeArrays(ms.getMathSet(), mathSet));
    }

    void join(MathSet... ms) {
        for (MathSet set : ms) mathSet = fromArrayToSet(mergeArrays(set.getMathSet(), mathSet));
    }

    public void intersection(MathSet ms) {
        int numberOfCommon = 0;
        for (int i = 0; i < ms.getMathSet().length; i++) if (isPresent((ms.getMathSet()[i]))) numberOfCommon++;
        int count = 0;
        Number[] newMathSet = new Number[numberOfCommon];
        for (int i = 0; i < ms.getMathSet().length; i++) {
            if (isPresent((ms.getMathSet()[i]))) {
                newMathSet[count] = ms.getMathSet()[i];
                count++;
            }
        }
        mathSet = newMathSet;
    }

    void intersection(MathSet... ms) {
        for (MathSet set : ms) {
            intersection(set);
        }
    }

    void sortDesc() {
        sortDesc(mathSet);
    }

    void sortDesc(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i < lastIndex + 1; i++) numbers[i - firstIndex] = mathSet[i];
        sortDesc(numbers);
        for (int i = firstIndex; i < lastIndex + 1; i++) mathSet[i] = numbers[i - firstIndex];
    }

    void sortDesc(Number value) {
        sortDesc(getIndex(value), mathSet.length - 1);
    }

    void sortAsc() {
        sort(mathSet);
    }

    void sortAsc(int firstIndex, int lastIndex) {
        Number[] sortedPart = new Number[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            sortedPart[i - firstIndex] = mathSet[i];
        }
        sort(sortedPart);
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            mathSet[i] = sortedPart[i - firstIndex];
        }
    }

    void sortAsc(Number value) {
        sortAsc(getIndex(value), mathSet.length - 1);
    }

    Number get(int index) {
        if (mathSet[index] != null) return mathSet[index];
        else throw new ArrayIndexOutOfBoundsException();
    }

    Number getMax() {
        Number max = Long.MIN_VALUE;
        for (Number number : mathSet) if (number != null) if (compareTo(number, max) == 1) max = number;
        return max;
    }

    Number getMin() {
        Number min = Long.MAX_VALUE;
        for (Number number : mathSet) if (number != null) if (compareTo(number, min) == -1) min = number;
        return min;
    }

    Number getAverage() {
        Number sum = 0;
        int notNullElement = 0;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                sum = sum(sum, mathSet[i]);
                notNullElement++;
            }
        }
        return sum.doubleValue() / notNullElement;
    }

    Number getMedian() {
        MathSet set = new MathSet(mathSet);
        Number[] numbers = set.getMathSet();
        sort(numbers);
        if (numbers.length % 2 == 1) return numbers[numbers.length / 2];
        else return (sum(numbers[numbers.length / 2 - 1], numbers[numbers.length / 2]).doubleValue() / 2);
    }

    Number[] toArray() {
        return this.getMathSet();
    }

    Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i < lastIndex + 1; i++) numbers[i] = mathSet[i];
        return numbers;
    }

    MathSet cut(int firstIndex, int lastIndex) {
        for (int i = lastIndex; i < mathSet.length; i++) mathSet[i - (lastIndex - firstIndex + 1)] = mathSet[i];
        for (int i = mathSet.length - (lastIndex - firstIndex + 1); i < mathSet.length; i++) mathSet[i] = null;
        MathSet set = new MathSet();
        set.setMathSet(mathSet);
        return set;
    }

    void clear() {
        for (int i = 0; i < mathSet.length; i++) mathSet[i] = null;
    }

    void clear(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < mathSet.length; j++) {
                if (mathSet[j] != null && compareTo(mathSet[j], numbers[j]) == 0) mathSet[j] = null;
            }
        }
    }

    /* METHODS END **/

    /* UTILS START **/

    private int getIndex(Number number) {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null && compareTo(mathSet[i], number) == 0) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private void sortDesc(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            Number max = numbers[i];
            int maxMathSet = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] != null) {
                    if (compareTo(numbers[j], max) == 1) {
                        max = numbers[j];
                        maxMathSet = j;
                    }
                }
            }
            if (compareTo(i, maxMathSet) != 0) {
                Number tmp = numbers[i];
                numbers[i] = numbers[maxMathSet];
                numbers[maxMathSet] = tmp;
            }
        }
    }

    public int compareTo(Number n1, Number n2) {
        BigDecimal b1 = BigDecimal.valueOf(n1.doubleValue());
        BigDecimal b2 = BigDecimal.valueOf(n2.doubleValue());
        return b1.compareTo(b2);
    }

    public Number sum(Number n1, Number n2) {
        BigDecimal b1 = BigDecimal.valueOf(n1.doubleValue());
        BigDecimal b2 = BigDecimal.valueOf(n2.doubleValue());
        return b1.add(b2);
    }

    public void sort(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            Number min = numbers[i];
            int minMathSet = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] != null) {
                    if (compareTo(numbers[j], min) == -1) {
                        min = numbers[j];
                        minMathSet = j;
                    }
                }
            }
            if (compareTo(i, minMathSet) != 0) {
                Number value = numbers[i];
                numbers[i] = numbers[minMathSet];
                numbers[minMathSet] = value;
            }
        }
    }

    private boolean isPresent(Number n) {
        for (Number number : mathSet) {
            if (n.equals(number)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFull(Number[] numbers) {
        int notNullElement = 0;
        for (Number number : numbers) {
            if (number != null) {
                notNullElement++;
            }
        }
        return notNullElement == numbers.length;
    }

    private void changeSize() {
        Number[] numbers = new Number[mathSet.length * 2];
        for (int i = 0; i < mathSet.length; i++) numbers[i] = mathSet[i];
        mathSet = numbers;
    }

    private Number[] fromArrayToSet(Number[] numbers) {
        int unique = 0;
        for (int i = 0; i < numbers.length; i++) {
            Number current = numbers[i];
            boolean isRepeatable = false;
            for (int j = 0; j < i; j++) {
                if (current != null) {
                    if (current.equals(numbers[j])) {
                        isRepeatable = true;
                    }
                }
            }
            if (!isRepeatable) {
                unique++;
            }
        }
        Number[] set = new Number[unique];
        int count = 0;
        for (Number number : numbers) {
            boolean isPresent = false;
            for (int j = 0; j < unique; j++) {
                if (number != null && number.equals(numbers[j])) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                set[count] = number;
                count++;
            }
        }
        return set;
    }

    private Number[] mergeArrays(Number[]... numbers) {
        int length = 0;
        int count = 0;
        for (Number[] number : numbers) {
            length += number.length;
        }
        Number all[] = new Number[length];
        for (Number[] n : numbers) {
            for (Number num : n) {
                all[count] = num;
                count++;
            }
        }
        return all;
    }

    private Number[] mergeMathSets(MathSet... numbers) {
        int count = 0;
        int length = 0;
        for (MathSet mathSet : numbers) {
            length += mathSet.getMathSet().length;
        }
        Number[] all = new Number[length];

        for (MathSet mathSet : numbers) {
            for (Number number : mathSet.getMathSet()) {
                all[count] = number;
                count++;
            }
        }
        return all;
    }
    /* UTILS END **/
}
