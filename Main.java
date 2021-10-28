package ru.geekbrains;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String str = "Я изучаю Java";
        String reverseString = reverseString(str);
        System.out.println(reverseString);

       MyDeque<Integer> myDeque = new MyDeque<>(3);
        myDeque.insertLeft(3);
        myDeque.removeLeft();
        myDeque.insertLeft(4);
        myDeque.insertRight(4);
        myDeque.removeLeft();
        myDeque.insertRight(5);
        myDeque.insertRight(6);
        myDeque.removeRight();
        myDeque.insertLeft(2);

        System.out.println(myDeque.toString());

        MyStack<Integer> myStack = new MyStack<>(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(1);
        myStack.push(9);
        myStack.push(4);

        System.out.println();
        PriorityQueueHomework<Integer> pqh = new PriorityQueueHomework<>(2);
        pqh.insert(6);
        pqh.insert(32);
        pqh.insert(19);
        pqh.insert(12);
        pqh.insert(15);
        pqh.insert(5);

        System.out.println(pqh.toString());
        pqh.remove();
        System.out.println(pqh.toString());
        pqh.remove();
        System.out.println(pqh.toString());

    }


    public static String reverseString(String str) {
        String reverseString = "";

        if(str.length() > 1) {
            char[] charArray = str.toCharArray();
            for(int i = charArray.length - 1; i >= 0; i--) {
                reverseString += charArray[i];
            }

        }
        return reverseString;
    }


}
