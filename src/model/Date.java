package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class Date {
    LocalDate myDate = LocalDate.now();
    private Map<LocalDateTime, Event> events = new TreeMap<>();

    public LocalDate currentDate() {
        return myDate;
    }


    public void setDate(LocalDate myDate) {
        this.myDate = myDate;
    }

    //Add function to check if event already scheduled at same time
    public void addEvent(Event event) {
        LocalDateTime eventDateTime = LocalDateTime.of(event.getDate(), event.getStartTime());

        if (events.containsKey(eventDateTime)) {
            JOptionPane.showMessageDialog(null, "Event already booked at this time.");
            return;
        }
        events.put(eventDateTime, event);
    }

    public void removeEvent(String eventName, LocalDate date) {
        events.entrySet().removeIf(entry -> entry.getKey().toLocalDate().equals(date) && entry.getValue().getName().equals(eventName));
    }

    public Integer eventCount(LocalDate date) {
        return (int) events.keySet().stream().filter(dt -> dt.toLocalDate().equals(date)).count();
    }

    public String getEvents(LocalDate date) {

        if (events.isEmpty()) {
            return "Nothing Planned!";
        } else {
            StringBuilder sb = new StringBuilder();
            events.forEach((dateTime, event) -> {
                if (dateTime.toLocalDate().equals(date)) {
                    sb.append(event.getName()).append("\n")
                            .append(event.getDescription()).append("\n")
                            .append("Starts:").append(event.getStartTime())
                            .append(" Ends:").append(event.getEndTime()).append("\n" + "Duration: ").append(event.getDurationInHours()).append(" hours").append("\n\n");
                }
            });
            return sb.toString();
        }
    }

    public int getYear() {
        return currentDate().getYear();
    }

    public Path getDefaultPath() {
        String home = System.getProperty("user.home");
        return Paths.get(home).resolve("calendar.json");
    }

    public void save() {
        save(getDefaultPath());
    }

    public void save(Path path) {
        JSONArray ja = new JSONArray();
        events.forEach((dateTime, event) -> {
            ja.add(event.toJsonObject());
        });
        try {
            Files.write(path, ja.toJSONString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save", e);
        }
        load(path);
    }

    public void load(Path path) {
        if (!Files.exists(path)){
            save(path);
        }

        String content = null;
        try {
            content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            JSONParser parser = new JSONParser();
            JSONArray ja = (JSONArray) parser.parse(content);
            events.clear();

            for (Object obj : ja) {
                JSONObject jsonObject = (JSONObject) obj;
                Event event = Event.fromJsonObject(jsonObject);
                events.put(LocalDateTime.of(event.getDate(), event.getStartTime()), event);
            }
        } catch (IOException e) {
            System.err.println("IOException occurred" + e.getMessage());
            throw new RuntimeException("Failed to load", e);
        } catch (ParseException e) {
            System.err.println("ParseException occurred" + e.getMessage());
            System.err.println("Problem Data" + content);
            throw new RuntimeException("Failed to load", e);
        } catch (Exception e) {
            System.err.println("Unexpected exception occurred" + e.getMessage());
            throw new RuntimeException("Failed to load", e);
        }
    }
}

