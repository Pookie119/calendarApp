package ui;

import model.Date;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class addEventDialogBox extends Dialog {

    private TextField eventNameField;
    private TextField eventDescriptionField;
    private TextField eventStartTime;
    private TextField  eventDurationField;
    private Date calendarDay;

    public addEventDialogBox(Frame owner, Date calendarDay) {
        super(owner, "New Event Information", true);
        this.calendarDay = calendarDay;

        setLayout(new GridLayout(5, 2));
        setSize(600,700);

        Label eventNameLabel = new Label("Event name: ");
        eventNameField = new TextField();
        eventNameField.setSize(20,20);

        Label eventDescriptionLabel = new Label("Description");
        eventDescriptionField = new TextField();

        Label eventStartTimeLabel = new Label("Start time (hh:mm)");
        eventStartTime = new TextField();

        Label eventDurationLabel = new Label("Duration (min)");
        eventDurationField = new TextField();

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddEvent();
                dispose();


            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(eventNameLabel);
        add(eventNameField);
        add(eventDescriptionLabel);
        add(eventDescriptionField);
        add(eventStartTimeLabel);
        add(eventStartTime);
        add(eventDurationLabel);
        add(eventDurationField);
        add(addButton);
        add(cancelButton);
    }

    private void handleAddEvent(){
        String startTime = eventStartTime.getText();
        String eventName = eventNameField.getText();
        if (eventName == null || eventName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Name cannot be empty");
            return;
        }
        String eventDescription = eventDescriptionField.getText();
        String durationText = eventDurationField.getText();
        int duration = Integer.parseInt(durationText);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTimeParsed = LocalTime.parse(startTime, formatter);
        LocalDate eventDate = calendarDay.currentDate();

        Event newEvent = new Event(eventName, startTimeParsed, duration , eventDescription, eventDate);
        calendarDay.addEvent(newEvent);
        System.out.println(eventDate);
        calendarDay.save();
        }


    }

