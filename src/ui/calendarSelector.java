package ui;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.time.LocalDate;

public class calendarSelector extends Panel {
    private Choice monthChoice;
    private Choice yearChoice;
    private LocalDate selectedDate;

    public interface monthYearSelectionListener {
        void onMonthYearChange(LocalDate newDate);
    }

    private monthYearSelectionListener listener;

    public calendarSelector(int startYear, int endYear) {
        this.selectedDate = LocalDate.now();
        setLayout(new FlowLayout());

        //Setup month choices
        monthChoice = new Choice();
        for (int i = 1; i <= 12; i++) {
            monthChoice.add(String.valueOf(i));
        }
        monthChoice.select(selectedDate.getMonthValue() - 1);

        //Setup year choices
        yearChoice = new Choice();
        for (int i = startYear; i <= endYear; i++) {
            yearChoice.add(String.valueOf(i));
        }
        yearChoice.select(String.valueOf(selectedDate.getYear()));


        add(new Label("Month"));
        add(monthChoice);
        add(new Label("Year:"));
        add(yearChoice);

        // Add listeners for month and year change
        monthChoice.addItemListener(this::selectionChange);
        yearChoice.addItemListener(this::selectionChange);
    }

    public void setMonthYearSelectionListener(monthYearSelectionListener listener) {
        this.listener = listener;
    }

    private void selectionChange(ItemEvent e) {
        int selectedMonth = monthChoice.getSelectedIndex() + 1;
        int selectedYear = Integer.parseInt(yearChoice.getSelectedItem()); // issues with saving, should be parsing to text instead of using index; saves with correct data tp JSON now but does not load
        selectedDate = LocalDate.of(selectedYear, selectedMonth, 1);

        if (listener != null) {
            listener.onMonthYearChange(selectedDate);
        }
    }
}



