public class Animal {

    private String name;
    private int idAnimal;

    public Animal(String name, int idAnimal){
        this.name = name;
        this.idAnimal = idAnimal;

    }

    public void run(int distance, String name){
        System.out.println(name + " пробежал " + distance + " метров");
    }

    public void swim(int distance, String name){
        System.out.println(name + " проплыл " + distance + " метров");
    }

    public String getName() {
        return name;
    }

}
