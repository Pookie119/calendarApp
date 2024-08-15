package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String name;
    private LocalTime startTime;
    private int durationInHours;
    private String description;
    private LocalDate date;

    public Event(String name, LocalTime startTime, int durationInMinutes, String description, LocalDate date) {
        this.name = name;
        this.description = description;
    this.startTime = startTime;
    this.durationInHours = durationInMinutes;
    this.date = date;}

    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getEndTime(){
        return startTime.plusMinutes(durationInHours);
    }

    public float getDurationInHours() {
        return ((float) durationInHours /60);
    }

    public String getDescription() {return description; }
}
