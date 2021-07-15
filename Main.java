import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

// 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//   Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
//          2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
  //  В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
  //  Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
  //  тогда при запросе такой фамилии должны выводиться все телефоны.


    public static void main(String[] args) {

        String[] array = {
                "Кот", "Собака", "Волк", "Медведь",
                "Бегемот", "Крот", "Лошадь", "Кот",
                "Волк", "Собака", "Кот", "Крот",
                "Медведь", "Кот", "Собака", "Лошадь",
                "Медведь", "Волк", "Бегемот", "Лев"
        };

        countUniqueElements(array);

        System.out.println();
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Фролов", 9234586);
        phoneBook.add("Фролов", 1235562);
        phoneBook.add("Иванов", 3451243);
        phoneBook.add("Петров", 8234521);
        phoneBook.add("Иванов", 3893539);

        System.out.println(phoneBook.get("Фролов"));
        System.out.println(phoneBook.get("Иванов"));
        System.out.println(phoneBook.get("Петров"));
    }

    public static Set<String> printWithoutDuplicates(String[] array){
        Set<String> set = new HashSet<>();
        for(int i = 0; i < array.length; i++){
            set.add(array[i]);
        }

        for(String element : set){
          System.out.println(element);
        }
        return set;
    }

    public static void countUniqueElements(String[] array){
        System.out.println("Уникальные элементы:");

        Set<String> set = printWithoutDuplicates(array);
        HashMap <String, Integer> map = new HashMap<>();

        for(String element : set){
            int count = 0;
            for(int i = 0; i < array.length; i++){
                if(array[i].equals(element)){
                    count++;
                }
                map.put(element, count);
            }
        }

        System.out.println();
        System.out.println("Кол-во повторов:");
        for(Map.Entry<String, Integer> element : map.entrySet()){
            System.out.println(element.getKey() + " - " + element.getValue());
        }

    }


}
