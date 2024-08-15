package ui;


import model.Date;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogBox extends Dialog {

    private TextField eventNameField;
    private TextField eventDescriptionField;
    private Date calendarDay;

    public DialogBox(Frame owner, Date calendarDay) {
        super(owner, "New Event Information", true);
        this.calendarDay = calendarDay;

        setLayout(new GridLayout(3, 2));
        setSize(400, 300);

        Label eventNameLabel = new Label("Event name: ");
        eventNameField = new TextField();

        Label eventDescriptionLabel = new Label("Description");
        eventDescriptionField = new TextField();

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddEvent();

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
        add(addButton);
        add(cancelButton);


    }

    private void handleAddEvent(){
        String eventName = eventNameField.getText();
        String eventDescription = eventDescriptionField.getText();

    }

}
