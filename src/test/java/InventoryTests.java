import java.util.List;

public class InventoryTests {
    private static int failures = 0;

    public static void main(String[] args) {
        System.out.println("Running InventoryTests...");
        testPushPeekPop();
        testViewAllOrder();
        testPopEmpty();
        testSizeBehavior();

        if (failures == 0) System.out.println("ALL TESTS PASSED");
        else System.out.println(failures + " TEST(S) FAILED");

        // Exit code 0 when all pass, non-zero otherwise
        System.exit(failures == 0 ? 0 : 1);
    }

    private static void assertEquals(Object expected, Object actual, String testName) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            System.out.println("[FAIL] " + testName + " - expected: " + expected + ", got: " + actual);
            failures++;
        } else {
            System.out.println("[PASS] " + testName);
        }
    }

    private static void assertTrue(boolean cond, String testName) {
        if (!cond) {
            System.out.println("[FAIL] " + testName + " - condition was false");
            failures++;
        } else {
            System.out.println("[PASS] " + testName);
        }
    }

    private static void testPushPeekPop() {
        String name = "testPushPeekPop";
        InventoryStack s = new InventoryStack();
        InventoryItem a = new InventoryItem(1, "A", 1);
        InventoryItem b = new InventoryItem(2, "B", 2);
        s.push(a);
        s.push(b);

        assertEquals(2, s.size(), name + " - size after two pushes");
        assertEquals(b.toString(), s.peek().toString(), name + " - peek returns last pushed");

        InventoryItem popped = s.pop();
        assertEquals(b.toString(), popped.toString(), name + " - pop returns last pushed");
        assertEquals(1, s.size(), name + " - size after pop");
    }

    private static void testViewAllOrder() {
        String name = "testViewAllOrder";
        InventoryStack s = new InventoryStack();
        InventoryItem a = new InventoryItem(1, "A", 1);
        InventoryItem b = new InventoryItem(2, "B", 2);
        InventoryItem c = new InventoryItem(3, "C", 3);
        s.push(a);
        s.push(b);
        s.push(c);

        List<InventoryItem> all = s.getAll();
        // Expect order: a, b, c (bottom -> top)
        assertEquals(3, all.size(), name + " - size");
        assertEquals(a.toString(), all.get(0).toString(), name + " - element 0");
        assertEquals(b.toString(), all.get(1).toString(), name + " - element 1");
        assertEquals(c.toString(), all.get(2).toString(), name + " - element 2");
    }

    private static void testPopEmpty() {
        String name = "testPopEmpty";
        InventoryStack s = new InventoryStack();
        assertTrue(s.isEmpty(), name + " - newly created stack is empty");
        InventoryItem popped = s.pop();
        assertEquals(null, popped, name + " - pop on empty returns null");
        assertEquals(null, s.peek(), name + " - peek on empty returns null");
    }

    private static void testSizeBehavior() {
        String name = "testSizeBehavior";
        InventoryStack s = new InventoryStack();
        assertEquals(0, s.size(), name + " - empty size 0");
        s.push(new InventoryItem(1, "X", 7));
        assertEquals(1, s.size(), name + " - size after one push");
        s.pop();
        assertEquals(0, s.size(), name + " - size after pop back to 0");
    }
}
