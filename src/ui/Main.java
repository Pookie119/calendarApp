package ui;

import model.Date;
import model.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Date d = new Date();
        new GUI(d);
        Scanner userEvent = new Scanner(System.in);
        System.out.println(d.currentDate().getMonth() + " " + d.getYear());
        d.printCalendar();


        // Add Events
        Event morningMeeting = new Event(LocalTime.of(10, 0), 30, "Meeting With Bailey", LocalDate.of(2024, 8, 13));
        d.addEvent(morningMeeting);

        Event Game = new Event(LocalTime.of(20, 0), 300, "Pick fiber W Nick", LocalDate.of(2024, 8, 13));
        d.addEvent(Game);

        Event dinnerWithSophie = new Event(LocalTime.of(18, 0), 180, "Dinner with Wifey", LocalDate.of(2024, 8, 13));
        d.addEvent(dinnerWithSophie);

        System.out.println("\nYou have " + d.eventCount() + " events today." + "\n");
        // Print the day calendar
        System.out.println(d.getEvents());

        System.out.println("Add(A) or Remove(R) event from calendar? (A/R): ");
        String addEvent = userEvent.nextLine();

        if (Objects.equals(addEvent, "A")) {

            System.out.println("Enter new event name details\n");

            System.out.println("Event Name:");
            String name = userEvent.nextLine();

            System.out.println("Event Date (dd/mm/yyyy) :");
            String date = userEvent.nextLine();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate;
            try {
                parsedDate = LocalDate.parse(date, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Format");
                return;
            }

            System.out.println("Start Time (hh:mm) :");
            String startTime = userEvent.nextLine();

            System.out.println("Duration (min) :");
            int duration = userEvent.nextInt();
            userEvent.nextLine();

            System.out.println("Notes:");
            String description = userEvent.nextLine();

            DateFormat sdf = new SimpleDateFormat("hh:mm aa");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTimeParsed;
            try {
                startTimeParsed = LocalTime.parse(startTime, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format.");
                return;
            }

            Event userAddedEvent = new Event(startTimeParsed, duration, description, parsedDate);
            d.addEvent(userAddedEvent);
            System.out.println("User added event name is : " + name + " " + date + " " + startTime + " " + duration + " " + description);
        }
        System.out.println(d.getEvents());
    }
}
