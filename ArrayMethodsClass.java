package ru.geekbrains;

import java.sql.SQLOutput;
import java.util.Arrays;

public class ArrayMethodsClass {

    // Метод для первого задания
    public static int[] getArrayAfterLastFour(int[] array) {
        int lastFourIndex = -1;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == 4) {
                lastFourIndex = i;
            }
        }

        if(lastFourIndex == -1) {
            throw new RuntimeException();
        }

        return Arrays.copyOfRange(array, lastFourIndex + 1, array.length);
    }

    // Метод для второго задания
    public static boolean checkArray(int[] array) {
        int checkNumbersCount = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == 1 || array[i] == 4) {
                checkNumbersCount++;
            }
        }

        if(checkNumbersCount > 0) {
            return true;
        }

        return false;
    }

}

/*
1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
 Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
 идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
 иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
  Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

 2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
  то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).

 */