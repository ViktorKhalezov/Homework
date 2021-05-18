public class HW_Lesson2 {
    public static void main(String[] args) {
        checkSum (3, 12);
        System.out.println();
        checkNumber(-25);
        System.out.println();
        checkNumber2(-40);
        System.out.println();
        printPhrase("С новым годом", 5);
        System.out.println();
        leapYear(2100);

    }

    public static void checkSum (int a, int b) {
        System.out.println("Первое задание: ");
        if(a + b >10 && a + b <= 20) {
            System.out.println("True");
        } else{
            System.out.println("False");
        }
    }

    public static void checkNumber(int a) {
        System.out.println("Второе задание: ");
        if (a >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    public static void checkNumber2(int a) {
        System.out.println("Третье задание: ");
        if (a >= 0) {
            System.out.println("False");
        } else{
            System.out.println("True");
        }
    }

    public static void printPhrase(String a, int b){
        System.out.println("Четвертое задание: ");
        for(int i= 0; i < b; i++){
            System.out.println(a);
        }
    }

    public static void leapYear(int a){
        System.out.println("Пятое задание: ");
        if(a%400 == 0){
            System.out.println("True. Високосный год");
        } else if(a%100 == 0){
            System.out.println("False. Невисокосный год");
        } else if(a%4 == 0){
            System.out.println("True. Високосный год");
        } else{
            System.out.println("False. Невисокосный год");
        }
    }

}
