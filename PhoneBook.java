import java.util.*;

public class PhoneBook{
    private String key;
    private ArrayList<Integer> value = new ArrayList<Integer>();
    private HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();



    public void add(String key, int number){
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<Integer>());
        }
        map.get(key).add(number);
      }

    public String get(String key){
       ArrayList<Integer> value = map.get(key);
        return Arrays.toString(value.toArray());
    }

}

