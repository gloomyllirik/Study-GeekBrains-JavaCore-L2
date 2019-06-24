public class Lesson05 {

    public static void main(String[] args) {
        Arrays arrClass = new Arrays();
        System.out.println("Время на формирование новых значений массива (1-й метод): " + arrClass.Method1() + "\n");
        System.out.println("Время на разбивку массива (2-й метод): " + arrClass.Method2()[0]);
        System.out.println("Время на формирование новых значений массива (2-й метод): " + arrClass.Method2()[1]);
        System.out.println("Время на склейку массива (2-й метод): " + arrClass.Method2()[2]);
    }
}

class Arrays {
    static final int size = 10000000;
    static final int h = size / 2;

    long Method1(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        a = System.currentTimeMillis() - a;

        return a;
    }

    long[] Method2(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        float[] a1 = new float[size];
        float[] a2 = new float[size];

        long[] a = new long[3];
        a[0] = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        a[0] = System.currentTimeMillis() - a[0];

        a[1] = System.currentTimeMillis();
        Thread t1 = new  Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a[1] = System.currentTimeMillis() - a[1];

        a[2] = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        a[2] = System.currentTimeMillis() - a[2];

        return a;
    }


}