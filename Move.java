public interface Move {

    boolean run(int treadmillSize, int runDistance, String name, int obstacleNumber);
    boolean jump(int wallHeight, int jumpHeight, String name, int obstacleNumber);

    String getName();
    int getRunDistance();
    int getJumpHeight();

}
