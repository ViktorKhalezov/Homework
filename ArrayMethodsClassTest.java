package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayMethodsClassTest {

    // Тесты для первого задания
    @Test
    void shouldThrowException_whenThereIsNoNumberFourInArray () {
        int[] testArray = {3, 5, 7, 9, 11, 14};
        Assertions.assertThrows(RuntimeException.class, () -> ArrayMethodsClass.getArrayAfterLastFour(testArray));
    }

    @Test
    void shouldGetEmptyArray_whenFourIsLastElementInArray () {
        int[] emptyArray = new int[0];
        int[] testArray = {3, 5, 7, 9, 11, 4};
        Assertions.assertArrayEquals(emptyArray, ArrayMethodsClass.getArrayAfterLastFour(testArray));
    }


    @ParameterizedTest
    @MethodSource ("arraysForChecking")
    void shouldGetArrayAfterLastNumberFour(int[] initArray, int[] resultArray) {
        Assertions.assertArrayEquals(resultArray, ArrayMethodsClass.getArrayAfterLastFour(initArray));
    }

    static Stream<Arguments> arraysForChecking() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] {1, 3, 4, 6, 7}, new int[] {6, 7}));
        out.add(Arguments.arguments(new int[] {1, 4, 1, 4, 7, 9}, new int[] {7, 9}));
        out.add(Arguments.arguments(new int[] {1, 4, 3, 6, 7}, new int[] {3, 6, 7}));
        out.add(Arguments.arguments(new int[] {4, 3, 5, 6, 7}, new int[] {3, 5, 6, 7}));
        return out.stream();
    }


    // Тесты для второго задания
    @Test
    void shouldReturnTrue_whenArrayContainsOneOrFour () {
        int[] testArray = new int[10];
        testArray[0] = 1;
        testArray[1] = 4;
        for(int i = 2; i < testArray.length; i++) {
            testArray[i] = (int) (Math.random() * 10);
        }
        Assertions.assertTrue(ArrayMethodsClass.checkArray(testArray));
    }

    @Test
    void shouldReturnFalse_whenArrayDoesNotContainOneOrFour() {
        int[] testArray = {3, 9, 18, 21, 33, 67};
        Assertions.assertFalse(ArrayMethodsClass.checkArray(testArray));
    }

    @Test
    void shouldReturnFalse_whenMethodReceivedEmptyArray() {
        int[] testArray = {};
        Assertions.assertFalse(ArrayMethodsClass.checkArray(testArray));
    }

    @ParameterizedTest
    @MethodSource ("arraysContainOne")
    void shouldReturnTrue_whenArrayContainsOne (int[] array) {
        Assertions.assertTrue(ArrayMethodsClass.checkArray(array));
    }

    static Stream<Arguments> arraysContainOne() {
        List<Arguments> out = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int[] testArray = new int[10];
            testArray[0] = 1;
            for(int j = 1; j < testArray.length; j++) {
                testArray[j] = (int) (Math.random() * 10);
            }
            out.add(Arguments.arguments(testArray));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource ("arraysContainFour")
    void shouldReturnTrue_whenArrayContainsFour (int[] array) {
        Assertions.assertTrue(ArrayMethodsClass.checkArray(array));
    }

    static Stream<Arguments> arraysContainFour() {
        List<Arguments> out = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int[] testArray = new int[10];
            testArray[testArray.length - 1] = 4;
            for(int j = 0; j < testArray.length - 1; j++) {
                testArray[j] = (int) (Math.random() * 10);
            }
            out.add(Arguments.arguments(testArray));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource ("arraysWithoutOneAndFour")
    void shouldReturnFalse_whenArrayDoesNotContainOneAndFour (int[] array) {
        Assertions.assertFalse(ArrayMethodsClass.checkArray(array));
    }

    static Stream<Arguments> arraysWithoutOneAndFour() {
        List<Arguments> out = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int[] testArray = new int[10];
            for(int j = 0; j < testArray.length; j++) {
                int a = (int) (Math.random() * 10);
                if(a == 1 || a == 4) {
                   a = 5;
                }
                testArray[j] = a;
            }
            out.add(Arguments.arguments(testArray));
        }
        return out.stream();
    }

}
