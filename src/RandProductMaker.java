import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RandProductMaker extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField IDField, nameField, descriptionField, costField, recordCountField;
    private int recordCount = 0;

    public RandProductMaker() {
        setTitle("Random Product Maker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel IDLabel = new JLabel("ID:");
        IDField = new JTextField();
        add(IDLabel);
        add(IDField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        add(descriptionLabel);
        add(descriptionField);

        JLabel costLabel = new JLabel("Cost:");
        costField = new JTextField();
        add(costLabel);
        add(costField);

        JButton addButton = new JButton("Add Record");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });
        add(addButton);

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window when done
            }
        });
        add(doneButton);

        JLabel recordCountLabel = new JLabel("Record Count:");
        recordCountField = new JTextField();
        recordCountField.setEditable(false);
        add(recordCountLabel);
        add(recordCountField);
    }

    private void addRecord() {
        String ID = padWithSpaces(IDField.getText(), 6);
        String name = padWithSpaces(nameField.getText(), 35);
        String description = padWithSpaces(descriptionField.getText(), 40); // Adjusted padding for description
        double cost = Double.parseDouble(costField.getText());

        Product product = new Product(ID, name, description, cost);
        String record = product.toRandomRecord() + "\n"; // Add newline for next record

        try (FileWriter fileWriter = new FileWriter("products.dat", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(record);
            recordCount++;
            recordCountField.setText(Integer.toString(recordCount));
            clearFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String padWithSpaces(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        } else {
            return String.format("%-" + length + "s", str);
        }
    }

    private void clearFields() {
        IDField.setText("");
        nameField.setText("");
        descriptionField.setText("");
        costField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RandProductMaker().setVisible(true);
            }
        });
    }
}
