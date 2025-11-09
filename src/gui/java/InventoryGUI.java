import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Simple Swing GUI for the Inventory (LIFO)
public class InventoryGUI extends JFrame {
    private final InventoryStack inventory = new InventoryStack();

    private final JTextField idField = new JTextField(8);
    private final JTextField nameField = new JTextField(12);
    private final JTextField qtyField = new JTextField(6);
    private final JTextArea outputArea = new JTextArea(12, 40);

    public InventoryGUI() {
        super("Inventory Management (LIFO) - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Item"));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(qtyField);

        JButton addBtn = new JButton("Add (Push)");
        JButton peekBtn = new JButton("Peek");
        JButton popBtn = new JButton("Pop");
        JButton viewBtn = new JButton("View All");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(addBtn);
        buttons.add(peekBtn);
        buttons.add(popBtn);
        buttons.add(viewBtn);

        JPanel top = new JPanel(new BorderLayout());
        top.add(inputPanel, BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Output"));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

        // Actions
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        peekBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onPeek(); }
        });

        popBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onPop(); }
        });

        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onViewAll(); }
        });
    }

    private void onAdd() {
        // reset output for each action
        outputArea.setText("");
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            InventoryItem it = new InventoryItem(id, name, qty);
            inventory.push(it);
            append("Added: " + it);
        } catch (NumberFormatException ex) {
            append("Invalid number format for ID or Quantity.");
        }
    }

    private void onPeek() {
        // reset output for each action
        outputArea.setText("");
        InventoryItem top = inventory.peek();
        if (top == null) append("Inventory is empty.");
        else append("Top: " + top);
    }

    private void onPop() {
        // reset output for each action
        outputArea.setText("");
        InventoryItem removed = inventory.pop();
        if (removed == null) append("Inventory is empty. Nothing to remove.");
        else append("Removed: " + removed);
    }

    private void onViewAll() {
        // reset output for each action
        outputArea.setText("");
        List<InventoryItem> all = inventory.getAll();
        if (all.isEmpty()) {
            append("Inventory is empty.");
            return;
        }
        append("Inventory (Bottom -> Top):");
        for (InventoryItem it : all) append(" - " + it);
    }

    private void append(String s) {
        outputArea.append(s + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InventoryGUI().setVisible(true);
            }
        });
    }
}
