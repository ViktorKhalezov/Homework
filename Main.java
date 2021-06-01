public class Main {

  /*  1. Создать классы Собака и Кот с наследованием от класса Животное.
      2. Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
      Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
      3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
      4. * Добавить подсчет созданных котов, собак и животных. */

    static int a = 0;
    static int d = 0;
    static int c = 0;

    public static void main(String[] args) {

        Animal wolf = new Animal("Джон", countAnimals());
        Dog sharik = new Dog("Шарик", countAnimals(), countDogs());
        Cat barsik = new Cat("Барсик", countAnimals(), countCats());
        Dog bobik = new Dog("Бобик", countAnimals(), countDogs());

        wolf.run(500, wolf.getName());
        wolf.swim(100, wolf.getName());
        System.out.println();

        sharik.run(1000, sharik.getName());
        sharik.swim(10, sharik.getName());
        System.out.println();

        barsik.run(100, barsik.getName());
        barsik.swim(5, barsik.getName());
        System.out.println();

        System.out.println("Количество животных: " + a);
        System.out.println("Количество собак: " + d);
        System.out.println("Количество котов: " + c);

    }

    public static int countAnimals(){
        return a++;
    }

    public static int countDogs(){
        return d++;
    }

    public static int countCats(){
        return c++;
    }

}
