package ua.com.alevel.tasks;

import java.util.Scanner;

public class Task3 {

    public void task3LessonsTimetable() {
        int lessonCounter, lessonEndHour, lessonEndMinute;
        int lessonDuration = 45;
        int hourDuration = 60;
        int breakDurationLong = 15;
        int breakDurationShort = 5;

        System.out.println("Input lesson number (1-10)");
        Scanner input = new Scanner(System.in);
        int lessonNumber = input.nextInt();

        lessonCounter = lessonNumber - 1;
        lessonEndHour = (9 * hourDuration + lessonNumber * lessonDuration + lessonCounter / 2 * breakDurationLong + (lessonCounter - lessonCounter / 2) * breakDurationShort) / hourDuration;
        lessonEndMinute = (9 * hourDuration + lessonNumber * lessonDuration + lessonCounter / 2 * breakDurationShort + (lessonCounter - lessonCounter / 2) * breakDurationLong) - lessonEndHour * hourDuration;
        System.out.println("Lesson " + lessonNumber + " ends at " + lessonEndHour + ":" + lessonEndMinute);
    }
}
