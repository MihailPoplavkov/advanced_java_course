import org.junit.Test;

/**
 * Created by mihailpoplavkov on 12/21/16.
 */
public class LFUTest {
    int capacity = 500;
    LFUCache cache;
    int res[] = new int[capacity];

    @Test
    public void testInitialize() {
        init(0);
        assert isEqualArrays(cache.getData(), res);
    }

    @Test
    public void testRewrite() {
        init(0);
        for (int i = 0; i < capacity; i++) {
            int val = capacity + i;
            cache.useValue(val);
            res[i] = val;
        }
        assert isEqualArrays(cache.getData(), res);
    }

    @Test
    public void testReuse() {
        init(0);
        for (int i = 0; i < capacity; i++) {
            if (i % 2 == 0) {;
                cache.useValue(i);
            }
        }
        for (int i = 0; i < capacity; i++) {
            if (i % 2 != 0) {
                int val = capacity + i;
                cache.useValue(val);
                res[i] = val;
            }
        }
        assert isEqualArrays(cache.getData(), res);
    }

    private boolean isEqualArrays(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private void init(int startValue) {
        cache = new LFUCache(capacity);
        for (int i = 0; i < capacity; i++) {
            int val = startValue + i;
            cache.useValue(val);
            res[i] = val;
        }
    }
}
