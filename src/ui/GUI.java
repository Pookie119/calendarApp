package ui;

import java.awt.*;
import java.time.LocalDate;


public class GUI {
    private Frame f;
    private TextArea eventDisplay;

    GUI() {
        f = new Frame("Calendar");
        f.setSize(600, 400);
        f.setLayout(new BorderLayout());

        LocalDate date = LocalDate.of(2025,3,1);
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        String month = String.valueOf(date.getMonth());
        int monthLength = firstDayOfMonth.getMonth().length(date.isLeapYear());
        int startDay = firstDayOfMonth.getDayOfWeek().getValue();

        //Set up calendar label for month & days of the week
        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());

        Label monthLabel = new Label(month, Label.CENTER);
        topPanel.add(monthLabel, BorderLayout.NORTH);

        Panel labelPanel = new Panel();
        labelPanel.setLayout(new GridLayout(1, 7));

        String[] label = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
        for (String day : label){
            labelPanel.add(new Label(day, Label.CENTER));
        }
        topPanel.add(labelPanel, BorderLayout.CENTER);


        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(6, 7)); // added extra row for months that start on weekend
//        buttonPanel.addMouseListener(l);

        //Set up Blanks for days before the 1st of the month
        for (int i = 1; i < startDay; i++){
            buttonPanel.add(new Button(""));
        }

        //Set up buttons on calendar for each day of the month
        for (int day = 1; day <= monthLength; day++) {
            buttonPanel.add(new Button(String.valueOf(day)));
            }

        //Add button panel to frame and set visible
        f.add(topPanel, BorderLayout.NORTH);
        f.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setForeground(Color.BLUE);
        f.setVisible(true);
    }
}