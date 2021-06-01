public class Cat extends Animal {
    private int idCat;

    public Cat(String name, int idAnimal, int idCat) {
        super(name, idAnimal);
        this.idCat = idCat;
    }

  @Override
  public void run(int distance, String name){
      if(distance > 200) {
          System.out.println("Кот не может пробежать более 500 м");
      }else {
          System.out.println(name + " пробежал " + distance + " метров");
      }
    }

    @Override
    public void swim(int distance, String name){
        System.out.println("Коты не умеют плавать");
    }



}
