public class Main {

    public static void main(String[] args) {

        Cat[] cats = new Cat[] {
                new Cat("Томас", 5, false),
                new Cat("Барсик", 6, false),
                new Cat("Мурзик", 7, false),
        };

        Plate plate = new Plate (0, 15);

        plate.info();

        System.out.println("Добавляем еду в тарелку");
        plate.addFood(12);
        plate.info();

        System.out.println("Кормим котов...");
        for(int i = 0; i < cats.length; i++){
            cats[i].eat(plate);
        }

        plate.info();

        System.out.println("Проверка сытости: ");
        for(int i = 0; i < cats.length; i++){
            System.out.println(cats[i].getName() + " " + cats[i].isSatiety());
        }

    }
}
