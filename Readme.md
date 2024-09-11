# Digital Clock Project

This project implements a simple **Digital Clock** with additional features such as setting alarms, customizing appearance, and selecting time zones. The graphical user interface (GUI) is built using **Java Swing**.

---

## Project Structure

- **Main.java**: Contains the main method that runs the digital clock.
- **DigitalClock.java**: Implements the core digital clock functionality (displaying time, date, etc.).
- **SetAlarmFrame.java**: Handles alarm settings and triggers the alarm at the specified time.
- **TimeZoneSettings.java**: Manages functionality for selecting and setting time zones.
- **DigitalClockAppearanceSettings.java**: Allows users to customize the clock's appearance, such as changing text color, frame color, and other visual aspects.

---

## Features

- **Real-time clock**: Displays current time, updated every second.
- **Alarm**: Set alarms with customized times.
- **Customizable appearance**: Adjust the clock's text, background, frame color, and other settings.
- **Time zone configuration**: Select from a list of time zones to adjust the clock to your preferred location.
- **12/24-hour format**: Switch between 12-hour and 24-hour time display.
- **Wake-up alarm**: Set alarms with custom sounds for different days of the week.

---

## Instructions to Run the Project

1. **Compile and Run Main.java:**
   - Open a terminal or command prompt.
   - Navigate to the `src` folder where the source files are located.
   - Use the following commands to compile and run the application:
     ```bash
     javac Main.java
     java Main
     ```

2. **Setting an Alarm:**
   - Once the digital clock is running, click the "Set Alarm" button on the GUI.
   - Enter the desired alarm time, choose the sound, and click "OK" to set the alarm.

3. **Customizing Appearance:**
   - Click the "Appearances" button to open the settings window.
   - Customize various options such as text color, background color, frame border, and date format to your liking.

4. **Setting Time Zone:**
   - Click the "Set Time Zone" button to open a window where you can select a time zone from a predefined list.

5. **Exiting the Application:**
   - To exit, simply close the digital clock window by clicking the close button on the frame.

---

## Requirements

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed on your system to compile and run the project.
  - You can check if Java is installed by running:
    ```bash
    java -version
    ```
- **Sound Files for Alarm**: Place the sound files for the alarms in the same directory as the source files for correct functionality.

---

## Customization Options

1. **Alarm Settings**:
   - You can modify the `SetAlarmFrame.java` class to add additional features, such as recurring alarms or different sounds for different days.
   
2. **Appearance Customization**:
   - The `DigitalClockAppearanceSettings.java` class provides various methods to adjust the appearance.
   - Customize colors, fonts, and date formats to match your personal preferences.

3. **Adding New Time Zones**:
   - You can expand the time zone selection by modifying the `TimeZoneSettings.java` file.

---

## Notes

- Make sure you have the latest version of Java installed for optimal performance.
- The GUI is built using Java Swing, making it compatible with most systems that support Java.
- Alarm sounds can be customized by adding your preferred sound files in the project's root directory.

---

## Acknowledgements

This project was developed as part of a learning exercise and can be freely used, modified, or extended. Contributions and feedback are always welcome!

---

Enjoy your digital clock! Keep track of time in style with customizable features, alarms, and time zone settings.

**Happy Coding!**


