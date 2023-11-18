import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitalClockAppearanceSettings {

    private final DigitalClock digitalClock;

    public DigitalClockAppearanceSettings(DigitalClock digitalClock) {
        this.digitalClock = digitalClock;
    }

    public void showAppearanceOptions() {
        // Create a dialog for appearance options
        JDialog appearanceDialog = new JDialog((Frame) SwingUtilities.getRoot(digitalClock), "Appearance Options", true);
        appearanceDialog.setSize(300, 200);
        appearanceDialog.setLocationRelativeTo(digitalClock);

        // Create buttons for different appearance options
        JButton textColorButton = new JButton("Text Color");
        JButton frameColorButton = new JButton("Frame Color");
        JButton backgroundColorButton = new JButton("Background Color");
        JButton frameWidthButton = new JButton("Frame Width");
        JButton dateStyleButton = new JButton("Date Style");

        // Add action listeners to the buttons
        textColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTextColor();
            }
        });

        frameColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFrameColor();
            }
        });

        backgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor();
            }
        });

        frameWidthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFrameWidth();
            }
        });

        dateStyleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDateStyle();
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
        buttonPanel.add(textColorButton);
        buttonPanel.add(frameColorButton);
        buttonPanel.add(backgroundColorButton);
        buttonPanel.add(frameWidthButton);
        buttonPanel.add(dateStyleButton);

        // Add the buttonPanel to the dialog
        appearanceDialog.add(buttonPanel);

        // Make the dialog visible
        appearanceDialog.setVisible(true);
    }

    private void changeTextColor() {
        Color selectedColor = JColorChooser.showDialog(digitalClock, "Choose Text Color", Color.BLACK);
        digitalClock.setClockTextColor(selectedColor);
    }

    private void changeFrameColor() {
        Color selectedColor = JColorChooser.showDialog(digitalClock, "Choose Frame Color", Color.GRAY);
        digitalClock.setClockFrameColor(selectedColor);
    }

    private void changeBackgroundColor() {
        Color selectedColor = JColorChooser.showDialog(digitalClock, "Choose Background Color", Color.WHITE);
        digitalClock.setClockBackgroundColor(selectedColor);
    }

    private void changeFrameWidth() {
        String widthStr = JOptionPane.showInputDialog(digitalClock, "Enter Frame Width:");
        try {
            int width = Integer.parseInt(widthStr);
            digitalClock.setClockFrameWidth(width);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(digitalClock, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeDateStyle() {
        String[] styles = {"Short", "Medium", "Long"};
        String selectedStyle = (String) JOptionPane.showInputDialog(digitalClock, "Choose Date Style:", "Date Style", JOptionPane.QUESTION_MESSAGE, null, styles, styles[0]);
        // Implement code to set the date style based on the selected option
        // You can use SimpleDateFormat to format the date accordingly
        // For example:
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String formattedDate = dateFormat.format(new Date());
    }
}
