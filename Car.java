public class Car implements Runnable {
    private static int CARS_COUNT;
    private static int placeInRace;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            MainClass.preparingBarrier.await();
            System.out.println(this.name + " готов");
            MainClass.preparingBarrier.await();
            MainClass.startingLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        placeInRace++;
        if(placeInRace == 1) {
            System.out.println(this.name + " выиграл гонку!!!");
        }
        MainClass.finishingLatch.countDown();
    }

}
