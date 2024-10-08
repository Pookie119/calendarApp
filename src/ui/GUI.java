package ui;

import model.Date;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;


public class GUI {
    private Frame f;
    private TextArea eventDisplay;
    private Date calendarDay;
    private Panel buttonPanel;

    public GUI(Date calendarDay) {
        this.calendarDay = calendarDay;
        f = new Frame("Calendar");
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.setLayout(new BorderLayout());

        setupTopPanel();
        setupButtonPanel(LocalDate.now());
        setupEventDisplay();


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
                System.exit(0);
            }
        });

        f.setVisible(true);
    }
    private calendarSelector CalendarSelector;

    private void setupTopPanel() {
        int currentYear = LocalDate.now().getYear();
        CalendarSelector = new calendarSelector(currentYear, 2050);

        CalendarSelector.setMonthYearSelectionListener(newDate -> setupButtonPanel(newDate));

        //Set up calendar label for month & days of the week
        Panel topPanel = new Panel();
        topPanel.setLayout(new BorderLayout());

        Panel choicePanel = new Panel();
        choicePanel.setLayout(new FlowLayout());
        choicePanel.add(CalendarSelector);
        topPanel.add(choicePanel, BorderLayout.NORTH);

        Panel labelPanel = new Panel();
        labelPanel.setLayout(new GridLayout(1, 7));

        String[] label = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        for (String day : label) {
            labelPanel.add(new Label(day, Label.CENTER));
        }

        topPanel.add(labelPanel, BorderLayout.CENTER);
        f.add(topPanel, BorderLayout.NORTH);
    }

    private void setupButtonPanel(LocalDate selectedDate) {
        int selectedMonth = selectedDate.getMonthValue();
        int selectedYear = selectedDate.getYear();
        LocalDate firstDayOfMonth = LocalDate.of(selectedYear,selectedMonth,1);
        int monthLength = firstDayOfMonth.getMonth().length(firstDayOfMonth.isLeapYear());
        int startDay = firstDayOfMonth.getDayOfWeek().getValue();

        if (buttonPanel != null){
            f.remove(buttonPanel);
        }

        buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(6, 7)); // added extra row for months that start on weekend

        //Set up Blanks for days before the 1st of the month
        for (int i = 1; i < startDay; i++) {
            Label emptyLabel = new Label("");
            emptyLabel.setBackground(Color.GRAY);
            buttonPanel.add(emptyLabel); //Changed from button to label to make more intuitive
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
            Label emptyLabel = new Label("");
            emptyLabel.setBackground(Color.GRAY);
            buttonPanel.add(emptyLabel);
        }

        //Refresh calendar with user selected month and year
        f.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setForeground(Color.BLUE);
        buttonPanel.setBackground(Color.GRAY);
        f.validate();
        f.repaint();

    }

    private Button currentlySelectedButton = null;

    // getButton method to help with readbility & Added Action Listener to display events for selected day in the text area
    private Button getButton(int day, LocalDate firstDayOfMonth) {
        Button dayButton = new Button(String.valueOf(day));

        dayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Check to see if a dayButton has already been clicked and resets it's color to white
                if(currentlySelectedButton != null){
                    currentlySelectedButton.setBackground(Color.WHITE);
                }

                //Highlights selected dayButton in yellow and displays to the user all events(sorted) for that day
                dayButton.setBackground(Color.getHSBColor(000,000,100));
                currentlySelectedButton = dayButton;
                LocalDate selectedDate = firstDayOfMonth.withDayOfMonth(Integer.parseInt(e.getActionCommand()));
                calendarDay.setDate(selectedDate);
                eventDisplay.setText("Events for today: "+ selectedDate +'\n'+'\n'+ calendarDay.getEvents(selectedDate));
            }
        });
        return dayButton;
    }

    //Set up text display area at bottom of the calendar

    private void setupEventDisplay(){
        Panel eventPanel = new Panel();
        eventPanel.setLayout(new BorderLayout());
        eventPanel.setSize(f.getWidth(), 10);

        eventDisplay = new TextArea();
        eventPanel.add(eventDisplay, BorderLayout.CENTER);

        //Get events for the current date & displays them as the default when launching the app
        String todayEvents = calendarDay.getEvents(LocalDate.now());

        eventDisplay.setText("Events for today: " + calendarDay.currentDate() + '\n' + todayEvents);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //Alow the user to add a new event to a selected date
        Button addEvent = new Button("Add Event");

        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEventDialogBox dialog = new addEventDialogBox(f, calendarDay);
                dialog.setVisible(true);
                refreshCalendar();
                f.repaint();
            }
        });

        //Allow the usre to remove an event on the selected date by name
        Button removeEvent = new Button("Remove Event");

        removeEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEventDialogBox dialog = new removeEventDialogBox(f, calendarDay);
                dialog.setVisible(true);
                refreshCalendar();
                f.repaint();

            }
        });

        buttonPanel.add(addEvent);
        buttonPanel.add(removeEvent);

        eventPanel.add(buttonPanel, BorderLayout.SOUTH);
        f.add(eventPanel, BorderLayout.SOUTH);
    }

    //Refresh the calendar after adding or removing an event
    private void refreshCalendar() {
        LocalDate selectedDate = calendarDay.currentDate();
        String updatedEvents = calendarDay.getEvents(selectedDate);
        eventDisplay.setText("Events for today: " + selectedDate + '\n' + '\n' + updatedEvents);
    }
}
