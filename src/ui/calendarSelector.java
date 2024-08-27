package ui;

import java.awt.*;
import java.time.LocalDate;

public class calendarSelector extends Panel {
    private Choice monthChoice;
    private Choice yearChoice;
    private LocalDate selectedDate;

    public interface monthYearChangeListener{
        void onMonthYearChange(LocalDate newDate);
    }

    private monthYearChangeListener listener;

    public calendarSelector (int startYear, int endYear){
        this.selectedDate = LocalDate.now();
        setLayout(new FlowLayout());

        //Setup month choices
        monthChoice = new Choice();
        for (int i = 1; i <= 12; i++){
            monthChoice.add(String.valueOf(i));
        }
        monthChoice.select(selectedDate.getMonthValue() - 1);

        //Setup year choices
        yearChoice = new Choice();
        for (int i = startYear; i <= endYear; i++){
            yearChoice.add(String.valueOf(i));
        }
        yearChoice.select(String.valueOf(selectedDate.getYear()));


        add(new Label("Month"));
        add(monthChoice);
        add(new Label("Year:"));
        add(yearChoice);
    }
}
