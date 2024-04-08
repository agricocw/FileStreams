import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RandProductSearch extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField partialNameField;
    private JTextArea resultArea;

    public RandProductSearch() {
        setTitle("Random Product Search");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JLabel partialNameLabel = new JLabel("Enter Partial Product Name:");
        partialNameField = new JTextField(20);
        inputPanel.add(partialNameLabel);
        inputPanel.add(partialNameField);
        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchProducts();
            }
        });
        add(searchButton, BorderLayout.SOUTH);
    }

    private void searchProducts() {
        String partialName = partialNameField.getText();

        // Assuming products are stored in a text file named "products.dat"
        try (BufferedReader reader = new BufferedReader(new FileReader("products.dat"))) {
            String line;
            resultArea.setText(""); // Clear previous results
            while ((line = reader.readLine()) != null) {
                if (line.contains(partialName)) {
                    resultArea.append(line + "\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RandProductSearch().setVisible(true);
            }
        });
    }
}

