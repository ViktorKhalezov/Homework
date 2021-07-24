public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {

        firstMethod();
        secondMethod();

    }



    public static void firstMethod(){
        float[] arr = initArray();

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Выполнение задачи в один поток: " + (System.currentTimeMillis() - startTime) + " мс");

    }

    public static void secondMethod(){
        float[] arr = initArray();

        long startTime = System.currentTimeMillis();
        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];

        System.arraycopy(arr, 0, leftHalf, 0, HALF);
        System.arraycopy(arr, 0, rightHalf, 0, HALF);

        Thread thread1 = new Thread ( () -> {
            for(int i = 0; i < leftHalf.length; i++){
                leftHalf[i] = (float)(leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread ( () -> {
            for(int i = 0; i < rightHalf.length; i++){
                rightHalf[i] = (float)(rightHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        thread1.start();
        thread2.start();

        float[] newArr = new float[SIZE];

        System.arraycopy(leftHalf, 0, newArr, 0, HALF);
        System.arraycopy(rightHalf, 0, newArr, HALF, HALF);

        System.out.println("Выполнение задачи в два потока: " + (System.currentTimeMillis() - startTime) + " мс");

    }


    private static float[] initArray(){
        float[] arr = new float[SIZE];
        for(int i = 0; i < arr.length; i++){
            arr[i] = 1.0f;
        }

        return arr;
    }




}
