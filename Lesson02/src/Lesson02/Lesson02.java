package Lesson02;

public class Lesson02 {

    public static void main(String[] args) {

        // ОСНОВНОЕ ЗАДАНИЕ

        try {
            // В качестве метода по заданию, которому передается массив, использую конструктор класса SumOfMas

            SumOfMas sumOfMas1 = new SumOfMas(new String[][] {{"1","4","5","4"},{"2","3","10","12"},{"1","3","1","3"},{"1","2","10","11"}});
            System.out.println("Сумма значений элементов двумерного массива 4x4: " + sumOfMas1.getSum());

/*            SumOfMas sumOfMas2 = new SumOfMas(new String[][] {{"1","4","str","4"},{"2","3","10","12"},{"1","3","1","3"},{"1","2","10","11"}});
            System.out.println("Сумма значений элементов двумерного массива 4x4: " + sumOfMas2.getSum());*/

/*            SumOfMas sumOfMas3 = new SumOfMas(new String[][] {{"1","1","1","4","str","4"},{"2","3","10","12"},{"1","3","1","3"},{"1","2","10","11"}});
            System.out.println("Сумма значений элементов двумерного массива 4x4: " + sumOfMas3.getSum());*/

            SumOfMas sumOfMas4 = new SumOfMas(new String[][] {{"1","4","5","4"},{"1","1","1","4","str","4"},{"2","3","10","12"},{"1","3","1","3"},{"1","2","10","11"}});
            System.out.println("Сумма значений элементов двумерного массива 4x4: " + sumOfMas4.getSum());

        }
        catch (MyArraySizeException e){
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e){
            System.out.println(e.getMessage());
        }


        // ДОП. ЗАДАНИЕ

        System.out.println("\n" + getWorkingHours(DayOfWeek.MONDAY));
        System.out.println(getWorkingHours(DayOfWeek.TUESDAY));
        System.out.println(getWorkingHours(DayOfWeek.WEDNESDAY));
        System.out.println(getWorkingHours(DayOfWeek.THURSDAY));
        System.out.println(getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println(getWorkingHours(DayOfWeek.SATURDAY));
        System.out.println(getWorkingHours(DayOfWeek.SUNDAY));

    }

    private static String getWorkingHours(DayOfWeek day) {
        String res;

        switch (day.getNum()){
            case 6:
            case 7:
                res = day.getRus() + " - выходной!";
                break;
            default:
                res = day.getRus() + ": до конца рабочей недели осталось " + (48 - day.getNum() * 8) + "ч!";
                break;
        }

        return res;
    }
}

enum DayOfWeek{
    MONDAY("Понедельник", 1), TUESDAY("Вторник", 2), WEDNESDAY("Среда", 3), THURSDAY("Четверг", 4), FRIDAY("Пятница", 5), SATURDAY("Суббота", 6), SUNDAY("Воскресенье", 7);

    private String rus;
    private int num;

    DayOfWeek(String rus, int num) {
        this.rus = rus;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getRus() {
        return rus;
    }
}