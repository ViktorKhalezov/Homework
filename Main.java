package ru.geekbrains;

import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[100000];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }


        long selectionSortStart = System.currentTimeMillis();
        selectionSort(Arrays.copyOf(arr, arr.length));
        long selectionSortFinish = System.currentTimeMillis() - selectionSortStart;

        long insertionSortStart = System.currentTimeMillis();
        insertionSort(Arrays.copyOf(arr, arr.length));
        long insertionSortFinish = System.currentTimeMillis() - insertionSortStart;

        long bubbleSortStart = System.currentTimeMillis();
        bubbleSort(Arrays.copyOf(arr, arr.length));
        long bubbleSortFinish = System.currentTimeMillis() - bubbleSortStart;

        System.out.println("Время выполнения сортировок:");
        System.out.println("Время выполнения сортировки выбором: " + selectionSortFinish / 1000 + " секунд");
        System.out.println("Время выполнения сортировки вставками: " + insertionSortFinish / 1000 + " секунд");
        System.out.println("Время выполнения пузырьковой сортировки: " + bubbleSortFinish / 1000 + " секунд");

    }



    public static void selectionSort(int[] arr) {
        int min = 0;
        int minIndex = 0;
        int firstIndex = 0;
        while(firstIndex != arr.length - 1) {
            min = arr[firstIndex];
            minIndex = firstIndex;
            for (int i = firstIndex + 1; i < arr.length; i++) {
                if(arr[i] < min) {
                 min = arr[i];
                 minIndex = i;
                }
            }
            arr[minIndex] = arr[firstIndex];
            arr[firstIndex] = min;
            firstIndex++;
        }

    }


    public static void insertionSort(int[] arr) {
        int element = 0;
        int elementIndex = 0;
        for(int i = 1; i < arr.length; i++) {
            element = arr[i];
            elementIndex = i;
            while(elementIndex > 0 && arr[elementIndex - 1] > element) {
                arr[elementIndex] = arr[elementIndex - 1];
                arr[elementIndex - 1] = element;
                elementIndex--;
            }
        }

   }


    public static void bubbleSort(int[] arr) {
        int last = arr.length - 1;
        int x = 0;
        while(last > 0) {
            for(int i = 0; i < last; i++) {
                if(arr[i] > arr[i + 1]) {
                    x = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = x;
                }
            }
            last--;
        }
    }


}
