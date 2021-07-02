public class Human implements Move {
    private String name;
    private int runDistance;
    private int jumpHeight;

    public Human(String name,  int runDistance, int jumpHeight){
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }


    @Override
    public boolean run(int treadmillSize, int runDistance, String name, int obstacleNumber) {
        if(runDistance >= treadmillSize){
            System.out.println("Человек "  + name + " пробежал дистанцию № " + obstacleNumber);
            return true;
        }
            System.out.println("Человек "  + name + " не пробежал дистанцию № " + obstacleNumber);
            return false;
    }

    @Override
    public boolean jump(int wallHeight, int jumpHeight, String name, int obstacleNumber) {
        if(jumpHeight >= wallHeight){
            System.out.println("Человек " + name + " перепрыгнул барьер № " + obstacleNumber);
            return true;
        }
            System.out.println("Человек " + name + " не перепрыгнул барьер № " + obstacleNumber);
            return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRunDistance() {
        return runDistance;
    }

    @Override
    public int getJumpHeight() {
        return jumpHeight;
    }

}
