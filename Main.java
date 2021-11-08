package ru.geekbrains;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<MyTreeMap> mapList = new ArrayList<>();

        for(int i = 0; i < 100000; i++) {
            MyTreeMap<Integer, Integer> map = new MyTreeMap<>();
            int key = 0;
            while(map.height() != 7) {
                key = getRandomNumber(-101, 100);
                map.put(key, 0);
            }
            map.delete(key);
            mapList.add(map);
        }

        int balancedCount = 0;
        for(MyTreeMap<Integer, Integer> map : mapList) {
            if(map.isBalanced()) {
                balancedCount++;
            }
        }

        double balancedPercent = balancedCount / 100000.0;
        System.out.println("Доля сбалансированных деревьев: " + balancedPercent + "%");

    }


    // метод дает возращает значения от min + 1 до max включительно (то есть необходимо будет взять min -101 и max 100)
    public static int getRandomNumber(int min, int max) {
            return (int) (Math.random() * (max - min) + 1) + min;
    }

}



