package ui;

import java.awt.*;




public class GUI {
    private Frame f;
    private TextArea eventDisplay;

    GUI() {
        f = new Frame("Calendar");
        f.setSize(600, 400);
        f.setLayout(new BorderLayout());


        String[] label = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
        Panel labelPanel = new Panel();
        labelPanel.setLayout(new GridLayout(1, 7));

        for (String day : label){
            labelPanel.add(new Label(day, Label.CENTER));
        }
        f.add(labelPanel, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(5, 7));

        for (int i = 0; i < (7 * 5); i++) {
            Button button = new Button();
            buttonPanel.add(button);
        }
        f.add(buttonPanel);
        f.setVisible(true);
    }
}