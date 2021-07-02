public abstract class Obstacle {
    private int size;
    private int obstacleNumber;


    public Obstacle(int size, int obstacleNumber){
        this.size = size;
        this.obstacleNumber = obstacleNumber;
    }


    public int getSize() {
        return size;
    }

    public int getObstacleNumber() {
        return obstacleNumber;
    }
}
