package tasks;

import java.util.Scanner;

public class Task3 {
    public void task3LessonsTimetable() {
        int lessonCounter, lessonEndHour, lessonEndMinute;

        System.out.println("Input lesson number (1-10)");
        Scanner input = new Scanner(System.in);
        int lessonNumber = input.nextInt();

        lessonCounter = lessonNumber - 1;
        lessonEndHour = (9 * 60 + lessonNumber * 45 + lessonCounter / 2 * 15 + (lessonCounter - lessonCounter / 2) * 5) / 60;
        lessonEndMinute = (9 * 60 + lessonNumber * 45 + lessonCounter / 2 * 5 + (lessonCounter - lessonCounter / 2) * 15) - lessonEndHour * 60;
        System.out.println("Lesson " + lessonNumber + " ends at " + lessonEndHour + ":" + lessonEndMinute);


    }
}
