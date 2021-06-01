public class Dog extends Animal {
    private int idDog;

    public Dog(String name, int idAnimal, int idDog) {
        super(name, idAnimal);
        this.idDog = idDog;
    }

   @Override
   public void run(int distance, String name){
       if(distance > 500) {
           System.out.println("Собака не может пробежать более 500 м");
       }else {
           System.out.println(name + " пробежал " + distance + " метров");
       }
    }

    @Override
    public void swim(int distance, String name) {
        if (distance > 10) {
            System.out.println("Собака не может проплыть более 10 м");
        } else {
            System.out.println(name + " проплыл " + distance + " метров");
        }
    }


}
