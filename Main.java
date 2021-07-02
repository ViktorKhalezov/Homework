public class Main {

  //  1.Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
    //  Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).

  //  2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать),
    //  результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).

    //  3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.

// 4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

    public static void main(String[] args) {

        Move[] participants = {
                new Human("Джон", 1500, 2),
                new Cat("Барсик", 150, 1),
                new Robot("Бендер", 5000, 5),
        };

        Obstacle[] obstacles = {
                new Treadmill(100, 1),
                new Wall(1, 1),
                new Treadmill(1000, 2),
                new Wall(2, 2),
                new Treadmill(3000, 3),
                new Wall(3, 3),
        };

        contest(participants, obstacles);

    }


    public static void contest(Move[] participants, Obstacle[] obstacles) {
        for (Move participant : participants) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle instanceof Treadmill) {
                    if (participant.run(obstacle.getSize(), participant.getRunDistance(), participant.getName(), obstacle.getObstacleNumber()) == false) {
                        System.out.println(participant.getName() + " выбыл");
                        System.out.println();
                        break;
                    }
                }
                if (obstacle instanceof Wall) {
                    if (participant.jump(obstacle.getSize(), participant.getJumpHeight(), participant.getName(), obstacle.getObstacleNumber()) == false) {
                        System.out.println(participant.getName() + " выбыл");
                        System.out.println();
                        break;
                    }
                }
            }
        }
    }

}
