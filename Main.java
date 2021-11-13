

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ChainingHashMap<Integer, String> map = new ChainingHashMap<>(7);
        map.put(0, "a");
        map.put(1, "b");
        map.put(2, "c");
        map.put(3, "d");
        map.put(4, "e");
        map.put(5, "f");
        map.put(6, "g");

        System.out.println(map.toString());
       System.out.println(map.remove(1));
       System.out.println(map.remove(5));
        System.out.println(map.get(1));
        System.out.println(map.get(5));
        System.out.println(map.toString());


        //  Random random = new Random();
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(5, 9999999);
//        System.out.println(map.get(5));
//        map.put(5, 77777);
//        System.out.println(map.get(5));


//        ChainingHashMap<Integer, String> map = new ChainingHashMap<>(7);
//        for (int i = 0; i < 7 ; i++) {
//            map.put(random.nextInt(100), "a");
//        }
//        System.out.println(map);


     /*   LinearProbingHashMap<Integer, String> lphm = new LinearProbingHashMap<>(97);
        lphm.put(5, "qwe");
        lphm.put(15, "qweerwer");
        System.out.println(lphm.get(15)); */


    }
}
