import javax.swing.*;
import java.awt.*;

public class EditCityDialog extends JDialog {
    private static final int MIN_CITY_SIZE = 10;
    private static final int MAX_CITY_SIZE = 50;

    private boolean isUpdated = false;

    private final JTextField labelTextField;
    private final JTextField sizeTextField;
    private final JColorChooser colorChooser;

    public EditCityDialog(JFrame parent, City city) {
        super(parent, "Edit City Properties", true);
        setSize(500, 550);
        setResizable(false);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        labelTextField = new JTextField(city.getLabel());
        labelTextField.setBorder(BorderFactory.createTitledBorder("Label"));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 0, 5);
        add(labelTextField, constraints);

        sizeTextField = new JTextField(Integer.toString(city.getDimension().height));
        sizeTextField.setBorder(BorderFactory.createTitledBorder(String.format("Size (Between %d to %d)", MIN_CITY_SIZE,
                MAX_CITY_SIZE)));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 0, 5);
        add(sizeTextField, constraints);

        colorChooser = new JColorChooser(city.getColor());
        colorChooser.setBorder(BorderFactory.createTitledBorder("Color"));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 5, 0, 5);
        add(colorChooser, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 2;

        JButton applyButton = new JButton("Apply");
        applyButton.setActionCommand("Apply");
        applyButton.setPreferredSize(new Dimension(75, 25));
        applyButton.addActionListener(ev -> {
            if (validateInput()) {
                city.setLabel(labelTextField.getText());
                int size = Integer.parseInt(sizeTextField.getText());
                city.setDimension(new Dimension(size, size));
                city.setColor(colorChooser.getColor());
                city.setShape(1);
                isUpdated = true;
                setVisible(false);
            }
        });
        constraints.insets = new Insets(5, 0, 0, 90);
        add(applyButton, constraints);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.setPreferredSize(new Dimension(75, 25));
        cancelButton.addActionListener(ev -> setVisible(false));
        constraints.insets = new Insets(5, 0, 0, 5);
        add(cancelButton, constraints);
    }

    public boolean getUpdated() {
        return isUpdated;
    }

    private boolean validateInput() {
        if (labelTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Label cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int size = Integer.parseInt(sizeTextField.getText());
            if (size < MIN_CITY_SIZE || size > MAX_CITY_SIZE) {
                JOptionPane.showMessageDialog(this, String.format("Size should be between %d and %d", MIN_CITY_SIZE,
                        MAX_CITY_SIZE), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Size is not a valid integer", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
