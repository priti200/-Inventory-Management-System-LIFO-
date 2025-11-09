import java.util.ArrayList;
import java.util.List;

public class InventoryStack {
    private final ArrayList<InventoryItem> stack = new ArrayList<>();

    // Push: add to top
    public void push(InventoryItem item) {
        stack.add(item);
    }

    // Pop: remove and return top, or null if empty
    public InventoryItem pop() {
        if (stack.isEmpty()) return null;
        return stack.remove(stack.size() - 1);
    }

    // Peek: view top without removing
    public InventoryItem peek() {
        if (stack.isEmpty()) return null;
        return stack.get(stack.size() - 1);
    }

    // Return all items from bottom->top
    public List<InventoryItem> getAll() {
        return new ArrayList<>(stack);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
