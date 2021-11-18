import javax.swing.*;
import java.awt.*;

/**
 * This class handles the dialogue box that pops up when the user double clicks to edit a city.
 * It is mainly responsible for fetching the new shape, color and size for the decorations that
 * are to be applied to the base shape.
 *
 * @author
 * @version 1.0
 * @since 2021-11-12
 */
public class EditCityDialog extends JDialog {
    private static final int MIN_CITY_SIZE = 10;
    private static final int MAX_CITY_SIZE = 50;
    private final JTextField labelTextField;
    private final JTextField sizeTextField;
    private final JColorChooser colorChooser;
    private boolean isUpdated = false;

    /**
     * Method responsible for the dialogue box and fetching the new parameters.
     * @param parent points to the JFrame object.
     * @param city the city which needs to be edited.
     */
    public EditCityDialog(JFrame parent, City city) {
        super(parent, "Edit City Properties", true);
        setSize(550, 550);
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

        JPanel shapeDecorationPanel = new JPanel();
        shapeDecorationPanel.setBorder(BorderFactory.createTitledBorder("Shape Decoration"));
        shapeDecorationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JCheckBox[] shapeDecorationCheckBoxList = new JCheckBox[5];
        shapeDecorationCheckBoxList[0] = new JCheckBox("Circle", city.getOptions()[0]);
        shapeDecorationCheckBoxList[1] = new JCheckBox("Square [North]", city.getOptions()[1]);
        shapeDecorationCheckBoxList[2] = new JCheckBox("Square [East]", city.getOptions()[2]);
        shapeDecorationCheckBoxList[3] = new JCheckBox("Square [West]", city.getOptions()[3]);
        shapeDecorationCheckBoxList[4] = new JCheckBox("Square [South]", city.getOptions()[4]);
        for (JCheckBox shapeDecorationCheckBox : shapeDecorationCheckBoxList)
            shapeDecorationPanel.add(shapeDecorationCheckBox);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(shapeDecorationPanel, constraints);

        colorChooser = new JColorChooser(city.getColor());
        colorChooser.setBorder(BorderFactory.createTitledBorder("Color"));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 5, 0, 5);
        add(colorChooser, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 3;

        JButton applyButton = new JButton("Apply");
        applyButton.setActionCommand("Apply");
        applyButton.setPreferredSize(new Dimension(75, 25));
        applyButton.addActionListener(ev -> {
            if (validateInput()) {
                city.setLabel(labelTextField.getText());
                int size = Integer.parseInt(sizeTextField.getText());
                city.setDimension(new Dimension(size, size));
                city.setColor(colorChooser.getColor());

                boolean[] options = new boolean[5];
                for (int i = 0; i < shapeDecorationCheckBoxList.length; i++)
                    options[i] = shapeDecorationCheckBoxList[i].isSelected();
                city.setOptions(options);

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

    /**
     * Used to check if the new parameters have been applied.
     * @return true or false.
     */
    public boolean getUpdated() {
        return isUpdated;
    }

    /**
     * Used to check if user has entered valid data into the dialogue box.
     * @return true or false.
     */
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
