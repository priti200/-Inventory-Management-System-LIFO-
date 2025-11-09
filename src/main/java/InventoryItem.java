public class InventoryItem {
    private final int id;
    private final String name;
    private final int quantity;

    public InventoryItem(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Name=\"" + name + "\", Quantity=" + quantity;
    }
}
