package model;

import org.json.simple.JSONObject;

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
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEndTime() {
        return startTime.plusMinutes(durationInHours);
    }

    public float getDurationInHours() {
        return ((float) durationInHours / 60);
    }

    public String getDescription() {
        return description;
    }

    public JSONObject toJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("startTime", startTime.toString());
        jo.put("date", date.toString());
        jo.put("duration", durationInHours);
        jo.put("description", description);
        return jo;
    }

    public static Event fromJsonObject(JSONObject jo) {
        try {
            String name = (String) jo.get("name");
            LocalTime timeStr = LocalTime.parse((String) jo.get("startTime"));
            LocalDate eventDate = LocalDate.parse((String) jo.get("date"));
            int duration = ((Long) jo.get("duration")).intValue();
            String description = (String) jo.get("description");
            return new Event(name, timeStr, duration, description, eventDate);
        } catch (Exception e) {
            System.err.println("Error parsing JSON obj: " + jo.toJSONString());
            throw e;
        }
    }
}


