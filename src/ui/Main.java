package ui;

import model.Date;

import java.nio.file.Path;


public class Main {
    public static void main(String[] args) {
        Date d = new Date();
        Path path = d.getDefaultPath();
        d.load(path);
        new GUI(d);
    }

}
