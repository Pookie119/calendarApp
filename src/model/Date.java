package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;



public class Date {
    LocalDate myDate = LocalDate.now();
    private Map<LocalDateTime, Event> events = new TreeMap<>();

    //Added duration of event -> Map no longer the appropriate data structure
    //Map<LocalTime, Event> events = new TreeMap<>();

    public LocalDate currentDate() {
        return myDate;
    }

    public void setDate(LocalDate myDate){
        this.myDate = myDate;
    }

    //Add function to check if event already scheduled at same time
    public void addEvent(Event event) {
        LocalDateTime eventDateTime = LocalDateTime.of(event.getDate(), event.getStartTime());
        if (events.containsKey(eventDateTime)){
//            doubleBookedEventTime();
        }
        events.put(eventDateTime, event);
    }

    public void removeEvent(LocalDateTime dateTime){
        events.remove(dateTime);
        }

    public Integer eventCount(LocalDate date){
        return (int) events.keySet().stream().filter(dt -> dt.toLocalDate().equals(date)).count();
    }

    public String getEvents(LocalDate date) {
        if (events.isEmpty()) {
            return "Nothing Planned!";
        } else {
            StringBuilder sb = new StringBuilder();
            events.forEach((dateTime, event) -> {
                if (dateTime.toLocalDate().equals(date)) {
                    sb.append(event.getDescription()).append("\n")
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

    public void printCalendar(LocalDate date){
        LocalDate currentDate = currentDate();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int monthLength = firstDayOfMonth.getMonth().length(currentDate.isLeapYear());
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();


        // print out header
        System.out.println("Mon Tue Wed Thur Fri Sat Sun");

        for (int i = 1; i < firstDayOfWeek; i++){
            System.out.print("    ");
        }

        for (int day = 1; day <= monthLength; day++){
            System.out.printf("%3d ", day);
            if ((day + firstDayOfWeek - 1) % 7 == 0){
                System.out.println();
            }
        }
        if ((monthLength + firstDayOfWeek - 1) % 7 != 0){
            System.out.println();
        }
    }
}

