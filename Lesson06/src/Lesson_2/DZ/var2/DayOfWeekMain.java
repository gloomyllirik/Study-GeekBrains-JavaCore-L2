package Lesson_2.DZ.var2;

public class DayOfWeekMain {

    enum DayOfWeek {
        MONDAY(8), TUESDAY(8), WEDNESDAY(8), THURSDAY(8),
        FRIDAY(8), SATURDAY(0), SUNDAY(0);
        private int workingHours;

        DayOfWeek(int workingHours) {
            this.workingHours = workingHours;
        }

        public static int getWorkingHours(DayOfWeek dayOfWeek) {
            int workingHoursForEndOfWeek = 0;
            for (int i = dayOfWeek.ordinal(); i < values().length; i++) {
                workingHoursForEndOfWeek += values()[i].workingHours;
            }
            return workingHoursForEndOfWeek;
        }
    }

    public static void main(final String[] args) {
        System.out.println(DayOfWeek.getWorkingHours(DayOfWeek.SATURDAY));
    }
}
