package ru.geekbrains.lesson5;

public class MainApp {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) throws InterruptedException {
        float[] bigArray = new float[SIZE];
        init(bigArray);

        long startTime = System.currentTimeMillis();
        firstMethod(bigArray);
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");

        init(bigArray);
        startTime = System.currentTimeMillis();
        secondMethod(bigArray);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void init(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i]=1f;
        }
    }

    public static void firstMethod(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void  secondMethod(float[] arr) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        thread.start();
        for (int i = HALF; i < arr.length; i++) {
            arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        thread.join();
    }
}