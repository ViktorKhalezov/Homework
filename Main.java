package ru.geekbrains;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        long a = exponentiation(5, 1);
        System.out.println(a);

        Bag bag = new Bag(20);

        ArrayList<Thing> things = new ArrayList<>();

        things.add(new Thing(5, 5));
        things.add(new Thing(3, 6));
        things.add(new Thing(6, 8));
        things.add(new Thing(3, 8));
        things.add(new Thing(8, 6));


        System.out.println(bagTaskDecision(bag, things));

    }


    // Первое задание
    public static long exponentiation(int number, int exponent) {
        if(exponent == 0) {
            return 1;
        }
        return exponentiation(number, exponent - 1) * number;
    }


    // Второе задание
    private static void bagFilling(Bag bag, ArrayList<Thing> thingsList) {

        if(bag.getCurrentWeight() == bag.getTotalWeight() || thingsList.size() == 0) {
            return;
        }

        for(int i = 0; i < thingsList.size(); i++) {
            Thing t = thingsList.get(i);
            if(bag.getCurrentWeight() + t.getWeight() < bag.getTotalWeight()) {
                bag.getThings().add(t);
                bag.increaseCurrentWeight(t.getWeight());
                bag.increaseCurrentValue(t.getValue());
            }
            thingsList.remove(t);
        }

         bagFilling(bag, thingsList);
    }


    public static String bagTaskDecision(Bag bag, ArrayList<Thing> things) {
        ArrayList<ArrayList<Thing>> allVariantsList = new ArrayList<>();

        fillingAllVariantsList(0, things, allVariantsList);

        ArrayList<Bag> bagList = getBagList(bag, allVariantsList);

        bag = getOptimalVariant(bagList);


        String result = "Оптимальная стоимость: " + bag.currentValue + ", вес по оптимальной стоимости: " + bag.getCurrentWeight();

        return result;
    }



    private static ArrayList<Bag> getBagList (Bag bag, ArrayList<ArrayList<Thing>> allVariantsList) {
        ArrayList<Bag> bagList = new ArrayList<>();

        for(int i = 0; i < allVariantsList.size(); i++) {
            ArrayList<Thing> things = allVariantsList.get(i);
            Bag newBag = bag.clone();
            bagFilling(newBag, things);
            bagList.add(newBag);
        }

        return bagList;
    }

    private static Bag getOptimalVariant(ArrayList<Bag> bagList) {
        int maxSum =  bagList.get(0).getCurrentValue();
        Bag optimalBag = bagList.get(0);

        for(int i = 1; i < bagList.size(); i++) {
            if(maxSum < bagList.get(i).getCurrentValue()) {
                maxSum = bagList.get(i).getCurrentValue();
                optimalBag = bagList.get(i);
            }
        }
        return optimalBag;
    }


    private static void fillingAllVariantsList(int index, ArrayList<Thing> things, ArrayList<ArrayList<Thing>> allVariantsList) {
        if(index == things.size()) {
            return;
        }

        for(int i = 0; i < things.size(); i++) {
            ArrayList<Thing> newList = new ArrayList<>();
            copyList(things, newList);
            change(index, i, newList);
            allVariantsList.add(newList);
        }

        fillingAllVariantsList(index + 1, things, allVariantsList);
    }

    private static void change(int index, int changeIndex, ArrayList<Thing> things) {
        if(index != changeIndex) {
            Thing indexThing = things.get(index);
            Thing changeThing = things.get(changeIndex);
            things.set(changeIndex, indexThing);
            things.set(index, changeThing);
        }
    }

    private static void copyList(ArrayList<Thing> things, ArrayList<Thing> newList) {
        for(int i = 0; i < things.size(); i++) {
            newList.add(things.get(i));
        }
    }

     static class Bag {
        private int totalWeight;
        private int currentWeight;
        private ArrayList<Thing> things;
        private int currentValue;

        public Bag (int totalWeight) {
            this.things = new ArrayList<>();
            this.currentWeight = 0;
            this.currentValue = 0;
            this.totalWeight = totalWeight;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public int getCurrentWeight () {
            return currentWeight;
        }

        public int getCurrentValue() {
            return currentValue;
        }

        public void increaseCurrentWeight(int weight) {
            currentWeight += weight;
        }

        public ArrayList<Thing> getThings() {
            return things;
        }

        public void increaseCurrentValue(int sum) {
            currentValue += sum;
        }

        private void setCurrentWeight(int weight) {
            this.currentWeight = weight;
        }

        private void setCurrentValue(int value) {
            this.currentValue = value;
        }

        public Bag clone() {
           Bag newBag = new Bag(this.getTotalWeight());
           newBag.currentValue = this.getCurrentValue();
           newBag.currentWeight = this.getCurrentWeight();
           newBag.things = new ArrayList<>();

           if(this.things.size() > 0) {
               for(int i = 0; i < this.things.size(); i++) {
                   newBag.things.add(this.things.get(i));
               }
           }
            return newBag;
        }

    }

     static class Thing {
       private int value;
        private int weight;

        public Thing (int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }


         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
             Thing thing = (Thing) o;
             return value == thing.value && weight == thing.weight;
         }

         @Override
         public int hashCode() {
             return Objects.hash(value, weight);
         }

         @Override
         public String toString() {
             return "Thing{" +
                     "value=" + value +
                     ", weight=" + weight +
                     '}';
         }
     }

}