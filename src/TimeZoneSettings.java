import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

public class TimeZoneSettings extends JDialog {

    private DigitalClock digitalClock;
    private JComboBox<String> timeZoneComboBox;
    private JLabel timeZoneLabel;

    public TimeZoneSettings(DigitalClock digitalClock) {
        super(digitalClock, "Set Time Zone", true);
        this.digitalClock = digitalClock;

        // Create components
        timeZoneComboBox = new JComboBox<>(getTimeZoneIDs());
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyTimeZone();
            }
        });

        timeZoneLabel = new JLabel("Selected Time Zone: " + TimeZone.getDefault().getID());

        // Set layout
        setLayout(new FlowLayout());
        add(new JLabel("Select Time Zone:"));
        add(timeZoneComboBox);
        add(applyButton);
        add(timeZoneLabel); // Add the label to the layout

        // Set dialog properties
        setSize(350, 150);
        setLocationRelativeTo(digitalClock);
    }

    private void applyTimeZone() {
        String selectedTimeZoneID = (String) timeZoneComboBox.getSelectedItem();
        TimeZone selectedTimeZone = TimeZone.getTimeZone(selectedTimeZoneID);
        digitalClock.setTimeZone(selectedTimeZone);
        timeZoneLabel.setText("Selected Time Zone: " + selectedTimeZoneID); // Update the label
    }

    private String[] getTimeZoneIDs() {
        return TimeZone.getAvailableIDs();
    }
}
