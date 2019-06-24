package Lesson_2.DZ.var1;

public class DayOfWeekMain {

    public static void main (String args[]) {
        System.out.println(getWorkingHours(DayOfWeek.SATURDAY));
    }

    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    static int getWorkingHours (DayOfWeek day) {
        int res = (DayOfWeek.SATURDAY.ordinal() - day.ordinal()) * 8;
        return res >= 0 ? res : 0;
    }

}