import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.Timer;

public class DigitalClock extends JFrame {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Default date format
    private Font font = new Font("", Font.BOLD, 20);
    private Font fontForCopyRight = new Font("", Font.BOLD, 12);
    private JLabel timeLabel;
    private JLabel dateLabel;
    private DigitalClockAppearanceSettings appearanceSettings;
    private JLabel timeZoneLabel;
    private TimeZone timeZone = TimeZone.getDefault();
    private JPanel panel1, panel2, panel3, panel4;
    private JButton setAlarmButton;

    public DigitalClock() {
        // Initialize the JFrame
        super("Digital Clock");
        setLayout(new GridLayout(4, 1));
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dateLabel = new JLabel("00-00-0000", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        startClock();
        panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        // panel1.add(dateLabel);
        panel1.add(dateLabel);
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // panel2 = createPanel();
        // panel2.add(timeLabel);
        timeZoneLabel = new JLabel(timeZone.getID());

        // Create a new panel for the date and timeZoneLabel
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        timePanel.add(timeLabel);
        timePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        timePanel.add(timeZoneLabel);

        // Add timePanel to the frame

        // Add clockPanel to the frame
        add(panel1);
        add(timePanel);
        // add(panel2);

        panel4 = createPanel();
        setAlarmButton = new JButton("Set Alarm");
        setAlarmButton.setFont(font);
        setAlarmButton.setFocusPainted(false);

        panel4.add(setAlarmButton);

        setAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetAlarmFrame(DigitalClock.this);
            }
        });
        add(panel4);

        // Create the "Appearances" button
        JButton appearanceButton = new JButton("Appearances");
        appearanceButton.setFont(font);

        appearanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAppearanceOptions();
            }
        });

        panel4.add(appearanceButton);

        // Initialize DigitalClockAppearanceSettings
        appearanceSettings = new DigitalClockAppearanceSettings(this);

        JButton timeZoneButton = new JButton("Set Time Zone");
        timeZoneButton.setFont(font);
        timeZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTimeZoneSettings();
            }
        });

        panel3 = createPanel();
        // panel3.add(timeZoneLabel);
        JLabel copyrightLabel = new JLabel("© Your Company 2023"); // Replace with your copyright text
        copyrightLabel.setFont(fontForCopyRight);
        copyrightLabel.setForeground(Color.WHITE);
        panel3.add(timeZoneButton);
        panel3.add(copyrightLabel);
        panel3.setBackground(Color.gray);
        add(panel3);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));// Add padding
        return panel;
    }

    public void setDateLabelFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void updateClock() {
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");

        // Set the time zone for formatting
        timeFormat.setTimeZone(timeZone);

        String time = timeFormat.format(currentDate);
        String date = dateFormat.format(currentDate);

        timeLabel.setText(time);
        dateLabel.setText(date);
    }

    private void showAppearanceOptions() {
        appearanceSettings.showAppearanceOptions();
    }

    // Getter and setter methods for appearance settings
    // Setter method for text color
    public void setClockTextColor(Color color) {
        timeLabel.setForeground(color);
        dateLabel.setForeground(color);
    }

    // Setter method for frame color
    public void setClockFrameColor(Color color) {
        panel1.setBorder(BorderFactory.createLineBorder(color, getClockFrameWidth()));
        panel2.setBorder(BorderFactory.createLineBorder(color, getClockFrameWidth()));
    }

    // Setter method for background color
    public void setClockBackgroundColor(Color color) {
        panel1.setBackground(color);
        panel2.setBackground(color);
    }

    // Setter method for frame width
    public void setClockFrameWidth(int width) {
        panel1.setBorder(BorderFactory.createLineBorder(getClockFrameColor(), width));

        panel2.setBorder(BorderFactory.createLineBorder(getClockFrameColor(), width));
    }

    // Getter method for frame color
    public Color getClockFrameColor() {
        return ((LineBorder) panel1.getBorder()).getLineColor();
    }

    // Getter method for frame width
    public int getClockFrameWidth() {
        Border border = panel2.getBorder();
        if (border instanceof LineBorder) {
            return ((LineBorder) border).getThickness();
        } else {
            // If the border is not an instance of LineBorder, return a default value or
            // handle it accordingly
            return 0; // Default value, you can change it based on your requirements
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DigitalClock().setVisible(true);
            }
        });
    }

    public void startClock() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                updateClock(); // Update the clock once

            }
        };
        timer.schedule(task, 0, 1000);
    }

    private void showTimeZoneSettings() {
        TimeZoneSettings timeZoneSettings = new TimeZoneSettings(this);
        timeZoneSettings.setVisible(true);
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
        timeZoneLabel.setText("Selected Time Zone: " + timeZone.getID());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updateClock();
            }
        });
    }

    // public void updateClock() {
    // Date currentDate = new Date();
    // SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
    // SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // timeFormat.setTimeZone(timeZone);
    // dateFormat.setTimeZone(timeZone);

    // String time = timeFormat.format(currentDate);
    // String date = dateFormat.format(currentDate);
    // timeLabel.setText(time);
    // dateLabel.setText(date);
    // }
}
