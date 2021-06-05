public class Plate {
    private int food;
    private int volume;

    public Plate(int food, int volume){
        this.food = food;
        this.volume = volume;
    }

    public void info(){
        System.out.println("Кол-во еды в тарелке: " + food);
    }

    public boolean decreaseFood(int n){
        if(food - n >= 0) {
            food -= n;
            return true;
        }else{
            return false;
        }
    }

    public void addFood(int f){
        if(food + f <= volume && food + f > 0){
            food = food + f;
        }else if(food + f < 0) {
            food = 0;
            System.out.println("В тарелке закончилась еда. Добавьте в нее еды");
        }else{
            food = volume;
            System.out.println("В тарелку добавлено " + volume + " единиц еды. Тарелка целиком полна");
        }
    }


}
