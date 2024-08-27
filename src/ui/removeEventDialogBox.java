package ui;

import model.Date;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class removeEventDialogBox extends Dialog {

    private TextField eventNameField;
    private Date calendarDay;

    public removeEventDialogBox(Frame owner, Date calendarDay) {
        super(owner, "New Event Information", true);
        this.calendarDay = calendarDay;

        setLayout(new GridLayout(2 , 2));
        setSize(500,150);

        Label eventNameLabel = new Label("Event name: ");
        eventNameField = new TextField();
        eventNameField.setSize(20,20);

        Button removeButton = new Button("Remove");
        Button cancelButton = new Button("Cancel");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemoveEvent();
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
        add(removeButton);
        add(cancelButton);
    }

    private void handleRemoveEvent() {
        String eventName = eventNameField.getText();
        LocalDate eventDate = calendarDay.currentDate();
        calendarDay.removeEvent(eventName, eventDate);
        calendarDay.save();

    }
}