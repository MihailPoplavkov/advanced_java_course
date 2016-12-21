import java.util.Arrays;

/**
 * Created by mihailpoplavkov on 12/21/16.
 */
public class LFUCache {
    private Elem[] data;
    private int capacity;
    private int elemCount = 0;

    private class Elem {
        private int value;
        private int frequency = 1;
        private int time = 0;

        public Elem(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getFrequency() {
            return frequency;
        }

        public int getTime() {
            return time;
        }

        public void incrementFrequency() {
            frequency++;
        }

        public void incrementTime() {
            time++;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        data = new Elem[capacity];
    }

    public void useValue(int value) {
        boolean isFind = false;
        for (int i = 0; i < elemCount; i++) {
            Elem e = data[i];
            e.incrementTime();
            if (e.getValue() == value) {
                e.incrementFrequency();
                isFind = true; //there is no return, because I want to increment time for all elements;
            }
        }
        if (isFind) {
            return;
        }
        int index;
        if (elemCount < capacity) {
            index = elemCount++;
        } else {
            index = destroyIndex();
        }
        data[index] = new Elem(value);
    }

    public int[] getData() {
        int[] res = new int[elemCount];
        for (int i = 0; i < elemCount; i++) {
            res[i] = data[i].getValue();
        }
        return res;
    }

    private int destroyIndex() {
        if (elemCount == 0) {
            return -1;
        }
        int minFrequency = data[0].getFrequency();
        int  maxTime = data[0].getTime();
        int index = 0;
        for (int i = 1; i < elemCount; i++) {
            Elem e = data[i];
            if ((e.getFrequency() == minFrequency) && (e.getTime() > maxTime)) {
                maxTime = e.getTime();
                index = i;
                continue;
            }
            if (e.getFrequency() < minFrequency) {
                minFrequency = e.getFrequency();
                maxTime = e.getTime();
                index = i;
            }
        }
        return index;
    }

    private void useAndPrint(int value) {
        useValue(value);
        System.out.println(Arrays.toString(getData()));
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(10);
        cache.useAndPrint(1);
        cache.useAndPrint(2);
        cache.useAndPrint(3);
        cache.useAndPrint(1);
        cache.useAndPrint(4);
        cache.useAndPrint(5);
        cache.useAndPrint(3);
        cache.useAndPrint(5);
        cache.useAndPrint(3);
        cache.useAndPrint(7);
    }
}
