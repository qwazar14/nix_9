package ua.com.alevel;

import ua.com.alevel.controller.CalendarController;

import java.io.IOException;

public class HW6ExceptionsMain {
    public static void main(String[] args) throws IOException {
        CalendarController calendar = new CalendarController();
        calendar.run();
    }
}
