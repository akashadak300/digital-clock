import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;
import javax.swing.border.LineBorder;
import java.util.Timer;

public class DigitalClock extends JFrame {
    private Font font = new Font("", Font.BOLD, 35);
    private JLabel timeLabel;
    private JLabel dateLabel;
    // private JPanel clockPanel;
    private DigitalClockAppearanceSettings appearanceSettings;
    private JLabel timeZoneLabel; // New label to display the selected time zone
    private TimeZone timeZone = TimeZone.getDefault(); // Default time zone
    private JPanel panel4, panel1,panel2,panel3;
    private JButton setAlarmButton;



    public DigitalClock() {
        // Initialize the JFrame
        super("Digital Clock");
        setLayout(new GridLayout(4,1));
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        startClock();

        // Create components
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        timeLabel.setForeground(Color.GREEN);
        dateLabel = new JLabel("00-00-0000", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        panel1 = new JPanel();
        panel1.add(timeLabel);

        panel2 = new JPanel();
        panel2.add(dateLabel);

        // Add clockPanel to the frame
        add(panel1);
        add(panel2);

        panel4 = new JPanel();
        setAlarmButton = new JButton("Set Alarm");
        panel4.add(setAlarmButton);
        setAlarmButton.setFont(font);

        setAlarmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              new SetAlarmFrame(DigitalClock.this);
            }
      
          });
        add(panel4);

    





        // Create the "Appearances" button
        JButton appearanceButton = new JButton("Appearances");
        appearanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAppearanceOptions();
            }
        });

        // Add the "Appearances" button to the frame
        // add(appearanceButton, BorderLayout.SOUTH);
    panel4.add(appearanceButton);

        // Initialize DigitalClockAppearanceSettings
        appearanceSettings = new DigitalClockAppearanceSettings(this);

        JButton timeZoneButton = new JButton("Set Time Zone");
        timeZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTimeZoneSettings();
            }
        });

        timeZoneLabel = new JLabel("Selected Time Zone: " + timeZone.getID()); // Initialize the label

        // Add the "Set Time Zone" button and label to the frame
        panel3=new JPanel();
        panel3.add(timeZoneLabel);
        panel3.add(timeZoneButton);
        add(panel3);
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
        return ((LineBorder) panel2.getBorder()).getThickness();
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
        timeZoneLabel.setText("Selected Time Zone: " + timeZone.getID()); // Update the label
        // Schedule the update on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                updateClock();
            }
        });
    }

    private void updateClock() {
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Set the time zone for formatting
        timeFormat.setTimeZone(timeZone);
        dateFormat.setTimeZone(timeZone);

        String time = timeFormat.format(currentDate);
        String date = dateFormat.format(currentDate);
        timeLabel.setText(time);
        dateLabel.setText(date);
    }
}
