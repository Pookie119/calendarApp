package ui;

import model.Date;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GUI {
    private Frame f;
    private TextArea eventDisplay;
    private Date calendarDay;

    public GUI(Date calendarDay) {
        this.calendarDay = calendarDay;
        f = new Frame("Calendar");
        f.setSize(600, 400);
        f.setLayout(new BorderLayout());

        setupTopPanel();
        setupButtonPanel();

        eventDisplay = new TextArea(calendarDay.getEvents());
        f.add(eventDisplay, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    private void setupTopPanel() {
        LocalDate date = LocalDate.now();
        String month = String.valueOf(date.getMonth());

        //Set up calendar label for month & days of the week
        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());

        Label monthLabel = new Label(month, Label.CENTER);
        topPanel.add(monthLabel, BorderLayout.NORTH);

        Panel labelPanel = new Panel();
        labelPanel.setLayout(new GridLayout(1, 7));

        String[] label = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : label) {
            labelPanel.add(new Label(day, Label.CENTER));
        }

        topPanel.add(labelPanel, BorderLayout.CENTER);
        f.add(topPanel, BorderLayout.NORTH);
    }

    private void setupButtonPanel() {
        LocalDate date = LocalDate.now();
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        int monthLength = firstDayOfMonth.getMonth().length(date.isLeapYear());
        int startDay = firstDayOfMonth.getDayOfWeek().getValue();

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(6, 7)); // added extra row for months that start on weekend

        //Set up Blanks for days before the 1st of the month
        for (int i = 1; i < startDay; i++) {
            buttonPanel.add(new Label("")); //Changed from button to label to make more intuitive
        }

        //Set up buttons on calendar for each day of the month
        for (int day = 1; day <= monthLength; day++) {
            Button dayButton = getButton(day, firstDayOfMonth);
            dayButton.setBackground(Color.WHITE);
            buttonPanel.add(dayButton);
        }

        //Fill in remaining cells in grid with mt labels - Should fix alignment issue with added row
        int totalCells = 6 * 7;
        int filledCells = startDay - 1 + monthLength;
        for (int i = filledCells; i < totalCells; i++) {
            buttonPanel.add(new Label(""));
        }
        f.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setForeground(Color.BLUE);
        buttonPanel.setBackground(Color.GRAY);

    }
    // getButton method to help with readbility & Added Action Listener to display events for selected day in the text area
    private Button getButton(int day, LocalDate firstDayOfMonth) {
        Button dayButton = new Button(String.valueOf(day));
        dayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate selectedDate = firstDayOfMonth.withDayOfMonth(Integer.parseInt(e.getActionCommand()));
                calendarDay.setDate(selectedDate);
                eventDisplay.setText("Events for: " + selectedDate +'\n'+'\n'+ calendarDay.getEvents());
            }
        });
        return dayButton;

    }
}
