package Lesson02;

class SumOfMas {
    private String[][] mas = new String[4][4];
    private int Sum = 0;

    public SumOfMas(String[][] mas) throws MyArraySizeException, MyArrayDataException {
        // если первый индекс массива не равен 4, нет смысла перебирать массив, - кидаем исключение
        if (mas.length != 4)
            throw new MyArraySizeException("Ошибка! Передайте двумерный строковый массив размером 4х4");
        try {
            for (int i = 0; i < 4; i++) {
                // если число элементов в строчке массива не равно 4, нет смысла дальше перебирать массив, - кидаем исключение
                if (mas[i].length != 4)
                    throw new MyArraySizeException("Ошибка! Передайте двумерный строковый массив размером 4х4");
                for (int j = 0; j < 4; j++) {
                    // для реализации защиты данных объекта класса
                    this.mas[i][j] = mas[i][j];
                    try {
                        // сумму можно было посчитать в отдельном методе класса, но зачем перебирать заново массив или передавать значение ячейки в отдельный метод?
                        Sum += Integer.valueOf(this.mas[i][j]);
                    } catch (NumberFormatException e){
                        // индексы массива указываем в сообщении об ошибке для удобства восприятия с 1 по 4, а не с 0 по 3
                        throw new MyArrayDataException("Ошибка значения массива в ячейке ", i + 1, j + 1);
                    }

//                    System.out.print(this.mas[i][j] + " ");   // Вывод значений массива для наглядности
                }
//                System.out.println(" ");    // Вывод значений массива для наглядности
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyArraySizeException("Ошибка! Передайте двумерный строковый массив размером 4х4");
        }
    }

    public int getSum() {
        return Sum;
    }

}

class MyArraySizeException extends Exception {
    public MyArraySizeException(String msg) {
        super(msg);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String msg, int i, int j) {
        super(msg + "[" + i + "][" + j +"]");
    }
}