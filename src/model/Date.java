package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Date {
    LocalDate myDate = LocalDate.now();
    List<Event> events = new ArrayList<>();

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
        events.add(event);
        events.sort(Comparator.comparing(Event::getStartTime));
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public Integer eventCount(){
        int counter = 0;
        for (Event event : events){
            if (event.getDate().equals(myDate)){
                counter += 1;
            }
        }
        return counter;
    }

    public String getEvents() {
        if (events.isEmpty()) {
            return "Nothing Planned!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Event event : events) {
                if (event.getDate().equals(myDate)){
                sb.append(event.getDescription()).append("\n")
                        .append("Starts:").append(event.getStartTime())
                        .append(" Ends:").append(event.getEndTime()).append("\n" + "Duration: ").append(event.getDurationInHours()).append(" hours").append("\n\n");
            }}
            return sb.toString();
        }
    }

    public int getYear() {
        return currentDate().getYear();
    }

    public void printCalendar(){
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

